/**
 * This class represent a leaf node
 * @author Broulaye Doumbia
 *
 * @param <KVPair>
 */
public class LeafNode<KVPair extends Comparable<? super KVPair>> extends Node<KVPair>{

    //a leaf node has a left and right value
    private KVPair leftKey; //left key
    private KVPair rightKey; //right key
    private LeafNode<KVPair> next;
    private LeafNode<KVPair> previous;
    
    
    
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
        return leftKey == null && rightKey == null;
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



    public boolean isLeaf() {
        return true;
    }
    
    public boolean isEqual(LeafNode<KVPair> newNode) {
        return newNode.leftKey.compareTo(this.leftKey) == 0 && newNode.rightKey.compareTo(this.rightKey) == 0; 
    }
    
    public String toString() {
        if(leftKey == null && rightKey == null) {
            return "";
        }
        else if(leftKey != null && rightKey == null) {
            return leftKey.toString();
        }
        else if(leftKey == null && rightKey != null) {
            return "Illegal State";
        }
        else {
            return leftKey.toString() + "->" + rightKey.toString();
        }
    }
}
