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

import Model.Direction;
import Model.SimpleGameManager;
import View.MainView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author spg011
 */
public class Controller implements ActionListener, ChangeListener, KeyListener {
    private SimpleGameManager theModel;
    private MainView GUI;

    public Controller() {
        this.theModel = new SimpleGameManager();
        //this.GUI = new MainView();

        //this.GUI.addKeyListener(KeyEvent.VK_RIGHT);
        //this.GUI.addKeyListener(KeyEvent.VK_LEFT);
        //this.GUI.addKeyListener(KeyEvent.VK_UP);
        //this.GUI.addKeyListener(KeyEvent.VK_DOWN);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void stateChanged(ChangeEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            theModel.movePlayer(Direction.RIGHT);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            theModel.movePlayer(Direction.LEFT);
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            theModel.movePlayer(Direction.UP);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            theModel.movePlayer(Direction.DOWN);
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            theModel.shoot(true);
        } else {
            theModel.shoot(false);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            theModel.movePlayer(Direction.RIGHT);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            theModel.movePlayer(Direction.LEFT);
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            theModel.movePlayer(Direction.UP);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            theModel.movePlayer(Direction.DOWN);
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            theModel.shoot(true);
        } else {
            theModel.shoot(false);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
