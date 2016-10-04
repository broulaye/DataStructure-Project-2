import student.TestCase;
/**
 * @author: Cheick Berthe
 * @author Broulaye Doumbia
 * @version:10/3/2016.
 */

public class TwoThreeTest extends TestCase
{
    private TwoThree tree;
    public void setUp(){
        tree = new TwoThree();
    }
    public void testInsertOnEmpty(){
        KVPair pair = new KVPair(new Handle(10), new Handle(15));
        tree.add(pair);
    }
    public void testInsertOnFull(){
        KVPair pair1 = new KVPair(new Handle(13), new Handle(20));
        KVPair pair2 = new KVPair(new Handle(11), new Handle(27));
        KVPair pair3 = new KVPair(new Handle(1), new Handle(5));
        tree.add(pair1);
        tree.add(pair2);
        tree.add(pair3);
    }
    public void testInsertLeft(){

    }
    public void testInsertRight(){

    }
    public void testPrint() {

    }
}
