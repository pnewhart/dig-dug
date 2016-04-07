/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016 *
 * Name: Patrick Newhart, Levi Adair, Sam Greenberg, Tom Ficcadenti
 * Date: Apr 6, 2016
 * Time: 10:37:14 AM *
 * Project: csci205FinalProject
 * Package: Model
 * File: GameBoard
 * Description:
 *
 * **************************************** */
package Model;

import java.util.Random;

/**
 *
 * @author laa024
 */
public class GameBoard {

    final static int BOARD_HEIGHT = 16;
    final static int BOARD_WIDTH = 14;
    private Tile[][] board = new Tile[16][14];

    public GameBoard() {
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                board[i][j] = new Tile(1);
            }
        }

    }

    public void initalOpenSpaces(int numSpaces) {
        Random rNum = new Random(14);

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 14; i++) {
                if (i < 9 && j == 8) {
                    this.openSpace(i, j);
                }
            }
        }
        for (int i = 0; i < numSpaces; i++) {
            this.openSpace(rNum.nextInt(), rNum.nextInt());
        }
    }

    public boolean isOpen(int x, int y) {
        if (board[x][y] == 1) {
            return false;
        } else {
            return true;
        }
    }

    private void openSpace(int x, int y) {
        if (board[x][y] == 1) {
            board[x][y] = 0;
        }
    }

    public void printString() {
        for (int i = 0; i < BOARD_HEIGHT; i++) {

            for (int j = 0; j < BOARD_WIDTH; j++) {
                if (j == 14) {
                    System.out.println("\n");
                }
                System.out.println(board[i][j]);

            }
        }

    }

}
