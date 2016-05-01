/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016 *
 * Name: Patrick Newhart, Levi Adair, Sam Greenberg, Tom Ficcadenti
 * Date: May 1, 2016
 * Time: 12:27:54 PM *
 * Project: csci205FinalProject
 * Package: Model
 * File: Collectible
 * Description:
 *
 * **************************************** */
package Model;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author laa024
 */
public class Collectible extends BoardObject {

    private CollectibleEnum type;
    private int difficulty;
    private ArrayList<CollectibleEnum> collectibleList = new ArrayList<CollectibleEnum>();

    /**
     * makes a collectible
     *
     * @param level
     */
    public Collectible(int level) {
        this.difficulty = level;
        this.pickCollectibleType();

    }

    /**
     * selects a collectible based off of the level/ difficulty higher level has
     * chance of getting a higher score collectible
     */
    private void pickCollectibleType() {
        Random r = new Random();
        int listSize = 0;

        for (CollectibleEnum ce : CollectibleEnum.values()) {
            if (ce.getPoints() < (this.difficulty * 100)) {
                collectibleList.add(ce);
                listSize++;

            }

        }
        if (listSize != 0) {
            int choice = r.nextInt(listSize);
            type = collectibleList.get(choice);
        } else {
            type = null;
        }

    }

    public CollectibleEnum getType() {
        return type;
    }

    @Override
    public void move() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     */
    @Override
    public void crush() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * destroys the collectible when called
     */
    @Override
    public void destroy() {
        getBoard().setCollect(null);

    }

}
