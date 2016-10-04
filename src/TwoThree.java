
public class TwoThree {

    private Node<KVPair> root;
    
    public void add(KVPair value) {

        Node<KVPair> temp1 = add(root, value);
        // check if tree was initially empty
        if (root == null) {
            root = temp1;
            return;
        }



        /**
         * check type of returned node
         * if returned node is a leaf node
         * a split happened else the root was not splt
         */
        if(temp1 instanceof IntNode) {
            IntNode<KVPair> temp2 = (IntNode<KVPair>) temp1;
            // check if a split happened downstream
            if(!temp2.isEqual(root)) {
                IntNode<KVPair> newNode = new IntNode<>();
                if(temp2.isFull()) { //right split
                    newNode.setMiddle(temp2);
                    // copy root
                    newNode.setLeft(new IntNode<KVPair>((IntNode<KVPair>) root));
                }
                else{   // left split
                    newNode.setMiddle(new IntNode<KVPair>((IntNode<KVPair>) root));
                    newNode.setLeft(temp2);
                }
                // reset root pointer
                root = newNode;
            }
        }
        else {
            LeafNode<KVPair> temp2 = (LeafNode<KVPair>) temp1;
            if(!temp2.isEqual(root)) {
                IntNode<KVPair> newNode;
                if(temp2.isFull()) { //right split
                    newNode = new IntNode<KVPair>(temp2.getLeftKey(), null);
                    newNode.setMiddle(temp2);
                    // copy root
                    newNode.setLeft(new LeafNode<KVPair>((LeafNode<KVPair>) root));
                    //newNode.setLeftKey(temp2.getLeftKey());
                }
                else{   // left split
                    newNode = new IntNode<>(((LeafNode<KVPair>) root).getLeftKey(), null);
                    newNode.setMiddle(new LeafNode<KVPair>((LeafNode<KVPair>) root));
                    newNode.setLeft(temp2);
                    newNode.setLeftKey(((LeafNode<KVPair>) root).getLeftKey());
                }
                // reset root pointer
                root = newNode;
            }

        }

    }
    
    
    @SuppressWarnings("unchecked")
    private Node<KVPair> add(Node<KVPair> root, KVPair value) {
        // base case
        if(root == null) {
            return new LeafNode<KVPair>(value, null);
        }// check if it's node
        else if(root instanceof LeafNode) {
            LeafNode<KVPair> node = (LeafNode<KVPair>) root;
            if(node.isFull()) {
                return splitLeaf(node, value);
            }
            else {
                int compare = value.compareTo(node.getLeftKey());
                if(compare > 0) {
                    node.setRightKey(value);
                }
                else {
                    node.setRightKey(node.getLeftKey());
                    node.setLeftKey(value);
                }
                return node;
            }
        }
        
        //check if we are in an Internal node
        if(root instanceof IntNode) {
            
            Node<KVPair> temp = null;
            
            //root = ((IntNode<KVPair>)root);
            IntNode<KVPair> node = (IntNode<KVPair>) root;
            int compare = value.compareTo(node.getLeftKey());

            if(compare < 0) {
                // The value goes left
                temp = add(node.getLeft(), value);
                //root = add(value, ((IntNode<KVPair>) root).getLeft());

                    // if the leaf child has been split, perform a split
                    if(!temp.isEqual(node.getLeft())){
                        return splitIntLeft(node, temp);
                    }
                    else {
                        // else return as is
                        return node;
                    }
            }
            else if(compare >= 0) {
                if(!node.isFull()){
                    temp = add(node.getMiddle(), value);
                    if(!temp.isEqual(node.getMiddle())){
                        return splitIntMiddle(node, temp);
                    }
                }
                compare = value.compareTo(node.getRightKey());
                
                // The value goes to the right
                if(compare >= 0) {
                    temp = add(node.getRight(), value);
                    //root = add(value, node.getRight());
                        if(!temp.isEqual(node.getRight())){
                            return splitIntRight(node, temp);
                        }
                }
                // The value go in the middle
                else if(compare < 0) {
                    temp = add(node.getMiddle(), value);
                    //root = add(value, node.getMiddle());
                    if(!temp.isEqual(node.getMiddle())) {
                        return splitIntMiddle(node, temp);
                    }
                }
                return node;
            }
        } 
        
            
        return root;
    }
    
    
    @SuppressWarnings("unchecked")
    private Node<KVPair> splitLeaf(Node<KVPair> root, KVPair value) {
        // create new leaf
        LeafNode<KVPair> newLeaf = new LeafNode<KVPair>();
        newLeaf.setNext(((LeafNode<KVPair>) root).getNext());
        ((LeafNode<KVPair>) root).setNext(newLeaf);
        newLeaf.setPrevious(((LeafNode<KVPair>) root));
        if(newLeaf.getNext() != null) {
            newLeaf.getNext().setPrevious(newLeaf);
        }


        LeafNode<KVPair> rootNode = (LeafNode<KVPair>) root;

        int compare = value.compareTo(rootNode.getRightKey());
        if(compare > 0) {
            newLeaf.setLeftKey(rootNode.getRightKey());
            newLeaf.setRightKey(value);
            rootNode.setRightKey(null);
        }
        else if(compare < 0) {
            compare = value.compareTo(rootNode.getLeftKey());
            if(compare >= 0) {
                newLeaf.setLeftKey(value);
                newLeaf.setRightKey(rootNode.getRightKey());
                rootNode.setRightKey(null);
            }
            if(compare < 0) {
                newLeaf.setLeftKey(rootNode.getLeftKey());
                newLeaf.setRightKey(rootNode.getRightKey());
                rootNode.setRightKey(null);
                rootNode.setLeftKey(value);
            }
        }
        return newLeaf;
    }
    
