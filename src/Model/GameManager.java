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
    private GameBoard theBoard;
    private Driller player1;
    private ArrayList<Enemy> enemies;
    private Image enemySprite;
    private Image diggerSprite;
    private Image backGround;
    //private Collectable collectables;
    //private ArrayList<Rock> rocks;
    private HashMap<String, Image> boardImageMap;

    public GameManager() {
        loadSprites();
        initializeFromFile();

        //theBoard = new GameBoard();
        // player1 = new Driller(theBoard);
        //enemies.add(new Enemy(theBoard));
    }

    private void loadSprites() {
        //loadMapSprites();
        //assignEnemySprites();
        //assignPlayerSprites();
        //assignCollectableSprites();
    }

    private void loadMapSprites() {
        String[] directions = {"North", "South", "East", "West"};
        String imageName;
        Image sprite;

        for (String dir : directions) {
            for (int i = 1; i < 20; i++) {
                imageName = "dig" + dir + i;

                sprite = loadAndResizeSprite(imageName, 48, 48);

                boardImageMap.put(imageName, sprite);
            }
        }

    }

    public Image getBackGround() {
        return backGround;
    }

    private void assignEnemySprites() {
        this.diggerSprite = loadAndResizeSprite("Digger_Left_1", 48, 48);
    }

    private void assignPlayerSprites() {
        this.enemySprite = loadAndResizeSprite("Pooka_Left_1", 48, 48);
    }

    public Image loadAndResizeSprite(String imageName, int pixWidth,
                                     int pixHeight) {

        Image spriteImage = null;
        try {
            File inStream = new File("src/PNGImages/" + imageName);

            spriteImage = ImageIO.read(inStream);

            spriteImage = spriteImage.getScaledInstance(pixWidth, pixHeight,
                                                        java.awt.Image.SCALE_SMOOTH);
        } catch (IOException ex) {
            System.out.println(ex.toString() + "   POOP");
        }

        return spriteImage;
    }

    public void moveObject() {
        for (Enemy enemy : enemies) {
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

    private void initializeFromFile() {
        this.backGround = this.loadAndResizeSprite("GrassLevel.png", 672, 864);
        this.theBoard = new GameBoard();
        this.player1 = new Driller(theBoard);
        this.loadPlayerSprites();
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
            this.player1.loadImage(file, this.loadAndResizeSprite(file, 48, 48));
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
            this.player1.loadImage(file, this.loadAndResizeSprite(file, 48, 48));
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
            this.player1.loadImage(file, this.loadAndResizeSprite(file, 48, 48));
        }

        //Need to add dead images!!
    }

    public GameBoard getTheBoard() {
        return theBoard;
    }

    public Driller getPlayer1() {
        return player1;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public Image getEnemySprite() {
        return enemySprite;
    }

    public Image getDiggerSprite() {
        return diggerSprite;
    }

    public HashMap<String, Image> getBoardImageMap() {
        return boardImageMap;
    }

}
