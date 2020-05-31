/**
 * The Move class represents a move in the Nim game by a player. A move consists of the row on which it is
 * applied, the left bound (inclusive) of the sequence of sticks to mark, and the right bound (inclusive)
 * of the same sequence.
 */

public class Move {


    private int inRow;
    private int inLeft;
    private int inRight;

    /**
     * Constructs a Move object with the given parameters.
     * @param inRow: The row on which the move is performed.
     * @param inLeft: The left bound of the sequence to mark.
     * @param inRight: The right bound of the sequence to mark.
     */
    public Move(int inRow, int inLeft, int inRight) {
        this.inRow = inRow;
        this.inLeft = inLeft;
        this.inRight = inRight;
    }

    /**
     * This function returns the left bound of the stick sequence to mark.
     * @return left bound of the stick sequence to mark.
     */
    public int getLeftBound() {return inLeft;}

    /**
     * This function returns the right bound of the stick sequence to mark.
     * @return right bound of the stick sequence to mark.
     */
    public int getRightBound() {return inRight;}

    /**
     * This function returns the row on which the move is performed.
     * @return row on which the move is performed.
     */
    public int getRow() {return inRow;}

    /**
     * This function returns a string representation of the move.
     * @return string representation of the move.
     */
    public String toString() {return inRow + ":" + inLeft + "-" + inRight;}

}
