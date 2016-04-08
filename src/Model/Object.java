/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016 *
 * Name: Patrick Newhart, Levi Adair, Sam Greenberg, Tom Ficcadenti
 * Date: Apr 7, 2016
 * Time: 3:43:01 PM *
 * Project: csci205FinalProject
 * Package: Model
 * File: Object
 * Description:
 *
 * **************************************** */
package Model;

/**
 *
 * @author laa024
 */
public abstract class Object {

    public Vector2 location = null;

    public Vector2 getTile() {
        return Vector2Utility.divide(this.location, Vector2.DIVS_PER_TILE);
    }

    public Vector2 getDiv() {
        return this.location;
    }

}
