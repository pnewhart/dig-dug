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

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;

/**
 *
 * @author laa024
 */
public class GameManager {

    /**
     *
     */
    protected GameBoard theBoard;
    private Driller player1 = new Driller();
    private Image backGround;
    private BoardObject newObject;
    private Enemy en;
    //private Collectable collectables;
    //private ArrayList<Rock> rocks;
    public int timesPressed = 0;

    /**
     *
     */
    public HashMap<String, Image> boardImageMap;

    /**
     *
     */
    public HashMap<String, Image> numbersAndLives;

    /**
     *
     */
    protected MainMenuManager menu = new MainMenuManager();
    private int levelCounter = -1;

    protected GameOverManager gameOver = new GameOverManager();

    /**
     *
     */
    public static final int NUMBER_WIDTH = 9;

    /**
     *
     */
    public static final int NUMBER_HEIGHT = 9;

    /**
     *
     */
    public final int LIFE_ICON_DIMMENSIONS = 32;

    /**
     *
     */
    public static final int PIXELS_PER_DIV = 3;

    /**
     *
     */
    public static final int TILE_SIZE_IN_DIVS = 16;

    /**
     *
     */
    public GameManager() {

        try {
            initializeGame();
        } catch (Exception ex) {
            System.out.println("ERRONEOUS FILE INTITIALIZATION!");
        }
    }

    /**
     * initialize an instance of a game
     *
     * @throws IOException
     */
    private void initializeGame() throws IOException {
        loadSprites();
        theBoard = new GameBoard();
        this.backGround = this.menu.getBackGround();

    }

    /**
     * creates a game without creating all the enemies and setting all the
     * images, used to create the main menu screen
     */
    public void createGame() {
        try {

            this.backGround = this.loadAndResizeSprite("GrassLevel.png", 672,
                                                       864);

            BoardObject.setBoard(theBoard);
            player1.setBoard(theBoard);
            File f = new File("input.txt");
            this.theBoard.generateFromFile(f);
            this.player1 = new Driller();
            theBoard.setDriller(this.player1);

            this.loadScoreAndLivesSprites();

            this.setCollectible();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("cannot find input file");
        }
    }

    /**
     * when called, sets the board to the next level
     */
    public void nextLevel() {
        theBoard.resetBoard();
        levelCounter++;
        try {
            String levelString = String.format("src/level%d.txt",
                                               levelCounter);
            //System.out.println(levelString);
            File inputFile = new File(levelString);
            theBoard.generateFromFile(inputFile);
            this.setCollectible();
            player1.setDiv(new Vector2(
                    (Vector2.NUM_TILE_HORIZONTAL / 2 - 1) * Vector2.DIVS_PER_TILE,
                    ((Vector2.NUM_TILE_VERTICAL / 2 - 1) * Vector2.DIVS_PER_TILE)));
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("cannot go to next level");
        }

    }

    /**
     * when called, sets the board to the next level
     */
    public void resetLevel() {
        theBoard.resetBoard();

        try {
            String levelString = String.format("src/level%d.txt",
                                               levelCounter);
            //System.out.println(levelString);
            File inputFile = new File(levelString);
            theBoard.generateFromFile(inputFile);
            this.setCollectible();
            player1.setDiv(new Vector2(
                    (Vector2.NUM_TILE_HORIZONTAL / 2 - 1) * Vector2.DIVS_PER_TILE,
                    ((Vector2.NUM_TILE_VERTICAL / 2 - 1) * Vector2.DIVS_PER_TILE)));
        } catch (Exception e) {
            //System.out.println(e);
            //System.out.println("cannot go to next level");
        }

    }

    public void gameOver() {
        theBoard.resetBoard();
        this.backGround = gameOver.getBackGround();
    }

    /**
     * sets collectables on the board
     */
    public void setCollectible() {
        //System.out.println("start set collect");

        theBoard.placeCollectible(new Collectible(levelCounter));
        //System.out.println("collect set");

    }

    /**
     * changes the background of the game
     */
    public void changeBackground() {
        this.backGround = menu.getBackGround();
    }

    public int getLevelCounter() {
        return levelCounter;
    }

