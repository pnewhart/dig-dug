/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016 *
 * Name: Patrick Newhart, Levi Adair, Sam Greenberg, Tom Ficcadenti
 * Date: Apr 24, 2016
 * Time: 11:00:00 PM *
 * Project: csci205FinalProject
 * Package: Model
 * File: SimpleGameManager
 * Description:
 *
 * **************************************** */
package Model;

/**
 *
 * @author spg011
 */
public class SimpleGameManager {

    private GameBoard theBoard;
    private Driller player1;

    public SimpleGameManager() {
        //loadSprites();
        //initializeFromFile();

        theBoard = new GameBoard();
        player1 = new Driller(theBoard);

    }

    public void movePlayer(Direction dir) {
        player1.move(dir);
    }

    public void shoot(boolean shoot) {
        player1.shoot(shoot);
    }

}
