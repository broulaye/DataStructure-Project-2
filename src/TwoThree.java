import org.omg.CORBA.OBJECT_NOT_EXIST;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TwoThree {

    private Node<KVPair> root;


    public KVPair find(Handle value) {
        return findHelper(root, value);
    }

    private LeafNode findLeaf(Node<KVPair> root, Handle value) {
        if(root == null) {
            return null;
        }
        if(root instanceof LeafNode) {
            int compare = value.compareTo(root.leftKey.key());
            if(compare == 0) {
                return (LeafNode)root;
            }
            else {
                if(root.rightKey != null) {
                    compare = value.compareTo(root.rightKey.key());
                    if(compare == 0) {
                        return (LeafNode)root;
                    }
                }
                return null;

            }
        }
        else {
            int compare = value.compareTo(root.leftKey.key());
            if(compare < 0) {
                return findLeaf(((IntNode)root).getLeft(), value);
            }
            else {
                if(root.rightKey != null) {
                    compare = value.compareTo(root.rightKey.key());
                    if(compare > 0) {
                        return findLeaf(((IntNode)root).getRight(), value);
                    }
                }
                return findLeaf(((IntNode)root).getMiddle(), value);
            }
        }
    }

    public List<Handle> get(Handle value) {
        List<Handle> handleList = new LinkedList<>();
        LeafNode node = findLeaf(root, value);
        if(node != null) {
            if(node.leftKey.key().compareTo(value) == 0) {
                handleList.add(node.leftKey.value());
                //writer.print(node.leftKey.value());
            }
            else {
                handleList.add(node.rightKey.value());
            }
        }

        while(node != null){
            node = node.getNext();
            if(node.leftKey.key().compareTo(value) == 0) {
                handleList.add(node.leftKey.value());
            }
            if(node.rightKey != null){
                if(node.rightKey.key().compareTo(value) == 0) {
                    handleList.add(node.rightKey.value());
                }
                else {
                    break;
                }
            }
            else {
                break;
            }

        }
        return handleList;
    }

    private KVPair findHelper(Node<KVPair> root, Handle value) {
        if(root == null) {
            return null;
        }
        if(root instanceof LeafNode) {
            int compare = value.compareTo(root.leftKey.key());
            if(compare == 0) {
                return root.leftKey;
            }
            else {
                if(root.rightKey != null) {
                    compare = value.compareTo(root.rightKey.key());
                    if(compare == 0) {
                        return root.rightKey;
                    }
                }
                return null;

            }
        }
        else {
            int compare = value.compareTo(root.leftKey.key());
            if(compare < 0) {
                return findHelper(((IntNode)root).getLeft(), value);
            }
            else {
                if(root.rightKey != null) {
                    compare = value.compareTo(root.rightKey.key());
                    if(compare >= 0) {
                        return findHelper(((IntNode)root).getRight(), value);
                    }
                }
                return findHelper(((IntNode)root).getMiddle(), value);
            }
        }
    }
    public boolean add(KVPair key) {
        try {
            // root is null or leaf
            if (root == null) {
                root = insertHelp(root, key);
                return true;
            } else if (root instanceof LeafNode) {
                Node<KVPair> temp = insertHelp(root, key);
                if (temp != null) {
                    IntNode newRoot = new IntNode(temp.getLeftKey());
                    newRoot.setMiddle(((IntNode) temp).getLeft());
                    newRoot.setLeft(root);
                    root = newRoot;
                }
                return true;
            }
            // root is internal node
            int compare = key.compareTo(root.getLeftKey());
            if (compare == 0) {
                return false;
            }
            if (compare < 0) {
                //key is lesser than left key. insert key to left node
                Node<KVPair> temp = insertHelp(((IntNode) root).getLeft(), key);
                if (temp != null) {
                    if (root.isFull()) {
                        // root is full and there was
                        // a split in the left subtree
                        IntNode newRoot = new IntNode(root.getLeftKey());
                        root.setLeftKey(temp.getLeftKey());
                        IntNode newNode = new IntNode(root.getRightKey());
                        root.setRightKey(null);
                        newRoot.setMiddle(newNode);
                        newRoot.setLeft(root);
                        newNode.setLeft(((IntNode) root).getMiddle());
                        ((IntNode) root).setMiddle(((IntNode) temp).getLeft());
                        newNode.setMiddle(((IntNode) root).getRight());
                        ((IntNode) root).setRight(null);
                        root = newRoot;

                    } else {
                        // root is not full
                        // move left key and middle pointer
                        root.setRightKey(root.getLeftKey());
                        ((IntNode) root).setRight(((IntNode) root).getMiddle());
                        // perform requested promotion
                        root.setLeftKey(temp.getLeftKey());
                        ((IntNode) root).setMiddle(((IntNode) temp).getLeft());
                    }
                }
                // if temp is null return true
                return true;
            }
            // if key is greater than left child
            if (root.isFull()) {
                // root is full, compare to right key
                compare = key.compareTo(root.getRightKey());
                if(compare == 0){
                    return false;
                }
                if (compare > 0) {
                    Node<KVPair> temp = insertHelp(((IntNode) root).getRight(), key);

                    if (temp != null) {
                        IntNode newNode = new IntNode(temp.getLeftKey());
                        newNode.setMiddle(((IntNode) temp).getLeft());
                        newNode.setLeft(((IntNode) root).getRight());
                        ((IntNode) root).setRight(null);
                        IntNode newRoot = new IntNode(root.getRightKey());
                        newRoot.setLeft(root);
                        newRoot.setMiddle(newNode);
                        root.setRightKey(null);
                        root = newRoot;
                    }
                    // No actions required
                    return true;
                }
                // key is lesser than right key
                Node<KVPair> temp = insertHelp(((IntNode) root).getMiddle(), key);
                if (temp != null) {
                    //split
                    IntNode newNode = new IntNode(root.rightKey);
                    root.rightKey = null;
                    newNode.setLeft(((IntNode) root).getRight());
                    ((IntNode) root).setRight(null);
                    IntNode newRoot = new IntNode(temp.getLeftKey());
                    newRoot.setLeft(root);
                    newNode.setMiddle(((IntNode) temp).getLeft());
                    newRoot.setMiddle(newNode);
                    root = newRoot;
                }
                return true;
            }
            // root is not full
            Node<KVPair> temp = insertHelp(((IntNode) root).getMiddle(), key);
            if (temp != null) {
                root.setRightKey(temp.getLeftKey());
                ((IntNode) root).setRight(((IntNode) temp).getLeft());
            }
        }
        catch (Exception e){
            return false;
        }
        return true;
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
    private Node<KVPair> insertHelp(Node<KVPair> node, KVPair key) throws Exception {
        if(node == null){
            // this is only done on an empty tree
            return new LeafNode(key);
        }
        // occupied node
        if(!node.isLeaf()){
            // internal node with only one key
            int compare = key.compareTo(node.getLeftKey());
            if(compare == 0){
                throw new Exception("duplicate");
            }
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
            // key is greater than left key so check if node is full
            if(node.isFull()){
                //compare to right key
                compare = key.compareTo(node.getRightKey());
                if(compare == 0){
                    throw new Exception("duplicate");
                }
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
            //internal node is not full
            Node<KVPair> temp = insertHelp(((IntNode)node).getMiddle(), key);
            if(temp == null){
                return temp;
            }
            //temp is not null
            node.setRightKey(temp.getLeftKey());
            ((IntNode) node).setRight(((IntNode)temp).getLeft());
            return null;
        }
        //leaf node
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
        if(compare == 0){
            throw new Exception("duplicate");
        }
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

    /**
    public String toString() {
        if (root != null) {
            return root.toString();
        }
        return "(Empty)";
    }*/

    public String print() {
        return "Printing 2-3 tree:\n" + print(root, 0);

    }

    private String print(Node<KVPair> node, int depth) {
        StringBuilder builder = new StringBuilder();
        if (node == null) {
            return builder.toString();
        }
        for (int it = 0; it < depth; it++) {
            builder.append("  ");
        }


        // if root is an internal node
        if (node instanceof IntNode) {
            // print the two KVPair followed by a newline
            builder.append(node.toString());
            builder.append('\n');
            // print subtrees in preorder
            depth++;
            builder.append(print(((IntNode) node).getLeft(), depth));
            builder.append(print(((IntNode) node).getMiddle(), depth));
            builder.append(print(((IntNode) node).getRight(), depth));
        }
        else {
            builder.append(node.toString());
            builder.append('\n');
        }

        return builder.toString();
    }

    public boolean remove(KVPair value) {
        if(root == null) {
            return false;
        }
        if(root instanceof LeafNode) {//root is an leaf node
            if(value.key().compareTo(root.leftKey.key()) == 0) {// value is equal to left key
                root.leftKey = null;//delete left key
                if(root.isEmpty()) {//check if root is empty
                    root = null;//if root is empty set it to null and return true
                    return true;
                }
                root.leftKey = root.rightKey;
                return true;
            }
            else {// value is not equal to the left key
                if(root.rightKey == null) {//check if a right key exist
                    return false;//right value doesn't exist so value not in tree
                }
                else if(value.key().compareTo(root.rightKey.key()) == 0) {//right value exist and is equal to value
                    root.rightKey = null;//delete right key
                    return true;//return true
                }
                else {//right value exist but not equal to value
                    return false;
                }
            }
        }
        else {//root is an internal node
            try {
                KVPair promotedValue = null;
                deleteHelp(root, value, promotedValue);
                if (root.isEmpty()) {
                    root = ((IntNode) root).getLeft();
                }
                else {
                    if (value.compareTo(root.leftKey) == 0) {
                        if(((IntNode) root).getMiddle() instanceof LeafNode) {
                            root.leftKey = ((IntNode) root).getMiddle().leftKey;// get rid of the key for the value that've been deleted
                        }
                        else {
                            KVPair promoted = findPromoted(((IntNode) root).getMiddle(), value);
                            root.leftKey = promoted;
                        }

                    }
                }
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }

    private Node<KVPair> deleteHelp(Node<KVPair> root, KVPair value, KVPair promotedValue) {
        if (root == null) {
            throw new OBJECT_NOT_EXIST("This value does not exist in the tree"); //Object is not present in the tree
        }
        if (root instanceof LeafNode) {
            int compare = value.compareTo(root.leftKey);// compare value to leftVal

            if (compare > 0) {// check if value greater than leftVal
                if (root.rightKey == null) {//if the right value is null than object doesn't exist
                    throw new OBJECT_NOT_EXIST("This value does not exist in the tree"); //Object is not present in the tree
                }
                compare = value.compareTo(root.rightKey);//else compare value to right value

                if (compare == 0) {//right value need to be deleted
                    root.rightKey = null;
                    return root;
                } else {//if not equal to the right value object not present return null
                    throw new OBJECT_NOT_EXIST("This value does not exist in the tree"); //Object is not present in the tree
                }

            } else if (compare == 0) {//left value need to be deleted
                root.leftKey = null;
                root.leftKey = root.rightKey; //set left value to right value
                promotedValue = root.rightKey;
                root.rightKey = null; //set right value to null
                if(root.isEmpty()) {
                    LeafNode rootNext = null;
                    LeafNode rootPrevious = null;
                    if(((LeafNode) root).getNext() != null) {
                        rootNext = ((LeafNode) root).getNext();
                        if(((LeafNode) root).getPrevious() != null) {
                            rootPrevious = ((LeafNode) root).getPrevious();
                        }
                        rootNext.setPrevious(rootPrevious);
                    }
                    if(((LeafNode) root).getPrevious() != null) {
                        rootPrevious = ((LeafNode) root).getPrevious();
                        if(((LeafNode) root).getNext() != null) {
                            rootNext = ((LeafNode) root).getNext();
                        }
                        rootPrevious.setNext(rootNext);
                    }

                }
                return root;
            }
            else {
                throw new OBJECT_NOT_EXIST("This value does not exist in the tree"); //Object is not present in the tree
            }


        }

        if (root instanceof IntNode) {
            int compare = value.compareTo(root.leftKey);// compare value to leftVal
            if (compare < 0) {// check if value less than leftVal if so delete from left
                return deleteLeft(root, value, promotedValue);
            }
            else {// Value is greater or equal to left value
                if (root.rightKey == null) {//greater than left and right value not represent delete from middle
                    return deleteMiddle(root, value, promotedValue);
                }
                compare = value.compareTo(root.rightKey);//else compare value to right value
                if (compare < 0) {//value less than right node so delete from the Middle
                    return deleteMiddle(root, value, promotedValue);
                } else {//delete from right
                    deleteRight(root, value, promotedValue);
                    if(((IntNode)root).getRight() == null) {
                        root.rightKey = null;
                    }
                    return root;
                }
            }


        }

        return null;
    }



    private Node<KVPair> deleteMiddle(Node<KVPair> root, KVPair value, KVPair promotedValue) {
        ((IntNode) root).setMiddle(deleteHelp(((IntNode) root).getMiddle(), value, promotedValue));//delete from the middle of the root
        if (value.compareTo(root.leftKey) == 0) {
            if(((IntNode) root).getMiddle() instanceof LeafNode && !((IntNode) root).getMiddle().isEmpty()) {
                root.leftKey = ((IntNode) root).getMiddle().leftKey;// get rid of the key for the value that've been deleted
            }
            else {
                KVPair promoted = findPromoted(((IntNode) root).getMiddle(), value);
                root.leftKey = promoted;
            }

        }
        if (((IntNode) root).getMiddle() instanceof LeafNode) {//check if the root new middle value is a leaf
            LeafNode middleNode = ((LeafNode) (((IntNode) root).getMiddle()));
            if ((((IntNode) root).getMiddle()).isEmpty()) {//the middle node was deleted. ASSUMPTION: root left is a leaf

                if ((((IntNode) root).getLeft()).isFull()) {//if the left is full borrow a value from it
                    middleNode.leftKey = (((IntNode) root).getLeft()).rightKey;//middle node new left value will be the left node right value
                    (((IntNode) root).getLeft()).rightKey = null;//left node right key will be null
                    root.leftKey = (((IntNode) root).getMiddle()).leftKey;//set the root new left value
                    return root;
                } else if (((IntNode) root).getRight() != null && ((IntNode) root).getRight().isFull()) {//borrow from right node
                    middleNode.leftKey = (((IntNode) root).getRight()).leftKey;//middle node new left value will be the right node left value
                    (((IntNode) root).getRight()).leftKey = null;//right node left key will be null
                    ((IntNode) root).getRight().leftKey = ((IntNode) root).getRight().rightKey;
                    root.leftKey = (((IntNode) root).getMiddle()).leftKey;//set the root new left value
                    return root;
                } else { // left node has only one key and can't borrow from right

                    if (((IntNode) root).getRight() == null) {//means the tree have only one child
                        root = ((IntNode) root).getLeft();// set root to the only child left
                        return root;// return the root
                    } else {//root have a right child

                        ((IntNode) root).setMiddle(((IntNode) root).getRight());//right child is the new middle child
                        ((IntNode) root).setRight(null);//get rid of the right pointer
                        root.rightKey = null;//no more right child so delete right value of node
                        root.leftKey = ((IntNode) root).getMiddle().leftKey;// the new leftVal of the root will be the middle node leftVal
                        return root;
                    }
                }
            } else {//root middle child is not empty and it's a leaf
                if (((IntNode) root).getLeft() instanceof IntNode) {// this means the tree is unbalanced after the remove so fix it
                    IntNode leftNode = (IntNode) (((IntNode) root).getLeft());


                    if (leftNode.getRight() != null) {//left node has three children borrow one
                        IntNode newNode = new IntNode();//represent the new internal after fixing the unbalanced
                        //new node left key is the middle node left key
                        newNode.leftKey = middleNode.leftKey;
                        //new node is pointing to the root left node right child
                        newNode.setLeft(leftNode.getRight());
                        //new node middle child is the root middle node
                        newNode.setMiddle(middleNode);
                        //root new middle child is the new node
                        ((IntNode) root).setMiddle(newNode);
                        //root new left key is the left node right key
                        root.leftKey = leftNode.rightKey;
                        //left node right key is set to null
                        leftNode.rightKey = null;
                        //the right pointer get removed
                        leftNode.setRight(null);
                        //TODO: set root right key to null maybe
                        return root;
                    }
                    else if(((IntNode) root).getRight() != null && ((IntNode) root).getRight().isFull()){
                        IntNode rightNode = (IntNode) (((IntNode) root).getRight());
                        //root have a right child and it has three children, borrow one
                        IntNode newNode = new IntNode();//represent the new internal after fixing the unbalanced
                        //new node is pointing to the root middle node
                        newNode.setLeft(middleNode);
                        //new node left key is the right node left child left key
                        newNode.leftKey = root.rightKey;
                        //root new middle child is the new node
                        ((IntNode) root).setMiddle(newNode);
                        //root middle child is the middle node left child
                        ((IntNode)((IntNode) root).getMiddle()).setMiddle(rightNode.getLeft());
                        //root new right key is the right node left key
                        root.rightKey = rightNode.leftKey;
                        //right node left key get replace by its right key
                        rightNode.leftKey = rightNode.rightKey;
                        rightNode.rightKey = null;
                        //right node new left becomes it's middle
                        rightNode.setLeft(rightNode.getMiddle());
                        //right right child becomes its middle child
                        rightNode.setMiddle(rightNode.getRight());
                        //the right pointer get removed
                        rightNode.setRight(null);
                        return root;

                    }
                    else {//left node has only two children and you can't borrow from right merge left
                        leftNode.rightKey = middleNode.leftKey;
                        leftNode.setRight(middleNode);
                        root.leftKey = root.rightKey;
                        root.rightKey = null;
                        ((IntNode) root).setMiddle(((IntNode) root).getRight());
                        ((IntNode) root).setRight(null);
                        return root;

                    }
                } else {// no change need to be made on the internal node
                    return root;
                }
            }

        }
        else {//root new middle node is an IntNode
            if(((IntNode) root).getMiddle().isEmpty()) {//there was merge at the bottom
                IntNode middleNode = (IntNode) ((IntNode) root).getMiddle();
                IntNode leftNode = (IntNode) ((IntNode) root).getLeft();
                if(leftNode.isFull()) {//left node got three children borrow one
                    middleNode.leftKey = root.leftKey;
                    root.leftKey = leftNode.rightKey;
                    leftNode.rightKey = null;
                    middleNode.setMiddle(middleNode.getLeft());
                    middleNode.setLeft(leftNode.getRight());
                    leftNode.setRight(null);
                    return root;
                }
                else if(((IntNode) root).getRight() != null && ((IntNode) root).getRight().isFull()){//borrow from right node
                    IntNode rightNode = (IntNode) ((IntNode) root).getRight();
                    middleNode.leftKey = root.rightKey;
                    root.rightKey = rightNode.leftKey;
                    rightNode.leftKey = rightNode.rightKey;
                    rightNode.rightKey = null;
                    middleNode.setMiddle(rightNode.getLeft());
                    rightNode.setLeft(rightNode.getMiddle());
                    rightNode.setMiddle(rightNode.getRight());
                    rightNode.setRight(null);
                    return root;
                }
                else {//left node has only two children,and can't borrow from right. merge
                    leftNode.setRight(middleNode.getLeft());
                    leftNode.rightKey = root.leftKey;
                    root.leftKey = root.rightKey;
                    root.rightKey = null;
                    ((IntNode) root).setMiddle(((IntNode) root).getRight());
                    ((IntNode) root).setRight(null);
                    return root;
                }

            }
            return root;

        }
    }

    private KVPair findPromoted(Node<KVPair> root, KVPair value) {
        if(root instanceof LeafNode) {
            return root.leftKey;
        }
        else {
            if(root.isEmpty()) {
                return findPromoted(((IntNode) root).getLeft(), value);
            }
            int compare = value.compareTo(root.leftKey);
            if(compare < 0) {
                return findPromoted(((IntNode) root).getLeft(), value);
            }
            else if(root.rightKey != null) {
                compare = value.compareTo(root.rightKey);
                if(compare < 0) {
                    return findPromoted(((IntNode) root).getMiddle(), value);
                }
                else {
                    return findPromoted(((IntNode) root).getRight(), value);
                }
            }
            return findPromoted(((IntNode) root).getMiddle(), value);
        }
    }


    private Node<KVPair> deleteRight(Node<KVPair> root, KVPair value, KVPair promotedValue) {
        ((IntNode) root).setRight(deleteHelp(((IntNode) root).getRight(), value, promotedValue));//delete from the right of the root

        if (value.compareTo(root.rightKey) == 0) {
            if(((IntNode) root).getRight() instanceof LeafNode && !((IntNode) root).getRight().isEmpty()) {
                root.rightKey = ((IntNode) root).getRight().leftKey;// get rid of the key for the value that've been deleted
            }
            else {
                KVPair promoted = findPromoted(((IntNode) root).getRight(), value);
                root.rightKey = promoted;
            }

        }
        if (((IntNode) root).getRight() instanceof LeafNode) {//check if the root new right value is a leaf
            LeafNode rightNode = ((LeafNode) (((IntNode) root).getRight()));
            if (rightNode.isEmpty()) {//the right node was deleted. ASSUMPTION: root middle is a leaf

                if ((((IntNode) root).getMiddle()).isFull()) {//if the middle is full borrow a value from it
                    rightNode.leftKey = (((IntNode) root).getMiddle()).rightKey;//right node new left value will be the middle node right value
                    root.rightKey = rightNode.leftKey;//set the root new left value
                    (((IntNode) root).getMiddle()).rightKey = null;
                    return root;
                } else { // middle node has only one child
                    root.rightKey = null;// set right key off root to null since no more right node exist
                    ((IntNode) root).setRight(null);//get rid of the right node
                    return root;
                }
            } else {//root right child is not empty and it's a leaf
                if (((IntNode) root).getMiddle() instanceof IntNode) {// this means the tree is unbalanced after the remove so fix it
                    IntNode middleNode = (IntNode)(((IntNode) root).getMiddle());


                    if(middleNode.getRight() != null) {//middle node has three children borrow one
                        IntNode newNode = new IntNode();//represent the new internal after fixing the unbalanced
                        //new node left key is the right node left key
                        newNode.leftKey = rightNode.leftKey;
                        //new node is pointing to the middle node right child
                        newNode.setLeft(middleNode.getRight());
                        //new node middle child is the root right child
                        newNode.setMiddle(rightNode);
                        //root new right child is the new node
                        ((IntNode) root).setRight(newNode);
                        //root new right key is the new node left key
                        root.rightKey = newNode.leftKey;
                        //middle node right key get set to null
                        middleNode.rightKey = null;
                        //middle node right pointer get removed
                        middleNode.setRight(null);
                        return root;
                    }
                    else {//middle node has only two children. merge left
                        middleNode.rightKey = root.rightKey;
                        middleNode.setRight(rightNode);
                        //root.leftKey = root.rightKey;
                        root.rightKey = null;
                        ((IntNode) root).setRight(null);
                        return root;

                    }
                }
                else {// no change need to be made on the internal node
                    return root;
                }
            }
        }
        else {//root new right node is an IntNode
            if(((IntNode) root).getRight().isEmpty()) {//there was merge at the bottom
                IntNode rightNode = (IntNode) ((IntNode) root).getRight();
                IntNode middleNode = (IntNode) ((IntNode) root).getMiddle();
                if(middleNode.isFull()) {//middle node got three children borrow one
                    rightNode.leftKey = root.rightKey;
                    root.rightKey = middleNode.rightKey;
                    middleNode.rightKey = null;
                    rightNode.setMiddle(rightNode.getLeft());
                    rightNode.setLeft(middleNode.getRight());
                    middleNode.setRight(null);
                }
                else {//middle node has only two children,merge
                    middleNode.setRight(rightNode.getLeft());
                    middleNode.rightKey = root.rightKey;
                    root.rightKey = null;
                    ((IntNode) root).setRight(null);
                }
                return root;

            }

            return root;

        }

    }

    private Node<KVPair> deleteLeft(Node<KVPair> root, KVPair value, KVPair promotedValue) {
        ((IntNode) root).setLeft(deleteHelp(((IntNode) root).getLeft(), value, promotedValue));//delete from the left of the root

        if (value.compareTo(root.leftKey) == 0) {
            if(((IntNode) root).getLeft() instanceof LeafNode && !((IntNode) root).getLeft().isEmpty()) {
                root.leftKey = ((IntNode) root).getLeft().leftKey;// get rid of the key for the value that've been deleted
            }
            else {
                KVPair promoted = findPromoted(((IntNode) root).getLeft(), value);
                root.leftKey = promoted;
            }

        }

        if (((IntNode) root).getLeft() instanceof LeafNode) {//check if the root new left value is a leaf
            LeafNode leftNode = ((LeafNode) (((IntNode) root).getLeft()));
            if ((((IntNode) root).getLeft()).isEmpty()) {//the left node was deleted. ASSUMPTION: root middle is a leaf

                if ((((IntNode) root).getMiddle()).isFull()) {//if the middle is full borrow a value from it
                    leftNode.leftKey = (((IntNode) root).getMiddle()).leftKey;//left node new left value will be the middle node left value
                    (((IntNode) root).getMiddle()).leftKey = (((IntNode) root).getMiddle()).rightKey;//middle node leftvalue will be its right value
                    (((IntNode) root).getMiddle()).rightKey = null;// set middle node rightVal to null
                    root.leftKey = (((IntNode) root).getMiddle()).leftKey;//set the root new left value
                    return root;
                } else { // middle node has only one child

                    if (leftNode.getPrevious() != null) {//if left node was pointing at a node behind him
                        ((LeafNode) (((IntNode) root).getMiddle())).setPrevious(leftNode.getPrevious());//make middle node point to the node left node was point at
                        ((LeafNode) (((IntNode) root).getMiddle())).getPrevious().setNext((LeafNode) ((IntNode) root).getMiddle());//make that same node point back at the middle node
                    } else {//if not
                        ((LeafNode) (((IntNode) root).getMiddle())).setPrevious(null);//make middle node point at a null value behind him
                    }
                    if (((IntNode) root).getRight() == null) {//means the tree have only one child
                        root = ((IntNode) root).getMiddle();// set root to the only child left
                        return root;// return the root
                    } else {//root have a right child

                        ((IntNode) root).setLeft(((IntNode) root).getMiddle());//middle child is the new left
                        ((IntNode) root).setMiddle(((IntNode) root).getRight());//right child is the new middle child
                        ((IntNode) root).setRight(null);//get rid of the right node
                        root.rightKey = null;//no more right child so delete right value of node
                        root.leftKey = ((IntNode) root).getMiddle().leftKey;// the new leftVal of the root will be the middle node leftVal
                        return root;
                    }
                }
            } else {//root left child is not empty and it's a leaf
                if (((IntNode) root).getMiddle() instanceof IntNode) {// this means the tree is unbalanced after the remove so fix it
                    IntNode middleNode = (IntNode)(((IntNode) root).getMiddle());


                    if(middleNode.getRight() != null) {//middle node has three children borrow one
                        IntNode newNode = new IntNode();//represent the new internal after fixing the unbalanced
                        //new node left key is the middle node left child left key
                        newNode.leftKey = middleNode.getLeft().leftKey;
                        //new node is pointing to the root left node
                        newNode.setLeft(((IntNode) root).getLeft());
                        //new node middle child is the middle node left child
                        newNode.setMiddle(middleNode.getLeft());
                        //root new left key is the middle node left key
                        root.leftKey = middleNode.leftKey;
                        //middle node left key get replace by its right key
                        middleNode.leftKey = middleNode.rightKey;
                        middleNode.rightKey = null;
                        //middle node new left becomes it's middle
                        middleNode.setLeft(middleNode.getMiddle());
                        //middle right child becomes its middle child
                        middleNode.setMiddle(middleNode.getRight());
                        //the right pointer get removed
                        middleNode.setRight(null);
                        //root new left child is the new node
                        ((IntNode) root).setLeft(newNode);
                        return root;
                    }
                    else {//middle node has only two children
                        middleNode.rightKey = middleNode.leftKey;
                        middleNode.leftKey = root.leftKey;
                        middleNode.setRight(middleNode.getMiddle());
                        middleNode.setMiddle(middleNode.getLeft());
                        middleNode.setLeft(((IntNode) root).getLeft());
                        ((IntNode) root).setLeft(middleNode);
                        if(((IntNode) root).getRight() != null) {//root have a right child
                            ((IntNode) root).setMiddle(((IntNode) root).getRight());
                            ((IntNode) root).setRight(null);
                            root.leftKey = ((IntNode) root).getMiddle().leftKey;
                        }
                        else {//root does not have a right child
                            ((IntNode) root).setMiddle(null);
                            root.leftKey = null;
                        }
                        return root;

                    }
                }
                else {// no change need to be made on the internal node
                    return root;
                }
            }
        }
        else {//root new left node is an IntNode
            if(((IntNode) root).getLeft().isEmpty()) {//there was merge at the bottom
                IntNode middleNode = (IntNode) ((IntNode) root).getMiddle();
                IntNode leftNode = (IntNode) ((IntNode) root).getLeft();
                if(middleNode.isFull()) {//middle node got three children borrow one
                    leftNode.leftKey = root.leftKey;
                    root.leftKey = middleNode.leftKey;
                    leftNode.setMiddle(middleNode.getLeft());
                    middleNode.leftKey = middleNode.rightKey;
                    middleNode.rightKey = null;
                    middleNode.setLeft(middleNode.getMiddle());
                    middleNode.setMiddle(middleNode.getRight());
                    middleNode.setRight(null);
                    return root;
                }
                else {//middle node has only two children, merge
                    middleNode.setRight(middleNode.getMiddle());
                    middleNode.setMiddle(middleNode.getLeft());
                    middleNode.setLeft(leftNode.getLeft());
                    ((IntNode) root).setLeft(middleNode);
                    middleNode.rightKey = middleNode.leftKey;
                    middleNode.leftKey = root.leftKey;
                    root.leftKey = root.rightKey;
                    ((IntNode) root).setMiddle(((IntNode) root).getRight());
                    root.rightKey = null;
                    if(((IntNode) root).getRight() != null) {
                        ((IntNode) root).setRight(null);
                    }


                    return root;
                }

            }
            return root;

        }


    }

}
