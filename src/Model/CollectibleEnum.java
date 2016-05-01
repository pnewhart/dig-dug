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

/**
 *
 * @author laa024
 */
public enum CollectibleEnum {
    APPLE("APPLE", 100), CARROT("CARROT", 150), EAGLE("EAGLE", 500), EGGPLANT(
            "EGGPLANT", 150), GARLIC("GARLIC", 75), MUSHROOM("MUSHROOM", 50), ONION(
                    "ONION",
                    125), PICKLE("PICKLE", 80), PINEAPPLE("PINEAPPLE", 150), TOMATO(
                    "TOMATO", 160), WATERMELON("WATERMELON", 200);
    private int points;
    private String name;

    CollectibleEnum(String name, int score) {
        this.points = score;
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;

    }

}
