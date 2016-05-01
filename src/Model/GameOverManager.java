/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016 *
 * Name: Patrick Newhart, Levi Adair, Sam Greenberg, Tom Ficcadenti
 * Date: May 1, 2016
 * Time: 4:54:41 PM *
 * Project: csci205FinalProject
 * Package: Model
 * File: GameOverManager
 * Description:
 *
 * **************************************** */
package Model;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author patricknewhart
 */
public class GameOverManager {

    private Image background;

    public GameOverManager() {
        try {
            this.initialize();
        } catch (Exception e) {
            System.out.println("cannot make menu");
        }
    }

    /**
     * initializes the main menu background
     *
     * @throws IOException
     */
    private void initialize() throws IOException {

        this.background = this.loadAndResizeSprite("GameOver.png", 672, 864);

    }

    /**
     * resizes the given sprite to the given dimensions
     *
     * @param imageName
     * @param pixWidth
     * @param pixHeight
     * @return image that has been resized
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

    public Image getBackGround() {
        return this.background;

    }

}
