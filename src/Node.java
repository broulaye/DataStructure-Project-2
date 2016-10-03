
public class Node<key extends Comparable<? super key>> {

    public boolean isLeaf() {
        return false;
    }
    public boolean isFull() {
        return false;
    }
    public boolean isEqual(Node aNode) {
        return this.equals(aNode);
    }
}
