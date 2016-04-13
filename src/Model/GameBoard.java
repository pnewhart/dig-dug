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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author laa024
 */
public class GameBoard {

    final static int BOARD_HEIGHT = Vector2.MAX_Y + 1;
    final static int BOARD_WIDTH = Vector2.MAX_X + 1;
    private Tile[][] board = new Tile[BOARD_HEIGHT][BOARD_WIDTH];
    protected ArrayList<Object> objects = new ArrayList<Object>();

    public GameBoard() {
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                board[i][j] = new Tile(i, j);
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

    public boolean isDivEmpty(Vector2 v) {
        return isEmpty((int) v.getX() / Vector2.DIVS_PER_TILE,
                       (int) v.getY() / Vector2.DIVS_PER_TILE);
    }

    /**
     * @see
     * <https://www.caveofprogramming.com/java/java-file-reading-and-writing-files-in-java.html>
     * @param inputFile
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void generateFromFile(File inputFile) throws FileNotFoundException, IOException {
        FileReader fileReader
                   = new FileReader(inputFile);

        BufferedReader buf
                       = new BufferedReader(fileReader);
        int num;
        int i = 0;
        int j = 0;
        while ((num = buf.read()) != -1) {

            char character = (char) num;

            if (j == BOARD_WIDTH) {
                j = 0;
                i++;
            }
            if (character == 'u') {
                board[i % BOARD_HEIGHT][j % BOARD_WIDTH].clearTile(Direction.UP);

            }
            if (character == 'd') {
                board[i % BOARD_HEIGHT][j % BOARD_WIDTH].clearTile(
                        Direction.DOWN);

            }
            if (character == 'l') {
                board[i % BOARD_HEIGHT][j % BOARD_WIDTH].clearTile(
                        Direction.LEFT);

            }
            if (character == 'r') {
                board[i % BOARD_HEIGHT][j % BOARD_WIDTH].clearTile(
                        Direction.RIGHT);

            }
            if (character == ' ' | character == '\n') {

                j--;
            }

            j++;
        }
        buf.close();
    }
//TODO: May need to use this in future

    /**
     *
     * @param coord in div not tile
     * @return
     */
    //public boolean isHole(Vector2 coord) {
    //coord = coord.getTile();
    //int x = (int) coord.getX() / Vector2.DIVS_PER_TILE;
    //int y = (int) coord.getY() / Vector2.DIVS_PER_TILE;
    //return board[x][y].isThere((int) coord.getX() % Vector2.DIVS_PER_TILE,
    //                           (int) coord.getY() % Vector2.DIVS_PER_TILE);
//
    //}
    public boolean isThereObject(Vector2 coord) {

        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).containsDiv((int) coord.getX(),
                                           (int) coord.getY())) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public Object returnObjectAt(Vector2 point) {
        return board[(int) point.getX()][(int) point.getY()];
    }

    public boolean isClearedVertical(Tile t) {
        return t.isClearedVertical();

    }

    public boolean isClearedHorizontal(Tile t) {
        return t.isClearedHorizontal();
    }

    public void printString() {
        for (int i = 0; i < (BOARD_HEIGHT); i++) {
            for (int j = 0; j < (BOARD_WIDTH); j++) {

                board[i][j].printOut();

            }
            System.out.print("  \n");

        }

    }

}
