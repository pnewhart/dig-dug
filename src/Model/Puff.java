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

import java.awt.Image;

/**
 *
 * @author laa024
 */
public class Puff extends Enemy {

    public Puff(Vector2 location) {
        super(location);
    }

    @Override
    public Image getCurrentImage() {
        String dir = null;
        if (direction == Direction.UP || direction == Direction.DOWN) {
            if (prevHorDirection == Direction.LEFT) {
                dir = "Left";
            } else {
                dir = "Right";
            }
        } else {
            if (direction == Direction.LEFT) {
                dir = "Left";
            } else {
                dir = "Right";
            }
        }

        return Images.get(
                "Pooka_" + dir + "_" + (1 + stepCount / (MAX_STEP_COUNT / 2)) + ".png");
    }
}
