import java.util.Arrays;

public class TwoThree {

    private Node<KVPair> root;

    public void add(KVPair key) {
        // root is null or leaf
        if (root == null){
            root = insertHelp(root, key);
            return;
        }
        else if(root.isLeaf()){
            Node<KVPair> temp = insertHelp(root, key);
            if(temp != null){
                IntNode newRoot = new IntNode(temp.getLeftKey());
                newRoot.setMiddle(((IntNode)temp).getLeft());
                newRoot.setLeft(root);
                root = newRoot;
            }
            return;
        }
        // root is internal node
        int compare = key.compareTo(root.getLeftKey());
        if(compare < 0){
            //key is lesser than left key. insert key to left node
            Node<KVPair> temp = insertHelp(((IntNode) root).getLeft(), key);
            if(temp != null){
                if(root.isFull()) {
                    // root is full and there was
                    // a split in the left subtree
                    IntNode newRoot = new IntNode(root.getLeftKey());
                    root.setLeftKey(temp.getLeftKey());
                    IntNode newNode = new IntNode(root.getRightKey());
                    root.setRightKey(null);
                    newRoot.setMiddle(newNode);
                    newRoot.setLeft(root);
                    newNode.setLeft(((IntNode) root).getMiddle());
                    ((IntNode) root).setMiddle(((IntNode)temp).getLeft());
                    newNode.setMiddle(((IntNode) root).getRight());
                    ((IntNode) root).setRight(null);
                    root = newRoot;

                }
                else{
                    // root is not full
                    // move left key and middle pointer
                    root.setRightKey(root.getLeftKey());
                    ((IntNode) root).setRight(((IntNode) root).getMiddle());
                    // perform requested promotion
                    root.setLeftKey(temp.getLeftKey());
                    ((IntNode) root).setMiddle(((IntNode)temp).getLeft());
                }
            }
            // if temp is null just return
            return;
        }
        // if key is greater than left child
        if(root.isFull()){
           // root is full, compare to right key
            compare = key.compareTo(root.getRightKey());
            if(compare >= 0){
                Node<KVPair> temp = insertHelp(((IntNode)root).getRight(), key);

                if(temp != null){
                //TODO: create a new 
                }
                // No actions required
                return;
            }
            // key is lesser than right key
            Node<KVPair> temp = insertHelp(((IntNode)root).getMiddle(), key);
            if(temp != null){
                //TODO:
            }
            return;
        }
        // root is not full
        Node<KVPair> temp = insertHelp(((IntNode)root).getMiddle(), key);
        if(temp!= null){
            //TODO:
        }
    }