    public void setLevelCounter(int levelCounter) {
        this.levelCounter = levelCounter;
    }

    /**
     * loads all the sprites of the characters into a hashmap
     */
    private void loadSprites() {
        this.loadPlayerSprites();
        this.loadMapSprites();
        this.loadEnemySprites();
        this.loadRockSprites();
        this.loadGunSprites();
    }

    /**
     * loads the scores and lives onto the screen
     */
    private void loadScoreAndLivesSprites() {
        this.numbersAndLives = new HashMap<>();

        this.numbersAndLives.put("LifeIcon", loadAndResizeSprite(
                                 "Digger_Right_1.png",
                                 this.LIFE_ICON_DIMMENSIONS,
                                 this.LIFE_ICON_DIMMENSIONS));

        for (int i = 0; i < 10; i++) {
            String iString = Integer.toString(i);
            this.numbersAndLives.put(iString, loadAndResizeSprite(
                                     iString + ".png",
                                     GameManager.NUMBER_WIDTH * GameManager.PIXELS_PER_DIV,
                                     GameManager.NUMBER_HEIGHT * GameManager.PIXELS_PER_DIV));
        }

        String scoreStr = "SCORE";
        for (int i = 0; i < scoreStr.length(); i++) {
            String iChar = scoreStr.substring(i, i + 1);

            this.numbersAndLives.put(iChar, loadAndResizeSprite(
                                     iChar + ".png",
                                     GameManager.NUMBER_WIDTH * GameManager.PIXELS_PER_DIV,
                                     GameManager.NUMBER_HEIGHT * GameManager.PIXELS_PER_DIV));
        }
    }

    private void loadGunSprites() {
        String[] gunSprites = {"Cable_Down_L1.png", "Cable_Down_L2.png",
                               "Cable_Down_R1.png", "Cable_Down_R2.png",
                               "Cable_Up_L1.png", "Cable_Up_L2.png",
                               "Cable_Up_R1.png", "Cable_Up_R2.png",
                               "Cable_Right_1.png", "Cable_Right_2.png",
                               "Cable_Left_1.png", "Cable_Left_2.png"};

        for (String gun : gunSprites) {
            BoardObject.loadImage(gun, loadAndResizeSprite(gun,
                                                           GameManager.PIXELS_PER_DIV * GameManager.TILE_SIZE_IN_DIVS,
                                                           GameManager.PIXELS_PER_DIV * GameManager.TILE_SIZE_IN_DIVS));
        }
    }

    /**
     * loads gun sprites into the hashmap
     */
    private void loadRockSprites() {
        String[] rockSprites = {"Rock_1.png", "Rock_2.png", "Rock_Ground_1.png", "Rock_Ground_2.png"};

        for (String rock : rockSprites) {
            BoardObject.loadImage(rock, loadAndResizeSprite(rock,
                                                            GameManager.PIXELS_PER_DIV * GameManager.TILE_SIZE_IN_DIVS,
                                                            GameManager.PIXELS_PER_DIV * GameManager.TILE_SIZE_IN_DIVS));
        }
    }

    /**
     * loads the sprites of the holes into the hashmap
     */
    private void loadMapSprites() {
        String[] directions = {"North", "South", "East", "West"};
        String imageName;
        Image sprite;

        for (String dir : directions) {
            for (int i = 1; i < 20; i++) {
                imageName = "dig" + dir + i + ".png";

                sprite = loadAndResizeSprite(imageName,
                                             GameManager.PIXELS_PER_DIV * GameManager.TILE_SIZE_IN_DIVS,
                                             GameManager.PIXELS_PER_DIV * GameManager.TILE_SIZE_IN_DIVS);
                try {
                    newObject.Images.put(imageName, sprite);
                } catch (Exception ex) {
                    System.out.println(imageName + " - " + ex.getMessage());
                }
            }
        }

    }

    /**
     *
     * @return background
     */
    public Image getBackGround() {
        return backGround;
    }

