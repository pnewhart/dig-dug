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

/**
 *
 * @author spg011
 */
public class Driller extends BoardObject {

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
    private boolean isShooting;
    private boolean isDigging;
    private Gun gun;

    private boolean isCrushed;
    private boolean isKilled;

    private int deadCount;
    private final int MAX_DEAD_COUNT = 5;

    private int prevWalkState;
    private final static int NUM_WALK_STATES = 8;

    private int lives;
    private static ScoreKeeper score = new ScoreKeeper();

    public Driller() {
        initDriller(3);
    }

    private void initDriller(int lives) {
        this.setDiv(new Vector2(
                (Vector2.NUM_TILE_HORIZONTAL / 2 - 1) * Vector2.DIVS_PER_TILE,
                (Vector2.NUM_TILE_VERTICAL / 2 - 1) * Vector2.DIVS_PER_TILE));
        this.prevDirection = Direction.DOWN;
        this.direction = Direction.RIGHT;
        this.speed = 1.0;
        this.isMoving = false;
        this.isShooting = false;
        this.isDigging = false;
        this.gun = null;

        this.isCrushed = false;
        this.isKilled = false;
        this.deadCount = 0;

        this.prevWalkState = 0;

        this.lives = lives;
    }

    @Override
    public Image getCurrentImage() {
        String s1 = "";
        String s2 = "";
        String s3 = "";

        if (isDead()) {
            s1 = "Dead";
        } else if (isDigging()) {
            s1 = "Digger";
        } else if (this.gun != null && this.gun.isPumping()) {
            s1 = "Pumper";
        } else {
            s1 = "Walker";
        }

        if (direction != null) {
            switch (direction) {
                case LEFT:
                    s2 = "Left_";
                    break;
                case RIGHT:
                    s2 = "Right_";
                    break;
                case UP:
                    if (this.prevDirection == Direction.RIGHT) {
                        s2 = "Up_L";
                    } else {
                        s2 = "Up_R";
                    }
                    break;
                case DOWN:
                    if (this.prevDirection == Direction.LEFT) {
                        s2 = "Down_L";
                    } else {
                        s2 = "Down_R";
                    }
                    break;
                default:
                    break;
            }
        }

        if (isMoving()) {
            prevWalkState += 1;
            if (prevWalkState == NUM_WALK_STATES) {
                prevWalkState = 0;
            }
        }
        if (isCrushed) {
            if (prevDirection == Direction.LEFT) {
                return this.Images.get("Dead_Rock_Left.png");
            } else if (prevDirection == Direction.RIGHT) {
                return this.Images.get("Dead_Rock_Right.png");
            }
        } else if (isDead()) {
            s3 = String.valueOf(1 + deadCount / 12);
        } else if (prevWalkState < NUM_WALK_STATES / 2) {
            s3 = "1";
        } else {
            s3 = "2";
        }

        String string = String.format("%s_%s%s.png", s1, s2, s3);

        //System.out.println(string);
        return this.Images.get(string);
    }

    public int getLives() {
        return lives;
    }

    /**
     * removes life from driller
     */
    public void killDriller() {
        this.lives -= 1;
    }

    public static int getScore() {
        return score.getCurrentScore();
    }

    public static String getScoreAsString() {
        return score.getScoreAsString();
    }

    public static void clearScore() {
        score.addToScore(score.getCurrentScore() * -1);
    }

    public static void addToScore(int scoreToBeAdded) {
        score.addToScore(scoreToBeAdded);
    }

    public Vector2 getLocation() {
        return new Vector2(getDiv().getX(), getDiv().getY());
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
        return this.isDigging && getDiv().getY() >= 1;
    }

