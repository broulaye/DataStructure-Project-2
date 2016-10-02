
public class TwoThree {

    private Node<KVPair> root;
    
    public void add(KVPair value) {
        root = add(value, root);
    }
    
    
    @SuppressWarnings("unchecked")
    private Node<KVPair> add(KVPair value, Node<KVPair> root) {
        if(root == null)
            return new LeafNode<KVPair>(value, null);
        else if(root instanceof LeafNode) {
            LeafNode<KVPair> node = (LeafNode<KVPair>) root;
            if(node.isFull()) {
                return splitLeaf(node, value);
            }
            else {
                int compare = value.compareTo(node.getLeftKey());
                if(compare > 0) {
                    node.setRightKey(value);
                    return node;
                }
                else {
                    node.setRightKey(node.getLeftKey());
                    node.setLeftKey(value);
                    return node;
                }
            }
        }
        int compare = 0;
        
        //check if we are in an Internal node
        if(root instanceof IntNode) {
            
            Node<KVPair> temp =null;
            
            //root = ((IntNode<KVPair>)root);
            IntNode<KVPair> node = (IntNode<KVPair>) root;
            compare = value.compareTo(node.getLeftKey());
            // The value go the left
            if(compare < 0) {
                temp = add(value, node.getLeft());
                //root = add(value, ((IntNode<KVPair>) root).getLeft());
                if(temp instanceof LeafNode){
                    if(!((LeafNode<KVPair>)temp).isEqual((LeafNode<KVPair>)node.getLeft())){
                        return splitIntLeft(node, temp, ((LeafNode<KVPair>)node.getLeft()).getLeftKey());
                    }
                    else {
                        return node;
                    }
                }
                else if(temp instanceof IntNode){
                    if(!((IntNode<KVPair>)temp).isEqual((IntNode<KVPair>)node.getLeft())){
                        return splitIntLeft(node, temp, ((IntNode<KVPair>)node.getLeft()).getLeftKey());
                    }
                    else {
                        return node;
                    }
                }
            }
            else if(compare >= 0) {
                compare = value.compareTo(node.getRightKey());
                
                // The value go to the right
                if(compare >= 0) {
                    temp = add(value, node.getRight());
                    //root = add(value, node.getRight());
                    if(temp instanceof LeafNode){
                        if(!((LeafNode<KVPair>)temp).isEqual((LeafNode<KVPair>)node.getLeft())){
                            return splitIntRight(node, temp, ((LeafNode<KVPair>)node.getLeft()).getLeftKey());
                        }
                    }
                    else if(temp instanceof IntNode){
                        if(!((IntNode<KVPair>)temp).isEqual((IntNode<KVPair>)node.getLeft())){
                            return splitIntRight(node, temp, ((IntNode<KVPair>)node.getLeft()).getLeftKey());
                        }
                    }
                    
                    return node;
                    
                }
                // The value go in the middle
                else if(compare < 0) {
                    temp = add(value, node.getMiddle());
                    //root = add(value, node.getMiddle());
                    if(temp instanceof LeafNode){
                        if(!((LeafNode<KVPair>)temp).isEqual((LeafNode<KVPair>)node.getLeft())){
                            return splitIntMiddle(node, temp, ((LeafNode<KVPair>)node.getLeft()).getLeftKey());
                        }
                    }
                    else if(temp instanceof IntNode){
                        if(!((IntNode<KVPair>)temp).isEqual((IntNode<KVPair>)node.getLeft())){
                            return splitIntMiddle(node, temp, ((IntNode<KVPair>)node.getLeft()).getLeftKey());
                        }
                    }
                    
                    return node;
                }
            }
        } 
        
            
        return root;
    }
    
    
    @SuppressWarnings("unchecked")
    public Node<KVPair> splitLeaf(Node<KVPair> root, KVPair value) {
        LeafNode<KVPair> newLeaf = new LeafNode<KVPair>();
        newLeaf.setNext(((LeafNode<KVPair>) root).getNext());
        ((LeafNode<KVPair>) root).setNext(newLeaf);
        newLeaf.setPrevious(((LeafNode<KVPair>) root));
        IntNode<KVPair> intNode = (IntNode<KVPair>) root;
        int compare = value.compareTo(intNode.getRightKey());
        if(compare > 0) {
            newLeaf.setLeftKey(intNode.getRightKey());
            newLeaf.setRightKey(value);
            intNode.setRightKey(null);
        }
        else if(compare < 0) {
            compare = value.compareTo(intNode.getLeftKey());
            if(compare >= 0) {
                newLeaf.setLeftKey(value);
                newLeaf.setRightKey(intNode.getRightKey());
                intNode.setRightKey(null);
            }
            if(compare < 0) {
                newLeaf.setLeftKey(intNode.getLeftKey());
                newLeaf.setRightKey(intNode.getRightKey());
                intNode.setRightKey(null);
                intNode.setLeftKey(value);
            }
        }
        return newLeaf;
    }
    
