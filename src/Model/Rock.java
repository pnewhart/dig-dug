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

import java.util.ArrayList;

/**
 *
 * @author laa024
 */
public class Rock extends Object {

    private boolean isBroken = false;
    private final int SPEED = 32; //moves over an entire tile per half second
    private GameBoard gBoard;
    private boolean isFalling;
    private final double ONE_SECOND_NS = Math.pow(10, 9);
    protected boolean canCrush = true;
    protected boolean isCrushed = true;

    /**
     *
     * @param Gameboard b
     */
    public Rock(GameBoard b) {
        this.gBoard = b;

    }

    /**
     * checks to see if rock should fall
     *
     * @return true if rock should fall
     */
    public boolean shouldRockFall() {
        int x = ((int) this.location.getX());
        int y = ((int) this.location.getY()) - 1; // -1 to look at the tile below the rock
        if (this.gBoard.board[x][y].isClearedVertical() || this.gBoard.board[x][y].isEmpty() || this.gBoard.board[x][y].isClearedHorizontal()) {
            this.breakRock();
            return true;

        } else {
            return false;

        }

    }

    private Vector2 getBelow() {
        Vector2 below = Vector2Utility.add(this.location,
                                           Vector2Utility.scale(
                                                   Direction.DOWN.getVector(),
                                                   Vector2.DIVS_PER_TILE + 1));
        return below;
    }

    private void crushObjectBelow(ArrayList<Object> objectList) {
        for (int i = 0; i < objectList.size(); i++) {
            Object obj = objectList.get(i);
            if (obj.containsDiv((int) this.getBelow().getX(),
                                (int) this.getBelow().getY())) {
                if (obj.canCrush) {
                    obj.crush();
                }
                //Add other collisions for things that arent e

            }
        }

    }

    public boolean isIsBroken() {
        return isBroken;
    }

    public void breakRock() {
        long startTime = System.nanoTime();
        while (startTime < ONE_SECOND_NS) {
        }
        this.isBroken = true;
    }

    @Override
    public void move() {
        if (shouldRockFall() && !this.isFalling) {
            this.isFalling = true;
            this.location.setX(this.location.getX() + this.SPEED);
        } else if (shouldRockFall() && this.isFalling) {
            this.location.setX(this.location.getX() + this.SPEED);
        } else if (!shouldRockFall() && this.isFalling) {
            this.isBroken = true;
        }
    }

    @Override
    public void crush() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
