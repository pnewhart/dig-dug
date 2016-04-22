/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016 *
 * Name: Patrick Newhart, Levi Adair, Sam Greenberg, Tom Ficcadenti
 * Date: Apr 15, 2016
 * Time: 10:11:52 AM *
 * Project: csci205FinalProject
 * Package: Model
 * File: Enemy
 * Description:
 *
 * **************************************** */
package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author laa024
 */
public class Enemy extends Object {

    private final double INITIAL_SPEED = 0.5;

    private Direction direction;
    private GameBoard gBoard;
    private double speed;

    private static final double DEFLATE_TIME = 0.8;
    private static final double PUMP_TIME = 0.5;
    private static final int MAX_PUMPS = 3;

    protected Date prevPumpTime;
    protected Date prevDeflateTime;
    protected int pumpCount;

    protected boolean isPumpable = true;
    private boolean isPopped = false;

    public Enemy(GameBoard b) {
        gBoard = b;
        this.speed = INITIAL_SPEED;
        this.direction = Direction.RIGHT;
    }

    @Override
    public void move() {
        if (gBoard.isDivEmpty(this.getFront())) {
            this.location = Vector2Utility.add(location, Vector2Utility.scale(
                                               this.direction.getVector(), speed));
        } else {
            ArrayList<Vector2> locations = new ArrayList<Vector2>();
            ArrayList<Direction> directions = new ArrayList<Direction>();

            Vector2 up = this.getDirection(Direction.UP);
            Vector2 down = this.getDirection(Direction.DOWN);
            Vector2 left = this.getDirection(Direction.LEFT);
            Vector2 right = this.getDirection(Direction.RIGHT);

            if (gBoard.isDivEmpty(up)) {
                locations.add(up);
                directions.add(Direction.UP);
            }
            if (gBoard.isDivEmpty(down)) {
                locations.add(down);
                directions.add(Direction.DOWN);
            }
            if (gBoard.isDivEmpty(left)) {
                locations.add(left);
                directions.add(Direction.LEFT);
            }
            if (gBoard.isDivEmpty(right)) {
                locations.add(right);
                directions.add(Direction.RIGHT);
            }

            Random r = new Random();

            int i = r.nextInt(locations.size());

            this.direction = directions.get(i);
            this.location = locations.get(i);
        }
    }

    /**
     * Current location of the direction in divs with respect to location
     *
     * @return Vector2 front location in divs
     */
    public Vector2 getDirection(Direction d) {
        Vector2 front = this.getDiv();
        if (d == Direction.RIGHT) {
            front = Vector2Utility.add(front, Vector2Utility.scale(
                                       Direction.RIGHT.getVector(),
                                       Vector2.DIVS_PER_TILE));
        } else if (d == Direction.DOWN) {
            front = Vector2Utility.add(front, Vector2Utility.scale(
                                       Direction.DOWN.getVector(),
                                       Vector2.DIVS_PER_TILE));
        }

        if (d == Direction.RIGHT || this.direction == Direction.LEFT) {
            Vector2Utility.add(front, new Vector2(0, Vector2.DIVS_PER_TILE / 2));
        } else {
            Vector2Utility.add(front, new Vector2(Vector2.DIVS_PER_TILE / 2, 0));
        }

        return front;
    }

    public Vector2 getFront() {
        return getDirection(this.direction);
    }

    public boolean isIsPopped() {
        return isPopped;
    }

    /**
     * Method that handles pumping
     */
    public void pump() {
        if (curPumpTime() > PUMP_TIME) {
            pumpCount += 1;
            prevPumpTime = new Date();
            prevDeflateTime = new Date();
        }
        if (pumpCount > MAX_PUMPS) {
            isPopped = true;
        }
    }

    /**
     * Method that handles deflating
     */
    public void deflate() {
        if (curDeflateTime() > DEFLATE_TIME) {
            pumpCount -= 1;
            prevDeflateTime = new Date();
        }
    }

    /**
     * Time since last pump is too great
     */
    public boolean isNotPumped() {
        return pumpCount == 0;
    }

    /**
     * Checks if the pumpCount is greater than zero to see if the Pumpable
     * object is being pumped
     *
     * @return
     */
    public boolean isPumped() {
        return pumpCount > 0;
    }

    /**
     * Will return double representing the number of seconds that the gun has
     * existed.
     *
     * @return double
     */
    public double curPumpTime() {
        Date now = new Date();
        return now.compareTo(prevPumpTime) / 1000.0;
    }

    /**
     * Will return double representing the number of seconds that the gun has
     * existed.
     *
     * @return double
     */
    public double curDeflateTime() {
        Date now = new Date();
        return now.compareTo(prevDeflateTime) / 1000.0;
    }

}
