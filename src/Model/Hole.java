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
            percentRemoved = MIN_FILL;
            return false;
        } else {
            if (percentRemoved < 3) {
                percentRemoved += 1;
            }
            percentRemoved += percentToDestroy;

            System.out.println(percentRemoved);

            return true;

        }
    }

    /**
     *
     * @return true if hole is full
     */
    public boolean isFull() {
        if (percentRemoved == MAX_FILL) {
            return true;
        } else {
            return false;
        }

    }

    /**
     *
     * @return true if hole if empty
     */
    public boolean isEmpty() {
        if (percentRemoved == MIN_FILL) {
            return true;
        } else {
            return false;
        }

    }

    public void clearHole() {
        percentRemoved = MIN_FILL;
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
