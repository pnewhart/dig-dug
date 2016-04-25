/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016 *
 * Name: Patrick Newhart, Levi Adair, Sam Greenberg, Tom Ficcadenti
 * Date: Apr 6, 2016
 * Time: 4:24:57 PM *
 * Project: csci205FinalProject
 * Package: Model
 * File: Driller
 * Description:
 *
 * **************************************** */
package Model;

import java.awt.Image;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author spg011
 */
public class Driller extends Object {

    /**
     * Direction in the Dig Dug board (LEFT, RIGHT, UP, DOWN)
     */
    private Direction direction;

    /**
     * Direction in the Dig Dug board (LEFT, RIGHT, UP, DOWN)
     */
    private Direction prevDirection;

    /**
     * Speed the character can move on the Dig Dug board
     */
    private double speed;

    /**
     * Is the character moving
     */
    private boolean isMoving;

    /**
     * The board that the character is within
     */
    private GameBoard board;

    private boolean isShooting;
    private Gun gun;

    private boolean isCrushed;
    private boolean isKilled;

    private Date deadTime;

    private int prevWalkState;
    private final static int NUM_WALK_STATES = 8;

    //TEMPORARY CHANGE LATER//
    private Image currentImage;
    //////////////////////////

    public Driller(GameBoard board) {
        this.location = new Vector2(
                (Vector2.NUM_TILE_HORIZONTAL / 2 - 1) * Vector2.DIVS_PER_TILE,
                (Vector2.NUM_TILE_VERTICAL / 2 - 1) * Vector2.DIVS_PER_TILE);
        this.prevDirection = Direction.DOWN;
        this.direction = Direction.RIGHT;
        this.speed = 1.0;
        this.isMoving = false;
        this.isShooting = false;
        this.gun = null;
        this.board = board;

        this.isCrushed = false;
        this.isKilled = false;
        this.deadTime = null;

        this.Images = new HashMap();
        this.currentImage = null;

        this.prevWalkState = 0;
    }

    @Override
    public Image getCurrentImage() {
        String s1 = "";
        String s2 = "";
        String s3 = "";

        if (isDigging()) {
            s1 = "Digger";
        } else if (this.gun != null && this.gun.isPumping()) {
            s1 = "Pumper";
        } else {
            s1 = "Walker";
        }

        if (direction == Direction.LEFT) {
            s2 = "Left_";
        } else if (direction == Direction.RIGHT) {
            s2 = "Right_";
        } else if (direction == Direction.UP) {
            if (this.prevDirection == Direction.RIGHT) {
                s2 = "Up_L";
            } else {
                s2 = "Up_R";
            }
        } else if (direction == Direction.DOWN) {
            if (this.prevDirection == Direction.LEFT) {
                s2 = "Down_L";
            } else {
                s2 = "Down_R";
            }
        }

        if (isMoving()) {
            prevWalkState += 1;
            if (prevWalkState == NUM_WALK_STATES) {
                prevWalkState = 0;
            }
        }
        if (prevWalkState < NUM_WALK_STATES / 2) {
            s3 = "1";
        } else {
            s3 = "2";
        }

        String string = String.format("%s_%s%s.png", s1, s2, s3);

        return Images.get(string);
    }

    public Vector2 getLocation() {
        return new Vector2(location.getX(), location.getY());
    }

    public Direction getPrevDirection() {
        return prevDirection;
    }

    public Direction getDirection() {
        return direction;
    }