    /**
     * Always splits to the right. When a split occurs in the given subtree
     * the returned node contains the key to be promoted as the left key.
     * The new node resulting from the split in the subtree is the left child
     * of the returned node
     * @param node root of subtree
     * @param key
     * @return is null if there was no split downstream
     */
    private Node<KVPair> insertHelp(Node<KVPair> node, KVPair key) {
        if(node == null){
            // this is only done on an empty tree
            return new LeafNode(key);
        }
        // occupied node
        if(!node.isLeaf()){
            // internal node with only one key
            int compare = key.compareTo(node.getLeftKey());
            if(compare < 0) {
                //key is lesser than left key
                Node<KVPair> temp = insertHelp(((IntNode) node).getLeft(), key);
                if(temp == null){
                    //do nothing
                    return null;
                }
                // temp is not null
                // temp contains promoting key as left key
                // temp left child points to new split node
                // we already have a handle to our current left child
                if(node.isFull()){
                    IntNode promoter = new IntNode(node.getLeftKey());
                    node.setLeftKey(temp.getLeftKey());
                    IntNode newNode = new IntNode(node.getRightKey());
                    node.setRightKey(null);
                    newNode.setLeft(((IntNode) node).getRight());
                    newNode.setMiddle(((IntNode) temp).getLeft());
                    ((IntNode) node).setRight(null);
                    node.setLeftKey(temp.getLeftKey());
                    promoter.setLeft(newNode);
                    // return new node to indicate split
                    return promoter;
                }
                // node is not full, and temp contains promotion key
                // move left key to right key position middle child to right child
                node.setRightKey(node.getLeftKey());
                ((IntNode) node).setRight(((IntNode) node).getMiddle());
                // consume promotion info stored in temp
                node.setLeftKey(temp.getLeftKey());
                ((IntNode) node).setMiddle(((IntNode)temp).getLeft());
                // return null to signal no need for promotion
                return null;
            }
            // key is greater than left key so check if tree is full
            if(node.isFull()){
                //compare to right key
                compare = key.compareTo(node.getRightKey());
                if(compare >= 0){// key is greater than right key
                    // insert in right child then check state of temp
                    Node<KVPair> temp = insertHelp(((IntNode)node).getRight(), key);
                    if(temp == null){
                        // nothing to do
                        return null;
                    }
                    // promotion request in temp and node is full
                    IntNode promoter = new IntNode(node.getRightKey());
                    node.setRightKey(null);
                    IntNode newNode = new IntNode(temp.getLeftKey());
                    promoter.setLeft(newNode);
                    newNode.setLeft(((IntNode) node).getRight());
                    ((IntNode) node).setRight(null);
                    newNode.setMiddle(((IntNode)temp).getLeft());
                    return promoter;
                }
                // key is lesser than right key so insert in middle child
                Node<KVPair> temp = insertHelp(((IntNode)node).getMiddle(), key);
                if(temp == null){
                    return null;
                }
                IntNode newNode = new IntNode(node.getRightKey());
                node.setRightKey(null);
                IntNode promoter = new IntNode(temp.getLeftKey());
                newNode.setMiddle(((IntNode) node).getRight());
                ((IntNode) node).setRight(null);
                promoter.setLeft(newNode);
                newNode.setLeft(((IntNode)temp).getLeft());
                return promoter;
            }

        }
        // non-empty leaf node
        if(node.isFull()) {
            // leaf is full, split then return new root
            KVPair pairs[] = {node.getLeftKey(), node.getRightKey(), key};
            //sort elements
            Arrays.sort(pairs);
            node.setLeftKey(pairs[0]);
            node.setRightKey(null);
            // create new leaf node and insert to the right
            LeafNode rightNeighbor = new LeafNode(pairs[1]);
            rightNeighbor.setRightKey(pairs[2]);
            rightNeighbor.setNext(((LeafNode) node).getNext());
            rightNeighbor.setPrevious((LeafNode) node);
            // check if node is not tail of linked list
            if(((LeafNode) node).getNext() != null){
                ((LeafNode) node).getNext().setPrevious(rightNeighbor);
            }
            ((LeafNode) node).setNext(rightNeighbor);
            // create new parent as internal node then promote median value
            IntNode promoter = new IntNode(pairs[1]);
            promoter.setLeft(rightNeighbor);
            return promoter;
        }
        // leaf is not full
        int compare = key.compareTo(node.getLeftKey());
        if(compare < 0) {
            node.setRightKey(node.getLeftKey());
            node.setLeftKey(key);
            return null;
        }
        // key is greater than left
        node.setRightKey(key);
        return null;
    }


    public String printLeaves() {
        return toString(root);
    }

    private String toString(Node<KVPair> root) {
        StringBuilder newString = new StringBuilder();
        if (root == null) {
            newString.append("");
            return newString.toString();
        }
        if (root instanceof LeafNode) {
            LeafNode node = (LeafNode) root;
            newString.append(node.toString());
            newString.append("->");
            while (node.getNext() != null) {
                newString.append(node.getNext().toString());
                if (node.getNext().getNext() != null) {
                    newString.append("->");
                }
            }
            return newString.toString();
        }
        else {
            IntNode node = (IntNode) root;
            return toString(node.getLeft());
        }
    }

    public String toString() {
        if (root != null) {
            return root.toString();
        }
        return "(Empty)";
    }

    public String print() {
        if (root != null) {
            return "Printing 2-3 tree:\n" + print(root, 0) + '\n';
        }
        return "";
    }

    private String print(Node<KVPair> node, int depth) {
        StringBuilder builder = new StringBuilder();
        if (node == null) {
            return builder.toString();
        }
        for (int it = 0; it < depth; it++) {
            builder.append("  ");
        }

        builder.append(node.toString());
        builder.append('\n');
        // if root is an internal node
        if (node instanceof IntNode) {
            // print the two KVPair followed by a newline

            // print subtrees in preorder
            depth++;
            builder.append(print(((IntNode) node).getLeft(), depth));
            builder.append(print(((IntNode) node).getMiddle(), depth));
            builder.append(print(((IntNode) node).getRight(), depth));
        }

        return builder.toString();
    }

}
