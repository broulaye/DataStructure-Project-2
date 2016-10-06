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
    public void testInsert(){
        tree.add(new KVPair(new Handle(23), new Handle(0)));
        tree.add(new KVPair(new Handle(0), new Handle(23)));
        tree.add(new KVPair(new Handle(44), new Handle(2)));
        tree.add(new KVPair(new Handle(57), new Handle(44)));
        tree.add(new KVPair(new Handle(0), new Handle(23)));
        tree.add(new KVPair(new Handle(23), new Handle(0)));
        tree.add(new KVPair(new Handle(44), new Handle(57)));
        tree.add(new KVPair(new Handle(57), new Handle(44)));
        tree.add(new KVPair(new Handle(83), new Handle(99)));
        tree.add(new KVPair(new Handle(99), new Handle(83)));
        tree.add(new KVPair(new Handle(127), new Handle(146)));
        tree.add(new KVPair(new Handle(146), new Handle(127)));
        tree.add(new KVPair(new Handle(44), new Handle(164)));
        tree.add(new KVPair(new Handle(164), new Handle(44)));
        tree.add(new KVPair(new Handle(191), new Handle(57)));
        tree.add(new KVPair(new Handle(57), new Handle(191)));
        tree.add(new KVPair(new Handle(44), new Handle(212)));
        tree.add(new KVPair(new Handle(212), new Handle(44)));
        tree.add(new KVPair(new Handle(191), new Handle(164)));
        tree.add(new KVPair(new Handle(164), new Handle(191)));
        tree.add(new KVPair(new Handle(44), new Handle(238)));
        tree.add(new KVPair(new Handle(238), new Handle(44)));
        tree.add(new KVPair(new Handle(191), new Handle(212)));
        tree.add(new KVPair(new Handle(212), new Handle(191)));
        tree.add(new KVPair(new Handle(83), new Handle(57)));
        tree.add(new KVPair(new Handle(57), new Handle(83)));
        tree.add(new KVPair(new Handle(83), new Handle(44)));
        tree.add(new KVPair(new Handle(44), new Handle(83)));
        tree.add(new KVPair(new Handle(265), new Handle(44)));
        tree.add(new KVPair(new Handle(44), new Handle(265)));
        tree.add(new KVPair(new Handle(83), new Handle(288)));
        tree.add(new KVPair(new Handle(288), new Handle(83)));
        tree.add(new KVPair(new Handle(83), new Handle(317)));
        tree.add(new KVPair(new Handle(317), new Handle(83)));
        tree.add(new KVPair(new Handle(265), new Handle(288)));
        tree.add(new KVPair(new Handle(288), new Handle(265)));
        tree.add(new KVPair(new Handle(83), new Handle(44)));
        tree.add(new KVPair(new Handle(44), new Handle(83)));
        tree.add(new KVPair(new Handle(265), new Handle(317)));
        tree.add(new KVPair(new Handle(317), new Handle(265)));
        tree.add(new KVPair(new Handle(265), new Handle(345)));
        tree.add(new KVPair(new Handle(345), new Handle(265)));
        tree.add(new KVPair(new Handle(265), new Handle(374)));
        tree.add(new KVPair(new Handle(374), new Handle(265)));
        tree.add(new KVPair(new Handle(402), new Handle(417)));
        tree.add(new KVPair(new Handle(417), new Handle(402)));
        tree.add(new KVPair(new Handle(433), new Handle(447)));
        tree.add(new KVPair(new Handle(447), new Handle(433)));
        tree.add(new KVPair(new Handle(476), new Handle(72)));
        tree.add(new KVPair(new Handle(72), new Handle(476)));
        tree.add(new KVPair(new Handle(83), new Handle(492)));
        tree.add(new KVPair(new Handle(492), new Handle(83)));
        tree.add(new KVPair(new Handle(402), new Handle(417)));
        tree.add(new KVPair(new Handle(417), new Handle(402)));
        tree.add(new KVPair(new Handle(433), new Handle(447)));
        tree.add(new KVPair(new Handle(447), new Handle(433)));
        tree.add(new KVPair(new Handle(476), new Handle(72)));
        tree.add(new KVPair(new Handle(72), new Handle(476)));
        tree.add(new KVPair(new Handle(83), new Handle(492)));
        tree.add(new KVPair(new Handle(492), new Handle(83)));
        tree.add(new KVPair(new Handle(516), new Handle(531)));
        tree.add(new KVPair(new Handle(531), new Handle(516)));
        tree.add(new KVPair(new Handle(545), new Handle(560)));
        System.out.println(tree.print());
    }
    public void testInsertLeft(){

    }
    public void testInsertRight(){

    }
    public void testPrint() {

    }
}