    /**
     * takes in a sprite and returns a newly resized image
     *
     * @param imageName
     * @param pixWidth
     * @param pixHeight
     * @return
     */
    public static Image loadAndResizeSprite(String imageName, int pixWidth,
                                            int pixHeight) {

        Image spriteImage = null;
        try {
            File inStream = new File("src/PNGImages/" + imageName);

            spriteImage = ImageIO.read(inStream);

            spriteImage = spriteImage.getScaledInstance(pixWidth, pixHeight,
                                                        java.awt.Image.SCALE_SMOOTH);
        } catch (IOException ex) {
            System.out.println(ex.toString() + imageName);
        }

        return spriteImage;
    }

    /**
     * moves all the objects on the board
     */
    public void moveObjects() {
        try {
            if (getEnemies().size() > 0) {
                for (Enemy enemy : getEnemies()) {
                    enemy.move();
                }
            }
        } catch (Exception e) {
            System.out.println("Enemy ArrayList Error!");
        }
        try {
            //System.out.println(getObjects().toString());
            for (BoardObject obj : getObjects()) {
                obj.move();
            }
        } catch (Exception e) {
            System.out.println("Object ArrayList Error!");
        }
        //move rocks (handles animations

        //ANIMATIONS ARE REPRESENTED AS A NUMBER THAT REPRESENTS THE CURRENT FRAME
        //THE PNGS WILL BE CHANGED LATER
    }

    /**
     * moves the driller in the specified direction
     *
     * @param dir
     */
    public void movePlayer(Direction dir) {
        //System.out.println(this.player1.getDiv());

        player1.move(dir);
    }

    /**
     * shoots the drillers gun
     *
     * @param shoot
     */
    public void shoot(boolean shoot) {
        player1.shoot(shoot);
        //System.out.println("SHOOT");
    }

    /**
     * checks the board to see if there is a collision
     */
    public void checkCollision() {

        this.getTheBoard().isCollision();
    }

    /**
     * laods all the enemy sprites into the hashmap
     */
    private void loadEnemySprites() {
        String[] fygarSprites = {"Fygar_Float_1.png", "Fygar_Float_2.png",
                                 "Fygar_Left_Inflate_1.png", "Fygar_Left_Inflate_2.png",
                                 "Fygar_Left_Inflate_3.png", "Fygar_Left_Inflate_4.png",
                                 "Fygar_Right_Inflate_1.png", "Fygar_Right_Inflate_2.png",
                                 "Fygar_Right_Inflate_3.png", "Fygar_Right_Inflate_4.png",
                                 "Fygar_Left_1.png", "Fygar_Left_2.png",
                                 "Fygar_Right_1.png", "Fygar_Right_2.png",
                                 "Fygar_Rock_Left.png", "Fygar_Rock_Right.png"};

        for (String fygar : fygarSprites) {
            BoardObject.loadImage(fygar, loadAndResizeSprite(fygar,
                                                             GameManager.PIXELS_PER_DIV * GameManager.TILE_SIZE_IN_DIVS,
                                                             GameManager.PIXELS_PER_DIV * GameManager.TILE_SIZE_IN_DIVS));
        }

        String[] pookaSprites = {"Pooka_Float_1.png", "Pooka_Float_2.png",
                                 "Pooka_Left_Inflate_1.png", "Pooka_Left_Inflate_2.png",
                                 "Pooka_Left_Inflate_3.png", "Pooka_Left_Inflate_4.png",
                                 "Pooka_Right_Inflate_1.png", "Pooka_Right_Inflate_2.png",
                                 "Pooka_Right_Inflate_3.png", "Pooka_Right_Inflate_4.png",
                                 "Pooka_Left_1.png", "Pooka_Left_2.png",
                                 "Pooka_Right_1.png", "Pooka_Right_2.png",
                                 "Pooka_Rock_Left.png", "Pooka_Rock_Right.png"};

        for (String pooka : pookaSprites) {
            BoardObject.loadImage(pooka, loadAndResizeSprite(pooka,
                                                             GameManager.PIXELS_PER_DIV * GameManager.TILE_SIZE_IN_DIVS,
                                                             GameManager.PIXELS_PER_DIV * GameManager.TILE_SIZE_IN_DIVS));
        }
    }

