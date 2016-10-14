import student.TestCase;

/**
 * @author: Cheick Berthe
 * @author Broulaye Doumbia
 * @version:10/3/2016.
 */

public class TwoThreeTest extends TestCase {
    private TwoThree tree;

    public void setUp() {
        tree = new TwoThree();
    }

    public void testInsert() {
        assertTrue(tree.insert(new KVPair(new Handle(1), new Handle(560))));
        assertTrue(tree.insert(new KVPair(new Handle(3), new Handle(560))));
        assertTrue(tree.insert(new KVPair(new Handle(8), new Handle(560))));
        assertTrue(tree.insert(new KVPair(new Handle(9), new Handle(560))));
        assertTrue(tree.insert(new KVPair(new Handle(4), new Handle(560))));
        assertTrue(tree.insert(new KVPair(new Handle(5), new Handle(560))));
        tree.insert(new KVPair(new Handle(83), new Handle(99)));
        tree.insert(new KVPair(new Handle(99), new Handle(83)));
        tree.insert(new KVPair(new Handle(127), new Handle(146)));
        tree.insert(new KVPair(new Handle(146), new Handle(127)));
        tree.insert(new KVPair(new Handle(44), new Handle(164)));
        tree.insert(new KVPair(new Handle(164), new Handle(44)));
        tree.insert(new KVPair(new Handle(191), new Handle(57)));
        tree.insert(new KVPair(new Handle(57), new Handle(191)));
        tree.insert(new KVPair(new Handle(44), new Handle(212)));
        tree.insert(new KVPair(new Handle(212), new Handle(44)));
        tree.insert(new KVPair(new Handle(191), new Handle(164)));
        tree.insert(new KVPair(new Handle(164), new Handle(191)));
        tree.insert(new KVPair(new Handle(44), new Handle(238)));
        tree.insert(new KVPair(new Handle(238), new Handle(44)));
        tree.insert(new KVPair(new Handle(191), new Handle(212)));
        tree.insert(new KVPair(new Handle(212), new Handle(191)));
        tree.insert(new KVPair(new Handle(83), new Handle(57)));
        tree.insert(new KVPair(new Handle(57), new Handle(83)));
        tree.insert(new KVPair(new Handle(83), new Handle(44)));
        tree.insert(new KVPair(new Handle(44), new Handle(83)));
        tree.insert(new KVPair(new Handle(265), new Handle(44)));
        tree.insert(new KVPair(new Handle(44), new Handle(265)));
        tree.insert(new KVPair(new Handle(83), new Handle(288)));
        tree.insert(new KVPair(new Handle(288), new Handle(83)));
        tree.insert(new KVPair(new Handle(83), new Handle(317)));
        tree.insert(new KVPair(new Handle(317), new Handle(83)));
        tree.insert(new KVPair(new Handle(265), new Handle(288)));
        tree.insert(new KVPair(new Handle(288), new Handle(265)));
        tree.insert(new KVPair(new Handle(83), new Handle(44)));
        tree.insert(new KVPair(new Handle(44), new Handle(83)));
        tree.insert(new KVPair(new Handle(265), new Handle(317)));
        tree.insert(new KVPair(new Handle(317), new Handle(265)));
        tree.insert(new KVPair(new Handle(265), new Handle(345)));
        tree.insert(new KVPair(new Handle(345), new Handle(265)));
        tree.insert(new KVPair(new Handle(265), new Handle(374)));
        tree.insert(new KVPair(new Handle(374), new Handle(265)));
        tree.insert(new KVPair(new Handle(402), new Handle(417)));
        tree.insert(new KVPair(new Handle(417), new Handle(402)));
        tree.insert(new KVPair(new Handle(433), new Handle(447)));
        tree.insert(new KVPair(new Handle(447), new Handle(433)));
        tree.insert(new KVPair(new Handle(476), new Handle(72)));
        tree.insert(new KVPair(new Handle(72), new Handle(476)));
        tree.insert(new KVPair(new Handle(83), new Handle(492)));
        tree.insert(new KVPair(new Handle(492), new Handle(83)));
        tree.insert(new KVPair(new Handle(402), new Handle(417)));
        tree.insert(new KVPair(new Handle(417), new Handle(402)));
        tree.insert(new KVPair(new Handle(433), new Handle(447)));
        tree.insert(new KVPair(new Handle(447), new Handle(433)));
        tree.insert(new KVPair(new Handle(476), new Handle(72)));
        tree.insert(new KVPair(new Handle(72), new Handle(476)));
        tree.insert(new KVPair(new Handle(83), new Handle(492)));
        tree.insert(new KVPair(new Handle(492), new Handle(83)));
        tree.insert(new KVPair(new Handle(516), new Handle(531)));
        tree.insert(new KVPair(new Handle(531), new Handle(516)));
        tree.insert(new KVPair(new Handle(545), new Handle(560)));
    }

