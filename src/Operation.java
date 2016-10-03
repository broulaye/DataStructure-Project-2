/**
 * This class is an enum representation of the different operation the code can
 * perform
 *
 * @author Broulaye Doumbia
 * @author Cheick Berthe
 * @version 09/04/2016
 */
public enum Operation {
    /**
     * Represent the different operations
     */
    insert, remove, print, list, delete, printtree;

    /**
     * Get a string representation of the operation
     *
     * @return a string representation of the operation
     */
    public String toString() {

        if (this == insert) {
            return "insert";
        }
        else if (this == remove) {
            return "remove";
        }
        else if(this == print) {
            return "print";
        }
        else if(this == list) {
            return "list";
        }
        else if(this == delete) {
            return "delete";
        }
        else {
            return "printtree";
        }
    }

}
