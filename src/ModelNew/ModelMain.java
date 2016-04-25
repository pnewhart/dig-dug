/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016 *
 * Name: Patrick Newhart, Levi Adair, Sam Greenberg, Tom Ficcadenti
 * Date: Apr 19, 2016
 * Time: 6:35:59 PM *
 * Project: csci205FinalProject
 * Package: ModelNew
 * File: ModelMain
 * Description:
 *
 * **************************************** */
package ModelNew;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import javax.imageio.ImageIO;

/**
 *
 * @author tjf010
 */
public class ModelMain {
    private HashMap<String, BufferedImage> images;
    private GameBoard gameBoard;

    public ModelMain() {
        //loadImages();

        this.gameBoard = new GameBoard();
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < GameBoard.BOARD_HEIGHT; i++) {
            for (int j = 0; j < GameBoard.BOARD_WIDTH; j++) {
                this.gameBoard.getBoard()[i][j] = new Tile(i, j, 1, "Grass");

                //BufferedImage tileImage = this.images.get("baseGrass1");
                //this.gameBoard.getBoard()[i][j].setBaseImage(tileImage);
            }
        }
    }

    private void loadImages() {
        loadHoleSprites();
        loadCharSprites();
    }

    public void loadHoleSprites() {
        String[] directions = {"Grass", "Snow"};

        String imageName;

        for (String biome : directions) {
            for (int layer = 0; layer <= 4; layer++) {
                imageName = "base" + biome + layer;

                try {
                    System.out.println("./src/PNGImages/" + imageName + ".png");

                    InputStream in = getClass().getResourceAsStream(
                            "./src/PNGImages/" + imageName + ".png");

                    BufferedImage image = ImageIO.read(in);

                    images.put(imageName, image);
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

            images.put(imageName, image);
        } catch (IOException ex) {
            System.out.println(
                    "ERROR: \'./src/PNGImages/" + imageName + ".png\' could not be read.");
        }
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

}