    public void testAddingDuplicates() {
        assertTrue(tree.insert(new KVPair(new Handle(545), new Handle(560))));
        assertTrue(tree.insert(new KVPair(new Handle(560), new Handle(560))));
        assertFalse(tree.insert(new KVPair(new Handle(545), new Handle(560))));
        assertFalse(tree.insert(new KVPair(new Handle(560), new Handle(560))));
        System.out.println("Duplicate Tree: ");
        System.out.println(tree.print());
    }

    /**
     * This test the deleting from the left
     */
    public void testDeleteLeft() {
        tree.insert(new KVPair(new Handle(7), new Handle(3)));
        tree.insert(new KVPair(new Handle(3), new Handle(7)));
        tree.insert(new KVPair(new Handle(7), new Handle(10)));
        tree.insert(new KVPair(new Handle(10), new Handle(7)));
        tree.insert(new KVPair(new Handle(3), new Handle(1)));
        tree.insert(new KVPair(new Handle(1), new Handle(3)));
        tree.insert(new KVPair(new Handle(10), new Handle(12)));
        tree.insert(new KVPair(new Handle(12), new Handle(10)));
        tree.insert(new KVPair(new Handle(1), new Handle(2)));
        tree.insert(new KVPair(new Handle(2), new Handle(1)));
        tree.insert(new KVPair(new Handle(12), new Handle(11)));
        tree.insert(new KVPair(new Handle(11), new Handle(12)));
        tree.insert(new KVPair(new Handle(12), new Handle(13)));
        tree.insert(new KVPair(new Handle(13), new Handle(12)));
        tree.remove(new KVPair(new Handle(1), new Handle(2)));
        tree.remove(new KVPair(new Handle(1), new Handle(3)));
        tree.remove(new KVPair(new Handle(2), new Handle(1)));
        tree.remove(new KVPair(new Handle(3), new Handle(1)));
    }

    public void testDeleteMiddle() {

        tree.insert(new KVPair(new Handle(7), new Handle(100)));
        tree.insert(new KVPair(new Handle(8), new Handle(101)));
        tree.insert(new KVPair(new Handle(9), new Handle(102)));
        tree.insert(new KVPair(new Handle(10), new Handle(103)));
        tree.insert(new KVPair(new Handle(12), new Handle(104)));
        tree.insert(new KVPair(new Handle(13), new Handle(105)));
        tree.insert(new KVPair(new Handle(22), new Handle(106)));
        tree.insert(new KVPair(new Handle(1), new Handle(107)));
        tree.insert(new KVPair(new Handle(0), new Handle(108)));
        tree.insert(new KVPair(new Handle(11), new Handle(109)));
        tree.insert(new KVPair(new Handle(28), new Handle(110)));
        tree.insert(new KVPair(new Handle(50), new Handle(111)));
        tree.insert(new KVPair(new Handle(6), new Handle(112)));
        tree.insert(new KVPair(new Handle(4), new Handle(113)));
        tree.insert(new KVPair(new Handle(2), new Handle(114)));
        tree.remove(new KVPair(new Handle(7), new Handle(100)));
        tree.remove(new KVPair(new Handle(8), new Handle(101)));
        tree.remove(new KVPair(new Handle(6), new Handle(112)));
        tree.remove(new KVPair(new Handle(2), new Handle(114)));
        tree.remove(new KVPair(new Handle(1), new Handle(107)));
        tree.remove(new KVPair(new Handle(50), new Handle(111)));
        tree.remove(new KVPair(new Handle(28), new Handle(110)));
        tree.remove(new KVPair(new Handle(11), new Handle(109)));
        tree.remove(new KVPair(new Handle(10), new Handle(103)));
        tree.remove(new KVPair(new Handle(9), new Handle(102)));
        tree.remove(new KVPair(new Handle(4), new Handle(113)));
        tree.remove(new KVPair(new Handle(12), new Handle(104)));
        tree.remove(new KVPair(new Handle(13), new Handle(105)));
        tree.remove(new KVPair(new Handle(22), new Handle(106)));
        System.out.println(tree.print());
    }

