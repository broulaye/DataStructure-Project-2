/**
 *
 * @param <T>
 */
public class Node<T extends Comparable<? super T>> {
    // each Internal Node has three pointers and two values
    protected T leftKey; // left key
    protected T rightKey; // right key
    public boolean isLeaf(){
        return false;
    }

    public boolean isFull() {
        return leftKey != null && rightKey != null;
    }

    public boolean isEqual(Node<T> aNode) {
        return this.equals(aNode);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (this.getLeftKey() != null) {
            builder.append(this.getLeftKey().toString());
        }
        if (this.getRightKey() != null) {
            builder.append(" ");
            builder.append(this.getRightKey().toString());
        }
        return builder.toString();
    }

    public T getLeftKey() {
        return leftKey;
    }

    public void setLeftKey(T leftKey) {
        this.leftKey = leftKey;
    }

    public T getRightKey() {
        return rightKey;
    }

    public void setRightKey(T rightKey) {
        this.rightKey = rightKey;
    }

}
