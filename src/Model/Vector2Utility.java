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

    public static final double EPSILON = 0.1;

    public Vector2 add(Vector2 vec1, Vector2 vec2) {
        return new Vector2(vec1.getX() + vec2.getX(), vec1.getX() + vec2.getX());
    }

    public Vector2 negate(Vector2 vec) {
        return new Vector2(-vec.getX(), -vec.getX());
    }

    public Vector2 scale(Vector2 vec, double factor) {
        return new Vector2(factor * vec.getX(), factor * vec.getX());
    }

    public static boolean isNear(double num1, double num2) {
        return Math.abs(num1 - num2) < EPSILON;
    }

}
