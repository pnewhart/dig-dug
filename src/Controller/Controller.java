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
import View.MainMenuVisual;
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
    private MainMenuVisual menuVis;
    private Direction moveState;
    private boolean shoot;
    private Timer timer;
    private int collectibleTimer = 0;
    private boolean rightIsPressed;
    private boolean leftIsPressed;
    private boolean upIsPressed;
    private boolean downIsPressed;
    private boolean spaceIsPressed;
    private boolean enterIsPressed;
    private boolean gameCreated = false;
    private int enterPressed = 0;

    private int timesPushed = 0;
    private int timesPlayed = 0;
    private int timesGO = 0;
    private int timesPlayed1 = 0;

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

    /**
     * updates the entire model based off of the key presses
     */
    public void update() {

        theModel.moveObjects();
        theModel.checkCollision();
        moveDriller();

        GUI.repaint();

        handleSound();

        handleLevels();

    }

    /**
     * Plays the sounds based on the current state of the game.
     */
    private void handleSound() {
        if (theModel.getPlayer1().isDead()) {
            if (timesPlayed == 0) {
                Model.Sound.DigDugDeadMusic();
                timesPlayed += 1;
            }
        }
        if (theModel.getPlayer1().isDead() == false) {
            timesPlayed = 0;
        }

        if (theModel.getPlayer1().getLives() == 0) {
            if (timesGO == 0) {
                theModel.getTheBoard().resetBoard();
                theModel.gameOver();
                Model.Sound.stopMain();
                Model.Sound.stopDead();
                Model.Sound.DigDugGameOverMusic();
            }
            timesGO += 1;
            GUI.getGameBoardVisual1().boardVisible = false;
        } else {
            System.out.println(theModel.getTheBoard().getObjects().isEmpty());
        }

        if (theModel.getEnemies().isEmpty() && theModel.getLevelCounter() > 1) {
            theModel.nextLevel();
            timesGO = 0;
            timesPlayed = 0;
            timesPlayed1 = 0;
            timesPushed = 0;
            Model.Sound.stopLastOneMusic();
            Model.Sound.loopMain();
        }
    }

    /**
     * Handles switched between levels or resetting levels.
     */
    private void handleLevels() {
        if (theModel.getEnemies().size() == 1 && theModel.getLevelCounter() > 0) {
            if (timesPlayed1 == 0) {
                Model.Sound.stopMain();
                Model.Sound.playLastOneMusic();
            }
            timesPlayed1 += 1;
        } else if (theModel.getPlayer1().hasReset || (theModel.getTheBoard().getObjects().isEmpty() && theModel.getLevelCounter() >= 1)) {
            theModel.resetLevel();
            theModel.getPlayer1().hasReset = false;
            theModel.getPlayer1().decrementLife();
        }
    }

    /**
     * Moves the Driller based on the button presses.
     */
    private void moveDriller() {
        if (rightIsPressed && !leftIsPressed && !upIsPressed && !downIsPressed) {
            moveState = Direction.RIGHT;
        }
        if (!rightIsPressed && leftIsPressed && !upIsPressed && !downIsPressed) {
            moveState = Direction.LEFT;
        }
        if (!rightIsPressed && !leftIsPressed && upIsPressed && !downIsPressed) {
            moveState = Direction.UP;
        }
        if (!rightIsPressed && !leftIsPressed && !upIsPressed && downIsPressed) {
            moveState = Direction.DOWN;
        }
        if (!rightIsPressed && !leftIsPressed && !upIsPressed && !downIsPressed) {
            moveState = null;
        }

        this.theModel.movePlayer(moveState);
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
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            enterIsPressed = true;
            if (!gameCreated) {
                gameCreated = true;
                theModel.createGame();
            }
            if (timesPushed == 0) {
                Model.Sound.stopStart();
                Model.Sound.DigDugGameMusic();
                Model.Sound.loopMain();
            }
            if (gameCreated) {
                theModel.nextLevel();
            }
            theModel.timesPressed += 1;
            timesPushed += 1;
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
