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
package ModelNew;

/**
 *
 * @author tjf010
 */
public class Box {

    private int eastX;
    private int westX;
    private int northY;
    private int southY;
    private DivLocation boxLoc;

    public Box(int lengthX, int lengthY, DivLocation loc) {
        //these variables are offsets of the location. For example, a box in the gameboard at location (div 0, div 0) covers Xdiv's 0-15 and Ydiv's 0-15
        //locations of boxes are the coordinates of the div of the North West corner

        this.boxLoc = loc;
        this.eastX = loc.getDivX();
        this.westX = loc.getDivX() + lengthX - 1;
        this.northY = loc.getDivY();
        this.southY = loc.getDivY() + lengthY - 1;
    }

    public DivLocation getBoxLoc() {
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
        int changeX = dir.getVector().getX() * numDivs;
        int changeY = dir.getVector().getY() * numDivs;

        this.boxLoc.setDivX(this.boxLoc.getDivX() + changeX);
        this.boxLoc.setDivY(this.boxLoc.getDivY() + changeY);
        this.eastX += changeX;
        this.westX += changeX;
        this.northY += changeY;
        this.southY += changeY;
    }
}