    @SuppressWarnings("unchecked")
    public Node<KVPair> splitIntLeft(IntNode<KVPair> currentNode, Node<KVPair> nodeReturned, KVPair upgradedValue) {
        if (nodeReturned instanceof LeafNode) {
            LeafNode<KVPair> leaf = (LeafNode<KVPair>) nodeReturned;
            if(currentNode.getRight() == null) {
                if(currentNode.getMiddle() == null) {
                    currentNode.setMiddle(leaf);
                    currentNode.setLeftKey(leaf.getLeftKey());
                }
                else {
                    currentNode.setRight(currentNode.getMiddle());
                    currentNode.setMiddle(leaf);
                    currentNode.setRightKey(((LeafNode<KVPair>)currentNode.getRight()).getLeftKey());
                    currentNode.setLeftKey(leaf.getLeftKey());
                }
                return currentNode;
            }
            else {
                return splitInt(currentNode, upgradedValue, leaf);
            }
        }
        else {
            IntNode<KVPair> IntN = (IntNode<KVPair>) nodeReturned;
            if(currentNode.getRight() == null) {
                if(currentNode.getMiddle() == null) {
                    currentNode.setMiddle(currentNode.getLeft());
                    currentNode.setLeft(IntN);
                    currentNode.setLeftKey(((IntNode<KVPair>)currentNode.getMiddle()).getLeftKey());
                }
                else {
                    currentNode.setRight(currentNode.getMiddle());
                    currentNode.setMiddle(currentNode.getLeft());
                    currentNode.setLeft(IntN);
                    currentNode.setLeftKey(((IntNode<KVPair>)currentNode.getMiddle()).getLeftKey());
                    currentNode.setRightKey(((IntNode<KVPair>)currentNode.getRight()).getLeftKey());
                }
                return currentNode;
            }
            else {
                return splitInt(currentNode, upgradedValue, IntN);

            }
            
        }
    }
    
    
    
    
    
    @SuppressWarnings("unchecked")
    public Node<KVPair> splitIntRight(IntNode<KVPair> currentNode, Node<KVPair> nodeReturned, KVPair upgradedValue) {
        IntNode<KVPair> newNode = new IntNode<KVPair>();
        if (nodeReturned instanceof LeafNode) {
            LeafNode<KVPair> leaf = (LeafNode<KVPair>) nodeReturned;
            newNode = (IntNode<KVPair>)splitInt2(currentNode, upgradedValue, leaf);
            
            newNode.setMiddle(leaf);
        }
        else {
            IntNode<KVPair> IntN = (IntNode<KVPair>) nodeReturned;
            newNode = (IntNode<KVPair>)splitInt2(currentNode, upgradedValue, IntN);  
            
            newNode.setMiddle(IntN);
        }
        newNode.setLeft(currentNode.getRight());
        
        currentNode.setRight(currentNode.getMiddle());
        currentNode.setMiddle(currentNode.getLeft());
        currentNode.setRight(null);
        
        return newNode;
    }
    
    
    
    
    @SuppressWarnings("unchecked")
    public Node<KVPair> splitIntMiddle(IntNode<KVPair> currentNode, Node<KVPair> nodeReturned, KVPair upgradedValue) {
        IntNode<KVPair> newNode = new IntNode<KVPair>();
        if (nodeReturned instanceof LeafNode) {
            LeafNode<KVPair> leaf = (LeafNode<KVPair>) nodeReturned;
            if(currentNode.getRight() == null) {
                currentNode.setRight(leaf);
                currentNode.setRightKey(leaf.getLeftKey());
                return currentNode;
            }
            newNode = (IntNode<KVPair>)splitInt2(currentNode, upgradedValue, leaf);
            
            newNode.setMiddle(currentNode.getRight());
            newNode.setLeft(leaf);
        }
        else {
            IntNode<KVPair> IntN = (IntNode<KVPair>) nodeReturned;
            if(currentNode.getRight() == null) {
                currentNode.setRight(IntN);
                currentNode.setRightKey(IntN.getLeftKey());
                return currentNode;
            }
            newNode = (IntNode<KVPair>)splitInt2(currentNode, upgradedValue, IntN);  
            
            newNode.setMiddle(currentNode.getRight());
            newNode.setLeft(IntN);
        }
        
        currentNode.setRight(currentNode.getMiddle());
        currentNode.setMiddle(currentNode.getLeft());
        currentNode.setRight(null);
        
        return newNode;
    }
    
    
    
