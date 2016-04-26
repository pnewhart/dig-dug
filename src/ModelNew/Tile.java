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
package ModelNew;

import java.awt.image.BufferedImage;

/**
 *
 * @author laa024
 */
public class Tile {

    private String biome;
    private int layer;
    private Box boundary;
    private BufferedImage northDig;
    private BufferedImage southDig;
    private BufferedImage eastDig;
    private BufferedImage westDig;
    private BufferedImage baseImage;
    private int northDigInt;
    private int southDigInt;
    private int eastDigInt;
    private int westDigInt;
    private int boardX;
    private int boardY;
    private Direction[] corner;

    public final int NUM_DIG_STATES = 19;

    /**
     *
     * @param boardX
     * @param boardY
     * @param layer
     * @param Biome
     */
    public Tile(int boardTileX, int boardTileY, int layer, String Biome) {
        ///////IMAGE VARIABLES///////
        this.biome = Biome;
        this.layer = layer;
        this.northDig = null;
        this.southDig = null;
        this.eastDig = null;
        this.westDig = null;
        this.corner = null;

        ///////MODEL VARIABLES////////
        int divX = boardTileX * Vector2.DIVS_PER_TILE;
        int divY = boardTileY * Vector2.DIVS_PER_TILE;

        this.boardX = boardTileX;
        this.boardY = boardTileY;
        this.boundary = new Box(Vector2.DIVS_PER_TILE, Vector2.DIVS_PER_TILE,
                                new DivLocation(divX, divY));
        this.northDigInt = 0;
        this.southDigInt = 0;
        this.eastDigInt = 0;
        this.westDigInt = 0;
    }

    public void setBaseImage(BufferedImage baseImage) {
        this.baseImage = baseImage;
    }

    public BufferedImage getBaseImage() {
        return baseImage;
    }

    public int getBoardX() {
        return boardX;
    }

    public void setBoardX(int boardX) {
        this.boardX = boardX;
    }

    public int getBoardY() {
        return boardY;
    }

    public void setBoardY(int boardY) {
        this.boardY = boardY;
    }

    public Box getBoundary() {
        return boundary;
    }

    public int getNorthDigInt() {
        return northDigInt;
    }

    public int getSouthDigInt() {
        return southDigInt;
    }

    public int getEastDigInt() {
        return eastDigInt;
    }

    public int getWestDigInt() {
        return westDigInt;
    }

    public BufferedImage getNorthDig() {
        return northDig;
    }

    public void setNorthDig(BufferedImage northDig) {
        this.northDig = northDig;
    }

    public BufferedImage getSouthDig() {
        return southDig;
    }

    public void setSouthDig(BufferedImage southDig) {
        this.southDig = southDig;
    }

    public BufferedImage getEastDig() {
        return eastDig;
    }

    public void setEastDig(BufferedImage eastDig) {
        this.eastDig = eastDig;
    }

    public BufferedImage getWestDig() {
        return westDig;
    }

    public void setWestDig(BufferedImage westDig) {
        this.westDig = westDig;
    }

    public Direction[] getCorner() {
        return corner;
    }

    public void setCorner(Direction[] corner) {
        this.corner = corner;
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
     * @param amountToDig
     */
    public void digHole(Direction dir, int amountToDig) {
        if (dir == Direction.EAST) {
            eastDigInt += amountToDig;
        }
        if (dir == Direction.WEST) {
            westDigInt += amountToDig;
        }
        if (dir == Direction.NORTH) {
            northDigInt += amountToDig;
        }
        if (dir == Direction.SOUTH) {
            southDigInt += amountToDig;
        }
        if (eastDigInt + westDigInt >= NUM_DIG_STATES) {
            this.eastDigInt = NUM_DIG_STATES;
            this.westDigInt = NUM_DIG_STATES;
        }
        if (northDigInt + southDigInt >= NUM_DIG_STATES) {
            this.northDigInt = NUM_DIG_STATES;
            this.westDigInt = NUM_DIG_STATES;
        }
    }

    /**
     *
     * @return true if tile is full
     */
    public boolean isFull() {
        return this.northDigInt == 0 && this.eastDigInt == 0 && this.westDigInt == 0 && this.southDigInt == 0;
    }

    /**
     *
     * @return true if tile is empty
     */
    public boolean isEmpty() {
        return this.northDigInt == NUM_DIG_STATES && this.eastDigInt == NUM_DIG_STATES && this.westDigInt == NUM_DIG_STATES && this.southDigInt == NUM_DIG_STATES;
    }

    /**
     * clears the tile in a specific direction
     *
     * @param dir
     */
    protected void clearTile(Direction dir) {
        digHole(dir, NUM_DIG_STATES);
    }

}
