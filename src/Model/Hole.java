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
        return percentRemoved;
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
        } else {
            if (percentRemoved < 4) {
                percentRemoved += 1;
            }
            percentRemoved += percentToDestroy;
            isEmpty = false;
            return true;

        }
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
