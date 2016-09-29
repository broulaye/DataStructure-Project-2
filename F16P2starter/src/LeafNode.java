/**
 * This class represent a leaf node
 * @author Broulaye Doumbia
 *
 * @param <KVPair>
 */
public class LeafNode<KVPair extends Comparable<? super KVPair>> extends Node{

    //a leaf node has a left and right value
    private KVPair leftKey; //left key
    private KVPair rightKey; //right key
    
    public LeafNode() {
        leftKey = null;
        rightKey = null;
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
}
