/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016 *
 * Name: Patrick Newhart, Levi Adair, Sam Greenberg, Tom Ficcadenti
 * Date: Apr 22, 2016
 * Time: 10:04:40 AM *
 * Project: csci205FinalProject
 * Package: Model
 * File: GameManager
 * Description:
 *
 * **************************************** */
package Model;

import java.io.File;

/**
 *
 * @author laa024
 */
public class GameManager {

    private GameBoard gBoard;

    public GameManager() {
        gBoard = new GameBoard();

    }

    public void createGameBoard(File f) {
        try {
            gBoard.generateFromFile(f);
        } catch (Exception E) {
            System.out.println("input file cannot be read");

        }
    }

    public void moveMoveable() {
        for (int i = 0; i < gBoard.objects.size(); i++) {
            gBoard.objects.get(i).move();
        }
    }

}
