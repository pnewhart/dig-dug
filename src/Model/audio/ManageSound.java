/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016 *
 * Name: Patrick Newhart, Levi Adair, Sam Greenberg, Tom Ficcadenti
 * Date: Apr 26, 2016
 * Time: 5:40:46 PM *
 * Project: csci205FinalProject
 * Package: Model.audio
 * File: ManageSound
 * Description:
 *
 * **************************************** */
package Model.audio;

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
public class ManageSound {

    private Clip myClip;

    public ManageSound(String fileName) {
        try {
            File file = new File(fileName);
            if (file.exists()) {
                Clip myClip = AudioSystem.getClip();
                AudioInputStream ais = AudioSystem.getAudioInputStream(
                        file.toURI().toURL());
                myClip.open(ais);
            } else {
                throw new RuntimeException(
                        "Sound: file not found: " + fileName);
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

    public void play() {
        myClip.setFramePosition(0);  // Must always rewind!
        myClip.loop(0);
        myClip.start();
    }

    public void loop() {
        myClip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        myClip.stop();
    }
}
