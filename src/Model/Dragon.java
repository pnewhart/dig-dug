/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016 *
 * Name: Patrick Newhart, Levi Adair, Sam Greenberg, Tom Ficcadenti
 * Date: Apr 25, 2016
 * Time: 4:50:03 PM *
 * Project: csci205FinalProject
 * Package: Model
 * File: Dragon
 * Description:
 *
 * **************************************** */
package Model;

import java.awt.Image;

/**
 *
 * @author laa024
 */
public class Dragon extends Enemy {

    //private Image testImage;
    protected boolean isFire = false;
    private final double HALF_SECOND_NS = Math.pow(5, 9);

    public Dragon(Vector2 location) {
        this.speed = INITIAL_SPEED;
        this.direction = Direction.RIGHT;
        this.setDiv(location);
        this.direction = Direction.RIGHT;
        this.prevDirection = Direction.RIGHT;
    }

//    @Override
//    public void move() {
//        if (getBoard().isDivEmpty(this.getFront())) {
//            this.location = Vector2Utility.add(location, Vector2Utility.scale(
//                                               this.direction.getVector(), speed));
//        } else {
//            ArrayList<Vector2> locations = new ArrayList<Vector2>();
//            ArrayList<Direction> directions = new ArrayList<Direction>();
//
//            Vector2 up = this.getDirection(Direction.UP);
//            Vector2 down = this.getDirection(Direction.DOWN);
//            Vector2 left = this.getDirection(Direction.LEFT);
//            Vector2 right = this.getDirection(Direction.RIGHT);
//
//            if (getBoard().isDivEmpty(up)) {
//                locations.add(up);
//                directions.add(Direction.UP);
//            }
//            if (getBoard().isDivEmpty(down)) {
//                locations.add(down);
//                directions.add(Direction.DOWN);
//            }
//            if (getBoard().isDivEmpty(left)) {
//                locations.add(left);
//                directions.add(Direction.LEFT);
//            }
//            if (getBoard().isDivEmpty(right)) {
//                locations.add(right);
//                directions.add(Direction.RIGHT);
//            }
//
//            Random r = new Random();
//
//            int i = r.nextInt(locations.size() + 2);
//            if (i == (locations.size() + 1)) {
//                this.direction = this.direction;
//                this.location = this.location;
//
//            }
//            if (i == (locations.size() + 2)) {
//                this.floatToDriller(getBoard().getDrillerLocation());
//
//            }
//            if (direction == Direction.LEFT || direction == Direction.RIGHT) {
//                this.prevDirection = direction;
//            }
//            direction = directions.get(i);
//            this.location = locations.get(i);
//        }
//    }
    public boolean isIsFire() {
        return isFire;
    }

    public void setIsFire(boolean isFire) {
        this.isFire = isFire;
    }

    private void breatheFire() {
        this.setIsFire(true);
        long startTime = System.nanoTime();
        while (startTime < HALF_SECOND_NS) {
        }
        this.setIsFire(false);

    }

    @Override
    public Image getCurrentImage() {
        String dir = null;
        if (prevDirection == Direction.LEFT) {
            dir = "Left";
        } else {
            dir = "Right";
        }

        return Images.get("Fygar_" + dir + "_1.png");
    }

}
