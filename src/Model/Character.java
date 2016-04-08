/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016 *
 * Name: Patrick Newhart, Levi Adair, Sam Greenberg, Tom Ficcadenti
 * Date: Apr 6, 2016
 * Time: 10:48:42 AM *
 * Project: csci205FinalProject
 * Package: Model
 * File: Character
 * Description:
 *
 * **************************************** */
package Model;

/**
 *
 * @author spg011
 */
public abstract class Character extends Object {

    /**
     * Location in the Dig Dug board
     */
    protected Vector2 location;

    /**
     * Direction in the Dig Dug board (LEFT, RIGHT, UP, DOWN)
     */
    protected Direction direction;

    /**
     * Speed the character can move on the Dig Dug board
     */
    protected double speed;

    /**
     * Is the character moving
     */
    protected boolean isMoving;

    /**
     * The board that the character is within
     */
    protected GameBoard board;

    /**
     * This method will have the character goUp according to the individual
     * character's speed and location, changing its direction to up
     */
    public abstract void goUp();

    /**
     * This method will have the character goDown according to the individual
     * character's speed and location, changing its direction to up
     */
    public abstract void goDown();

    /**
     * This method will have the character goLeft according to the individual
     * character's speed and location, changing its direction to up
     */
    public abstract void goLeft();

    /**
     * This method will have the character goRight according to the individual
     * character's speed and location, changing its direction to up
     */
    public abstract void goRight();

    /**
     * This method will have the character goRight according to the individual
     * character's speed and location, changing its direction to up
     */
    public abstract void stop();

}
