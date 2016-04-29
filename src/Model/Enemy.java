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
public abstract class Enemy extends Object {

    protected final double INITIAL_SPEED = 0.5;

    protected Direction prevDirection;
    protected Direction direction;
    protected double speed;

    private static final double DEFLATE_TIME = 0.8;
    private static final double PUMP_TIME = 0.5;
    private static final int MAX_PUMPS = 3;

    protected Date prevPumpTime;
    protected Date prevDeflateTime;
    protected int pumpCount;

    protected boolean isPumpable = true;
    private boolean isPopped = false;
    public boolean isFloating = false;
    protected boolean canCrush = true;
    protected boolean isCrushed = false;

    public Enemy(Vector2 location) {
        this.speed = INITIAL_SPEED;
        this.direction = Direction.RIGHT;
        this.setDiv(location);
        this.direction = Direction.RIGHT;
        this.prevDirection = Direction.RIGHT;
    }

    @Override
    public void move() {
        System.out.println(this.getDiv());
        if (getBoard().isClearedVertical(
                this.getDiv())) {
            System.out.println(
                    "i9s cleared vert" + getBoard().isClearedVertical(
                            this.getDiv()));
        }
        if (getBoard().isClearedHorizontal(
                this.getDiv())) {
            System.out.println(
                    "is cleared horizontal" + getBoard().isClearedHorizontal(
                            this.getDiv()));
        }

        if ((getBoard().isClearedHorizontal(this.getFront()) && (direction == Direction.RIGHT || direction == Direction.LEFT)) || (getBoard().isClearedVertical(
                                                                                                                                   this.getFront()) && (direction == Direction.UP || direction == Direction.DOWN))) {
            this.setDiv(Vector2Utility.add(this.getDiv(), Vector2Utility.scale(
                                           this.direction.getVector(), speed)));

        } else {
            ArrayList<Vector2> locations = new ArrayList<Vector2>();
            ArrayList<Direction> directions = new ArrayList<Direction>();

            Vector2 up = this.getDirection(Direction.UP);
            Vector2 down = this.getDirection(Direction.DOWN);
            Vector2 left = this.getDirection(Direction.LEFT);
            Vector2 right = this.getDirection(Direction.RIGHT);
            if (getBoard().isClearedVertical(up)) {
                locations.add(up);
                directions.add(Direction.UP);
            }
            if (getBoard().isClearedVertical(down)) {
                locations.add(down);
                directions.add(Direction.DOWN);
            }
            if (getBoard().isClearedHorizontal(left)) {
                locations.add(left);
                directions.add(Direction.LEFT);
            }
            if (getBoard().isClearedHorizontal(right)) {
                locations.add(right);
                directions.add(Direction.RIGHT);
            }

            Random r = new Random();

            int i = r.nextInt(locations.size());

            if ((directions.get(i) == Direction.UP || directions.get(i) == Direction.DOWN) && (this.direction == Direction.LEFT || this.direction == direction.RIGHT)) {
                this.prevDirection = direction;
            }
            this.direction = directions.get(i);

        }
    }

    /**
     * makes enemy float to driller location that is given
     *
     * @param coord (Vector2)
     */
    public void floatToDriller(Vector2 coord) {
        Vector2 drillerLocation = coord;
        double enemyX = this.getDiv().getX();
        double enemyY = this.getDiv().getY();
        double drillerX = drillerLocation.getX();
        double drillerY = drillerLocation.getY();
        double horizontalMove = drillerX - enemyX;
        double verticalMove = drillerY - enemyY;
        int i = 0;
        startFloat();
        while (i < 10 && isFloating) {

            if (horizontalMove < 0) {
                if (verticalMove < 0) {
                    this.setDiv(new Vector2((enemyX + (.1 * horizontalMove)),
                                            (enemyY + (.1 * verticalMove)))); // vert and horiz are negative
                } else {
                    this.setDiv(new Vector2((enemyX + (.1 * horizontalMove)),
                                            (enemyY - (.1 * verticalMove)))); // vert is positive horiz is negative
                }

            } else if (verticalMove < 0) {
                this.setDiv(new Vector2((enemyX - (.1 * horizontalMove)),
                                        (enemyY + (.1 * verticalMove)))); // vert is neg and horiz is positive

            } else {
                this.setDiv(new Vector2((enemyX - (.1 * horizontalMove)),
                                        (enemyY - (.1 * verticalMove)))); // vert and horiz are positive
            }
            i++;
        }
        stopFloat();

    }

    public void startFloat() {
        isFloating = true;
    }

    public void stopFloat() {
        isFloating = false;
    }

    @Override
    public void crush() {
        isCrushed = true;

    }

    /**
     * Current location of the direction in divs with respect to location
     *
     * @return Vector2 loc location in divs
     */
    public Vector2 getDirection(Direction d) {
        Vector2 loc = this.getDiv();
        if (d == Direction.RIGHT || d == Direction.DOWN) {
            loc = Vector2Utility.add(loc, Vector2Utility.scale(
                                     d.getVector(),
                                     Vector2.DIVS_PER_TILE));
        }

//        if (d == Direction.RIGHT || this.direction == Direction.LEFT) {
//            Vector2Utility.add(loc, new Vector2(0, Vector2.DIVS_PER_TILE / 2));
//        } else {
//            Vector2Utility.add(loc, new Vector2(Vector2.DIVS_PER_TILE / 2, 0));
//        }
        return loc;
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
