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

    private int percentFill;
    private final int MAX_FILL = 100;
    private final int MIN_FILL = 0;

    /**
     *
     */
    public Hole() {
        percentFill = MAX_FILL;

    }

    /**
     *
     * @return percent of hole filled
     */
    public int getPercentFill() {
        return percentFill;
    }

    /**
     * destroys part of the hole thats filled
     *
     * @param percentToDestroy
     * @throws Exception
     */
    public void destroy(int percentToDestroy) throws Exception {
        if (percentToDestroy > percentFill || percentToDestroy < 0) {
            throw new Exception(
                    "percent to destroy is larger than whats left, or negative");
        } else {
            percentFill -= percentToDestroy;

        }
    }

    /**
     *
     * @return true if hole is full
     */
    public boolean isFull() {
        if (percentFill == MAX_FILL) {
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
        if (percentFill == MIN_FILL) {
            return true;
        } else {
            return false;
        }

    }

    public void clearHole() {
        percentFill = MIN_FILL;
    }
}
