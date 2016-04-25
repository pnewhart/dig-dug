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

import java.awt.Image;
import java.util.HashMap;

/**
 *
 * @author laa024
 */
public class Tile extends Object {

    private Hole leftHole = new Hole();
    private Hole rightHole = new Hole();
    private Hole upHole = new Hole();
    private Hole downHole = new Hole();
    private String baseImageKey;
    private boolean clearedHorizontal = false;
    private boolean clearedVertical = false;
    private String biome;
    private int layer;
    private boolean hasBeenUpdated = true;

    private Image image;

    public static HashMap<String, Image> TileImages;

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
        this.location = new Vector2(x * Vector2.DIVS_PER_TILE,
                                    y * Vector2.DIVS_PER_TILE);
        this.image = GameManager.loadAndResizeSprite("digEast19.png", 48, 48);
    }

    public Vector2 getLocation() {
        return location;
    }

    public static void loadAllImage(String name, Image image) {
        try {
            TileImages.put(name, image);
        } catch (NullPointerException e) {
            System.out.println(name);
        }
    }

    @Override
    public Image getCurrentImage() {
//        if (Math.max(this.leftHole.getPercentRemoved(),
//                     this.downHole.getPercentRemoved()) >= Math.max(
//                        this.upHole.getPercentRemoved(),
//                        this.downHole.getPercentRemoved())) {
//            if (this.leftHole.getPercentRemoved() == 0 && this.rightHole.getPercentRemoved() > 0) {
//                return TileImages.get(this.getRightHoleImageKey() + ".png");
//            } else if (this.rightHole.getPercentRemoved() == 0 && this.leftHole.getPercentRemoved() > 0) {
//                return TileImages.get(this.getLeftHoleImageKey() + ".png");
//            } else if (this.leftHole.getPercentRemoved() > 0 && this.rightHole.getPercentRemoved() > 0) {
//                return TileImages.get("digEast19.png");
//            }
//        } else if (Math.max(this.leftHole.getPercentRemoved(),
//                            this.downHole.getPercentRemoved()) < Math.max(
//                        this.upHole.getPercentRemoved(),
//                        this.downHole.getPercentRemoved())) {
//            if (this.upHole.getPercentRemoved() == 0 && this.downHole.getPercentRemoved() > 0) {
//                return TileImages.get(this.getDownHoleImageKey() + ".png");
//            } else if (this.downHole.getPercentRemoved() == 0 && this.upHole.getPercentRemoved() > 0) {
//                return TileImages.get(this.getUpHoleImageKey() + ".png");
//            } else if (this.downHole.getPercentRemoved() > 0 && this.upHole.getPercentRemoved() > 0) {
//                return TileImages.get("digNorth19.png");
//            }
//        }
        if (!this.isFull()) {
            return image;
        } else {
            return null;
        }
    }

    public String getBaseImageKey() {
        return baseImageKey;
    }

    public void setBaseImageKey(String baseImageKey) {
        this.baseImageKey = baseImageKey;
    }

    public String getLeftHoleImageKey() {
        return "digWest" + this.leftHole.getPercentRemoved();
    }

    public String getRightHoleImageKey() {
        return "digEast" + this.rightHole.getPercentRemoved();
    }

    public String getUpHoleImageKey() {
        return "digNorth" + this.upHole.getPercentRemoved();
    }

    public String getDownHoleImageKey() {
        return "digSouth" + this.downHole.getPercentRemoved();
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
    public boolean makeHole(Direction dir, int percentToDestroy) {
        try {
            if (dir == Direction.RIGHT) {
                return rightHole.destroy(percentToDestroy);
            }
            if (dir == Direction.LEFT) {
                return leftHole.destroy(percentToDestroy);
            }
            if (dir == Direction.UP) {
                return upHole.destroy(percentToDestroy);
            }
            if (dir == Direction.DOWN) {
                return downHole.destroy(percentToDestroy);
            }
            if (rightHole.getPercentRemoved() + leftHole.getPercentRemoved() > 19) {
                this.clearTileHorizontal();
                return true;
            }
            if (upHole.getPercentRemoved() + downHole.getPercentRemoved() > 19) {
                this.clearTileVertical();
                return true;
            }
            this.hasBeenUpdated = true;
        } catch (Exception e) {
            System.out.format(
                    "tried to remove %d dirt than available, right hole %d, left %d, up %d, down %d  going \n",
                    percentToDestroy, rightHole.getPercentRemoved(),
                    leftHole.getPercentRemoved(), upHole.getPercentRemoved(),
                    downHole.getPercentRemoved());

        }
        return false;

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
        if (leftHole.getPercentRemoved() < leastFill) {
            leastFill = leftHole.getPercentRemoved();
        }
        if (rightHole.getPercentRemoved() < leastFill) {
            leastFill = rightHole.getPercentRemoved();
        }
        if (upHole.getPercentRemoved() < leastFill) {
            leastFill = upHole.getPercentRemoved();
        }
        if (downHole.getPercentRemoved() < leastFill) {
            leastFill = downHole.getPercentRemoved();
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
