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

    public static final int DIVS_PER_TILE = 18;

    public static final int NUM_TILE_HORIZONTAL = 14;
    public static final int NUM_TILE_VERTICAL = 16;

    public static final int MAX_X = NUM_TILE_HORIZONTAL - 1; // BoardWidth - 1
    public static final int MAX_Y = NUM_TILE_VERTICAL - 1; //BoardHeight - 1

    public static final int MAX_TILE_X = MAX_X * DIVS_PER_TILE; // BoardWidth - 1
    public static final int MAX_TILE_Y = MAX_Y * DIVS_PER_TILE; //BoardHeight - 1

    private double x;
    private double y;

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    private void adjust() {
        if (this.y > MAX_Y) {
            this.y = MAX_Y;
        } else if (this.y < 0) {
            this.y = 0.0;
        }
        if (this.x > MAX_X) {
            this.x = MAX_X;
        } else if (this.x < 0) {
            this.x = 0.0;
        }

    }

    @Override
    public String toString() {
        return String.format("<%d, %d>", this.x, this.y);
    }

}
