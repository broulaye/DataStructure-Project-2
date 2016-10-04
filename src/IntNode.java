/**
 * This class represent an Internal Node
 * @author Broulaye Doumbia
 *
 * @param <KVPair>
 */
public class IntNode<KVPair extends Comparable<? super KVPair>> extends Node{
    //each Internal Node has three pointers and two values
    private KVPair leftKey; //left key
    private KVPair rightKey; //right key
    private Node<KVPair> left; //left Node
    private Node<KVPair> right; //right Node
    private Node<KVPair> middle; //middle Node
    
    public IntNode() {
        middle = null;
        right = null;
        left = null;
        rightKey = null;
        leftKey = null;
    }

    public  IntNode(KVPair neftKey, KVPair nrightKey) {
        leftKey = neftKey;
        rightKey = nrightKey;
        middle = null;
        right = null;
        left = null;
    }

    /**
     * Copy constructor
     * @param root node to be copied
     */
    public IntNode(IntNode<KVPair> root) {
        left = root.getLeft();
        right = root.getRight();
        middle = root.getMiddle();
        leftKey = root.getLeftKey();
        rightKey = root.getRightKey();
    }

    @Override
    public boolean isFull() {
        return leftKey != null && rightKey != null;
    }
    
    public KVPair getLeftKey() {
        return leftKey;
    }

    public void setLeftKey(KVPair leftKey) {
        this.leftKey = leftKey;
    }

    public KVPair getRightKey() {
        return rightKey;
    }

    public void setRightKey(KVPair rightKey) {
        this.rightKey = rightKey;
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
    public boolean isEqual(Node node) {
        IntNode newNode = (IntNode) node;
        return newNode.leftKey.compareTo(this.leftKey) == 0 && newNode.rightKey.compareTo(this.rightKey) == 0;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if(getLeftKey() != null) {
            builder.append(this.getLeft().toString());
        }
        if(this.getRightKey() != null) {

            builder.append(this.getRightKey().toString());
        }
        return builder.toString();
    }
}
