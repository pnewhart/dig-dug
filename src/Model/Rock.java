/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016 *
 * Name: Patrick Newhart, Levi Adair, Sam Greenberg, Tom Ficcadenti
 * Date: Apr 15, 2016
 * Time: 10:14:16 AM *
 * Project: csci205FinalProject
 * Package: Model
 * File: Rock
 * Description:
 *
 * **************************************** */
package Model;

/**
 *
 * @author laa024
 */
public class Rock extends Object {

    private boolean isBroken = false;
    private final int SPEED = 32; //moves over an entire tile per half second

    private boolean shouldFall() {
        if ((int) location.getY() - 1) {
        }
    }

}
