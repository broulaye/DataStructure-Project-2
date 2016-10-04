import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

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
                        break;
                    case insert:
                        insert(command.getValues()[0], command.getValues()[1],
                                writer);
                        break;
                    case remove:
                        remove(command.getTyp(), command.getValues()[0],
                                writer);
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
     * Delete the specifc record for this particular songName by this
     * particular artistName.
     * @param artistName artist name
     * @param songName song of given artist
     * @param writer output stream
     */
    private void delete(String artistName, String songName, PrintWriter writer) {
    }

    /**
     *If type is artist then all songs by the artist with name {name}
     * are listed. If type is song then all artists who have recorded
     * that song are listed.
     * @param type artist or song
     * @param str  string associated with type
     * @param writer output stream
     */
    private void list(Type type, String str, PrintWriter writer) {
        switch (type) {
            case Song:
                //TODO:
                break;
            case Artist:
                //TODO:
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
        if (what == Type.Song) {
            if (songHashTable.removeString(str)) {
                writer.println(
                        "|" + str + "| is removed from the song database.");
            }
            else {
                writer.println(
                        "|" + str + "| does not exist in the song database.");
            }
        }
        else if (what == Type.Artist) {
            if (artistHashTable.removeString(str)) {
                writer.println(
                        "|" + str + "| is removed from the artist database.");
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
        // create then insert KVPair with song as key and artist value
        KVPair songAsKey = new KVPair(songHandle, artistHandle);
        theTree.add(songAsKey);
        // then create and insert KVPair with artist as key and song as value
        KVPair artistAsKey = new KVPair(artistHandle, songHandle);
        theTree.add(artistAsKey);

    }

}