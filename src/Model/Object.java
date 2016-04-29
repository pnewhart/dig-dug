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

    static void loadBoard(GameBoard theBoard) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Vector2 location = null;
    protected boolean isPumpable = false;
    protected boolean canCrush = false;
    protected boolean isCrushed = false;
    protected static HashMap<String, Image> Images = new HashMap<String, Image>();
    protected String currentImage;

    private static GameBoard gBoard;

    public void loadImage(String name, Image image) {

        Images.put(name, image);
    }

    public static void setBoard(GameBoard b) {
        gBoard = b;
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
        location.adjust();
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
