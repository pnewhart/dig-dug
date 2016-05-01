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

    /**
     *
     * @return opposite direction of current direction
     */
    public Direction getOpposite() {
        if (this == UP) {
            return DOWN;
        } else if (this == DOWN) {
            return UP;
        } else if (this == RIGHT) {
            return LEFT;
        } else if (this == LEFT) {
            return RIGHT;
        } else {
            return null;
        }
    }

    /**
     *
     * @return true if direction is horizontal
     */
    public boolean isHorizontal() {
        return (this == RIGHT || this == LEFT);
    }

    /**
     *
     * @return true if direction is up or down
     */
    public boolean isVertical() {
        return (this == UP || this == DOWN);
    }

    /**
     * returns true if direction given is complementary to another direction
     *
     * @param other
     * @return
     */
    public boolean isComplementaryTo(Direction other) {
        if (other == null) {
            return false;
        } else {
            return (this.isHorizontal() && other.isVertical()) || (this.isVertical() && other.isHorizontal());
        }
    }

}
