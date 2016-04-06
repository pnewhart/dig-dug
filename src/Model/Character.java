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
public abstract class Character {

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
