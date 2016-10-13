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
        System.out.println(tree.toString());
        System.out.println("====================================");

        tree.add(new KVPair(new Handle(0), new Handle(23)));
        System.out.println(tree.toString());
        System.out.println("====================================");

        tree.add(new KVPair(new Handle(44), new Handle(2)));
        System.out.println(tree.toString());
        System.out.println("====================================");

        tree.add(new KVPair(new Handle(57), new Handle(44)));
        System.out.println(tree.toString());
        System.out.println("====================================");

        tree.add(new KVPair(new Handle(0), new Handle(23)));
        System.out.println(tree.toString());
        System.out.println("====================================");

        tree.add(new KVPair(new Handle(23), new Handle(0)));
        System.out.println(tree.toString());
        System.out.println("====================================");

        tree.add(new KVPair(new Handle(44), new Handle(57)));
        System.out.println(tree.toString());
        System.out.println("====================================");

        tree.add(new KVPair(new Handle(57), new Handle(44)));
        System.out.println(tree.toString());
        System.out.println("====================================");
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
        tree.structureChecker();
        tree.sanityChecker();
        //System.out.println(tree.print());

    }

    public void testAddingDuplicates() {
        assertTrue(tree.add(new KVPair(new Handle(545), new Handle(560))));
        assertTrue(tree.add(new KVPair(new Handle(560), new Handle(560))));
        assertFalse(tree.add(new KVPair(new Handle(545), new Handle(560))));
        assertFalse(tree.add(new KVPair(new Handle(560), new Handle(560))));
        System.out.println("Duplicate Tree: ");
        System.out.println(tree.print());
    }

    /**
     * This test the deleting from the left
     */
    public void testDeleteLeft(){
        tree.add(new KVPair(new Handle(7), new Handle(3)));
        tree.add(new KVPair(new Handle(3), new Handle(7)));
        tree.add(new KVPair(new Handle(7), new Handle(10)));
        tree.add(new KVPair(new Handle(10), new Handle(7)));
        tree.add(new KVPair(new Handle(3), new Handle(1)));
        tree.add(new KVPair(new Handle(1), new Handle(3)));
        tree.add(new KVPair(new Handle(10), new Handle(12)));
        tree.add(new KVPair(new Handle(12), new Handle(10)));
        tree.add(new KVPair(new Handle(1), new Handle(2)));
        tree.add(new KVPair(new Handle(2), new Handle(1)));
        tree.add(new KVPair(new Handle(12), new Handle(11)));
        tree.add(new KVPair(new Handle(11), new Handle(12)));
        tree.add(new KVPair(new Handle(12), new Handle(13)));
        tree.add(new KVPair(new Handle(13), new Handle(12)));

        //System.out.println(tree.print());
        tree.remove(new KVPair(new Handle(1), new Handle(2)));
        //System.out.println(tree.print());
        tree.remove(new KVPair(new Handle(1), new Handle(3)));
       // System.out.println(tree.print());
        tree.remove(new KVPair(new Handle(2), new Handle(1)));
       // System.out.println(tree.print());
        tree.remove(new KVPair(new Handle(3), new Handle(1)));
       // System.out.println(tree.print());

    }

    public void testDeleteMiddle(){

        tree.add(new KVPair(new Handle(7), new Handle(100)));
        tree.add(new KVPair(new Handle(8), new Handle(101)));
        tree.add(new KVPair(new Handle(9), new Handle(102)));
        tree.add(new KVPair(new Handle(10), new Handle(103)));
        tree.add(new KVPair(new Handle(12), new Handle(104)));
        tree.add(new KVPair(new Handle(13), new Handle(105)));
        tree.add(new KVPair(new Handle(22), new Handle(106)));
        tree.add(new KVPair(new Handle(1), new Handle(107)));
        tree.add(new KVPair(new Handle(0), new Handle(108)));
        tree.add(new KVPair(new Handle(11), new Handle(109)));
        tree.add(new KVPair(new Handle(28), new Handle(110)));
        tree.add(new KVPair(new Handle(50), new Handle(111)));
        tree.add(new KVPair(new Handle(6), new Handle(112)));
        tree.add(new KVPair(new Handle(4), new Handle(113)));
        tree.add(new KVPair(new Handle(2), new Handle(114)));

        System.out.println(tree.print());

        tree.remove(new KVPair(new Handle(7), new Handle(100)));

        System.out.println(tree.print());

        tree.remove(new KVPair(new Handle(8), new Handle(101)));

        System.out.println(tree.print());

        tree.remove(new KVPair(new Handle(6), new Handle(112)));

        System.out.println(tree.print());

        tree.remove(new KVPair(new Handle(2), new Handle(114)));

        System.out.println(tree.print());

        tree.remove(new KVPair(new Handle(1), new Handle(107)));

        System.out.println(tree.print());

        tree.remove(new KVPair(new Handle(50), new Handle(111)));

        System.out.println(tree.print());

        tree.remove(new KVPair(new Handle(28), new Handle(110)));

        System.out.println(tree.print());

        tree.remove(new KVPair(new Handle(11), new Handle(109)));

        System.out.println(tree.print());

        tree.remove(new KVPair(new Handle(10), new Handle(103)));

        System.out.println(tree.print());

        tree.remove(new KVPair(new Handle(9), new Handle(102)));

        System.out.println(tree.print());

        tree.remove(new KVPair(new Handle(4), new Handle(113)));

        System.out.println(tree.print());

        tree.remove(new KVPair(new Handle(12), new Handle(104)));

        System.out.println(tree.print());

        tree.remove(new KVPair(new Handle(13), new Handle(105)));

        System.out.println(tree.print());

        tree.remove(new KVPair(new Handle(22), new Handle(106)));

        System.out.println(tree.print());


    }



    public void testDeleteRight(){
        tree.add(new KVPair(new Handle(7), new Handle(8)));
        tree.add(new KVPair(new Handle(8), new Handle(7)));
        tree.add(new KVPair(new Handle(9), new Handle(2)));
        tree.add(new KVPair(new Handle(2), new Handle(9)));
        tree.add(new KVPair(new Handle(12), new Handle(13)));
        tree.add(new KVPair(new Handle(13), new Handle(12)));
        tree.add(new KVPair(new Handle(22), new Handle(1)));
        tree.add(new KVPair(new Handle(1), new Handle(22)));
        tree.add(new KVPair(new Handle(0), new Handle(11)));
        tree.add(new KVPair(new Handle(11), new Handle(0)));
        tree.add(new KVPair(new Handle(28), new Handle(50)));
        tree.add(new KVPair(new Handle(50), new Handle(28)));
        tree.add(new KVPair(new Handle(6), new Handle(4)));
        tree.add(new KVPair(new Handle(4), new Handle(6)));
        tree.add(new KVPair(new Handle(2), new Handle(30)));
        tree.add(new KVPair(new Handle(30), new Handle(2)));
        tree.add(new KVPair(new Handle(35), new Handle(40)));
        tree.add(new KVPair(new Handle(40), new Handle(35)));
        tree.add(new KVPair(new Handle(45), new Handle(55)));
        tree.add(new KVPair(new Handle(55), new Handle(45)));
        tree.add(new KVPair(new Handle(45), new Handle(60)));
        tree.add(new KVPair(new Handle(60), new Handle(45)));
        System.out.println(tree.print());
        tree.remove(new KVPair(new Handle(45), new Handle(55)));
        System.out.println(tree.print());
        tree.remove(new KVPair(new Handle(55), new Handle(45)));
        System.out.println(tree.print());
        tree.remove(new KVPair(new Handle(60), new Handle(45)));
        System.out.println(tree.print());
        tree.remove(new KVPair(new Handle(28), new Handle(50)));
        System.out.println(tree.print());
        tree.remove(new KVPair(new Handle(30), new Handle(2)));
        System.out.println(tree.print());
        tree.remove(new KVPair(new Handle(6), new Handle(4)));
        System.out.println(tree.print());
        tree.remove(new KVPair(new Handle(7), new Handle(8)));
        System.out.println(tree.print());
        tree.remove(new KVPair(new Handle(8), new Handle(7)));
        System.out.println(tree.print());


        System.out.println(tree.print());

        tree.remove(new KVPair(new Handle(60), new Handle(-1)));
        System.out.println(tree.print());

        tree.remove(new KVPair(new Handle(55), new Handle(-1)));
        System.out.println(tree.print());

        tree.remove(new KVPair(new Handle(50), new Handle(-1)));
        System.out.println(tree.print());

        tree.remove(new KVPair(new Handle(45), new Handle(-1)));
        System.out.println(tree.print());

        tree.remove(new KVPair(new Handle(40), new Handle(-1)));
        System.out.println(tree.print());

        tree.remove(new KVPair(new Handle(35), new Handle(-1)));
        System.out.println(tree.print());

        tree.remove(new KVPair(new Handle(30), new Handle(-1)));
        System.out.println(tree.print());

        tree.remove(new KVPair(new Handle(11), new Handle(-1)));
        System.out.println(tree.print());

        tree.remove(new KVPair(new Handle(10), new Handle(-1)));
        System.out.println(tree.print());

        tree.remove(new KVPair(new Handle(9), new Handle(-1)));
        System.out.println(tree.print());

        tree.remove(new KVPair(new Handle(0), new Handle(-1)));
        System.out.println(tree.print());

        tree.remove(new KVPair(new Handle(28), new Handle(-1)));
        System.out.println(tree.print());

        tree.remove(new KVPair(new Handle(22), new Handle(-1)));
        System.out.println(tree.print());

        tree.remove(new KVPair(new Handle(13), new Handle(-1)));
        System.out.println(tree.print());

        tree.remove(new KVPair(new Handle(12), new Handle(-1)));
        System.out.println(tree.print());

    }

    public void testDeleteAll() {

        tree.add(new KVPair(new Handle(7), new Handle(-1)));
        System.out.println(tree.print());

        tree.remove(new KVPair(new Handle(7), new Handle(-1)));
        System.out.println(tree.print());


    }

    public void testPrint() {

    }
}
