import student.TestCase;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;


/**
 * @author Broulaye Doumbia
 * @author Cheick Berthe
 * @version 9/14/2016
 */
public class SearchTreeTest extends TestCase {
    private MemManager pool;
    private PrintWriter writer;
    
    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        pool = new MemManager(80);
        try {
            writer = new PrintWriter("testMemman.txt", "UTF-8");
        }
        catch (UnsupportedEncodingException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get code coverage of the class declaration.
     */
    public void testMInit() {
        SearchTree mem = new SearchTree();
        assertNotNull(mem);
        SearchTree.main(null);
    }
    
    /**
     * test insert
     */
    public void testInsert() {
        System.out.println(pool.dump());
        pool.insert("broulaye", writer);
        System.out.println(pool.dump());
        Handle handle = new Handle(0);
        pool.remove(handle);
        System.out.println(pool.dump());
        String value = null;
        assertNull(value);
    }
    
    /**
     * test sample file
     */
    public void testSampleInputFile() {
        final String[] arguments = new String[3];
        arguments[0] = "10";
        arguments[1] = "32";
        arguments[2] = "bad-file.txt";
        SearchTree.main(arguments);
        arguments[2] = "P2_Input1_Sample.txt";
        SearchTree.main(arguments);
        String value = null;
        assertNull(value);
        SearchTree.main(null);
        arguments[0] = "-19";
        SearchTree.main(arguments);
    }
}
