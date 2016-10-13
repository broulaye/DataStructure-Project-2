import org.junit.Assert;

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

    public String toString() {
        StringBuilder builder = new StringBuilder();
        // print keys first
        if (leftKey != null) {
            builder.append(leftKey.toString());

        }
        if (rightKey != null) {
            Assert.assertNotNull(leftKey);
            builder.append(" ")
                    .append(rightKey.toString());
        }
        builder.append(" ");
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
    public boolean isEmpty() {
        return leftKey == null && rightKey == null;
    }

}
