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

/**
 *
 * @author laa024
 */
public class GameBoard {

    final static int BOARD_HEIGHT = Vector2.MAX_Y;
    final static int BOARD_WIDTH = Vector2.MAX_X;
    private Tile[][] board = new Tile[BOARD_HEIGHT][BOARD_HEIGHT];

    public GameBoard() {
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                board[i][j] = new Tile();
            }
        }

    }

    public void clearTile(Vector2 num, Direction dir) {
        int x = (int) num.getX();
        int y = (int) num.getY();

        board[x][y].clearTile(dir);

    }

    public boolean isEmpty(int x, int y) {
        if (board[x][y].isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public void printString() {
        for (int i = 0; i < (BOARD_HEIGHT - 1); i++) {
            for (int j = 0; j < (BOARD_WIDTH - 1); j++) {
                System.out.print(board[i][j]);

            }
            System.out.print("\n");
        }

    }

}
