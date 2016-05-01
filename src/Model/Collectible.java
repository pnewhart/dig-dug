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

/**
 *
 * @author laa024
 */
public class Collectible extends Object {

    private CollectibleEnum type;

    public Collectible(int level) {
        super();

    }

    @Override
    public void move() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void crush() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
