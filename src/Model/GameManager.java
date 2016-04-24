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
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.ImageIcon;

/**
 *
 * @author laa024
 */
public class GameManager {
    private GameBoard theBoard;
    private Driller player1;
    private ArrayList<Enemy> enemies;
    private ImageIcon enemySprite;
    private ImageIcon diggerSprite;
    //private Collectable collectables;
    //private ArrayList<Rock> rocks;
    private HashMap<String, ImageIcon> boardImageMap;

    public GameManager() {
        loadSprites();
        initializeFromFile();

        theBoard = new GameBoard();
        player1 = new Driller(theBoard);

        enemies.add(new Enemy(theBoard));

    }

    private void loadSprites() {
        loadMapSprites();
        assignEnemySprites();
        assignPlayerSprites();
        //assignCollectableSprites();
    }

    private void loadMapSprites() {
        String[] directions = {"North", "South", "East", "West"};
        String imageName;
        ImageIcon sprite;

        for (String dir : directions) {
            for (int i = 1; i < 20; i++) {
                imageName = "dig" + dir + i;

                sprite = loadAndResizeSprite(imageName);

                boardImageMap.put(imageName, sprite);
            }
        }

        String[] biomes = {"Grass", "Snow"};

        for (String biome : biomes) {
            for (int layer = 1; layer <= 4; layer++) {
                imageName = "base" + biome + layer;

                sprite = loadAndResizeSprite(imageName);

                boardImageMap.put(imageName, sprite);
            }
        }
    }

    private void assignEnemySprites() {
        this.diggerSprite = loadAndResizeSprite("Digger_Left_1");
    }

    private void assignPlayerSprites() {
        this.enemySprite = loadAndResizeSprite("Pooka_Left_1");
    }

    private ImageIcon loadAndResizeSprite(String imageName) {
        ImageIcon sprite = new ImageIcon(
                "src/PNGImages/" + imageName + ".png");
        Image resizedSprite = sprite.getImage();
        resizedSprite = resizedSprite.getScaledInstance(48, 48,
                                                        java.awt.Image.SCALE_SMOOTH);
        sprite = new ImageIcon(resizedSprite);
        return sprite;
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
        player1.move(dir);
    }

    public void initializeFromFile() {
        int rowNum = 0;
        int layer;
        for (Tile[] row : this.theBoard.getBoard()) {
            if (rowNum >= 0 && rowNum < 2) {
                layer = 4;
            } else if (rowNum >= 2 && rowNum < 4) {
                layer = 1;
            } else if (rowNum >= 4 && rowNum < 7) {
                layer = 2;
            } else if (rowNum >= 7 && rowNum < 10) {
                layer = 3;
            } else {
                layer = 4;
            }
            for (Tile tile : row) {
                tile.setBaseImageKey("baseGrass" + layer);
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
        return enemies;
    }

    public ImageIcon getEnemySprite() {
        return enemySprite;
    }

    public ImageIcon getDiggerSprite() {
        return diggerSprite;
    }

    public HashMap<String, ImageIcon> getBoardImageMap() {
        return boardImageMap;
    }

}