    @SuppressWarnings("unchecked")
    private Node<KVPair> splitInt(IntNode<KVPair> currentNode, KVPair upgradedValue, Node<KVPair> nodeReturn) {
        IntNode<KVPair> newNode = new IntNode<KVPair>();
        
        int compare = upgradedValue.compareTo(currentNode.getRightKey());
        if(compare > 0) {
            newNode.setLeftKey(currentNode.getLeftKey());
            currentNode.setLeftKey(currentNode.getRightKey());
            currentNode.setRightKey(upgradedValue);
            
            
        }
        else if(compare < 0) {
            compare = upgradedValue.compareTo(currentNode.getLeftKey());
            if(compare > 0) {
                newNode.setLeftKey(currentNode.getLeftKey());
                currentNode.setLeftKey(upgradedValue);
            }
            else if(compare < 0) {
                newNode.setLeftKey(upgradedValue);
            }
        }
        
        newNode.setMiddle(nodeReturn);
        newNode.setLeft(currentNode.getLeft());
        
        currentNode.setLeft(currentNode.getMiddle());
        currentNode.setMiddle(currentNode.getRight());
        currentNode.setRight(null);
        
        return newNode;
    }
    
    @SuppressWarnings("unchecked")
    private Node<KVPair> splitInt2(IntNode<KVPair> currentNode, KVPair upgradedValue, Node<KVPair> nodeReturn) {
        IntNode<KVPair> newNode = new IntNode<KVPair>();
        
        int compare = upgradedValue.compareTo(currentNode.getRightKey());
        if(compare > 0) {
            newNode.setRightKey(upgradedValue);
            newNode.setLeftKey(currentNode.getRightKey());
        }
        else if(compare < 0) {
            compare = upgradedValue.compareTo(currentNode.getLeftKey());
            if(compare > 0) {
                newNode.setLeftKey(upgradedValue);
                newNode.setRightKey(currentNode.getRightKey());
            }
            else if(compare < 0) {
                newNode.setLeftKey(currentNode.getLeftKey());
                newNode.setRightKey(currentNode.getRightKey());
                currentNode.setLeftKey(upgradedValue);
            }
        }
        
        currentNode.setRightKey(null);

        return newNode;
    }
    
    public String toString(Node<KVPair> root) {
        StringBuilder newString = new StringBuilder();
        if(root == null) {
            newString.append("");
            return newString.toString();
        }
        if(root instanceof LeafNode) {
            LeafNode<KVPair> node = (LeafNode<KVPair>)root;
            newString.append(node.toString());
            newString.append("->");
            while(node.getNext() != null) {
                newString.append(node.getNext().toString());
                if(node.getNext().getNext() != null) {
                    newString.append("->");
                }
            }
            return newString.toString();
        }
        else {
            IntNode<KVPair> node = (IntNode<KVPair>)root;
            return toString(node.getLeft());
        }
    }
    
}
