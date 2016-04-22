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

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;

/**
 *
 * @author laa024
 */
public class GameManager {

    private GameBoard gBoard;
    private ArrayList<BufferedImage> imageList;
    private Driller player1;
    private Object collectable;
    private ArrayList<Rock> rocks;
    private HashMap<String, BufferedImage> imageMap;

    public GameManager() {
        gBoard = new GameBoard();
        //loadSprites();

    }

    public void createFromFile(File f) {
        try {
            gBoard.generateFromFile(f);
        } catch (Exception E) {
            System.out.println("input file cannot be read");

        }
    }

    public void moveMoveable() {
        for (int i = 0; i < gBoard.objects.size(); i++) {
            //TODO:make move
            //gBoard.objects.get(i).move();
        }
    }

    public void updatePNG() {

    }

    private void loadImages() {
        loadMapSprites();
        loadCharSprites();
    }

    public void loadMapSprites() {
        String[] biomes = {"Grass", "Snow"};

        String imageName;

        for (String biome : biomes) {
            for (int layer = 0; layer <= 4; layer++) {
                imageName = "base" + biome + layer;

                try {
                    System.out.println("./src/PNGImages/" + imageName + ".png");
                    InputStream in = getClass().getResourceAsStream(
                            "./src/PNGImages/" + imageName + ".png");

                    BufferedImage image = ImageIO.read(in);

                    imageMap.put(imageName, image);
                } catch (IOException ex) {
                    System.out.println(
                            "ERROR: \'./src/PNGImages/" + imageName + ".png\' could not be read.");
                }
            }
        }
    }

    public void loadCharSprites() {
        String imageName = "Digger_Left_1";

        try {
            InputStream in = getClass().getResourceAsStream(
                    "./src/PNGImages/" + imageName + ".png");

            BufferedImage image = ImageIO.read(in);

            imageMap.put(imageName, image);
        } catch (IOException ex) {
            System.out.println(
                    "ERROR: \'./src/PNGImages/" + imageName + ".png\' could not be read.");
        }
    }

    public ArrayList<BufferedImage> getImageList() {
        return imageList;
    }

    public void setImageList(ArrayList<BufferedImage> imageList) {
        this.imageList = imageList;
    }

}
