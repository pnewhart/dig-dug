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
import Model.GameManager;
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
    private GameManager theModel;
    private MainView GUI;
    private Direction moveState;
    private boolean shoot;

    public Controller(MainView GUI, GameManager theModel) {
        this.theModel = theModel;
        this.GUI = GUI;
        this.moveState = null;
        this.shoot = false;

        GUI.addKeyListener(this);
    }

    public void update() {
        this.theModel.movePlayer(moveState);
        this.theModel.shoot(shoot);
        this.GUI.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void stateChanged(ChangeEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.moveState = Direction.RIGHT;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            this.moveState = Direction.LEFT;
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            this.moveState = Direction.UP;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            this.moveState = Direction.DOWN;
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            this.shoot = true;
        }

        update();
    }

    @Override
    public void keyTyped(KeyEvent e) {

        update();
    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.moveState = null;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            this.moveState = null;
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            this.moveState = null;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            this.moveState = null;
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            this.shoot = false;
        }

        update();
    }

}
