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

import DigDugMain.MainView;
import Model.Direction;
import Model.GameManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;
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
    private Timer timer;

    private boolean rightIsPressed;
    private boolean leftIsPressed;
    private boolean upIsPressed;
    private boolean downIsPressed;
    private boolean spaceIsPressed;

    private final int DELAY = 25;

    public Controller(MainView GUI, GameManager theModel) {
        this.theModel = theModel;
        this.GUI = GUI;
        this.moveState = null;
        this.shoot = false;

        GUI.addKeyListener(this);

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                update();
            }
        }, 0, DELAY);

    }

    public void update() {
        if (rightIsPressed && !leftIsPressed && !upIsPressed && !downIsPressed) {
            moveState = Direction.RIGHT;
            shoot = false;
        }
        if (!rightIsPressed && leftIsPressed && !upIsPressed && !downIsPressed) {
            moveState = Direction.LEFT;
            shoot = false;
        }
        if (!rightIsPressed && !leftIsPressed && upIsPressed && !downIsPressed) {
            moveState = Direction.UP;
            shoot = false;
        }
        if (!rightIsPressed && !leftIsPressed && !upIsPressed && downIsPressed) {
            moveState = Direction.DOWN;
            shoot = false;
        }
        if (!rightIsPressed && !leftIsPressed && !upIsPressed && !downIsPressed) {
            moveState = null;
        }
        this.theModel.movePlayer(moveState);
        GUI.repaint();
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
            rightIsPressed = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            leftIsPressed = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            upIsPressed = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            downIsPressed = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            spaceIsPressed = true;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

        update();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightIsPressed = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            leftIsPressed = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            upIsPressed = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            downIsPressed = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            spaceIsPressed = false;
        }
    }
}
