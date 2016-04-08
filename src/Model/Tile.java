/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016 *
 * Name: Patrick Newhart, Levi Adair, Sam Greenberg, Tom Ficcadenti
 * Date: Apr 7, 2016
 * Time: 1:43:10 PM *
 * Project: csci205FinalProject
 * Package: Model
 * File: Tile
 * Description:
 *
 * **************************************** */
package Model;

/**
 *
 * @author laa024
 */
public class Tile implements Object {

    private Hole leftHole = new Hole();
    private Hole rightHole = new Hole();
    private Hole upHole = new Hole();
    private Hole downHole = new Hole();
    private boolean clearedHorizontal = false;
    private boolean clearedVertical = false;

    public Tile() {

    }

    /**
     * destroys part of a hole in a certain direction
     *
     * @param dir
     * @param percentToDestroy
     */
    public void makeHole(Direction dir, int percentToDestroy) {
        try {
            if (dir == Direction.RIGHT) {
                rightHole.destroy(percentToDestroy);
            }
            if (dir == Direction.LEFT) {
                leftHole.destroy(percentToDestroy);
            }
            if (dir == Direction.UP) {
                upHole.destroy(percentToDestroy);
            }
            if (dir == Direction.DOWN) {
                downHole.destroy(percentToDestroy);
            }
            if (rightHole.getPercentFill() + leftHole.getPercentFill() > 100) {
                this.clearTileHorizontal();
            }
            if (upHole.getPercentFill() + downHole.getPercentFill() > 100) {
                this.clearTileVertical();
            }

        } catch (Exception e) {
            System.out.println("tried to remove more dirt than available");

        }

    }

    /**
     *
     * @return true if tile is full
     */
    public boolean isFull() {
        if (leftHole.isFull() && rightHole.isFull() && upHole.isFull() && downHole.isFull()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @return true if tile is empty
     */
    public boolean isEmpty() {
        if (leftHole.isEmpty() && rightHole.isEmpty() && upHole.isEmpty() && downHole.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * clears the tile in a specific direction
     *
     * @param dir
     */
    protected void clearTile(Direction dir) {
        this.makeHole(dir, 100);

    }

    protected void clearTileHorizontal() {

        rightHole.clearHole();
        leftHole.clearHole();
        clearedHorizontal = true;
    }

    public void clearTileVertical() {

        upHole.clearHole();
        downHole.clearHole();
        clearedVertical = true;
    }

    public void printOut() {
        int leastFill = 100;
        if (leftHole.getPercentFill() < leastFill) {
            leastFill = leftHole.getPercentFill();
        }
        if (rightHole.getPercentFill() < leastFill) {
            leastFill = rightHole.getPercentFill();
        }
        if (upHole.getPercentFill() < leastFill) {
            leastFill = upHole.getPercentFill();
        }
        if (downHole.getPercentFill() < leastFill) {
            leastFill = downHole.getPercentFill();
        }

        String returnString = String.format(" |%20d| ", leastFill);
        System.out.print(returnString);
    }
}
