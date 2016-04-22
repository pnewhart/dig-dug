/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016 *
 * Name: Patrick Newhart, Levi Adair, Sam Greenberg, Tom Ficcadenti
 * Date: Apr 20, 2016
 * Time: 1:20:00 AM *
 * Project: csci205FinalProject
 * Package: ModelNew
 * File: DivLocation
 * Description:
 *
 * **************************************** */
package ModelNew;

/**
 *
 * @author tjf010
 */
public class DivLocation {
    private int divX;
    private int divY;

    public DivLocation(int x, int y) {
        this.divX = x;
        this.divY = y;
    }

    public int getDivX() {
        return divX;
    }

    public void setDivX(int divX) {
        this.divX = divX;
    }

    public int getDivY() {
        return divY;
    }

    public void setDivY(int divY) {
        this.divY = divY;
    }

    public boolean equals(DivLocation other) {
        return this.getDivX() == other.getDivX() && this.getDivY() == other.getDivY();
    }
}
