import org.junit.Assert;

/**
 * This class represent an Internal Node
 * 
 * @author Broulaye Doumbia
 * @author Cheick Berthe
 */
public class IntNode extends Node<KVPair> {

    private Node<KVPair> left; // left Node
    private Node<KVPair> right; // right Node
    private Node<KVPair> middle; // middle Node

    public IntNode() {
        middle = null;
        right = null;
        left = null;
        rightKey = null;
        leftKey = null;
    }

    /**
     * Copy constructor
     * 
     * @param root
     *            node to be copied
     */
    public IntNode(IntNode root) {
        if (root == null) {
            new IntNode();
        }
        left = root.getLeft();
        right = root.getRight();
        middle = root.getMiddle();
        leftKey = root.getLeftKey();
        rightKey = root.getRightKey();
    }

    public IntNode(KVPair key) {
        leftKey = key;
        rightKey = null;
        right = null;
        middle = null;
        left = null;
    }


    public Node<KVPair> getLeft() {
        return left;
    }

    public void setLeft(Node<KVPair> left) {
        this.left = left;
    }

    public Node<KVPair> getRight() {
        return right;
    }

    public void setRight(Node<KVPair> right) {
        this.right = right;
    }

    public Node<KVPair> getMiddle() {
        return middle;
    }

    public void setMiddle(Node<KVPair> middle) {
        this.middle = middle;
    }
    /**
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        if(rightKey != null){
            Assert.assertTrue(isFull());
        }
        if(left != null){
            builder.append(left.toString());
        }
        if(middle != null){
            Assert.assertNotNull(left);
            builder.append(middle.toString());
        }
        if(right != null){
            Assert.assertNotNull(middle);
            builder.append(right.toString());
        }
        return builder.toString();
    }
*/
    @Override
    public boolean isFull(){
        if(leftKey != null && rightKey != null){
            Assert.assertNotNull(left);
            Assert.assertNotNull(middle);
            Assert.assertNotNull(right);
            return true;
        }
        return false;
    }
    @Override
    public boolean isLeaf(){
        return false;
    }
}
