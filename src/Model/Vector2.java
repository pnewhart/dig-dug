/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016 *
 * Name: Patrick Newhart, Levi Adair, Sam Greenberg, Tom Ficcadenti
 * Date: Apr 6, 2016
 * Time: 4:29:51 PM *
 * Project: csci205FinalProject
 * Package: Model
 * File: Vector2
 * Description:
 *
 * **************************************** */
package Model;

/**
 *
 * @author spg011
 */
public class Vector2 {

    public static final int MAX_X = 13; // BoardWidth - 1
    public static final int MAX_Y = 15; //BoardHeight - 1
    public static final double ROUND_FACTOR = 1000.0;

    private double x;
    private double y;

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setX(double x) {
        this.x = Math.round(x * ROUND_FACTOR) / ROUND_FACTOR;
    }

    public void setY(double y) {
        this.y = Math.round(y * ROUND_FACTOR) / ROUND_FACTOR;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    @Override
    public String toString() {
        return String.format("<%.3f, %.3f>", this.x, this.y);
    }

}
