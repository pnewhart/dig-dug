/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016 *
 * Name: Patrick Newhart, Levi Adair, Sam Greenberg, Tom Ficcadenti
 * Date: Apr 21, 2016
 * Time: 8:49:46 PM *
 * Project: csci205FinalProject
 * Package: MainGame
 * File: Controller
 * Description:
 *
 * **************************************** */
package Controller;

import Model.GameManager;
import View.MainView;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author tjf010
 */
public class Controller implements ActionListener, ChangeListener {
    private GameManager theModel;
    private MainView GUI;

    public Controller(GameManager theModel, MainView GUI) {
        this.theModel = theModel;
        this.GUI = GUI;

        this.GUI.addKeyListener(KeyEvent.VK_RIGHT);
        this.GUI.addKeyListener(KeyEvent.VK_LEFT);
        this.GUI.addKeyListener(KeyEvent.VK_UP);
        this.GUI.addKeyListener(KeyEvent.VK_DOWN);
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }
}
