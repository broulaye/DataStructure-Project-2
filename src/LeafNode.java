/**
 * This class represent a leaf node
 * 
 * @author Broulaye Doumbia
 * @author Cheick Berthe
 *@version:10/3/2016.
 */

public class LeafNode extends Node<KVPair> {

    private LeafNode next;
    private LeafNode previous;

    /**
     *
     * @return
     */
    public LeafNode getNext() {
        return next;
    }

    /**
     *
     * @param next
     */
    public void setNext(LeafNode next) {
        this.next = next;
    }

    /**
     *
     * @return
     */
    public LeafNode getPrevious() {
        return previous;
    }

    /**
     *
     * @param previous
     */
    public void setPrevious(LeafNode previous) {
        this.previous = previous;
    }

    /**
     *
     * @param key
     */
    public LeafNode(KVPair key) {
        leftKey = key;
        rightKey = null;
        next = null;
        previous = null;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isLeaf() {
        return true;
    }

}
