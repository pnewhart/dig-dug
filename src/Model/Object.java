/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016 *
 * Name: Patrick Newhart, Levi Adair, Sam Greenberg, Tom Ficcadenti
 * Date: Apr 7, 2016
 * Time: 3:43:01 PM *
 * Project: csci205FinalProject
 * Package: Model
 * File: Object
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
public abstract class Object {

    protected Vector2 location = null;
    protected boolean isPumpable = false;
    protected boolean canCrush = false;
    protected boolean isCrushed = false;
    protected static HashMap<String, Image> Images = new HashMap<String, Image>();
    protected String currentImage;
    private static GameBoard gBoard;

    public void loadImage(String name, Image image) {
        System.out.println("loading images");
        Images.put(name, image);
    }

    public void setBoard(GameBoard b) {
        this.gBoard = b;
    }

    public static GameBoard getBoard() {
        return gBoard;
    }

    public Image getCurrentImage() {
        return Images.get(this.currentImage);
    }

    public boolean isPumpable() {
        return isPumpable;
    }

    public void setDiv(Vector2 location) {
        this.location = location;
    }

    public Vector2 getTile() {
        return Vector2Utility.roundDivide(this.location, Vector2.DIVS_PER_TILE);
    }

    public Vector2 getDiv() {
        return new Vector2(this.location.getX(), this.location.getY());
    }

    public int[] getPixel() {
        int[] loc = {(int) location.getX() * Vector2.PIXELS_PER_DIV, (int) location.getY() * Vector2.PIXELS_PER_DIV + Vector2.PIXELS_PER_DIV * Vector2.DIVS_PER_TILE};
        return loc;
    }

    public boolean containsDiv(int x, int y) {
        return x >= this.location.getX() && x < this.location.getX() + Vector2.DIVS_PER_TILE && y >= this.location.getY() && y < this.location.getY() + Vector2.DIVS_PER_TILE;
    }

    /**
     * Handles all the movements
     */
    public abstract void move();

    public abstract void crush();

}