    /**
     * laods all of the driller sprites into the hashmap
     */
    private void loadPlayerSprites() {
        String[] diggerFiles = {"Digger_Up_L1.png",
                                "Digger_Up_L2.png",
                                "Digger_Up_R1.png",
                                "Digger_Up_R2.png",
                                "Digger_Down_L1.png",
                                "Digger_Down_L2.png",
                                "Digger_Down_R1.png",
                                "Digger_Down_R2.png",
                                "Digger_Left_1.png",
                                "Digger_Left_2.png",
                                "Digger_Right_1.png",
                                "Digger_Right_2.png"};

        for (String file : diggerFiles) {

            BoardObject.loadImage(file, loadAndResizeSprite(file,
                                                            GameManager.PIXELS_PER_DIV * GameManager.TILE_SIZE_IN_DIVS,
                                                            GameManager.PIXELS_PER_DIV * GameManager.TILE_SIZE_IN_DIVS));
        }

        String[] walkerFiles = {"Walker_Up_L1.png",
                                "Walker_Up_L2.png",
                                "Walker_Up_R1.png",
                                "Walker_Up_R2.png",
                                "Walker_Down_L1.png",
                                "Walker_Down_L2.png",
                                "Walker_Down_R1.png",
                                "Walker_Down_R2.png",
                                "Walker_Left_1.png",
                                "Walker_Left_2.png",
                                "Walker_Right_1.png",
                                "Walker_Right_2.png"};

        for (String file : walkerFiles) {
            BoardObject.loadImage(file, GameManager.loadAndResizeSprite(file,
                                                                        GameManager.PIXELS_PER_DIV * GameManager.TILE_SIZE_IN_DIVS,
                                                                        GameManager.PIXELS_PER_DIV * GameManager.TILE_SIZE_IN_DIVS));
        }

        String[] pumperFiles = {"Pumper_Up_L1.png",
                                "Pumper_Up_L2.png",
                                "Pumper_Up_R1.png",
                                "Pumper_Up_R2.png",
                                "Pumper_Down_L1.png",
                                "Pumper_Down_L2.png",
                                "Pumper_Down_R1.png",
                                "Pumper_Down_R2.png",
                                "Pumper_Left_1.png",
                                "Pumper_Left_2.png",
                                "Pumper_Right_1.png",
                                "Pumper_Right_2.png"};

        for (String file : pumperFiles) {
            BoardObject.loadImage(file, loadAndResizeSprite(file,
                                                            GameManager.PIXELS_PER_DIV * GameManager.TILE_SIZE_IN_DIVS,
                                                            GameManager.PIXELS_PER_DIV * GameManager.TILE_SIZE_IN_DIVS));
        }

        String[] deadFiles = {"Dead_Left_1.png", "Dead_Left_2.png", "Dead_Left_3.png", "Dead_Left_4.png", "Dead_Left_5.png",
                              "Dead_Right_1.png", "Dead_Right_2.png", "Dead_Right_3.png", "Dead_Right_4.png", "Dead_Right_5.png",
                              "Dead_Down_L1.png", "Dead_Down_L2.png", "Dead_Down_L3.png", "Dead_Down_L4.png", "Dead_Down_L5.png",
                              "Dead_Down_R1.png", "Dead_Down_R2.png", "Dead_Down_R3.png", "Dead_Down_R4.png", "Dead_Down_R5.png",
                              "Dead_Up_L1.png", "Dead_Up_L2.png", "Dead_Up_L3.png", "Dead_Up_L4.png", "Dead_Up_L5.png",
                              "Dead_Up_R1.png", "Dead_Up_R2.png", "Dead_Up_R3.png", "Dead_Up_R4.png", "Dead_Up_R5.png",
                              "Dead_Rock_Left.png", "Dead_Rock_Right.png", "Dead_Fire_Left.png", "Dead_Fire_Right.png"};

        for (String file : deadFiles) {
            BoardObject.loadImage(file, loadAndResizeSprite(file,
                                                            GameManager.PIXELS_PER_DIV * GameManager.TILE_SIZE_IN_DIVS,
                                                            GameManager.PIXELS_PER_DIV * GameManager.TILE_SIZE_IN_DIVS));
        }

        //Need to add dead images!!
    }