    public double getSpeed() {
        return speed;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public boolean isDigging() {
        Vector2 front = Vector2Utility.add(this.getFront(),
                                           this.direction.getVector());
        return isMoving && !this.board.isDivEmpty(front);
    }

    public void move(Direction direction) {
        if (this.isShooting && !this.gun.isPumping()) {
            this.stop();
        } else {
            if (this.isShooting && this.gun != null) {
                this.gun.destroy();
            }
            if (direction == Direction.UP) {
                this.goUp();
            } else if (direction == Direction.DOWN) {
                this.goDown();
            } else if (direction == Direction.LEFT) {
                this.goLeft();
            } else if (direction == Direction.RIGHT) {
                this.goRight();
            } else {
                this.stop();
            }
        }
        this.board.makeHole(this.getFront(), direction);
    }

    /**
     * Does nothing it is just for the override
     */
    @Override
    public void move() {

    }

    /**
     * This method will have the character goUp according to the individual
     * character's speed and location, changing its direction to up. When the
     * driller is not aligned with any column it will move in its current
     * direction to the next column.
     */
    public void goUp() {
        //this.location.setX(Math.round(this.location.getX()));
        if (location.getY() > Vector2Utility.EPSILON) {
            if (this.direction == Direction.LEFT && !Vector2Utility.isNearTile(
                    this.location)) {
                this.location.setX(this.location.getX() - speed);
            } else if (this.direction == Direction.RIGHT && !Vector2Utility.isNearTile(
                    this.location)) {
                this.location.setX(this.location.getX() + speed);
            } else {
                location.setX(this.getTile().getX() * Vector2.DIVS_PER_TILE);
                location.setY(location.getY() - speed);
                if (direction != Direction.UP) {
                    if (direction != Direction.DOWN) {
                        this.prevDirection = direction;
                    } else if (direction != Direction.UP) {
                        if (prevDirection == Direction.LEFT) {
                            this.prevDirection = Direction.RIGHT;
                        } else {
                            this.prevDirection = Direction.LEFT;
                        }
                    }
                    this.direction = Direction.UP;
                }
            }
            this.isMoving = true;
        }
    }

    /**
     * This method will have the character goUp according to the individual
     * character's speed and location, changing its direction to up. When the
     * driller is not aligned with any column it will move in its current
     * direction to the next column.
     */
    public void goDown() {
        //this.location.setX(Math.round(this.location.getX()));
        if (location.getY() < Vector2.MAX_Y * Vector2.DIVS_PER_TILE - Vector2Utility.EPSILON) {
            if (this.direction == Direction.LEFT && !Vector2Utility.isNearTile(
                    this.location)) {
                this.location.setX(this.location.getX() - speed);
            } else if (this.direction == Direction.RIGHT && !Vector2Utility.isNearTile(
                    this.location)) {
                this.location.setX(this.location.getX() + speed);
            } else {
                location.setX(this.getTile().getX() * Vector2.DIVS_PER_TILE);
                location.setY(location.getY() + speed);
                if (direction != Direction.DOWN) {
                    if (direction != Direction.UP) {
                        this.prevDirection = direction;
                    } else if (direction != Direction.DOWN) {
                        if (prevDirection == Direction.LEFT) {
                            this.prevDirection = Direction.RIGHT;
                        } else {
                            this.prevDirection = Direction.LEFT;
                        }
                    }
                    this.direction = Direction.DOWN;
                }
            }
            this.isMoving = true;
        }
    }

    /**
     * This method will have the character goUp according to the individual
     * character's speed and location, changing its direction to up. When the
     * driller is not aligned with any row it will move in its current direction
     * to the next row.
     */
    public void goLeft() {
        //this.location.setX(Math.round(this.location.getX()));

        if (this.direction == Direction.UP && !Vector2Utility.isNearTile(
                this.location)) {
            this.location.setY(this.location.getY() - speed);
        } else if (this.direction == Direction.DOWN && !Vector2Utility.isNearTile(
                this.location)) {
            this.location.setY(this.location.getY() + speed);
        } else {
            location.setY(this.getTile().getY() * Vector2.DIVS_PER_TILE);
            location.setX(location.getX() - speed);
            if (this.location.getX() < 0) {
                this.location.setX(0);
            }
            if (direction != Direction.LEFT) {
                this.prevDirection = direction;
                this.direction = Direction.LEFT;
            }
        }
        this.isMoving = true;
    }

    /**
     * This method will have the character goUp according to the individual
     * character's speed and location, changing its direction to up. When the
     * driller is not aligned with any row it will move in its current direction
     * to the next row.
     */
    public void goRight() {
        //this.location.setX(Math.round(this.location.getX()));

        if (this.direction == Direction.UP && !Vector2Utility.isNearTile(
                this.location)) {
            this.location.setY(this.location.getY() - speed);
        } else if (this.direction == Direction.DOWN && !Vector2Utility.isNearTile(
                this.location)) {
            this.location.setY(this.location.getY() + speed);
        } else {
            location.setY(this.getTile().getY() * Vector2.DIVS_PER_TILE);
            location.setX(location.getX() + speed);
            if (this.location.getX() < 0) {
                this.location.setX(0);
            }
            if (direction != Direction.RIGHT) {
                this.prevDirection = direction;
                this.direction = Direction.RIGHT;
            }
        }
        this.isMoving = true;
    }

    public void stop() {
        this.isMoving = false;
    }

    public void shoot(boolean pump) {
        if (!isShooting && pump) {
            isShooting = true;
            this.gun = new Gun(location, direction, board);
        } else if (isShooting) {
            this.gun.shoot(pump);
        }
    }

    /**
     * Gets the location of the Driller, what cell is he most in.
     *
     * @return Vector2
     */
    public Vector2 getCurrentCell() {
        Vector2 curCell = new Vector2(0, 0);
        if (this.direction == Direction.DOWN || this.direction == Direction.UP) {
            curCell.setX(location.getX());
            //curCell[1] = (direction == Direction.UP) ? (int) Math.ceil(
            //        location.getY()) : (int) Math.floor(location.getY());
            curCell.setY(Math.round(location.getY()));
        } else {
            //curCell[0] = (direction == Direction.LEFT) ? (int) Math.ceil(
            //        location.getX()) : (int) Math.floor(location.getX());
            curCell.setX(Math.round(location.getX()));
            curCell.setY(location.getY());
        }
        return curCell;
    }

    /**
     * Current location of the front in divs
     *
     * @return Vector2 front location in divs
     */
    public Vector2 getFront() {
        Vector2 front = this.getDiv();
        if (this.direction == Direction.RIGHT) {
            front = Vector2Utility.add(front, Vector2Utility.scale(
                                       Direction.RIGHT.getVector(),
                                       Vector2.DIVS_PER_TILE));
        } else if (this.direction == Direction.DOWN) {
            front = Vector2Utility.add(front, Vector2Utility.scale(
                                       Direction.DOWN.getVector(),
                                       Vector2.DIVS_PER_TILE));
        }

        if (this.direction == Direction.RIGHT || this.direction == Direction.LEFT) {
            Vector2Utility.add(front, new Vector2(0, Vector2.DIVS_PER_TILE / 2));
        } else {
            Vector2Utility.add(front, new Vector2(Vector2.DIVS_PER_TILE / 2, 0));
        }

        return front;
    }

    /**
     * This will crush Mr Driller when at rock is above him and falling
     */
    public void crush() {
        this.isCrushed = true;
        this.deadTime = new Date();
    }

    /**
     * This will kill Mr Driller when an enemy collides into him
     */
    public void kill() {
        this.isKilled = true;
        this.deadTime = new Date();
    }

    /**
     * This is Mr Driller dead in any way
     *
     * @return boolean
     */
    public boolean isDead() {
        return this.isCrushed || this.isKilled;
    }

    public double timeSinceDeath() {
        return new Date().compareTo(deadTime) / 1000.0;
    }

}
