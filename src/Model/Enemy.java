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
public abstract class Enemy extends BoardObject {

    protected final double INITIAL_SPEED = 0.8;

    protected Direction prevHorDirection;
    protected Direction prevDirection;
    protected Direction direction;
    protected double speed;

    protected int floatPercent;
    protected final int INITIAL_FLOAT_PERCENT = 20;

    protected Vector2 startFloatTile;

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
    public boolean wasFloating = false;
    protected boolean canCrush = true;
    protected boolean isCrushed = false;

    private int deadCount;
    private boolean isDead;
    private final int MAX_DEAD_COUNT = 32;

    public Enemy(Vector2 location) {
        this.speed = INITIAL_SPEED;
        this.direction = Direction.RIGHT;
        this.setDiv(location);
        this.direction = Direction.RIGHT;
        this.prevHorDirection = Direction.RIGHT;
        this.floatPercent = INITIAL_FLOAT_PERCENT;
    }

    @Override
    public void move() {
        boolean shouldFloat = this.decideToFloat();
        //System.out.println("Should float" + shouldFloat);
        if (!this.hasBeenKilled()) {
            if (!isFloating && getBoard().isDugTo(getFront(), direction)) {
                moveForward();
            } else if (shouldFloat || isFloating) {
                //System.out.println("floating");
                this.floatToDriller();
            } else if (!isFloating) {
                this.changeDirection();
            }
        } else if (hasBeenKilled() && deadCount < MAX_DEAD_COUNT && isDead) {
            deadCount += 1;
        } else if (isDead && deadCount >= MAX_DEAD_COUNT) {
            this.destroy();
        }
        stepCount = (stepCount + 1) % MAX_STEP_COUNT;
    }

    private void moveForward() {
        this.setDiv(Vector2Utility.add(this.getDiv(),
                                       Vector2Utility.scale(
                                               this.direction.getVector(),
                                               speed)));
        prevDirection = direction;
        this.align(direction);
        wasFloating = false;
    }

    private void changeDirection() {
        ArrayList<Direction> directions = new ArrayList();

        Direction[] dirs = {Direction.UP, Direction.DOWN, Direction.RIGHT, Direction.LEFT};

        for (Direction dir : dirs) {
            Vector2 place = this.getDirection(dir);

            if ((prevDirection != dir.getOpposite() && !wasFloating) && (direction.isVertical() || (direction.isHorizontal() && this.isAtTurnableDiv(
                                                                                                    getDiv()))) && getBoard().isDugTo(
                    place,
                    dir)) {
                directions.add(dir);
            }
        }
        Random r = new Random();
        if (directions.size() > 0) {
            int i = r.nextInt(directions.size());
            if (directions.get(i).isVertical() && direction.isHorizontal()) {
                this.prevHorDirection = direction;
            }
            prevDirection = direction;
            this.direction = directions.get(i);
            this.setDiv(Vector2Utility.add(this.getDiv(),
                                           Vector2Utility.scale(
                                                   this.direction.getVector(),
                                                   speed)));
        } else {
            prevDirection = direction;
            direction = direction.getOpposite();
            this.setDiv(Vector2Utility.add(this.getDiv(),
                                           direction.getVector()));
        }
        this.align(direction);
    }

    private boolean decideToFloat() {
        Random r = new Random();
        int choice = r.nextInt(floatPercent);
        if (choice == 1) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * makes enemy float to driller location that is given
     *
     * @param coord (Vector2)
     */
    public void floatToDriller() {
        //System.out.println("in float driller");

        Vector2 relLoc = Vector2Utility.sub(getBoard().driller.getDiv(),
                                            getDiv());

        double xMove = 0;
        double yMove = 0;

        if (relLoc.getX() > 1) {
            xMove = 0.5;
        } else if (relLoc.getX() < -1) {
            xMove = -0.5;
        }
        if (relLoc.getY() > 1) {
            yMove = 0.5;
        } else if (relLoc.getY() < -1) {
            yMove = -0.5;
        }

        if (!isFloating) {
            this.startFloat();
        }

        Vector2 move = Vector2Utility.setMagnitude(new Vector2(xMove, yMove),
                                                   0.5);
        //System.out.println(move);

        Vector2 prevDiv = getDiv();
        this.setDiv(Vector2Utility.add(getDiv(), move));
        Vector2 curDiv = getDiv();
        //System.out.println(prevDiv + " " + curDiv);

        if (this.getBoard().isClearedVertical(this.getRoundTile()) && Vector2Utility.distanceBetween(
                getDiv(), startFloatTile) > 2) {
            //System.out.println("going to end floating");
            if (yMove >= 0) {
                this.direction = Direction.UP;
            } else {
                this.direction = Direction.DOWN;
            }
            this.stopFloat();
            this.align(direction);
        } else if (getBoard().isClearedHorizontal(this.getRoundTile()) && Vector2Utility.distanceBetween(
                getDiv(), startFloatTile) > Vector2.DIVS_PER_TILE) {
            //System.out.println("going to end floating");
            if (xMove >= 0) {
                this.direction = Direction.RIGHT;
            } else {
                this.direction = Direction.LEFT;
            }
            this.stopFloat();
            this.align(direction);
        }
    }

    public void startFloat() {
        this.startFloatTile = this.getDiv();
        isFloating = true;
        wasFloating = true;
    }

    public void stopFloat() {
        isFloating = false;
    }

    @Override
    public void crush() {
        isCrushed = true;
    }

    public void kill() {
        this.isDead = true;
    }

    public boolean hasBeenKilled() {
        return this.isCrushed || this.isPopped;
    }

    /**
     * Current location of the direction in tiles with respect to location
     *
     * @return Vector2 loc location in tiles
     */
    public Vector2 getDirection(Direction d) {
        Vector2 loc = getDiv();
        loc = Vector2Utility.add(loc, Vector2Utility.scale(new Vector2(1, 1),
                                                           Vector2.DIVS_PER_TILE / 2));
        loc = Vector2Utility.add(loc, Vector2Utility.scale(d.getVector(),
                                                           Vector2.DIVS_PER_TILE));

        return loc;
    }

    public Vector2 getFront() {
        Vector2 loc = getDiv();
        loc = Vector2Utility.add(loc, Vector2Utility.scale(new Vector2(1, 1),
                                                           Vector2.DIVS_PER_TILE / 2));
        loc = Vector2Utility.add(loc,
                                 Vector2Utility.scale(direction.getVector(),
                                                      Vector2.DIVS_PER_TILE / 2 + speed));

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
        if (pumpCount >= MAX_PUMPS) {
            isPopped = true;
            isDead = true;
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

    public Vector2 getRoundTile() {
        Vector2 tile = Vector2Utility.roundDivide(getDiv(),
                                                  Vector2.DIVS_PER_TILE);
        return tile;
    }

    @Override
    public void destroy() {
        try {
            getBoard().enemyList.remove(this);
        } catch (Exception e) {
            //System.out.println("Could not destroy enemy!");
        }
    }

}
