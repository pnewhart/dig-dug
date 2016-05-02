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
    private static Clip myGameOverClip;
    private static Clip myDeadClip;
    private static Clip myLastOneSoundClip;
    private static Clip myLastOneSoundMusic;

    /**
     * starts the music for main menu
     */
    public static void DigDugDeadMusic() {
        try {
            File file = new File("src/Dig_Dug_Dead.wav");
            if (file.exists()) {
                myDeadClip = AudioSystem.getClip();
                AudioInputStream ais = AudioSystem.getAudioInputStream(
                        file.toURI().toURL());
                myDeadClip.open(ais);
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
        playDead();
    }

    public static void playDead() {
        myDeadClip.setFramePosition(0);  // Must always rewind!
        myDeadClip.loop(0);
        myDeadClip.start();
    }

    public static void loopDead() {
        myDeadClip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public static void stopDead() {
        myDeadClip.stop();
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
        if (myMainClip.isActive()) {
            myMainClip.stop();
        }
    }

    /**
     * starts the music for main menu
     */
    public static void DigDugGameOverMusic() {
        try {
            File file = new File("src/21 Game Over.wav");
            if (file.exists()) {
                myGameOverClip = AudioSystem.getClip();
                AudioInputStream ais = AudioSystem.getAudioInputStream(
                        file.toURI().toURL());
                myGameOverClip.open(ais);
            } else {
                throw new RuntimeException("Sound: file not found: Game Over");
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
        playGameOver();
    }

    public static void playGameOver() {
        myGameOverClip.setFramePosition(0);  // Must always rewind!
        myGameOverClip.loop(0);
        myGameOverClip.start();
    }

    public static void loopGameOver() {
        myGameOverClip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public static void stopGameOver() {
        if (myGameOverClip.isActive()) {
            myGameOverClip.stop();
        }
    }

    public static void DigDugLastOneMusic() {
        try {
            File file = new File("src/08 Last One Music.wav");
            if (file.exists()) {
                myLastOneSoundMusic = AudioSystem.getClip();
                AudioInputStream ais = AudioSystem.getAudioInputStream(
                        file.toURI().toURL());
                myLastOneSoundMusic.open(ais);
            } else {
                throw new RuntimeException(
                        "Sound: file not found: Last One Music");
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
        playLastOneMusic();
    }

    public static void playLastOneMusic() {
        try {
            if (myLastOneSoundMusic == null) {
                DigDugLastOneMusic();
            } else if (myLastOneSoundMusic.isActive()) {
                loopLastOneMusic();
            } else {
                myLastOneSoundMusic.setFramePosition(0);  // Must always rewind!
                myLastOneSoundMusic.loop(0);
                myLastOneSoundMusic.start();
            }
        } catch (Exception e) {
            DigDugLastOneMusic();
        }
    }

    public static void loopLastOneMusic() {
        myLastOneSoundMusic.setFramePosition(0);
        myLastOneSoundMusic.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public static void stopLastOneMusic() {
        if (myLastOneSoundMusic.isActive()) {
            myLastOneSoundMusic.stop();
        }
    }

}
