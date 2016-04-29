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

    protected final double INITIAL_SPEED = 1.0;

    protected Direction prevDirection;
    protected Direction direction;
    protected double speed;

    protected int stepCount;
    protected final int MAX_STEP_COUNT = 12;

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
        if (getBoard().isDugTo(getFront(), direction)) {
            this.setDiv(Vector2Utility.add(this.getDiv(), Vector2Utility.scale(
                                           this.direction.getVector(), speed)));
            stepCount = (stepCount + 1) % MAX_STEP_COUNT;

        } else {
            ArrayList<Direction> directions = new ArrayList<Direction>();

            Vector2 up = this.getDirection(Direction.UP);
            Vector2 down = this.getDirection(Direction.DOWN);
            Vector2 left = this.getDirection(Direction.LEFT);
            Vector2 right = this.getDirection(Direction.RIGHT);
            if ((direction.isVertical() || (direction.isHorizontal() && this.isAtTurnableDiv(
                                            getDiv()))) && getBoard().isDugTo(up,
                                                                              Direction.UP)) {
                directions.add(Direction.UP);
//                System.out.println("Can Move Up. canTurn = " + isAtTurnableDiv(
//                        getDiv()));
            }
            if ((direction.isVertical() || (direction.isHorizontal() && this.isAtTurnableDiv(
                                            getDiv()))) && getBoard().isDugTo(
                    down, Direction.DOWN)) {
                directions.add(Direction.DOWN);
//                System.out.println(
//                        "Can Move Down. canTurn = " + isAtTurnableDiv(getDiv()));
            }
            if ((direction.isHorizontal() || (direction.isVertical() && this.isAtTurnableDiv(
                                              getDiv()))) && getBoard().isDugTo(
                    left, Direction.LEFT)) {
                directions.add(Direction.LEFT);
//                System.out.println(
//                        "Can Move Left. canTurn = " + isAtTurnableDiv(getDiv()));
            }
            if ((direction.isHorizontal() || (direction.isVertical() && this.isAtTurnableDiv(
                                              getDiv()))) && getBoard().isDugTo(
                    right, Direction.RIGHT)) {
                directions.add(Direction.RIGHT);
//                System.out.println(
//                        "Can Move Right. canTurn = " + isAtTurnableDiv(getDiv()));
            }

            Random r = new Random();
            if (directions.size() > 0) {
                int i = r.nextInt(directions.size());
                if (directions.get(i).isVertical() && direction.isHorizontal()) {
                    this.prevDirection = direction;
                }
                this.direction = directions.get(i);
            } else {
                direction = direction.getOpposite();
                this.setDiv(Vector2Utility.add(this.getDiv(),
                                               direction.getVector()));
                stepCount = (stepCount + 1) % MAX_STEP_COUNT;
                System.out.println("Error turning!");
            }
        }
        this.align(direction);
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
     * Current location of the direction in tiles with respect to location
     *
     * @return Vector2 loc location in tiles
     */
    public Vector2 getDirection(Direction d) {
        Vector2 loc = Vector2Utility.scale(this.getTile(), Vector2.DIVS_PER_TILE);
        if (d == Direction.RIGHT || d == Direction.DOWN) {
            loc = Vector2Utility.add(loc, Vector2Utility.scale(
                                     d.getVector(),
                                     Vector2.DIVS_PER_TILE));
        }
        loc = Vector2Utility.add(loc, Vector2Utility.scale(
                                 d.getVector(),
                                 speed));

        return loc;
    }

    public Vector2 getFront() {
        Vector2 loc = getDiv();
        if (direction == Direction.RIGHT || direction == Direction.DOWN) {
            loc = Vector2Utility.add(loc, Vector2Utility.scale(
                                     direction.getVector(),
                                     Vector2.DIVS_PER_TILE));
        }
        loc = Vector2Utility.add(loc, Vector2Utility.scale(
                                 direction.getVector(),
                                 speed));

        return loc;
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

    @Override
    public Vector2 getTile() {
        Vector2 tile = Vector2Utility.divide(this.getDiv(),
                                             Vector2.DIVS_PER_TILE);
        if (direction == Direction.LEFT) {
            tile.setX(Math.ceil(tile.getX()));
            tile.setY(Math.floor(tile.getY()));
        } else if (direction == Direction.RIGHT) {
            tile.setX(Math.ceil(tile.getX()) - 1);
            tile.setY(Math.floor(tile.getY()));
        } else if (direction == Direction.UP) {
            tile.setY(Math.ceil(tile.getY()));
            tile.setX(Math.floor(tile.getX()));
        } else if (direction == Direction.DOWN) {
            tile.setY(Math.ceil(tile.getY()) - 1);
            tile.setX(Math.floor(tile.getX()));
        }
        return tile;
    }

}
