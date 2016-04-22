/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016 *
 * Name: Patrick Newhart, Levi Adair, Sam Greenberg, Tom Ficcadenti
 * Date: Apr 21, 2016
 * Time: 8:12:25 PM *
 * Project: csci205FinalProject
 * Package: MainGame
 * File: Model
 * Description:
 *
 * **************************************** */
package MainGame;

import Model.Driller;
import Model.GameBoard;
import Model.Pumpable;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author tjf010
 */
public class Model {
    private GameBoard theBoard;
    private Driller player1;
    private ArrayList<Pumpable> enemies;
    private Collectable collectables;
    private ArrayList<Rock> rocks;
    private HashMap<String, BufferedImage> imageMap;

    public Model() {
        loadSprites();
        initializeFromFile();
    }

    public void initializeFromFile() {

    }

    public void move() {
        //move player (handles tile changes and animations)
        //move enemies (handles animations)
        //move rocks (handles animations

        //ANIMATIONS ARE REPRESENTED AS A NUMBER THAT REPRESENTS THE CURRENT FRAME
        //THE PNGS WILL BE CHANGED LATER
    }

    public void updatePNGs() {
        //update the PNG of any tile that was altered by a player
        //update PNG's of players and enemies
    }
}
