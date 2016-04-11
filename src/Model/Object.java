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

    protected Vector2 location = null;

    public void setDiv(Vector2 location) {
        this.location = location;
    }

    public Vector2 getTile() {
        return Vector2Utility.roundDivide(this.location, Vector2.DIVS_PER_TILE);
    }

    public Vector2 getDiv() {
        return new Vector2(this.location.getX(), this.location.getY());
    }

}
