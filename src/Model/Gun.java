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

import java.awt.Image;
import java.util.ArrayList;

/**
 *
 * @author spg011
 */
public class Gun extends BoardObject {

    public static final int MAX_LENGTH = 3;
    private static final double SPEED = 0.02;

    private double length;
    private Direction direction;
    private Direction prevDirection;
    private boolean destroyed;

    private boolean isPumping;

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

    public Gun(Vector2 location, Direction direction, Direction prevDirection) {
        this.length = 0.0;
        this.direction = direction;
        this.prevDirection = prevDirection;
        this.setDiv(location);
        this.destroyed = false;

        this.isPumping = false;
        this.board = board;
    }

    /**
     * Current location of the tip in divs
     *
     * @return Vector2 tip location in divs
     */
    public Vector2 getTip() {
        Vector2 tip = Vector2Utility.add(this.getDiv(), Vector2Utility.scale(
                                         direction.getVector(), length));
        tip = Vector2Utility.add(tip, new Vector2(Vector2.DIVS_PER_TILE / 2,
                                                  Vector2.DIVS_PER_TILE / 2));

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
            } else if (this.board.isPumpableObjectAt(tip)) {                 // If there is a pumpable object, set isPumping true
                ArrayList<Enemy> objects = this.board.returnPumpableObjectsAt(
                        tip);
                this.isPumping = true;
                this.pumpingObject = objects.get(0);
                this.pumpingObject.pump();
            } else {
                this.length += SPEED;                                        // Else increase length
            }
        } else {
            if (pump) {
                this.pumpingObject.pump();
            } else {
                this.pumpingObject.deflate();
                if (this.pumpingObject.isNotPumped()) {
                    this.destroyed = true;
                }
            }
        }
    }

    public void destroy() {
        if (this.isPumping) {
            this.pumpingObject = null;
        }
        this.destroyed = true;
    }

    public boolean isAlive() {
        return !this.destroyed;
    }

    @Override
    public void move() {

    }

    @Override
    public void crush() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Image getCurrentImage() {
        if (this.direction == Direction.LEFT) {
            return this.Images.get("Cable_Left_2.png");
        } else if (this.direction == Direction.RIGHT) {
            return this.Images.get("Cable_Right_2.png");
        } else if (this.direction == Direction.UP) {
            if (this.prevDirection == Direction.RIGHT) {
                return this.Images.get("Cable_Up_R2.png");
            } else {
                return this.Images.get("Cable_Up_L2.png");
            }
        } else if (this.direction == Direction.DOWN) {
            if (this.prevDirection == Direction.RIGHT) {
                return this.Images.get("Cable_Down_R2.png");
            } else {
                return this.Images.get("Cable_Down_L2.png");
            }
        } else {
            return null;
        }

    }
}
