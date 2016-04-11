/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016 *
 * Name: Patrick Newhart, Levi Adair, Sam Greenberg, Tom Ficcadenti
 * Date: Apr 7, 2016
 * Time: 1:58:33 PM *
 * Project: csci205FinalProject
 * Package: Model
 * File: Gun
 * Description:
 *
 * **************************************** */
package Model;

import java.util.Date;

/**
 *
 * @author spg011
 */
public class Gun extends Object {

    public static final int MAX_LENGTH = 3;
    private static final double SPEED = 0.02;

    private double length;
    private Direction direction;
    private boolean destroyed;

    private Date prevPump;
    private int pumpCount;

    private Date timeCreated;
    private int prevTime;

    private GameBoard board;

    public int pumpCount() {
        return pumpCount;
    }

    public boolean isPumping() {
        return pumpCount > 0;
    }

    public double getLength() {
        return length;
    }

    public Direction getDirection() {
        return direction;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public Gun(Vector2 location, Direction direction, GameBoard board) {
        this.length = 0.0;
        this.direction = direction;
        this.timeCreated = new Date();
        this.location = location;
        this.destroyed = false;

        this.pumpCount = 0;
        this.prevPump = new Date();
        this.board = board;
    }

    /**
     * Will return double representing the number of seconds that the gun has
     * existed.
     *
     * @return double
     */
    public double curTime() {
        Date now = new Date();
        return now.compareTo(timeCreated);
    }

    public Vector2 getTip() {
        Vector2 tip = Vector2Utility.add(this.location, Vector2Utility.scale(
                                         direction.getVector(), length));
        if (this.direction == Direction.RIGHT) {
            tip = Vector2Utility.add(tip, Direction.RIGHT.getVector());
        } else if (this.direction == Direction.DOWN) {
            tip = Vector2Utility.add(tip, Direction.DOWN.getVector());
        }

        if (this.direction == Direction.RIGHT || this.direction == Direction.LEFT) {
            Vector2Utility.add(tip, new Vector2(0, 0.5));
        } else {
            Vector2Utility.add(tip, new Vector2(0.5, 0));
        }

        return tip;
    }

    public void shoot(boolean pump) {
        if (this.length >= MAX_LENGTH) {
            this.destroyed = true;
        } else if (!isPumping()) {
            this.length += SPEED;
        } else {
            if (pump) {
                //Get object from board for location tip
            } else {
                //IDK JUST FIGURE THIS OUT LATER
            }
        }
    }

}
