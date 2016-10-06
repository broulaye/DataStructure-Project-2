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

    @Override
    public boolean isLeaf(){
        return false;
    }
}
