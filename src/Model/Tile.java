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

/**
 *
 * @author laa024
 */
public class Tile extends Object {

    private Hole leftHole = new Hole(Direction.LEFT);
    private Hole rightHole = new Hole(Direction.RIGHT);
    private Hole upHole = new Hole(Direction.UP);
    private Hole downHole = new Hole(Direction.DOWN);
    private String baseImageKey;
    private boolean clearedHorizontal = false;
    private boolean clearedVertical = false;
    private String biome;
    private int layer;

    public Hole getLeftHole() {
        return leftHole;
    }

    public void setLeftHole(Hole leftHole) {
        this.leftHole = leftHole;
    }

    public Hole getRightHole() {
        return rightHole;
    }

    public void setRightHole(Hole rightHole) {
        this.rightHole = rightHole;
    }

    public Hole getUpHole() {
        return upHole;
    }

    public void setUpHole(Hole upHole) {
        this.upHole = upHole;
    }

    public Hole getDownHole() {
        return downHole;
    }

    public void setDownHole(Hole downHole) {
        this.downHole = downHole;
    }

    /**
     *
     * @param x
     * @param y
     */
    public Tile(int x, int y) {
        this.location = new Vector2(x * Vector2.DIVS_PER_TILE,
                                    y * Vector2.DIVS_PER_TILE);
    }

    public Vector2 getLocation() {
        return location;
    }

//
//    public void loadRightImage(String name, Image image) {
//        TileRightImages.put(name, image);
//    }
//
//    public void loadLeftImage(String name, Image image) {
//        TileLeftImages.put(name, image);
//    }
//
//    public void loadUpImage(String name, Image image) {
//        TileUpImages.put(name, image);
//    }
//
//    public void loadDownImage(String name, Image image) {
//        TileDownImages.put(name, image);
//    }
    public Image[] getCurrentImages() {
        Image[] images = {Images.get(getRightHoleImageKey()), Images.get(
                          getLeftHoleImageKey()),
                          Images.get(getUpHoleImageKey()), Images.get(
                          getDownHoleImageKey())};
        return images;
    }

    public String getBaseImageKey() {
        return baseImageKey;
    }

    public void setBaseImageKey(String baseImageKey) {
        this.baseImageKey = baseImageKey;
    }

    public String getLeftHoleImageKey() {
        if (this.leftHole.getPercentRemoved() > 0) {
            return "digWest" + this.leftHole.getPercentRemoved() + ".png";
        }
        return null;
    }

    public String getRightHoleImageKey() {
        if (this.rightHole.getPercentRemoved() > 0) {
            return "digEast" + this.rightHole.getPercentRemoved() + ".png";
        }
        return null;
    }

    public String getUpHoleImageKey() {
        if (this.upHole.getPercentRemoved() > 0) {
            return "digNorth" + this.upHole.getPercentRemoved() + ".png";
        }
        return null;
    }

    public String getDownHoleImageKey() {
        if (this.downHole.getPercentRemoved() > 0) {
            return "digSouth" + this.downHole.getPercentRemoved() + ".png";
        }
        return null;
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
    public boolean makeHole(Direction dir, Vector2 loc,
                            int percentToDestroy) {
        if (loc.getY() > 14) {

            try {
                if (dir == Direction.RIGHT) {

                    if (rightHole.isFull()) {
                        return rightHole.destroy(percentToDestroy);

                    } else {

                        if (loc.getX() >= (this.location.getX() + rightHole.getPercentRemoved())) {
                            return rightHole.destroy(percentToDestroy);

                        } else {
                            return false;
                        }

                    }
                }
                if (dir == Direction.LEFT) {

                    if (leftHole.isFull()) {
                        return leftHole.destroy(percentToDestroy);

                    } else {
                        if (loc.getX() >= (this.location.getX() + (Vector2.DIVS_PER_TILE - leftHole.getPercentRemoved()))) {
                            return leftHole.destroy(percentToDestroy);

                        } else {
                            return false;
                        }

                    }
                }
                if (dir == Direction.UP) {
                    if (upHole.isFull()) {
                        return upHole.destroy(percentToDestroy);

                    } else {

                        if (loc.getY() >= (this.location.getY() + (Vector2.DIVS_PER_TILE - upHole.getPercentRemoved()))) {
                            return upHole.destroy(percentToDestroy);

                        } else {
                            return false;
                        }

                    }
                }
                if (dir == Direction.DOWN) {
                    if (downHole.isFull()) {
                        return downHole.destroy(percentToDestroy);

                    } else {

                        if (loc.getY() >= (this.location.getY() - downHole.getPercentRemoved())) {
                            return downHole.destroy(percentToDestroy);

                        } else {
                            return false;
                        }

                    }
                }
                if (rightHole.getPercentRemoved() + leftHole.getPercentRemoved() > 19) {
                    this.clearTileHorizontal();
                    return false;
                }
                if (upHole.getPercentRemoved() + downHole.getPercentRemoved() > 19) {
                    this.clearTileVertical();
                    return false;
                }

            } catch (Exception e) {
                System.out.format(
                        "tried to remove %d dirt than available, right hole %d, left %d, up %d, down %d  going \n",
                        percentToDestroy, rightHole.getPercentRemoved(),
                        leftHole.getPercentRemoved(), upHole.getPercentRemoved(),
                        downHole.getPercentRemoved());

            }
            System.out.println(this.isClearedHorizontal());
            return false;
        } else {
            System.out.println(this.isClearedHorizontal());
            return false;
        }

    }

    public void checkForCorner() {
        //TODO check if there is a corner
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
        if ((leftHole.isEmpty() || rightHole.isEmpty()) && (upHole.isEmpty() || downHole.isEmpty())) {
            rightHole.clearHole();
            leftHole.clearHole();
            upHole.clearHole();
            downHole.clearHole();
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
        this.makeHole(dir, this.location, 19);

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
    public boolean isClearedVertical() {
        if (upHole.getPercentRemoved() == 19 || downHole.getPercentRemoved() == 19 || (downHole.getPercentRemoved() + upHole.getPercentRemoved()) == 19) {
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
    public boolean isClearedHorizontal() {

        if (leftHole.getPercentRemoved() == 19 || rightHole.getPercentRemoved() == 19 || (leftHole.getPercentRemoved() + rightHole.getPercentRemoved()) == 19) {
            this.clearTileHorizontal();
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