    @SuppressWarnings("unchecked")
    private Node<KVPair> splitIntLeft(IntNode<KVPair> currentNode, Node<KVPair> nodeReturned) {
        KVPair upgradedValue;
            if (nodeReturned instanceof LeafNode) {
                upgradedValue = ((LeafNode<KVPair>)nodeReturned).getLeftKey();
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
                upgradedValue = ((IntNode<KVPair>)currentNode.getLeft()).getLeftKey();
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
    private Node<KVPair> splitIntRight(IntNode<KVPair> currentNode, Node<KVPair> nodeReturned) {
        KVPair upgradedValue;
        IntNode<KVPair> newNode;
        if (nodeReturned instanceof LeafNode) {
            LeafNode<KVPair> leaf = (LeafNode<KVPair>) nodeReturned;
            upgradedValue = leaf.getLeftKey();
            newNode = (IntNode<KVPair>)splitInt2(currentNode, upgradedValue);
            
            newNode.setMiddle(leaf);
        }
        else {
            IntNode<KVPair> IntN = (IntNode<KVPair>) nodeReturned;
            upgradedValue = IntN.getLeftKey();
            newNode = (IntNode<KVPair>)splitInt2(currentNode, upgradedValue);
            
            newNode.setMiddle(IntN);
        }
        newNode.setLeft(currentNode.getRight());
        
        currentNode.setRight(currentNode.getMiddle());
        currentNode.setMiddle(currentNode.getLeft());
        currentNode.setRight(null);
        
        return newNode;
    }
    
    
    
    
    @SuppressWarnings("unchecked")
    private Node<KVPair> splitIntMiddle(IntNode<KVPair> currentNode, Node<KVPair> nodeReturned) {
        IntNode<KVPair> newNode;
        KVPair upgradedValue;
        if (nodeReturned instanceof LeafNode) {
            LeafNode<KVPair> leaf = (LeafNode<KVPair>) nodeReturned;
            upgradedValue = leaf.getLeftKey();
            if(currentNode.getRight() == null) {
                currentNode.setRight(leaf);
                currentNode.setRightKey(leaf.getLeftKey());
                return currentNode;
            }
            newNode = (IntNode<KVPair>)splitInt2(currentNode, upgradedValue);
            
            newNode.setMiddle(currentNode.getRight());
            newNode.setLeft(leaf);
        }
        else {
            IntNode<KVPair> IntN = (IntNode<KVPair>) nodeReturned;
            upgradedValue =IntN.getLeftKey();
            if(currentNode.getRight() == null) {
                currentNode.setRight(IntN);
                currentNode.setRightKey(IntN.getLeftKey());
                return currentNode;
            }
            newNode = (IntNode<KVPair>)splitInt2(currentNode, upgradedValue);
            
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
    private Node<KVPair> splitInt2(IntNode<KVPair> currentNode, KVPair upgradedValue) {
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
    
    public String printLeaves() {
        return toString(root);
    }
    
    private String toString(Node<KVPair> root) {
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
    public String toString(){
        if(root != null){
            return root.toString();
        }
        return "(Empty)";
    }

    public String print() {
        if (root != null){
            return "Printing 2-3 tree:\n" + print(root, 0) + '\n';
        }
        return "";
    }

    private String print(Node<KVPair> node, int depth) {
        StringBuilder builder = new StringBuilder();
        if (node == null) {
            return builder.toString();
        }
        for (int it = 0; it < depth; it++){
            builder.append("  ");
        }

        builder.append(node.toString());
        // if root is an internal node
        if (node instanceof IntNode){
            // print the two KVPair followed by a newline
            builder.append('\n');
            // print subtress in preorder
            depth++;
            builder.append(print(((IntNode) node).getLeft(), depth));
            builder.append(print(((IntNode) node).getMiddle(), depth));
            builder.append(print(((IntNode) node).getRight(), depth));
        }

        return builder.toString();
    }

}
