import org.w3c.dom.css.ElementCSSInlineStyle;

public class TwoThree {

    private Node<KVPair> root;
    
    public void add(KVPair value) {
        root = add(value, root);
    }
    
    
    private Node<KVPair> add(KVPair value, Node<KVPair> root) {
        if(root == null)
            return new LeafNode<KVPair>(value, null);
        int compare = 0;
        
        //check if we are in an Internal node
        if(root instanceof IntNode) {
            root = ((IntNode<KVPair>)root);
            compare = value.compareTo(((IntNode<KVPair>) root).getLeftKey());
            // The value go the left
            if(compare < 0) {
                root = add(value, ((IntNode<KVPair>) root).getLeft());
            }
            else if(compare > 0) {
                compare = value.compareTo(((IntNode<KVPair>) root).getRightKey());
                
                // The value go to the right
                if(compare > 0) {
                    root = add(value, ((IntNode<KVPair>) root).getRight());
                }
                // The value go in the middle
                else if(compare < 0) {
                    root = add(value, ((IntNode<KVPair>) root).getMiddle());
                }
            }
        } 
        //check if we are in a leaf node
        else if(root instanceof LeafNode) {
            
            root = ((LeafNode<KVPair>)root);
            
            // Check if both leaf value are null then just insert the value at the right
            if(((LeafNode<KVPair>) root).getRightKey() == null && ((LeafNode<KVPair>) root).getLeftKey() == null) {
                ((LeafNode<KVPair>) root).setLeftKey(value);
            }
            
            // check when the left key is not null and the right key is
            if(((LeafNode<KVPair>) root).getRightKey() == null && ((LeafNode<KVPair>) root).getLeftKey() != null) {
                compare = value.key().compareTo(((LeafNode<KVPair>) root).getLeftKey().key()); //compare value to left key
                
                // if is less than left key than move the left key to the right and move the value in its place
                if(compare < 0) {
                    ((LeafNode<KVPair>) root).setRightKey(((LeafNode<KVPair>) root).getLeftKey());
                    ((LeafNode<KVPair>) root).setLeftKey(value);
                }
                // if is greater than left key than just insert the value in the right
                else if(compare > 0) {
                    ((LeafNode<KVPair>) root).setRightKey(value);
                }
                
                //STILL DIDN'T CHECK WHEN THE VALUE ARE EQUALS THIS WHERE WE USE 
                //THE OTHER VALUE TO BREAK THE TIE OR NOT INSERT AT ALL 
            }
            else {
                // right key is not null and the left key is
                compare = value.key().compareTo(((LeafNode<KVPair>) root).getRightKey().key()); //compare value to right key
                
                // check if its less than the right key than just insert in the left
                if(compare < 0) {
                    ((LeafNode<KVPair>) root).setLeftKey(((LeafNode<KVPair>) root).getRightKey());
                }
                // otherwise when its greater move right key to the left and insert it in its place
                else if(compare > 0) {
                    ((LeafNode<KVPair>) root).setLeftKey(((LeafNode<KVPair>) root).getRightKey());
                    ((LeafNode<KVPair>) root).setRightKey(value);
                }
                
                //STILL DIDN'T CHECK WHEN THE VALUE ARE EQUALS THIS WHERE WE USE 
                //THE OTHER VALUE TO BREAK THE TIE OR NOT INSERT AT ALL
            }
        
        }
        
            
        return root;
    }
    
}
