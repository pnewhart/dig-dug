/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016 *
 * Name: Patrick Newhart, Levi Adair, Sam Greenberg, Tom Ficcadenti
 * Date: Apr 20, 2016
 * Time: 2:28:07 AM *
 * Project: csci205FinalProject
 * Package: View
 * File: GameBoardVisual
 * Description:
 *
 * **************************************** */
package View;

import Model.BoardObject;
import Model.Collectible;
import Model.Enemy;
import Model.GameManager;
import Model.Tile;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author tjf010
 */
public class GameBoardVisual extends javax.swing.JComponent {
    private GameManager theModel;
    public boolean boardVisible = false;
//    private Timer timer;
//    private final int DELAY = 25;

    /**
     * Creates new form GameBoardVisualP
     */
    public GameBoardVisual() {
        try {
            initComponents();
            this.theModel = null;

        } catch (Exception e) {
            System.out.println("error");
        }
//
//        timer = new Timer();
//        timer.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                repaint();
//            }
//        }, 0, DELAY);
    }

    public GameManager getTheModel() {
        return theModel;
    }

    public void setTheModel(GameManager theModel) {
        this.theModel = theModel;
    }

    @Override
    public void paintComponent(Graphics g) {
        drawBackGround(g);

        if (boardVisible) {
            drawHoles(g);
            drawEnemies(g);
            drawObjects(g);
            drawDriller(g);
        }

        drawScoreAndLives(g);

    }

    public void drawScoreAndLives(Graphics g) {
        try {
            for (int i = 0; i < theModel.getPlayer1().getLives(); i++) {
                g.drawImage(theModel.getNumbersAndLives().get("LifeIcon"),
                            i * theModel.LIFE_ICON_DIMMENSIONS,
                            theModel.NUMBER_HEIGHT * theModel.PIXELS_PER_DIV,
                            this);
            }

            String scoreStr = "SCORE";
            for (int i = 0; i < scoreStr.length(); i++) {
                if (i < scoreStr.length()) {
                    String iChar = scoreStr.substring(i, i + 1);

                    g.drawImage(theModel.getNumbersAndLives().get(iChar),
                                i * theModel.NUMBER_WIDTH * theModel.PIXELS_PER_DIV,
                                0,
                                this);
                } else if (i > scoreStr.length()) {
                    g.drawImage(theModel.getNumbersAndLives().get("0"),
                                i * theModel.NUMBER_WIDTH * theModel.PIXELS_PER_DIV,
                                0, this);
                }
            }

            int xOffset = (scoreStr.length() + 1) * theModel.NUMBER_WIDTH * theModel.PIXELS_PER_DIV;
            String score = theModel.getPlayer1().getScoreAsString();
            for (int i = 0; i < score.length(); i++) {
                String scoreChar = score.substring(i, i + 1);

                g.drawImage(theModel.getNumbersAndLives().get(scoreChar),
                            xOffset + i * theModel.NUMBER_WIDTH * theModel.PIXELS_PER_DIV,
                            0, this);
            }
        } catch (Exception ex) {
            //this exception is caught when the images for score and lives aren't loaded yet and therefore should not be displayed
            //this catch is here so the game doesnt display the score and lives when its not supposed to
        }
    }

    /**
     * Draws the BackGround
     *
     * @param g
     */
    private void drawBackGround(Graphics g) {
        g.drawImage(theModel.getBackGround(), 0, 0, this);
    }

    /**
     * Draws Mr.Driller getting his current image based on his state
     *
     * @param g
     */
    private void drawDriller(Graphics g) {
        g.drawImage(theModel.getPlayer1().getCurrentImage(),
                    theModel.getPlayer1().getPixel()[0],
                    theModel.getPlayer1().getPixel()[1], this);
    }

    private void drawEnemies(Graphics g) {
        for (Enemy enemy : theModel.getEnemies()) {
            g.drawImage(enemy.getCurrentImage(), enemy.getPixel()[0],
                        enemy.getPixel()[1], this);
        }
    }

    private void drawObjects(Graphics g) {
        for (BoardObject obj : theModel.getObjects()) {
            g.drawImage(obj.getCurrentImage(), obj.getPixel()[0],
                        obj.getPixel()[1], this);
        }

        Collectible collect = theModel.getTheBoard().getCollect();
        System.out.println(theModel.getTheBoard().isCollectPlaced());
        if (theModel.getTheBoard().isCollectPlaced() && theModel.timesPressed > 3) {
            g.drawImage(
                    theModel.getTheBoard().getCollect().getType().getCollectableImage(),
                    (int) collect.getDiv().getX() * GameManager.PIXELS_PER_DIV,
                    (int) collect.getDiv().getY() * GameManager.PIXELS_PER_DIV,
                    this);
        }
    }

    /**
     * Draws the holes onto the board by getting 4 directional holes from the
     * tile class
     *
     * @param g
     */
    private void drawHoles(Graphics g) {
        for (Tile[] column : theModel.getTheBoard().board) {
            for (Tile tile : column) {
                Image[] tempImages = tile.getCurrentImages();
                if (tempImages != null) {
                    for (int i = 0; i < 4; i++) {
                        g.drawImage(tempImages[i], tile.getPixel()[0],
                                    tile.getPixel()[1], this);
                    }
                }
            }
        }
    }

    public boolean isBoardVisible() {
        return boardVisible;
    }

    public void setBoardVisible(boolean boardVisible) {
        this.boardVisible = boardVisible;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setPreferredSize(new java.awt.Dimension(672, 864));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(View.class.getName()).log(
//                    java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(View.class.getName()).log(
//                    java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(View.class.getName()).log(
//                    java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(View.class.getName()).log(
//                    java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                GameBoardVisual gb = new GameBoardVisual();
//                gb.setVisible(true);
//            }
//        });
//    }
}
