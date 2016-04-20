/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016 *
 * Name: Patrick Newhart, Levi Adair, Sam Greenberg, Tom Ficcadenti
 * Date: Apr 19, 2016
 * Time: 11:44:03 PM *
 * Project: csci205FinalProject
 * Package: ModelNew
 * File: CollisionBox
 * Description:
 *
 * **************************************** */
package ModelNew;

/**
 *
 * @author tjf010
 */
public class CollisionBox extends Box {
    public CollisionBox(int lengthX, int lengthY, DivLocation loc) {
        super(lengthX, lengthY, loc);
    }

    public boolean isCollision(Box otherBox) {
        if (this.getWestX() > otherBox.getEastX() || this.getWestX() < otherBox.getEastX()) {
            return false;
        }

        if (this.getNorthY() > otherBox.getSouthY() || this.getSouthY() < otherBox.getNorthY()) {
            return false;
        }

        return true;
    }
}
