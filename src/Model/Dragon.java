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
        super(location);
    }

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

        if (isCrushed) {
            if (prevHorDirection == Direction.LEFT) {
                return Images.get("Fygar_Rock_Left.png");
            } else {
                return Images.get("Fygar_Rock_Right.png");
            }
        }

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

        if (isFloating) {
            dir = "Float";
        }

        return Images.get(
                "Fygar_" + dir + "_" + (1 + stepCount / (MAX_STEP_COUNT / 2)) + ".png");
    }

}
