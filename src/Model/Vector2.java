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

    public static final int DIVS_PER_TILE = 16;
    public static final int PIXELS_PER_DIV = 3;

    public static final int NUM_TILE_HORIZONTAL = 14;
    public static final int NUM_TILE_VERTICAL = 17;

    public static final int MAX_X = NUM_TILE_HORIZONTAL - 1; // BoardWidth - 1
    public static final int MAX_Y = NUM_TILE_VERTICAL - 1; //BoardHeight - 1

    public static final int MAX_DIV_X = MAX_X * DIVS_PER_TILE; // BoardWidth - 1
    public static final int MAX_DIV_Y = MAX_Y * DIVS_PER_TILE; //BoardHeight - 1

    private double x;
    private double y;

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;

        //this.adjust();
    }

    public boolean equalTo(Vector2 other, int comp) {
        return Math.abs(this.x - other.getX()) < comp && Math.abs(
                this.y - other.getY()) < comp;
    }

    public void setX(double x) {
        this.x = x;
        //this.adjust();
    }

    public void setY(double y) {
        this.y = y;
        //this.adjust();
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public void adjust() {
        if (this.y > MAX_DIV_Y) {
            this.y = MAX_DIV_Y;
        } else if (this.y < 0) {
            this.y = 0.0;
        }
        if (this.x > MAX_DIV_X) {
            this.x = MAX_DIV_X;
        } else if (this.x < 0) {
            this.x = 0.0;
        }

    }

    @Override
    public String toString() {
        return String.format("<%.2f, %.2f>", this.x, this.y);
    }

}
