/**
 * @author: Broulaye Doumbia
 * @author: Cheick Berthe
 * @version:10/3/2016.
 */

/**
 *
 * @param <T>
 */
public class Node<T extends Comparable<? super T>> {
    // each Internal Node has three pointers and two values
    protected T leftKey; // left key
    protected T rightKey; // right key

    /**
     *
     * @return
     */
    public boolean isLeaf() {
        return false;
    }

    /**
     *
     * @return
     */
    public boolean isFull() {
        return leftKey != null && rightKey != null;
    }

    /**
     *
     * @return
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        // print keys first
        if (leftKey != null) {
            builder.append(leftKey.toString());

        }
        if (rightKey != null) {
            builder.append(" ").append(rightKey.toString());
        }
        builder.append(" ");
        return builder.toString();
    }

    /**
     *
     * @return
     */
    public T getLeftKey() {
        return leftKey;
    }

    /**
     *
     * @param leftKey
     */
    public void setLeftKey(T leftKey) {
        this.leftKey = leftKey;
    }

    /**
     *
     * @return
     */
    public T getRightKey() {
        return rightKey;
    }

    /**
     *
     * @param rightKey
     */
    public void setRightKey(T rightKey) {
        this.rightKey = rightKey;
    }

    /**
     *
     * @return
     */
    public boolean isEmpty() {
        return leftKey == null && rightKey == null;
    }

}
