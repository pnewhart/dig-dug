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
package ModelNew;

/**
 *
 * @author spg011
 */
public class Vector2 {

    public static final int DIVS_PER_TILE = 16;

    public static final int NUM_TILE_HORIZONTAL = 14;
    public static final int NUM_TILE_VERTICAL = 16;

    public static final int MAX_X = NUM_TILE_HORIZONTAL;
    public static final int MAX_Y = NUM_TILE_VERTICAL;

    public static final int MAX_DIV_X = NUM_TILE_HORIZONTAL * DIVS_PER_TILE;
    public static final int MAX_DIV_Y = NUM_TILE_VERTICAL * DIVS_PER_TILE;

    private int x;
    private int y;

    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;

    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    public String toString() {
        return String.format("<%d, %d>", this.x, this.y);
    }
}
