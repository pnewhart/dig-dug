/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016 *
 * Name: Patrick Newhart, Levi Adair, Sam Greenberg, Tom Ficcadenti
 * Date: Apr 6, 2016
 * Time: 10:52:44 AM *
 * Project: csci205FinalProject
 * Package: Model
 * File: Direction
 * Description:
 *
 * **************************************** */
package Model;

/**
 *
 * @author spg011
 */
public enum Direction {
    UP(0, -1), DOWN(0, 1), LEFT(-1, 0), RIGHT(1, 0);

    private Vector2 vector;

    Direction(int x, int y) {
        this.vector = new Vector2(x, y);
    }

    public Vector2 getVector() {
        return this.vector;
    }

}
