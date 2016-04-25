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

    /**
     *
     */
    public Hole() {
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
        if (percentRemoved == 19) {
            return false;
        } else {
            percentRemoved += percentToDestroy;
            System.out.println("hole dug");
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
}
