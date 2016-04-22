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

import java.util.ArrayList;
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

    private boolean isPumping;

    private Date timeCreated;

    private GameBoard board;

    private Enemy pumpingObject;

    public boolean isPumping() {
        return isPumping;
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

        this.isPumping = false;
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

    /**
     * Current location of the tip in divs
     *
     * @return Vector2 tip location in divs
     */
    public Vector2 getTip() {
        Vector2 tip = Vector2Utility.add(this.getDiv(), Vector2Utility.scale(
                                         direction.getVector(), length));
        if (this.direction == Direction.RIGHT) {
            tip = Vector2Utility.add(tip, Vector2Utility.scale(
                                     Direction.RIGHT.getVector(),
                                     Vector2.DIVS_PER_TILE));
        } else if (this.direction == Direction.DOWN) {
            tip = Vector2Utility.add(tip, Vector2Utility.scale(
                                     Direction.DOWN.getVector(),
                                     Vector2.DIVS_PER_TILE));
        }

        if (this.direction == Direction.RIGHT || this.direction == Direction.LEFT) {
            Vector2Utility.add(tip, new Vector2(0, Vector2.DIVS_PER_TILE / 2));
        } else {
            Vector2Utility.add(tip, new Vector2(Vector2.DIVS_PER_TILE / 2, 0));
        }

        return tip;
    }

    /**
     * Handles the pumping in the game
     *
     * @param pump
     */
    public void shoot(boolean pump) {
        Vector2 tip = this.getTip();
        if (this.length >= MAX_LENGTH) {
            this.destroyed = true;
        } else if (!isPumping()) {
            if (!this.board.isDivEmpty(tip)) { // If there is a wall destroy the gun
                this.destroyed = true;
            } else if (this.isPumping()) {
                if (pump) {
                    this.pumpingObject.pump();
                } else {
                    this.pumpingObject.deflate();
                }
            } else if (this.board.isPumpableObjectAt(tip)) {                 // If there is a pumpable object, set isPumping true
                ArrayList<Enemy> objects = this.board.returnPumpableObjectsAt(
                        tip);
                this.isPumping = true;
                this.pumpingObject = objects.get(0);
                this.pumpingObject.pump();
            }
        } else {                                                            // Else increase length
            this.length += SPEED;
        }
    }

    @Override
    public void move() {

    }
}
