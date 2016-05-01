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
import java.io.IOException;
import java.net.MalformedURLException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author patricknewhart
 */
public class Sound {

    private static Clip myMainClip;
    private static Clip myStartClip;

    /**
     * plays music when driller is killed
     */
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

    /**
     * starts the music for main menu
     */
    public static void DigDugStartMusic() {
        try {
            File file = new File("src/02 Start Music.wav");
            if (file.exists()) {
                myStartClip = AudioSystem.getClip();
                AudioInputStream ais = AudioSystem.getAudioInputStream(
                        file.toURI().toURL());
                myStartClip.open(ais);
            } else {
                throw new RuntimeException("Sound: file not found: Start Music");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Sound: Malformed URL: " + e);
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException("Sound: Unsupported Audio File: " + e);
        } catch (IOException e) {
            throw new RuntimeException("Sound: Input/Output Error: " + e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException("Sound: Line Unavailable: " + e);
        }
        playStart();
    }

    public static void playStart() {
        myStartClip.setFramePosition(0);  // Must always rewind!
        myStartClip.loop(0);
        myStartClip.start();
    }

    public static void loopStart() {
        myStartClip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public static void stopStart() {
        myStartClip.stop();
    }

    /**
     * starts music for the game
     */
    public static void DigDugGameMusic() {
        try {
            File file = new File("src/InGameMusic.wav");
            if (file.exists()) {
                myMainClip = AudioSystem.getClip();
                AudioInputStream ais = AudioSystem.getAudioInputStream(
                        file.toURI().toURL());
                myMainClip.open(ais);
            } else {
                throw new RuntimeException("Sound: file not found: InGameMusic");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Sound: Malformed URL: " + e);
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException("Sound: Unsupported Audio File: " + e);
        } catch (IOException e) {
            throw new RuntimeException("Sound: Input/Output Error: " + e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException("Sound: Line Unavailable: " + e);
        }
    }

    public static void playMain() {
        myMainClip.setFramePosition(0);  // Must always rewind!
        myMainClip.loop(0);
        myMainClip.start();
    }

    public static void loopMain() {
        myMainClip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public static void stopMain() {
        myMainClip.stop();
    }
}
