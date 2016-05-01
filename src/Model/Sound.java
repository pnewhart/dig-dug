/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016 *
 * Name: Patrick Newhart, Levi Adair, Sam Greenberg, Tom Ficcadenti
 * Date: Apr 30, 2016
 * Time: 8:57:20 AM *
 * Project: csci205FinalProject
 * Package: Model
 * File: Sound
 * Description:
 *
 * **************************************** */
package Model;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author patricknewhart
 */
public class Sound {

    public static void DigDugDead() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                            new File("src/Dig_Dug_Dead.wav"));
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    System.out.println(
                            "play sound error: " + e.getMessage() + " for Dig_Dug_Dead.wav");
                }
            }
        }).start();
    }

    public static void DigDugMusic() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                            new File("src/02 Start Music.wav"));
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    System.out.println(
                            "play sound error: " + e.getMessage() + " for Dig_Dug_Dead.wav");
                }
            }
        }).start();
    }
}
