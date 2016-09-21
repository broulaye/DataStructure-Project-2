import student.TestCase;

/**
 * @author {Your Name Here}
 * @version {Put Something Here}
 */
public class SearchTreeTest extends TestCase {
    /**
     * Sets up the tests that follow. In general, used for initialization.
     */
    public void setUp() {
        // Nothing Here
    }
    
    /**
     * Get code coverage of the class declaration.
     */
    public void testSInit() {
        SearchTree tree = new SearchTree();
        assertNotNull(tree);
        SearchTree.main(null);
    }
}
