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

import java.io.File;

/**
 *
 * @author laa024
 */
public class Tile extends Object {

    private Hole leftHole = new Hole();
    private Hole rightHole = new Hole();
    private Hole upHole = new Hole();
    private Hole downHole = new Hole();
    private boolean clearedHorizontal = false;
    private boolean clearedVertical = false;
    private int xCoord;
    private String biome;
    private int layer;
    private File northDig = null;
    private File southDig = null;
    private File eastDig = null;
    private File westDig = null;
    private int northDigInt = 0;
    private int southDigInt = 0;
    private int eastDigInt = 0;
    private int westDigInt = 0;
    private boolean hasBeenUpdated = true;

    public boolean isHasBeenUpdated() {
        return hasBeenUpdated;
    }

    public void setHasBeenUpdated(boolean hasBeenUpdated) {
        this.hasBeenUpdated = hasBeenUpdated;
    }

    /**
     *
     * @param x
     * @param y
     */
    public Tile(int x, int y) {
        this.location = new Vector2(x, y);
    }

    /**
     *
     * @return string that represents biome
     */
    public String getBiome() {
        return biome;
    }

    /**
     *
     * @param biome
     */
    public void setBiome(String biome) {
        this.biome = biome;
    }

    /**
     *
     * @return
     */
    public int getLayer() {
        return layer;
    }

    /**
     *
     * @param layer
     */
    public void setLayer(int layer) {
        this.layer = layer;
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
            if (rightHole.getPercentFill() + leftHole.getPercentFill() > 19) {
                this.clearTileHorizontal();
            }
            if (upHole.getPercentFill() + downHole.getPercentFill() > 19) {
                this.clearTileVertical();
            }
            this.hasBeenUpdated = true;
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
        this.makeHole(dir, 19);

    }

    /**
     * clears the tile in the horizontal direction
     */
    protected void clearTileHorizontal() {

        rightHole.clearHole();
        leftHole.clearHole();
        clearedHorizontal = true;
    }

    /**
     * clears tile in vertical direction
     */
    public void clearTileVertical() {

        upHole.clearHole();
        downHole.clearHole();
        clearedVertical = true;
    }

    /**
     * checks if tile is cleared horizontally
     *
     * @return boolean
     */
    public boolean isClearedHorizontal() {
        if (upHole.isEmpty() && downHole.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * checks if tile is cleared vertically
     *
     * @return boolean
     */
    public boolean isClearedVertical() {
        if (leftHole.isEmpty() && rightHole.isEmpty()) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * used to check tiles
     */
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

        String returnString = String.format(" |%3d |", leastFill);
        System.out.print(returnString);

    }

    /**
     * Will not be called
     */
    @Override
    public void move() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void crush() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
