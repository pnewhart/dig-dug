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

    protected ArrayList<BoardObject> objects = new ArrayList<BoardObject>();

    protected ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
    final static int DIVS_TO_DIG = 1;

    protected Driller driller;
    private Collectible collect;

    private boolean collectPlaced = false;
    final static int COLLECT_OFFSET = 5 * Vector2.DIVS_PER_TILE;

    /**
     * creates a new GameBoard of tiles
     */
    public GameBoard() {
        for (int i = 0; i < BOARD_WIDTH; i++) {
            for (int j = 0; j < BOARD_HEIGHT; j++) {
                board[i][j] = new Tile(i, j);

            }
        }

    }

    public boolean isCollectPlaced() {
        return collectPlaced;
    }

    /**
     *
     * @return collectible on the board
     */
    public Collectible getCollect() {
        return collect;
    }

    /**
     * sets collectible on the board
     *
     * @param collect
     */
    public void setCollect(Collectible collect) {
        this.collect = collect;
    }

    /**
     * places collectible on the board in the center
     *
     * @param c
     */
    public void placeCollectible(Collectible c) {
        this.collect = c;
        c.setDiv(new Vector2(
                (Vector2.NUM_TILE_HORIZONTAL / 2 - 1) * Vector2.DIVS_PER_TILE,
                ((Vector2.NUM_TILE_VERTICAL / 2 - 1) * Vector2.DIVS_PER_TILE) - COLLECT_OFFSET));
        collectPlaced = true;

    }

    /**
     * takes collectible off the board
     */
    public void destroyCollectible() {
        this.collect.destroy();
        collectPlaced = false;
    }

    /**
     * gives board access to the driller
     *
     * @param d
     */
    public void setDriller(Driller d) {
        this.driller = d;
    }

    /**
     *
     * @return Vector2 driller location
     */
    public Vector2 getDrillerLocation() {
        return this.driller.getLocation();

    }

    /**
     *
     * @return list of all objects on the board
     */
    public ArrayList<BoardObject> getObjects() {
        return objects;
    }

    /**
     * checks to see if there is a collision on the board
     *
     * @return boolean isCollison
     */
    public boolean isCollision() {

        boolean isCollision = false;
        if (driller != null) {
            for (Enemy e : enemyList) {

                isCollision = e.isCollidedWith(this.driller);
                if (isCollision) {
                    driller.kill();
                    break;
                }

            }
            try {
                if (collectPlaced && this.collect != null) {
                    if (this.collect.isCollidedWith(this.driller)) {

                        driller.addToScore(this.collect.getType().getPoints());
                        this.destroyCollectible();

                    }
                }
            } catch (Exception e) {
                System.out.println("Collision with collectible error");
            }
        }

        return isCollision;
    }

    /**
     * Will dig the hole for the digger based on the location and direction of
     * the Driller.
     *
     * @author Sam Greenberg
     * @param loc
     * @param d
     * @return
     */
    public boolean digHole(Vector2 loc, Direction d) {
        int x = (int) loc.getX() / Vector2.DIVS_PER_TILE;
        int y = (int) loc.getY() / Vector2.DIVS_PER_TILE;

        boolean dig1 = false, dig2 = false;

        if (d == Direction.RIGHT) {
            if (x > 0) {
                dig1 = board[x - 1][y].digHole(d,
                                               Math.abs(
                                                       (int) loc.getX() - (x - 1) * Vector2.DIVS_PER_TILE));
                if (board[x - 1][y].isClearedVertical()) {
                    board[x - 1][y].clearTileHorizontal();
                    board[x - 1][y].clearTileVertical();
                }
            }
            dig2 = board[x][y].digHole(d,
                                       Math.abs(
                                               (int) loc.getX() - x * Vector2.DIVS_PER_TILE));
            if (board[x - 1][y].isClearedVertical()) {
                board[x - 1][y].clearTile();

            }

        } else if (d == Direction.LEFT) {
            if (x <= Vector2.MAX_X) {
                dig1 = board[x + 1][y].digHole(d, 31
                                                  - Math.abs(
                                                       (int) loc.getX() - (x + 1) * Vector2.DIVS_PER_TILE));
                if (board[x + 1][y].isClearedVertical()) {
                    board[x + 1][y].clearTileHorizontal();
                    board[x + 1][y].clearTileVertical();
                }
            }
            dig2 = board[x][y].digHole(d, 15
                                          - Math.abs(
                                               (int) loc.getX() - x * Vector2.DIVS_PER_TILE));
        } else if (d == Direction.UP) {
            if (y <= Vector2.MAX_Y) {
                dig1 = board[x][y + 1].digHole(d, 31
                                                  - Math.abs(
                                                       (int) loc.getY() - (y + 1) * Vector2.DIVS_PER_TILE));
                if (board[x][y + 1].isClearedHorizontal()) {
                    board[x][y + 1].clearTileHorizontal();
                    board[x][y + 1].clearTileVertical();
                }
            }
            dig2 = board[x][y].digHole(d, 15
                                          - Math.abs(
                                               (int) loc.getY() - y * Vector2.DIVS_PER_TILE));
        } else if (d == Direction.DOWN) {
            if (y <= Vector2.MAX_Y) {
                dig1 = board[x][y - 1].digHole(d,
                                               Math.abs(
                                                       (int) loc.getY() - (y - 1) * Vector2.DIVS_PER_TILE));
                if (board[x][y - 1].isClearedHorizontal()) {
                    board[x][y - 1].clearTileHorizontal();
                    board[x][y - 1].clearTileVertical();
                }
            }
            dig2 = board[x][y].digHole(d,
                                       Math.abs(
                                               (int) loc.getY() - y * Vector2.DIVS_PER_TILE));
        }

        return dig1 || dig2;
    }

    /**
     * Will see if a hole is dug into by a certain amount.
     *
     * @author Sam Greenberg
     * @param loc
     * @param dir
     * @return
     */
    public boolean isDugTo(Vector2 loc, Direction dir) {
        int x = (int) loc.getX() / Vector2.DIVS_PER_TILE;
        int y = (int) loc.getY() / Vector2.DIVS_PER_TILE;

        if (x > Vector2.MAX_X || loc.getX() < 0 || y > Vector2.MAX_Y || loc.getY() < 0) {
            return false;
        } else if (dir == Direction.RIGHT) {
            return board[x][y].isDugTo(dir, Math.abs(
                                       (int) loc.getX() - x * Vector2.DIVS_PER_TILE));
        } else if (dir == Direction.LEFT) {
            return board[x][y].isDugTo(dir, 15 - Math.abs(
                                       (int) loc.getX() - x * Vector2.DIVS_PER_TILE));
        } else if (dir == Direction.UP) {
            return board[x][y].isDugTo(dir, Math.abs(
                                       (int) loc.getY() - y * Vector2.DIVS_PER_TILE));
        } else if (dir == Direction.DOWN) {
            return board[x][y].isDugTo(dir, 15 - Math.abs(
                                       (int) loc.getY() - y * Vector2.DIVS_PER_TILE));
        } else {
            return false;
        }
    }

    /**
     * Will see if a hole is dug into fully in the direction specified.
     *
     * @author Sam Greenberg
     * @param loc
     * @param dir
     * @return
     */
    public boolean isTileDug(Vector2 loc, Direction dir) {
        int x = (int) loc.getX();
        int y = (int) loc.getY();

        if (x > Vector2.MAX_X || x < 0 || y > Vector2.MAX_Y || y < 0) {
            return false;
        } else if (dir.isHorizontal()) {
            return board[x][y].isClearedHorizontal();
        } else {
            return board[x][y].isClearedVertical();
        }
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
        this.objects.clear();
        while ((num = buf.read()) != -1) {

            char character = (char) num;

            if (j == BOARD_WIDTH) {
                j = 0;
                i++;
            }
            if (character == 'v') {
                board[j % BOARD_WIDTH][i % BOARD_HEIGHT].clearTileVertical();

            }

            if (character == 'h') {
                board[j % BOARD_WIDTH][i % BOARD_HEIGHT].clearTileHorizontal();

            }
            if (character == 'c') {
                board[j % BOARD_WIDTH][i % BOARD_HEIGHT].clearTile();

            }

            if (character == 'r') {

                this.objects.add(new Rock(Vector2Utility.scale(new Vector2(
                        j % BOARD_WIDTH, i % BOARD_HEIGHT),
                                                               Vector2.DIVS_PER_TILE)));

            }

            if (character == 'g') {
                board[j % BOARD_WIDTH][i % BOARD_HEIGHT].clearTile();
                enemyList.add(new Dragon(Vector2Utility.scale(new Vector2(
                        j % BOARD_HEIGHT, i % BOARD_WIDTH),
                                                              Vector2.DIVS_PER_TILE)));

            }
            if (character == 'p') {
                board[j % BOARD_WIDTH][i % BOARD_HEIGHT].clearTile();
                enemyList.add(new Puff(Vector2Utility.scale(new Vector2(
                        j % BOARD_HEIGHT, i % BOARD_WIDTH),
                                                            Vector2.DIVS_PER_TILE)));
            }

            if (character == ' ' | character == '\n') {

                j--;
            }

            j++;
        }
        buf.close();

    }

    /**
     * resets the board between levels
     */
    public void resetBoard() {
        for (int i = 0; i < BOARD_WIDTH; i++) {
            for (int j = 0; j < BOARD_HEIGHT; j++) {
                board[i][j].fillHoles();
            }
        }
        enemyList.clear();
        objects.clear();

    }

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
    public ArrayList<BoardObject> returnObjectsAt(Vector2 coord) {
        ArrayList<BoardObject> returnList = new ArrayList<>();
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
            BoardObject a = objects.get(i);
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
            BoardObject a = objects.get(i);
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
        int x = (int) (coord.getX() / Vector2.DIVS_PER_TILE);
        int y = (int) (coord.getY() / Vector2.DIVS_PER_TILE);
        try {
            for (BoardObject obj : getObjects()) {
                if (obj instanceof Rock) {
                    int X = (int) (obj.getDiv().getX() / Vector2.DIVS_PER_TILE);
                    int Y = (int) (obj.getDiv().getY() / Vector2.DIVS_PER_TILE);

                    if (X == x && y == Y) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {

        }
        return false;
    }

    /**
     * checks if tile is cleared vertically
     *
     * @param location
     * @return boolean
     */
    public boolean isClearedVertical(Vector2 location) {
        int x = (int) location.getX();
        int y = (int) location.getY();
        if (x < GameBoard.BOARD_WIDTH && y < GameBoard.BOARD_HEIGHT && x >= 0 && y >= 0) {
            return board[x][y].isClearedVertical();
        } else {
            return false;
        }
    }

    /**
     * checks if tile is cleared horizontally
     *
     * @param location
     * @return boolean
     */
    public boolean isClearedHorizontal(Vector2 location) {
        int x = (int) location.getX();
        int y = (int) location.getY();
        if (x < GameBoard.BOARD_WIDTH && y < GameBoard.BOARD_HEIGHT && x >= 0 && y >= 0) {
            return board[x][y].isClearedHorizontal();
        } else {
            return false;
        }

    }

    /**
     * checks if tile is cleared vertically
     *
     * @param t
     * @return boolean
     */
    public boolean isDivClearedVertical(Vector2 location) {
        int x = (int) location.getX() / Vector2.DIVS_PER_TILE;
        int y = (int) location.getY() / Vector2.DIVS_PER_TILE;
        if (x < GameBoard.BOARD_WIDTH && y < GameBoard.BOARD_HEIGHT && x >= 0 && y >= 0) {
            return board[x][y].isClearedVertical();
        } else {
            return false;
        }
    }

    /**
     * checks if tile is cleared horizontally
     *
     * @param t
     * @return boolean
     */
    public boolean isDivClearedHorizontal(Vector2 location) {
        int x = (int) location.getX() / Vector2.DIVS_PER_TILE;
        int y = (int) location.getY() / Vector2.DIVS_PER_TILE;
        if (x < GameBoard.BOARD_WIDTH && y < GameBoard.BOARD_HEIGHT && x >= 0 && y >= 0) {
            return board[x][y].isClearedHorizontal();
        } else {
            return false;
        }

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