    public void testDeleteRight() {
        tree.insert(new KVPair(new Handle(7), new Handle(8)));
        tree.insert(new KVPair(new Handle(8), new Handle(7)));
        tree.insert(new KVPair(new Handle(9), new Handle(2)));
        tree.insert(new KVPair(new Handle(2), new Handle(9)));
        tree.insert(new KVPair(new Handle(12), new Handle(13)));
        tree.insert(new KVPair(new Handle(13), new Handle(12)));
        tree.insert(new KVPair(new Handle(22), new Handle(1)));
        tree.insert(new KVPair(new Handle(1), new Handle(22)));
        tree.insert(new KVPair(new Handle(0), new Handle(11)));
        tree.insert(new KVPair(new Handle(11), new Handle(0)));
        tree.insert(new KVPair(new Handle(28), new Handle(50)));
        tree.insert(new KVPair(new Handle(50), new Handle(28)));
        tree.insert(new KVPair(new Handle(6), new Handle(4)));
        tree.insert(new KVPair(new Handle(4), new Handle(6)));
        tree.insert(new KVPair(new Handle(2), new Handle(30)));
        tree.insert(new KVPair(new Handle(30), new Handle(2)));
        tree.insert(new KVPair(new Handle(35), new Handle(40)));
        tree.insert(new KVPair(new Handle(40), new Handle(35)));
        tree.insert(new KVPair(new Handle(45), new Handle(55)));
        tree.insert(new KVPair(new Handle(55), new Handle(45)));
        tree.insert(new KVPair(new Handle(45), new Handle(60)));
        tree.insert(new KVPair(new Handle(60), new Handle(45)));
        tree.remove(new KVPair(new Handle(45), new Handle(55)));
        tree.remove(new KVPair(new Handle(55), new Handle(45)));
        tree.remove(new KVPair(new Handle(60), new Handle(45)));
        tree.remove(new KVPair(new Handle(28), new Handle(50)));
        tree.remove(new KVPair(new Handle(30), new Handle(2)));
        tree.remove(new KVPair(new Handle(6), new Handle(4)));
        tree.remove(new KVPair(new Handle(7), new Handle(8)));
        tree.remove(new KVPair(new Handle(60), new Handle(-1)));
        tree.remove(new KVPair(new Handle(55), new Handle(-1)));
        tree.remove(new KVPair(new Handle(50), new Handle(-1)));
        tree.remove(new KVPair(new Handle(45), new Handle(-1)));
        tree.remove(new KVPair(new Handle(40), new Handle(-1)));
        tree.remove(new KVPair(new Handle(35), new Handle(-1)));
        tree.remove(new KVPair(new Handle(30), new Handle(-1)));
        tree.remove(new KVPair(new Handle(11), new Handle(-1)));
        tree.remove(new KVPair(new Handle(10), new Handle(-1)));
        tree.remove(new KVPair(new Handle(9), new Handle(-1)));
        tree.remove(new KVPair(new Handle(0), new Handle(-1)));
        tree.remove(new KVPair(new Handle(28), new Handle(-1)));
        tree.remove(new KVPair(new Handle(22), new Handle(-1)));
        tree.remove(new KVPair(new Handle(13), new Handle(-1)));
        tree.remove(new KVPair(new Handle(12), new Handle(-1)));

    }

    public void testDeleteAll() {

        tree.insert(new KVPair(new Handle(7), new Handle(-1)));
        System.out.println(tree.print());

        tree.remove(new KVPair(new Handle(7), new Handle(-1)));
        System.out.println(tree.print());

    }
}
