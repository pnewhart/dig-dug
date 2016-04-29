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
        this.setDiv(new Vector2(x * Vector2.DIVS_PER_TILE,
                                y * Vector2.DIVS_PER_TILE));
    }

    public Image[] getCurrentImages() {
        Image[] images = {Images.get(getRightHoleImageKey()), Images.get(
                          getLeftHoleImageKey()),
                          Images.get(getUpHoleImageKey()), Images.get(
                          getDownHoleImageKey())};
        if (this.getDiv().getY() == 0) {
            return null;
        } else {
            return images;
        }
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
     * Digs the hole for the digger in this tile
     *
     * @param dir
     * @param loc (divs)
     * @return
     */
    public boolean digHole(Direction dir, int amount) {
        boolean dig = false;
        if (dir == Direction.RIGHT) {
            dig = this.rightHole.dig(amount);
        } else if (dir == Direction.LEFT) {
            dig = this.leftHole.dig(amount);
        } else if (dir == Direction.UP) {
            dig = this.upHole.dig(amount);
        } else if (dir == Direction.DOWN) {
            dig = this.downHole.dig(amount);
        } else {
            return false;
        }

        if (this.rightHole.isClear() || this.leftHole.isClear()) {
            this.rightHole.clear();
            this.leftHole.clear();
        }
        if (this.upHole.isClear() || this.downHole.isClear()) {
            this.upHole.clear();
            this.downHole.clear();
        }

        return dig;
    }

    public boolean isDugTo(Direction dir, int amount) {
        if (dir == Direction.RIGHT) {
            return this.rightHole.isDugTo(amount);
        } else if (dir == Direction.LEFT) {
            return this.leftHole.isDugTo(amount);
        } else if (dir == Direction.UP) {
            return this.upHole.isDugTo(amount);
        } else if (dir == Direction.DOWN) {
            return this.downHole.isDugTo(amount);
        } else {
            return false;
        }
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

                    if (!rightHole.isEmpty()) {
                        return rightHole.destroy(percentToDestroy);

                    } else {

                        if ((loc.getX() == (this.getDiv().getX() + rightHole.getPercentRemoved())) && !this.isClearedHorizontal()) {
                            return rightHole.destroy(percentToDestroy);

                        } else {
                            return false;
                        }

                    }
                }
                if (dir == Direction.LEFT) {

                    if (!leftHole.isEmpty()) {
                        return leftHole.destroy(percentToDestroy);

                    } else {
                        if ((loc.getX() == (this.getDiv().getX() + (Vector2.DIVS_PER_TILE - leftHole.getPercentRemoved()))) && !this.isClearedHorizontal()) {
                            return leftHole.destroy(percentToDestroy);

                        } else {
                            return false;
                        }

                    }
                }
                if (dir == Direction.UP) {
                    if (!upHole.isEmpty() && !upHole.hasBeenDug) {
                        return upHole.destroy(percentToDestroy);

                    } else {

                        if ((loc.getY() == (this.getDiv().getY() + (Vector2.DIVS_PER_TILE - upHole.getPercentRemoved()))) && !this.isClearedVertical()) {
                            return upHole.destroy(percentToDestroy);

                        } else {
                            return false;
                        }

                    }
                }
                if (dir == Direction.DOWN) {
                    if (!downHole.isEmpty() && downHole.hasBeenDug) {
                        return downHole.destroy(percentToDestroy);

                    } else {

                        if ((loc.getY() == (this.getDiv().getY() - downHole.getPercentRemoved())) && !this.isClearedVertical()) {
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
        if (!(leftHole.isEmpty() && rightHole.isEmpty() && upHole.isEmpty() && downHole.isEmpty())) {
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
        this.makeHole(dir, this.getDiv(), 19);

    }

    /**
     * clears the tile in the horizontal direction
     */
    protected void clearTileHorizontal() {

        rightHole.clearHole();
        leftHole.clearHole();
        clearedHorizontal = true;
        leftHole.hasBeenDug = true;
        rightHole.hasBeenDug = true;
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
        if ((downHole.getPercentRemoved() + upHole.getPercentRemoved()) > 16) {
            this.clearTileVertical();
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

        if ((leftHole.getPercentRemoved() + rightHole.getPercentRemoved()) > 16) {
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
