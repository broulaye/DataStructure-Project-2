import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * This class process the commands and write the outputs to a file
 *
 * @author Broulaye Doumbia
 * @author Cheick Berthe
 * @version 09/05/2016
 */
public class Processor {

    private Commands commands;
    private Hash songHashTable;
    private Hash artistHashTable;
    private MemManager memoryManager;
    private TwoThree theTree;

    /**
     * Constructor that set the fields to provided values
     * 
     * @param hashSize
     *            represent the size of the hash table
     * @param blockSize
     *            represent the size of the memory manager
     * @param fileName
     *            represent the file to be parsed
     * @throws Exception
     *             exception is thrown if the parsing failed
     */
    public Processor(int hashSize, int blockSize, String fileName)
            throws Exception {
        this.commands = Parser.parse(fileName);
        memoryManager = new MemManager(blockSize);
        this.songHashTable = new Hash(hashSize, memoryManager, "song");
        this.artistHashTable = new Hash(hashSize, memoryManager, "artist");
        this.theTree = new TwoThree();
    }

    /**
     * @throws Exception
     *             various exception from nested calls
     */
    public void process() throws Exception {
        try {
            PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
            LinkedList<Command> list = commands.getCommandList();
            for (Command command : list) {
                switch (command.getOp()) {
                    case list:
                        list(command.getTyp(), command.getValues()[0], writer);
                        break;
                    case delete:
                        delete(command.getValues()[0], command.getValues()[1],
                                writer);
                        theTree.sanityChecker();
                        break;
                    case insert:
                        insert(command.getValues()[0], command.getValues()[1],
                                writer);
                        theTree.sanityChecker();
                        break;
                    case remove:
                        remove(command.getTyp(), command.getValues()[0],
                                writer);
                        theTree.sanityChecker();
                        break;
                    case print:
                        printContent(command.getTyp(), writer);
                        break;
                    default:
                        break;

                }
            }
            writer.close();
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Delete the specifc record for this particular songName by this particular
     * artistName.
     * 
     * @param artistName
     *            artist name
     * @param songName
     *            song of given artist
     * @param writer
     *            output stream
     */
    private void delete(String artistName, String songName,
            PrintWriter writer) {
        artistName = artistName.trim();
        songName = songName.trim();
        int artistPos = artistHashTable.get(artistName);
        int songPos = songHashTable.get(songName);
        if(artistPos < 0 || songPos < 0) {
            if(artistPos < 0) {
                writer.println(
                        "|" + artistName + "| does not exist in the artist database.");
            }
            if(songPos < 0) {
                writer.println(
                        "|" + songName + "| does not exist in the song database.");
            }
        }
        else {
            Handle artistHandle = artistHashTable.getHandle(artistPos);
            Handle songHandle = songHashTable.getHandle(songPos);

            KVPair pair = new KVPair(artistHandle, songHandle);
            theTree.remove(pair);
            writer.println("The KVPair (|" + artistName + "|,|" + songName + "|) is deleted from the tree.");
            KVPair reversePair = new KVPair(songHandle, artistHandle);
            theTree.remove(reversePair);
            writer.println("The KVPair (|" + songName + "|,|" + artistName + "|) is deleted from the tree.");

            if(theTree.find(artistHandle) == null) {
                artistHashTable.removeString(artistName);
                writer.println(
                        "|" + artistName + "| is deleted from the artist database.");
            }

            if(theTree.find(songHandle) == null) {
                songHashTable.removeString(songName);
                writer.println(
                        "|" + songName + "| is deleted from the song database.");
            }

        }
    }

    /**
     * If type is artist then all songs by the artist with name {name} are
     * listed. If type is song then all artists who have recorded that song are
     * listed.
     * 
     * @param type
     *            artist or song
     * @param str
     *            string associated with type
     * @param writer
     *            output stream
     */
    private void list(Type type, String str, PrintWriter writer) {
        int pos;
        str = str.trim();

        switch (type) {
            case Song:
                pos = songHashTable.get(str);
                if(pos < 0) {
                    writer.println(
                            "|" + str + "| does not exist in the song database.");
                }
                else {
                    Handle songHandle = songHashTable.getHandle(pos);
                    List<Handle> songHandles = theTree.get(songHandle);
                    ListIterator<Handle> listIterator = songHandles.listIterator();
                    while(listIterator.hasNext()) {
                        writer.print(listIterator.next());
                        writer.print(" ");
                    }
                    writer.println();
                    
                }

                break;
            case Artist:
                pos = artistHashTable.get(str);
                if(pos < 0) {
                    writer.println(
                            "|" + str + "| does not exist in the artist database.");
                }
                else {
                    Handle artistHandle = artistHashTable.getHandle(pos);
                    List<Handle> artistHandles = theTree.get(artistHandle);
                    ListIterator<Handle> listIterator = artistHandles.listIterator();
                    while(listIterator.hasNext()) {
                        writer.print(listIterator.next());
                        writer.print(" ");
                    }
                    writer.println();


                }
                break;
            default:
                break;
        }
    }

    /**
     * print content of given type of database
     *
     * @param type
     *            requested type of database
     * @param writer
     *            used for output
     */
    private void printContent(Type type, PrintWriter writer) {
        switch (type) {
            case Song:
                writer.print(songHashTable.printTable());
                break;
            case Tree:
                writer.print(theTree.print());
                break;
            case Artist:
                writer.print(artistHashTable.printTable());
                break;
            case Block:
                writer.println(memoryManager.dump());
                break;
            default:
                break;
        }
    }

    /**
     * @param what
     *            type to be removed
     * @param str
     *            string to be removed
     * @param writer
     *            for status report
     */
    private void remove(Type what, String str, PrintWriter writer) {
        str = str.trim();
        int pos;
        if (what == Type.Song) {
            pos = songHashTable.get(str);
            if (pos >= 0) {
                Handle Handle = songHashTable.getHandle(pos);//create new handdle
                KVPair pairToDelete = theTree.find(Handle);//find the KVPair corresponding to that handle
                KVPair reverse = new KVPair(pairToDelete.value(), pairToDelete.key());
                while(pairToDelete != null){// while handle exist in tree repeat the following process
                        writer.println("The KVPair (|" + str + "|,|" + artistHashTable.handle2String(pairToDelete.value()) + "|) is deleted from the tree.");
                        writer.println("The KVPair (|" + artistHashTable.handle2String(pairToDelete.value()) + "|,|" + str + "|) is deleted from the tree.");
                        theTree.remove(pairToDelete);//remove the left KVPair
                        theTree.remove(reverse);//remove the reverse
                    if(theTree.find(pairToDelete.value()) == null) {
                        artistHashTable.removeString(artistHashTable.handle2String(pairToDelete.value()));
                    }
                        pairToDelete = theTree.find(Handle);//update the node
                }
                songHashTable.removeString(str);
                writer.println(
                        "|" + str + "| is deleted from the song database.");
            }
            else {
                writer.println(
                        "|" + str + "| does not exist in the song database.");
            }
        }
        else if (what == Type.Artist) {
            pos = artistHashTable.get(str);
            if (pos >= 0) {
                Handle Handle = artistHashTable.getHandle(pos);//create new handdle
                KVPair pairToDelete = theTree.find(Handle);//find the KVPair corresponding to that handle
                KVPair reverse = new KVPair(pairToDelete.value(), pairToDelete.key());
                while(pairToDelete != null){// while handle exist in tree repeat the following process
                        writer.println("The KVPair (|" + str + "|,|" + songHashTable.handle2String(pairToDelete.value()) + "|) is deleted from the tree.");
                        writer.println("The KVPair (|" + songHashTable.handle2String(pairToDelete.value()) + "|,|" + str + "|) is deleted from the tree.");
                        theTree.remove(pairToDelete);//remove the left KVPair
                        theTree.remove(reverse);//remove the reverse
                        if(theTree.find(pairToDelete.value()) == null) {
                            songHashTable.removeString(songHashTable.handle2String(pairToDelete.value()));
                        }
                        pairToDelete = theTree.find(Handle);//update the node
                        if(pairToDelete != null) {
                            reverse = new KVPair(pairToDelete.value(), pairToDelete.key());
                        }
                }
                artistHashTable.removeString(str);
                writer.println(
                        "|" + str + "| is deleted from the artist database.");
            }
            else {
                writer.println(
                        "|" + str + "| does not exist in the artist database.");
            }

        }

    }

    /**
     * Insert
     *
     * @param artist
     *            artist name
     * @param song
     *            song title
     * @param writer
     *            used for status output
     * @throws Exception
     *             exception from inner calls
     */
    private void insert(String artist, String song, PrintWriter writer)
            throws Exception {
        artist = artist.trim();
        song = song.trim();
        // First, insert the strings to their respective databases
        Handle artistHandle = artistHashTable.insertString(artist, writer);
        Handle songHandle = songHashTable.insertString(song, writer);
        // then create and insert KVPair with artist as key and song as value
        KVPair artistAsKey = new KVPair(artistHandle, songHandle);
        // create then insert KVPair with song as key and artist value
        KVPair songAsKey = new KVPair(songHandle, artistHandle);

        if(theTree.add(artistAsKey)) {
            writer.println("The KVPair (|" + artist + "|,|" + song + "|),(" + artistHandle + "," + songHandle + ") is added to the tree.");
            theTree.add(songAsKey);
            writer.println("The KVPair (|" + song + "|,|" + artist + "|),(" + songHandle + "," + artistHandle + ") is added to the tree.");
        }
        else {
            writer.println("The KVPair (|" + artist + "|,|" + song + "|),(" + artistHandle + "," + songHandle + ") duplicates a record already in the tree.");
            writer.println("The KVPair (|" + song + "|,|" + artist + "|),(" + songHandle + "," + artistHandle + ") duplicates a record already in the tree.");
        }



    }

}