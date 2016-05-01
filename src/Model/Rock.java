/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016 *
 * Name: Patrick Newhart, Levi Adair, Sam Greenberg, Tom Ficcadenti
 * Date: Apr 15, 2016
 * Time: 10:14:16 AM *
 * Project: csci205FinalProject
 * Package: Model
 * File: Rock
 * Description:
 *
 * **************************************** */
package Model;

import java.awt.Image;
import java.util.ArrayList;

/**
 *
 * @author laa024
 */
public class Rock extends BoardObject {

    private double speed;
    private boolean isBroken = false;
    private final static double SPEED = 1.2; //moves over an entire tile per half second
    private boolean isFalling;
    private int wiggleCount;
    private static final int MAX_WIGGLES = 64;
    private int brokenCount;
    private static final int MAX_BROKEN = 64;

    protected ArrayList<BoardObject> crushedObjects;

    /**
     *
     * @param Gameboard b
     */
    public Rock(Vector2 loc) {
        speed = SPEED;
        this.setDiv(loc);
        isFalling = false;
        isBroken = false;
        brokenCount = 0;
        wiggleCount = 0;
    }

    /**
     * checks to see if rock should fall
     *
     * @return true if rock should fall
     */
    public boolean shouldRockFall() {
        int x = ((int) this.getDiv().getX()) / Vector2.DIVS_PER_TILE;
        int y = ((int) this.getDiv().getY()) / Vector2.DIVS_PER_TILE + 1; // -1 to look at the tile below the rock
        if (getBoard().board[x][y].isClearedVertical() || getBoard().board[x][y].isEmpty() || getBoard().board[x][y].isClearedHorizontal()) {
            this.breakRock();
            return true;

        } else {
            return false;

        }

    }

    public Vector2 getBottom() {
        Vector2 loc = getDiv();
        loc = Vector2Utility.add(loc, Vector2Utility.scale(new Vector2(1, 1),
                                                           Vector2.DIVS_PER_TILE / 2));
        loc = Vector2Utility.add(loc,
                                 Vector2Utility.scale(Direction.DOWN.getVector(),
                                                      Vector2.DIVS_PER_TILE / 2 + speed));

        return loc;
    }

    public Vector2 getBottomLeft() {
        Vector2 loc = getDiv();

        loc = Vector2Utility.add(loc, Vector2Utility.scale(
                                 Direction.DOWN.getVector(),
                                 Vector2.DIVS_PER_TILE));

        return loc;
    }

    private void crushObjectsBelow() {
        for (Enemy enemy : BoardObject.getBoard().enemyList) {
            this.crushObject(enemy);
        }
        this.crushObject(getBoard().driller);
    }

    @Override
    public void crush() {

    }

    public boolean isIsBroken() {
        return isBroken;
    }

    public void breakRock() {
        this.isBroken = true;
    }

    @Override
    public void move() {
        if (shouldRockFall() && !this.isFalling && wiggleCount < MAX_WIGGLES && !isBroken) {
            this.wiggleCount += 1;
        } else if (shouldRockFall() && !this.isFalling && wiggleCount >= MAX_WIGGLES && !isBroken) {
            this.isFalling = true;
        } else if (shouldRockFall() && this.isFalling && !isBroken) {
            this.getDiv().setY(this.getDiv().getY() + this.speed);
            this.crushObjectsBelow();
            for (BoardObject obj : this.crushedObjects) {
                obj.setDiv(this.getBottomLeft());
            }
        } else if (!shouldRockFall() && this.isFalling && !isBroken) {
            this.isBroken = true;
            this.isFalling = false;
        } else if (isBroken && brokenCount < MAX_BROKEN) {
            brokenCount += 1;
        } else if (brokenCount >= MAX_BROKEN) {
        }
    }

    public void crushObject(BoardObject other) {
        if ((other instanceof Enemy || other instanceof Driller) && this.isCollidedWith(
                other)) {
            other.crush();
            this.crushedObjects.add(other);
        }
    }

    @Override
    public Image getCurrentImage() {
        if (!isFalling && wiggleCount > 0) {
            return Images.get(String.format("Rock_%d.png",
                                            (wiggleCount / (MAX_WIGGLES / 8)) % 2 + 1));
        } else if (isBroken) {
            return Images.get(String.format("Rock_Ground_%d.png",
                                            (brokenCount / (MAX_BROKEN / 2)) % 2 + 1));
        } else {
            return Images.get("Rock_2.png");
        }
    }
}
