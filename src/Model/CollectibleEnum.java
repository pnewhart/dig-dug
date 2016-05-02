/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016 *
 * Name: Patrick Newhart, Levi Adair, Sam Greenberg, Tom Ficcadenti
 * Date: May 1, 2016
 * Time: 12:32:28 PM *
 * Project: csci205FinalProject
 * Package: Model
 * File: CollectibleEnum
 * Description:
 *
 * **************************************** */
package Model;

import java.awt.Image;

/**
 *
 * @author laa024
 */
public enum CollectibleEnum {

    APPLE("APPLE", 100),
    CARROT("CARROT", 100),
    EAGLE("EAGLE", 800),
    EGGPLANT("EGGPLANT", 200),
    GARLIC("GARLIC", 200),
    MUSHROOM("MUSHROOM", 300),
    ONION("ONION", 400),
    PICKLE("PICKLE", 500),
    PINEAPPLE("PINEAPPLE", 600),
    TOMATO("TOMATO", 500),
    WATERMELON("WATERMELON", 700);

    private int points;
    private String name;
    private Image collectableImage;

    CollectibleEnum(String name, int score) {
        this.points = score;
        this.name = name;
        this.collectableImage = GameManager.loadAndResizeSprite(name + ".png",
                                                                GameManager.PIXELS_PER_DIV * GameManager.TILE_SIZE_IN_DIVS,
                                                                GameManager.PIXELS_PER_DIV * GameManager.TILE_SIZE_IN_DIVS);
    }

    public Image getCollectableImage() {
        return collectableImage;
    }

    /**
     * get the point value of a collectible
     *
     * @return points
     */
    public int getPoints() {
        return points;
    }

    /**
     * name of the collectible
     *
     * @return
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name;

    }

}