    /**
     * loads all of the hole images into the hashmap
     */
    public void loadTileSprites() {
        String[] tileRightSprites = {"digEast1.png", "digEast2.png", "digEast3.png", "digEast4.png",
                                     "digEast5.png", "digEast6.png", "digEast7.png", "digEast8.png",
                                     "digEast9.png", "digEast10.png", "digEast11.png", "digEast12.png",
                                     "digEast13.png", "digEast14.png", "digEast15.png", "digEast16.png",
                                     "digEast17.png", "digEast18.png", "digEast19.png"};
        String[] tileLeftSprites = {"digWest1.png", "digWest2.png", "digWest3.png", "digWest4.png",
                                    "digWest5.png", "digWest6.png", "digWest7.png", "digWest8.png",
                                    "digWest9.png", "digWest10.png", "digWest11.png", "digWest12.png",
                                    "digWest13.png", "digWest14.png", "digWest15.png", "digWest16.png",
                                    "digWest17.png", "digWest18.png", "digWest19.png"};
        String[] tileUpSprites = {"digNorth1.png", "digNorth2.png", "digNorth3.png", "digNorth4.png",
                                  "digNorth5.png", "digNorth6.png", "digNorth7.png", "digNorth8.png",
                                  "digNorth9.png", "digNorth10.png", "digNorth11.png", "digNorth12.png",
                                  "digNorth13.png", "digNorth14.png", "digNorth15.png", "digNorth16.png",
                                  "digNorth17.png", "digNorth18.png", "digNorth19.png"};
        String[] tileDownSprites = {"digSouth1.png", "digSouth2.png", "digSouth3.png", "digSouth4.png",
                                    "digSouth5.png", "digSouth6.png", "digSouth7.png", "digSouth8.png",
                                    "digSouth9.png", "digSouth10.png", "digSouth11.png", "digSouth12.png",
                                    "digSouth13.png", "digSouth14.png", "digSouth15.png", "digSouth16.png",
                                    "digSouth17.png", "digSouth18.png", "digSouth19.png"};

        for (String sprite : tileRightSprites) {
            BoardObject.loadImage(sprite, loadAndResizeSprite(sprite,
                                                              GameManager.PIXELS_PER_DIV * GameManager.TILE_SIZE_IN_DIVS,
                                                              GameManager.PIXELS_PER_DIV * GameManager.TILE_SIZE_IN_DIVS));
        }
        for (String sprite : tileLeftSprites) {
            BoardObject.loadImage(sprite, loadAndResizeSprite(sprite,
                                                              GameManager.PIXELS_PER_DIV * GameManager.TILE_SIZE_IN_DIVS,
                                                              GameManager.PIXELS_PER_DIV * GameManager.TILE_SIZE_IN_DIVS));
        }
        for (String sprite : tileUpSprites) {
            BoardObject.loadImage(sprite, loadAndResizeSprite(sprite,
                                                              GameManager.PIXELS_PER_DIV * GameManager.TILE_SIZE_IN_DIVS,
                                                              GameManager.PIXELS_PER_DIV * GameManager.TILE_SIZE_IN_DIVS));
        }
        for (String sprite : tileDownSprites) {
            BoardObject.loadImage(sprite, loadAndResizeSprite(sprite,
                                                              GameManager.PIXELS_PER_DIV * GameManager.TILE_SIZE_IN_DIVS,
                                                              GameManager.PIXELS_PER_DIV * GameManager.TILE_SIZE_IN_DIVS));
        }
    }

    /**
     * returns all the numbers and lives that the driller has
     *
     * @return
     */
    public HashMap<String, Image> getNumbersAndLives() {
        return numbersAndLives;
    }

    public GameBoard getTheBoard() {
        return theBoard;
    }

    public Driller getPlayer1() {
        return player1;
    }

    public ArrayList<Enemy> getEnemies() {
        return theBoard.getEnemyList();
    }

    public ArrayList<BoardObject> getObjects() {
        return theBoard.getObjects();
    }

    public HashMap<String, Image> getBoardImageMap() {
        return boardImageMap;
    }

}
