/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016 *
 * Name: Patrick Newhart, Levi Adair, Sam Greenberg, Tom Ficcadenti
 * Date: Apr 7, 2016
 * Time: 1:54:23 PM *
 * Project: csci205FinalProject
 * Package: Model
 * File: Hole
 * Description:
 *
 * **************************************** */
package Model;

/**
 *
 * @author laa024
 */
public class Hole {

    private int percentRemoved = 0;
    private final int MAX_FILL = 0;
    private final int MIN_FILL = 19;
    protected Direction dir;
    protected boolean isEmpty;
    protected boolean hasBeenDug = false;

    /**
     *
     */
    public Hole(Direction d) {
        this.dir = d;
        percentRemoved = MAX_FILL;
    }

    /**
     *
     * @return percent of hole filled
     */
    public int getPercentRemoved() {
        if (isClear()) {
            return 19;
        } else {
            return percentRemoved;
        }
    }

    /**
     * destroys part of the hole thats filled
     *
     * @param percentToDestroy
     * @throws Exception
     */
    public boolean destroy(int percentToDestroy) throws Exception {
        if (percentRemoved >= MIN_FILL) {
            isEmpty = true;
            return false;
        } else if (!hasBeenDug && percentRemoved < 2) {
            if (percentRemoved < 4) {
                percentRemoved += 1;
            }
            percentRemoved += percentToDestroy;
            isEmpty = false;
            hasBeenDug = true;
            return true;
        } else if (hasBeenDug && percentRemoved < 19) {
            if (percentRemoved < 4) {
                percentRemoved += 1;
            }
            percentRemoved += percentToDestroy;
            isEmpty = false;
            hasBeenDug = true;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Digs in the correct amount
     *
     * @param digAmount
     * @return
     */
    public boolean dig(int digAmount) {
        if (digAmount == 1 && !hasBeenDug) {
            percentRemoved = 1;
            hasBeenDug = true;
            return true;
        } else if (hasBeenDug) {
            if (!isDugTo(digAmount)) {
                percentRemoved = digAmount;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Digs in the correct amount for special circumstances
     *
     * @param digAmount
     * @return boolean
     */
    public boolean forceDig(int digAmount) {
        if (!isDugTo(digAmount)) {
            percentRemoved = digAmount;
            return true;
        } else {
            return true;
        }
    }

    /**
     * Is the hole cleared (dug more than or equal to 19)
     *
     * @return boolean
     */
    public boolean isClear() {
        return this.percentRemoved >= 19;
    }

    /**
     * Clears the hole
     */
    public void clear() {
        this.percentRemoved = 100;
    }

    public boolean isDugTo(int digAmount) {
        return percentRemoved >= digAmount;
    }

    /**
     *
     * @return true if hole if empty
     */
    public boolean isEmpty() {
        return this.isEmpty;

    }

    public void clearHole() {
        percentRemoved = MIN_FILL;
        this.percentRemoved = MIN_FILL;
        this.isEmpty = true;
    }

    public void fillHole() {
        percentRemoved = MAX_FILL;
    }

    public String filePath() {
        String pathString = "";
        if (this.dir == Direction.UP) {
            pathString = "digNorth";
        }
        if (this.dir == Direction.DOWN) {
            pathString = "digSouth";
        }

        if (this.dir == Direction.LEFT) {
            pathString = "digEast";
        }

        if (this.dir == Direction.RIGHT) {
            pathString = "digWest";
        }
        pathString += String.format("%d", percentRemoved);
        return pathString;
    }
}
