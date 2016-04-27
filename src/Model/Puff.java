/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016 *
 * Name: Patrick Newhart, Levi Adair, Sam Greenberg, Tom Ficcadenti
 * Date: Apr 25, 2016
 * Time: 4:50:09 PM *
 * Project: csci205FinalProject
 * Package: Model
 * File: Puff
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
public class Puff extends Enemy {

    public Puff(GameBoard b) {
        super(b);
    }

    @Override
    public void move() {
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

            int i = r.nextInt(locations.size() + 2);
            if (i == (locations.size() + 1)) {
                this.direction = this.direction;
                this.location = this.location;

            }
            if (i == (locations.size() + 2)) {
                this.floatToDriller(gBoard.getDrillerLocation());

            }
            this.direction = directions.get(i);
            this.location = locations.get(i);
        }
    }

}
