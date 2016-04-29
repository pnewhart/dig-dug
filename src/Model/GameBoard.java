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

    final static int BOARD_HEIGHT = Vector2.NUM_TILE_VERTICAL;
    final static int BOARD_WIDTH = Vector2.NUM_TILE_HORIZONTAL;
    public Tile[][] board = new Tile[BOARD_WIDTH][BOARD_HEIGHT];
    protected ArrayList<Object> objects = new ArrayList<Object>();
    protected ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
    final static int DIVS_TO_DIG = 1;
    protected Driller driller;

    /**
     * creates a new GameBoard of tiles
     */
    public GameBoard() {
        for (int i = 0; i < BOARD_WIDTH; i++) {
            for (int j = 0; j < BOARD_HEIGHT; j++) {
                board[i][j] = new Tile(i, j);

            }
        }
        board[5][5].clearTileHorizontal();
        board[4][5].clearTileHorizontal();
        board[3][5].clearTileHorizontal();
        Dragon d = new Dragon(Vector2Utility.scale(new Vector2(4, 5),
                                                   Vector2.DIVS_PER_TILE));

        enemyList.add(d);
    }

    public void setDriller(Driller d) {
        this.driller = d;
    }

    public Vector2 getDrillerLocation() {
        return this.driller.getLocation();

    }

    /**
     *
     * @param location(in terms of divs)
     * @param d
     * @return
     */
    public boolean makeHole(Vector2 location, Direction d) {

        int x = (int) location.getX() / Vector2.DIVS_PER_TILE;
        int y = (int) location.getY() / Vector2.DIVS_PER_TILE;

        if (d == Direction.RIGHT) {
            board[x - 1][y].makeHole(d, location, DIVS_TO_DIG);
        } else if (d == Direction.LEFT) {
            board[x + 1][y].makeHole(d, location, DIVS_TO_DIG);
        } else if (d == Direction.UP) {
            board[x][y + 1].makeHole(d, location, DIVS_TO_DIG);
        } else if (d == Direction.UP) {
            board[x][y - 1].makeHole(d, location, DIVS_TO_DIG);
        }

        return board[x][y].makeHole(d, location, DIVS_TO_DIG);

    }

    /**
     * clears the dirt from a tile
     *
     * @param num
     * @param dir
     */
    public void clearTile(Vector2 num, Direction dir) {
        int x = (int) num.getX();
        int y = (int) num.getY();

        board[x][y].clearTile(dir);

    }

    /**
     * checks to see if tile is empty
     *
     * @param x
     * @param y
     * @return true if tile is empty
     */
    public boolean isEmpty(int x, int y) {
        return board[x][y].isEmpty();
    }

    /**
     * checks to see if specific div is empty
     *
     * @param v
     * @return true if div is empty
     */
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
    protected void generateFromFile(File inputFile) throws FileNotFoundException, IOException {
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
                board[i % BOARD_HEIGHT][j % BOARD_WIDTH].clearTile(Direction.UP);
                board[i % BOARD_HEIGHT][j % BOARD_WIDTH].clearTile(
                        Direction.RIGHT);
                board[i % BOARD_HEIGHT][j % BOARD_WIDTH].clearTile(
                        Direction.LEFT);

            }
            if (character == 'l') {
                board[i % BOARD_HEIGHT][j % BOARD_WIDTH].clearTile(
                        Direction.LEFT);

            }
            if (character == 'r') {
                board[i % BOARD_HEIGHT][j % BOARD_WIDTH].clearTile(
                        Direction.RIGHT);

            }
            if (character == 'g') {
                board[i % BOARD_HEIGHT][j % BOARD_WIDTH].clearTile(
                        Direction.RIGHT);
                //add dragon

            }
            if (character == 'p') {
                board[i % BOARD_HEIGHT][j % BOARD_WIDTH].clearTile(
                        Direction.RIGHT);
                //add puff
            }

            if (character == ' ' | character == '\n') {

                j--;
            }

            j++;
        }
        buf.close();
        this.generateEnemyFromFile(inputFile);
    }

    /**
     * @see
     * <https://www.caveofprogramming.com/java/java-file-reading-and-writing-files-in-java.html>
     * @param inputFile
     * @throws FileNotFoundException
     * @throws IOException
     */
    private void generateEnemyFromFile(File inputFile) throws FileNotFoundException, IOException {
        FileReader fileReader
                   = new FileReader(inputFile);

        BufferedReader buf
                       = new BufferedReader(fileReader);
        int num;
        int i = 0;
        int j = 0;
        while ((num = buf.read()) != -1) {
            Vector2 location = new Vector2(i, j);
            char character = (char) num;

            if (j == BOARD_WIDTH) {
                j = 0;
                i++;
            }
            if (character == 'g') {

                System.out.println("dragon made");

            }
            if (character == 'p') {

                enemyList.add(new Puff(this));
                System.out.println("puff made");
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
    /**
     * checks to see if there is an object at the coord
     *
     * @param coord
     * @return true if object is at given coord
     */
    public boolean isThereObjectAt(Vector2 coord) {

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

    /**
     * returns all objects at coord
     *
     * @param coord
     * @return list of objects at certain coord
     */
    public ArrayList<Object> returnObjectsAt(Vector2 coord) {
        ArrayList<Object> returnList = new ArrayList<>();
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).containsDiv((int) coord.getX(),
                                           (int) coord.getY())) {
                returnList.add(objects.get(i));
            }
        }
        return returnList;

    }

    /**
     * checks to see if pumpable object is at coord
     *
     * @param coord
     * @return true if pumpable object is there
     */
    public boolean isPumpableObjectAt(Vector2 coord) {
        for (int i = 0; i < objects.size(); i++) {
            Object a = objects.get(i);
            if (objects.get(i).containsDiv((int) coord.getX(),
                                           (int) coord.getY())) {
                return a.isPumpable();
            }

        }
        return false;
    }

    /**
     *
     * @param coord
     * @return list of pumpable objects at coord
     */
    public ArrayList<Enemy> returnPumpableObjectsAt(Vector2 coord) {
        ArrayList<Enemy> returnList = new ArrayList<>();
        Enemy b;
        for (int i = 0; i < objects.size(); i++) {
            Object a = objects.get(i);
            if (objects.get(i).containsDiv((int) coord.getX(),
                                           (int) coord.getY())) {
                if (a.isPumpable()) {
                    b = (Enemy) a;
                    returnList.add(b);
                }
            }
        }
        return returnList;
    }

    /**
     * Checks if there if a Rock at the given Vector2 in divs (THIS NEEDS TO BE
     * FINISHED!)
     *
     * @param coord (divs)
     * @return
     */
    public boolean isRockAt(Vector2 coord) {
        return false;
    }

    /**
     * checks if tile is cleared vertically
     *
     * @param t
     * @return boolean
     */
    public boolean isClearedVertical(Vector2 location) {
        int x = (int) location.getX() / Vector2.DIVS_PER_TILE;
        int y = (int) location.getY() / Vector2.DIVS_PER_TILE;
        return board[x][y].isClearedVertical();

    }

    /**
     * checks if tile is cleared horizontally
     *
     * @param t
     * @return boolean
     */
    public boolean isClearedHorizontal(Vector2 location) {
        int x = (int) location.getX() / Vector2.DIVS_PER_TILE;
        int y = (int) location.getY() / Vector2.DIVS_PER_TILE;
        return board[x][y].isClearedHorizontal();

    }

    /**
     * used to check board
     */
//    public void printString() {
//        for (int i = 0; i < (BOARD_HEIGHT); i++) {
//            for (int j = 0; j < (BOARD_WIDTH); j++) {
//
//                board[i][j].printOut();
//
//            }
//            System.out.print("  \n");
//
//        }
//
//    }
    public Tile[][] getBoard() {
        return board;
    }

    public ArrayList<Enemy> getEnemyList() {
        return enemyList;
    }

}
