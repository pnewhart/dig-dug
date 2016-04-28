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
    protected GameBoard theBoard;
    private Driller player1 = new Driller();
    private Image backGround;
    private Object newObject;
    private Enemy en;
    //private Collectable collectables;
    //private ArrayList<Rock> rocks;
    public HashMap<String, Image> boardImageMap;

    public GameManager() {
        theBoard = new GameBoard();
        Object.setBoard(theBoard);
        loadSprites();
        try {
            initializeFromFile();
        } catch (Exception ex) {
            System.out.println("ERRONEOUS FILE INTITIALIZATION!");
        }
    }

    private void loadSprites() {
        this.loadPlayerSprites();
        this.loadMapSprites();
        this.loadEnemySprites();
    }

    private void loadMapSprites() {
        String[] directions = {"North", "South", "East", "West"};
        String imageName;
        Image sprite;

        for (String dir : directions) {
            for (int i = 1; i < 20; i++) {
                imageName = "dig" + dir + i + ".png";

                sprite = loadAndResizeSprite(imageName, 48, 48);
                try {
                    newObject.Images.put(imageName, sprite);
                } catch (Exception ex) {
                    System.out.println(imageName + " - " + ex.getMessage());
                }
            }
        }

    }

    public Image getBackGround() {
        return backGround;
    }

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

    public void moveObject() {
        for (Enemy enemy : getEnemies()) {
            enemy.move();
        }
        //move rocks (handles animations

        //ANIMATIONS ARE REPRESENTED AS A NUMBER THAT REPRESENTS THE CURRENT FRAME
        //THE PNGS WILL BE CHANGED LATER
    }

    public void movePlayer(Direction dir) {
        //System.out.println(this.player1.getDiv());

        player1.move(dir);
    }

    public void shoot(boolean shoot) {
        player1.shoot(shoot);
        //System.out.println("SHOOT");
    }

    private void initializeFromFile() throws IOException {

        try {
            this.backGround = this.loadAndResizeSprite("GrassLevel.png", 672,
                                                       864);
            System.out.println("worked");
            this.theBoard = new GameBoard();
            player1.setBoard(theBoard);
            System.out.println("2");
            //File f = new File("input.txt");
            //this.theBoard.generateFromFile(f);
            this.player1 = new Driller();
            this.loadPlayerSprites();
            this.loadMapSprites();
            this.loadEnemySprites();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

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
            player1.loadImage(fygar, loadAndResizeSprite(fygar, 48, 48));
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
            player1.loadImage(pooka, loadAndResizeSprite(pooka, 48, 48));
        }
    }

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

            player1.loadImage(file, loadAndResizeSprite(file, 48, 48));
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
            player1.loadImage(file, this.loadAndResizeSprite(file, 48, 48));
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
            player1.loadImage(file, loadAndResizeSprite(file, 48, 48));
        }

        //Need to add dead images!!
    }

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

        for (Tile[] col : theBoard.board) {
            for (Tile tile : col) {
                for (String sprite : tileRightSprites) {
                    newObject.loadImage(sprite, loadAndResizeSprite(sprite, 48,
                                                                    48));
                }
                for (String sprite : tileLeftSprites) {
                    newObject.loadImage(sprite, loadAndResizeSprite(sprite, 48,
                                                                    48));
                }
                for (String sprite : tileUpSprites) {
                    newObject.loadImage(sprite, loadAndResizeSprite(sprite, 48,
                                                                    48));
                }
                for (String sprite : tileDownSprites) {
                    newObject.loadImage(sprite, loadAndResizeSprite(sprite, 48,
                                                                    48));
                }
            }
        }
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

    public HashMap<String, Image> getBoardImageMap() {
        return boardImageMap;
    }

}
