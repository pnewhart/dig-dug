/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016 *
 * Name: Patrick Newhart, Levi Adair, Sam Greenberg, Tom Ficcadenti
 * Date: Apr 6, 2016
 * Time: 4:31:32 PM *
 * Project: csci205FinalProject
 * Package: Model
 * File: Vector2Utility
 * Description:
 *
 * **************************************** */
package Model;

/**
 *
 * @author spg011
 */
public class Vector2Utility {

    public static final double EPSILON = 0.75;

    public static Vector2 add(Vector2 vec1, Vector2 vec2) {
        return new Vector2(vec1.getX() + vec2.getX(), vec1.getY() + vec2.getY());
    }

    public static Vector2 negate(Vector2 vec) {
        return new Vector2(-vec.getX(), -vec.getY());
    }

    public static Vector2 divide(Vector2 vec, double div) {
        return new Vector2(vec.getX() / div, vec.getY() / div);
    }

    public static Vector2 roundDivide(Vector2 vec, double div) {
        return new Vector2((int) vec.getX() / div, (int) (vec.getY() / div));
    }

    public static Vector2 scale(Vector2 vec, double factor) {
        return new Vector2(factor * vec.getX(), factor * vec.getY());
    }

    public static boolean isNearTile(Vector2 vec) {
        boolean nearX = (Math.abs(vec.getX() - (Math.round(
                                                vec.getX() / Vector2.DIVS_PER_TILE) * Vector2.DIVS_PER_TILE)) < EPSILON);
        boolean nearY = (Math.abs(vec.getY() - (Math.round(
                                                vec.getY() / Vector2.DIVS_PER_TILE) * Vector2.DIVS_PER_TILE)) < EPSILON);
        return nearX && nearY;
    }

}
