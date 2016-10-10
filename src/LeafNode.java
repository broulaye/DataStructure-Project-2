/**
 * This class represent a leaf node
 * 
 * @author Broulaye Doumbia
 * @author Cheick Berthe
 *
 */
public class LeafNode extends Node<KVPair> {

    private LeafNode next;
    private LeafNode previous;

    /**
     * Copy constructor
     * 
     * @param root
     *            node to be copied
     */
    public LeafNode(LeafNode root) {
        leftKey = root.getLeftKey();
        rightKey = root.getRightKey();
    }

    public LeafNode getNext() {
        return next;
    }

    public void setNext(LeafNode next) {
        this.next = next;
    }

    public LeafNode getPrevious() {
        return previous;
    }

    public void setPrevious(LeafNode previous) {
        this.previous = previous;
    }

    public LeafNode(KVPair key) {
        leftKey = key;
        rightKey = null;
        next = null;
        previous = null;
    }
    public LeafNode() {
        leftKey = null;
        rightKey = null;
        next = null;
        previous = null;
    }

    @Override
    public boolean isLeaf(){
        return true;
    }


}
