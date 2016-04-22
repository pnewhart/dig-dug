/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016 *
 * Name: Patrick Newhart, Levi Adair, Sam Greenberg, Tom Ficcadenti
 * Date: Apr 19, 2016
 * Time: 11:22:04 PM *
 * Project: csci205FinalProject
 * Package: ModelNew
 * File: Box
 * Description:
 *
 * **************************************** */
package Model;

/**
 *
 * @author tjf010
 */
public class Box {

    private int eastX;
    private int westX;
    private int northY;
    private int southY;
    private Vector2 boxLoc;

    public Box(int lengthX, int lengthY, Vector2 loc) {
        //these variables are offsets of the location. For example, a box in the gameboard at location (div 0, div 0) covers Xdiv's 0-15 and Ydiv's 0-15
        //locations of boxes are the coordinates of the div of the North West corner

        this.boxLoc = loc;
        this.eastX = (int) loc.getX();
        this.westX = loc.getX() + lengthX - 1;
        this.northY = loc.getY();
        this.southY = loc.getY() + lengthY - 1;
    }

    public Vector2 getBoxLoc() {
        return boxLoc;
    }

    public int getEastX() {
        return eastX;
    }

    public int getWestX() {
        return westX;
    }

    public int getNorthY() {
        return northY;
    }

    public int getSouthY() {
        return southY;
    }

    public void moveBox(Direction dir, int numDivs) {
        double changeX = dir.getVector().getX() * numDivs;
        double changeY = dir.getVector().getY() * numDivs;

        this.boxLoc.setX(this.boxLoc.getX() + changeX);
        this.boxLoc.setY(this.boxLoc.getY() + changeY);
        this.eastX += changeX;
        this.westX += changeX;
        this.northY += changeY;
        this.southY += changeY;
    }
}
