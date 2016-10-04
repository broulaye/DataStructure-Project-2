
public class Node<key extends Comparable<? super key>> {

    public boolean isFull() {
        return false;
    }
    public boolean isEqual(Node<?> aNode) {
        return this.equals(aNode);
    }
    public String toString() {
        return "";
    }
}
