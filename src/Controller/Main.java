/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author http://staticvoidgames.com/tutorials/swing/listeners
 * @author spg011
 */
public class Main {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Custom Painting");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        panel.addKeyListener(new Controller());

        frame.add(panel);

        frame.setSize(500, 500);
        frame.setVisible(true);

        //make sure the JPanel has the focus
        panel.requestFocusInWindow();
    }

}
