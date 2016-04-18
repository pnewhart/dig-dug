/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016 *
 * Name: Patrick Newhart, Levi Adair, Sam Greenberg, Tom Ficcadenti
 * Date: Apr 15, 2016
 * Time: 10:11:52 AM *
 * Project: csci205FinalProject
 * Package: Model
 * File: Enemy
 * Description:
 *
 * **************************************** */
package Model;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author laa024
 */
public class Enemy extends Pumpable {

    private final double INITIAL_SPEED = 0.5;

    private Direction direction;
    private GameBoard gBoard;
    private double speed;

    public Enemy(GameBoard b) {
        gBoard = b;
        this.speed = INITIAL_SPEED;
        this.direction = Direction.RIGHT;
    }

    public void walk() {
        if (gBoard.isDivEmpty(this.getFront())) {
            this.location = Vector2Utility.add(location, Vector2Utility.scale(
                                               this.direction.getVector(), speed));
        } else {
            ArrayList<Vector2> locations = new ArrayList<Vector2>();
            ArrayList<Direction> directions = new ArrayList<Direction>();

            Vector2 up = this.getDirection(Direction.UP);
            Vector2 down = this.getDirection(Direction.DOWN);
            Vector2 left = this.getDirection(Direction.LEFT);
            Vector2 right = this.getDirection(Direction.RIGHT);

            if (gBoard.isDivEmpty(up)) {
                locations.add(up);
                directions.add(Direction.UP);
            }
            if (gBoard.isDivEmpty(down)) {
                locations.add(down);
                directions.add(Direction.DOWN);
            }
            if (gBoard.isDivEmpty(left)) {
                locations.add(left);
                directions.add(Direction.LEFT);
            }
            if (gBoard.isDivEmpty(right)) {
                locations.add(right);
                directions.add(Direction.RIGHT);
            }

            Random r = new Random();

            int i = r.nextInt(locations.size());

            this.direction = directions.get(i);
            this.location = locations.get(i);
        }
    }

    /**
     * Current location of the direction in divs with respect to location
     *
     * @return Vector2 front location in divs
     */
    public Vector2 getDirection(Direction d) {
        Vector2 front = this.getDiv();
        if (d == Direction.RIGHT) {
            front = Vector2Utility.add(front, Vector2Utility.scale(
                                       Direction.RIGHT.getVector(),
                                       Vector2.DIVS_PER_TILE));
        } else if (d == Direction.DOWN) {
            front = Vector2Utility.add(front, Vector2Utility.scale(
                                       Direction.DOWN.getVector(),
                                       Vector2.DIVS_PER_TILE));
        }

        if (d == Direction.RIGHT || this.direction == Direction.LEFT) {
            Vector2Utility.add(front, new Vector2(0, Vector2.DIVS_PER_TILE / 2));
        } else {
            Vector2Utility.add(front, new Vector2(Vector2.DIVS_PER_TILE / 2, 0));
        }

        return front;
    }

    public Vector2 getFront() {
        return getDirection(this.direction);
    }

}
