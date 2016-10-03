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
    }
    
    public boolean isLeaf() {
        return middle == right && right == left && left == null;
    }

    @Override
    public boolean isFull() {
        return leftKey == null && rightKey == null;
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

    
    public boolean isEqual(IntNode<KVPair> newNode) {
        return newNode.leftKey.compareTo(this.leftKey) == 0 && newNode.rightKey.compareTo(this.rightKey) == 0; 
    }
    
}
