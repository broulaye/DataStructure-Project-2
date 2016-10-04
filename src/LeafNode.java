/**
 * This class represent a leaf node
 * 
 * @author Broulaye Doumbia
 *
 * @param <KVPair>
 */
public class LeafNode<KVPair extends Comparable<? super KVPair>>
        extends Node<KVPair> {

    // a leaf node has a left and right value
    private KVPair leftKey; // left key
    private KVPair rightKey; // right key
    private LeafNode<KVPair> next;
    private LeafNode<KVPair> previous;

    /**
     * Copy constructor
     * 
     * @param root
     *            node to be copied
     */
    public LeafNode(LeafNode<KVPair> root) {
        leftKey = root.getLeftKey();
        rightKey = root.getRightKey();
    }

    public LeafNode<KVPair> getNext() {
        return next;
    }

    public void setNext(LeafNode<KVPair> next) {
        this.next = next;
    }

    public LeafNode<KVPair> getPrevious() {
        return previous;
    }

    public void setPrevious(LeafNode<KVPair> previous) {
        this.previous = previous;
    }

    public LeafNode() {
        leftKey = null;
        rightKey = null;
        next = null;
        previous = null;
    }

    @Override
    public boolean isFull() {
        return leftKey != null && rightKey != null;
    }

    public LeafNode(KVPair leftKey, KVPair rightKey) {
        super();
        this.leftKey = leftKey;
        this.rightKey = rightKey;
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

    @Override
    public boolean isEqual(Node node) {
        LeafNode newNode = (LeafNode) node;
        Boolean result = false;
        if (newNode == null) {
            return false;
        }
        if (newNode.getLeftKey() != null && this.getLeftKey() != null) {
            result = newNode.leftKey.compareTo(this.leftKey) == 0;
            if (newNode.getRightKey() != null && this.getRightKey() != null) {
                result = newNode.rightKey.compareTo(this.rightKey) == 0;
                return result;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (this.getLeftKey() != null) {
            builder.append(this.getLeftKey().toString());
        }
        if (this.getRightKey() != null) {
            builder.append(" ");
            builder.append(this.getRightKey().toString());
        }
        return builder.toString();
    }
}
