
public class twoThreeTree <KVPair extends Comparable<? super KVPair>>{
    class Node {
        KVPair element;
        Node left;
        Node right;
        Color color;
        
        public Node(KVPair newelement) {
            element = newelement;
            color = Color.BLACK;
        }
        
        
        
   
    }
    
    Node root;
    
    public void add(KVPair value) {
        root = add(value, root);
    }
    
    private boolean isRED(Node x) {
        return x.color == Color.RED;
    }
    
    private Node add(KVPair value, Node root) {
        if(root == null)
            return new Node(value);
        int compare = root.element.compareTo(value);
        if(compare < 0) {
            root.left = add(value, root.left);
        }
        else if(compare > 0) {
            root.right = add(value, root.right);
        }
        else
            root.element = value;
         
        if(isRED(root.right) && !isRED(root.left)) {
            root = rotateLeft(root);
        }
        if(isRED(root.left) && isRED(root.left.left)) {
            root = rotateRight(root);
        }
        if(isRED(root.right) && isRED(root.left)) {
            flipColors(root);
        }
            
        return root;
    }
    
    public boolean find(KVPair x) {
        return find(x, root);
    }
    
    private boolean find(KVPair x, Node root) {
        if(root == null) 
            return false;
        int compare = root.element.compareTo(x);
        if(compare < 0) {
            return find(x, root.left);
        }
        else if(compare > 0) {
            return find(x, root.right);
        }
        else
            return true;
    }
    
    private Node findLeaf(Node root) {
        Node leaf = root;
        while(leaf.right != null) {
            leaf = leaf.right;
        }
        
        if(leaf.left != null) {
            leaf = findLeaf(leaf.left);
        }
        
        return leaf;
    }
    
    public boolean delete(KVPair x) {
        root = delete(x, root);
        return true;
    }
    
    private Node delete(KVPair x, Node root) {
        if(root == null)
            return root;
        
        int compare = root.element.compareTo(x);
        
        if(compare < 0) {
            root.left = delete(x, root.left);
        }
        else if(compare > 0) {
            root.right = delete(x, root.right);
        }
        else {
            if(root.left != null && root.right != null) {
                KVPair min = findLeaf(root).element;
                root.element = min;
                root.right = delete(min, root.right);
            }
            else if(root.left != null) {
                root = root.left;
            }
            else if(root.right != null) {
                root = root.right;
            }
            else
                root = null;
        }
        return root;
        
        
    }
    
    public void preOrderTraversal(Node root) {
        if(root != null) {
            System.out.println(root.element);
            preOrderTraversal(root.left);
            preOrderTraversal(root.right);
        }
    }
    
    public void inOrderTraversal(Node root) {
        if(root != null) {
            inOrderTraversal(root.left);
            System.out.println(root.element);
            inOrderTraversal(root.right);
        }
    }
    
    public void postOrderTraversal(Node root) {
        if(root != null) {
            postOrderTraversal(root.left);
            postOrderTraversal(root.right);
            System.out.println(root.element);
        }
    }
    
    private Node rotateLeft(Node x) {
        Node newNode = x.right;
        x.right = newNode.left;
        newNode.left = x;
        newNode.color = x.color;
        x.color = Color.RED;
        return newNode;
    }
    
    private Node rotateRight(Node x) {
        Node newNode = x.left;
        x.left = newNode.right;
        newNode.right = x;
        newNode.color = x.color;
        x.color = Color.RED;
        return newNode;
    }
    
    private void flipColors(Node x) {
        x.color = Color.RED;
        x.left.color = Color.BLACK;
        x.right.color = Color.BLACK;
    }
}
