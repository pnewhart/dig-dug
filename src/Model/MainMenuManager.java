/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016 *
 * Name: Patrick Newhart, Levi Adair, Sam Greenberg, Tom Ficcadenti
 * Date: Apr 28, 2016
 * Time: 9:42:29 PM *
 * Project: csci205FinalProject
 * Package: Model
 * File: MainMenuManager
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
public class MainMenuManager {

    private Image background;

    public Image getBackGround() {
        return background;
    }

    private void initialize() throws IOException {

        this.background = this.loadAndResizeSprite("MainMenu.png", 672, 864);
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

}