    /**
     * moves the driller in a specified direction
     *
     * @param direction
     */
    public void move(Direction direction) {
        try {
            if (!isDead()) {
                if ((this.isShooting && !this.gun.isPumping())) {
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
                if (direction != null) {
                    this.isDigging = getBoard().digHole(this.getFront(),
                                                        this.direction);
                }
            } else {
                deadCount += 1;
            }
        } catch (Exception e) {
            System.out.println("Driller move failed");
        }
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
        if (getDiv().getY() > Vector2Utility.EPSILON) {
            if (this.direction == Direction.LEFT && !Vector2Utility.isNearTile(
                    this.getDiv())) {
                this.setDiv(new Vector2(this.getDiv().getX() - speed,
                                        getDiv().getY()));
            } else if (this.direction == Direction.RIGHT && !Vector2Utility.isNearTile(
                    this.getDiv())) {
                this.setDiv(new Vector2(this.getDiv().getX() + speed,
                                        this.getDiv().getY()));
            } else if (!getBoard().isRockAt(getDirection(Direction.UP))) {
                setDiv(new Vector2(this.getTile().getX() * Vector2.DIVS_PER_TILE,
                                   getDiv().getY() - speed));
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
     * This method will have the character goDown according to the individual
     * character's speed and location, changing its direction to up. When the
     * driller is not aligned with any column it will move in its current
     * direction to the next column.
     */
    public void goDown() {
        if (getDiv().getY() < Vector2.MAX_Y * Vector2.DIVS_PER_TILE - Vector2Utility.EPSILON) {
            if (this.direction == Direction.LEFT && !Vector2Utility.isNearTile(
                    this.getDiv())) {
                this.setDiv(new Vector2(this.getDiv().getX() - speed,
                                        getDiv().getY()));
            } else if (this.direction == Direction.RIGHT && !Vector2Utility.isNearTile(
                    this.getDiv())) {
                this.setDiv(new Vector2(this.getDiv().getX() + speed,
                                        this.getDiv().getY()));
            } else if (!getBoard().isRockAt(getDirection(Direction.DOWN))) {
                setDiv(new Vector2(this.getTile().getX() * Vector2.DIVS_PER_TILE,
                                   getDiv().getY() + speed));
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
     * This method will have the character goLeft according to the individual
     * character's speed and location, changing its direction to up. When the
     * driller is not aligned with any row it will move in its current direction
     * to the next row.
     */
    public void goLeft() {
        //this.location.setX(Math.round(this.location.getX()));

        if (this.direction == Direction.UP && !Vector2Utility.isNearTile(
                this.getDiv())) {
            this.setDiv(new Vector2(getDiv().getX(),
                                    this.getDiv().getY() - speed));
        } else if (this.direction == Direction.DOWN && !Vector2Utility.isNearTile(
                this.getDiv())) {
            this.setDiv(new Vector2(getDiv().getX(),
                                    this.getDiv().getY() + speed));
        } else if (!getBoard().isRockAt(getDirection(Direction.LEFT))) {
            setDiv(new Vector2(getDiv().getX() - speed,
                               this.getTile().getY() * Vector2.DIVS_PER_TILE));
            if (this.getDiv().getX() < 0) {
                this.getDiv().setX(0);
            }
            if (direction != Direction.LEFT) {
                this.prevDirection = direction;
                this.direction = Direction.LEFT;
            }
        }
        this.isMoving = true;
    }

    /**
     * This method will have the character goRight according to the individual
     * character's speed and location, changing its direction to up. When the
     * driller is not aligned with any row it will move in its current direction
     * to the next row.
     */
    public void goRight() {
        //this.location.setX(Math.round(this.location.getX()));

        if (this.direction == Direction.UP && !Vector2Utility.isNearTile(
                this.getDiv())) {
            this.setDiv(new Vector2(getDiv().getX(),
                                    this.getDiv().getY() - speed));
        } else if (this.direction == Direction.DOWN && !Vector2Utility.isNearTile(
                this.getDiv())) {
            this.setDiv(new Vector2(getDiv().getX(),
                                    this.getDiv().getY() + speed));
        } else if (!getBoard().isRockAt(getDirection(Direction.RIGHT))) {
            setDiv(new Vector2(getDiv().getX() + speed,
                               this.getTile().getY() * Vector2.DIVS_PER_TILE));
            if (this.getDiv().getX() < 0) {
                this.getDiv().setX(0);
            }
            if (direction != Direction.RIGHT) {
                this.prevDirection = direction;
                this.direction = Direction.RIGHT;
            }
        }
        this.isMoving = true;
    }

    /**
     * stops the driller from moving
     */
    public void stop() {
        this.isMoving = false;
    }

    /**
     * shoots the drillers gun
     *
     * @param pump
     */
    public void shoot(boolean pump) {

        if (!isShooting && pump) {
            isShooting = true;
            this.gun = new Gun(getDiv(), direction, prevDirection);
        } else if (isShooting && this.gun.isAlive()) {
            this.gun.shoot(pump);
        } else {
            this.gun = null;
        }
    }

    /**
     * Gets the gun object
     *
     * @return
     */
    public Gun getGun() {
        return this.gun;
    }

    /**
     * Gets the location of the Driller, what cell is he most in.
     *
     * @return Vector2
     */
    public Vector2 getCurrentCell() {
        Vector2 curCell = new Vector2(0, 0);
        if (this.direction == Direction.DOWN || this.direction == Direction.UP) {
            curCell.setX(getDiv().getX());
            //curCell[1] = (direction == Direction.UP) ? (int) Math.ceil(
            //        location.getY()) : (int) Math.floor(location.getY());
            curCell.setY(Math.round(getDiv().getY()));
        } else {
            //curCell[0] = (direction == Direction.LEFT) ? (int) Math.ceil(
            //        location.getX()) : (int) Math.floor(location.getX());
            curCell.setX(Math.round(getDiv().getX()));
            curCell.setY(getDiv().getY());
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
                                       Vector2.DIVS_PER_TILE - 1));
        } else if (this.direction == Direction.DOWN) {
            front = Vector2Utility.add(front, Vector2Utility.scale(
                                       Direction.DOWN.getVector(),
                                       Vector2.DIVS_PER_TILE - 1));
        }

        return front;
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
                                                           Vector2.DIVS_PER_TILE / 2 + 1));

        return loc;
    }

    /**
     * This will crush Mr Driller when at rock is above him and falling
     */
    public void crush() {
        this.isCrushed = true;

    }

    /**
     * This will kill Mr Driller when an enemy collides into him
     */
    public void kill() {
        this.isKilled = true;

    }

    /**
     * This is Mr Driller dead in any way
     *
     * @return boolean
     */
    public boolean isDead() {
        return this.isCrushed || this.isKilled;

    }

    public void destroy() {
        initDriller(lives - 1);
    }

}
