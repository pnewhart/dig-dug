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
package ModelNew;

/**
 *
 * @author laa024
 */
public class GameBoard {

    final static int BOARD_HEIGHT = Vector2.MAX_Y + 1;
    final static int BOARD_WIDTH = Vector2.MAX_X + 1;
    public Tile[][] board = new Tile[BOARD_HEIGHT][BOARD_WIDTH];
    final static int DIVS_TO_DIG = 1;

    /**
     * creates a new GameBoard of tiles
     */
    public GameBoard() {

    }

    public Tile[][] getBoard() {
        return board;
    }

    public Tile getTile(int x, int y) {
        return board[x][y];
    }

//    /**
//     * clears the dirt from a tile
//     *
//     * @param num
//     * @param dir
//     */
//    public void clearTile(Vector2 num, Direction dir) {
//        int x = (int) num.getX();
//        int y = (int) num.getY();
//
//        board[x][y].clearTile(dir);
//
//    }
    /**
     * checks to see if tile is empty
     *
     * @param x
     * @param y
     * @return true if tile is empty
     */
    public boolean isEmpty(int x, int y) {
        if (board[x][y].isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

//    /**
//     * checks to see if specific div is empty
//     *
//     * @param v
//     * @return true if div is empty
//     */
//    public boolean isDivEmpty(Vector2 v) {
//        return isEmpty((int) v.getX() / Vector2.DIVS_PER_TILE,
//                       (int) v.getY() / Vector2.DIVS_PER_TILE);
//    }
//    /**
//     * @see
//     * <https://www.caveofprogramming.com/java/java-file-reading-and-writing-files-in-java.html>
//     * @param inputFile
//     * @throws FileNotFoundException
//     * @throws IOException
//     */
//    public void generateFromFile(File inputFile) throws FileNotFoundException, IOException {
//        FileReader fileReader
//                   = new FileReader(inputFile);
//
//        BufferedReader buf
//                       = new BufferedReader(fileReader);
//        int num;
//        int i = 0;
//        int j = 0;
//        while ((num = buf.read()) != -1) {
//
//            char character = (char) num;
//
//            if (j == BOARD_WIDTH) {
//                j = 0;
//                i++;
//            }
//            if (character == 'u') {
//                board[i % BOARD_HEIGHT][j % BOARD_WIDTH].clearTile(Direction.NORTH);
//
//            }
//            if (character == 'd') {
//                board[i % BOARD_HEIGHT][j % BOARD_WIDTH].clearTile(Direction.SOUTH);
//
//            }
//            if (character == 'l') {
//                board[i % BOARD_HEIGHT][j % BOARD_WIDTH].clearTile(Direction.WEST);
//
//            }
//            if (character == 'r') {
//                board[i % BOARD_HEIGHT][j % BOARD_WIDTH].clearTile(Direction.EAST);
//
//            }
//            if (character == ' ' | character == '\n') {
//
//                j--;
//            }
//
//            j++;
//        }
//        buf.close();
//    }
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
//    public boolean isThereObjectAt(Vector2 coord) {
//
//        for (int i = 0; i < objects.size(); i++) {
//            if (objects.get(i).containsDiv((int) coord.getX(),
//                                           (int) coord.getY())) {
//                return true;
//            } else {
//                return false;
//            }
//        }
//        return false;
//    }
//    /**
//     * returns all objects at coord
//     *
//     * @param coord
//     * @return list of objects at certain coord
//     */
//    public ArrayList<Object> returnObjectsAt(Vector2 coord) {
//        ArrayList<Object> returnList = new ArrayList<>();
//        for (int i = 0; i < objects.size(); i++) {
//            if (objects.get(i).containsDiv((int) coord.getX(),
//                                           (int) coord.getY())) {
//                returnList.add(objects.get(i));
//            }
//
//        }
//        return returnList;
//
//    }
//    /**
//     * checks to see if pumpable object is at coord
//     *
//     * @param coord
//     * @return true if pumpable object is there
//     */
//    public boolean isPumpableObjectAt(Vector2 coord) {
//        for (int i = 0; i < objects.size(); i++) {
//            Object a = objects.get(i);
//            if (objects.get(i).containsDiv((int) coord.getX(),
//                                           (int) coord.getY())) {
//                return a.isPumpable();
//            }
//
//        }
//        return false;
//    }
//    /**
//     *
//     * @param coord
//     * @return list of pumpable objects at coord
//     */
//    public ArrayList<Pumpable> returnPumpableObjectsAt(Vector2 coord) {
//        ArrayList<Pumpable> returnList = new ArrayList<>();
//        Pumpable b;
//        for (int i = 0; i < objects.size(); i++) {
//            Object a = objects.get(i);
//            if (objects.get(i).containsDiv((int) coord.getX(),
//                                           (int) coord.getY())) {
//                if (a.isPumpable()) {
//                    b = (Pumpable) a;
//                    returnList.add(b);
//                }
//            }
//        }
//        return returnList;
//    }
//    /**
//     * checks if tile is cleared vertically
//     *
//     * @param t
//     * @return boolean
//     */
//    public boolean isClearedVertical(Tile t) {
//        return t.isClearedVertical();
//
//    }
//
//    /**
//     * checks if tile is cleared horizontally
//     *
//     * @param t
//     * @return boolean
//     */
//    public boolean isClearedHorizontal(Tile t) {
//        return t.isClearedHorizontal();
//    }
//
//    /**
//     * used to check board
//     */
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
}
