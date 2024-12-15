package Utils;

import java.net.URL;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

// This class manages audio playback using Java's Sound API
public class SoundManager implements LineListener {

    Clip soundClip; // The audio clip to be played
    URL soundURL[] = new URL[30];

    // Constructor to initialize and load a sound clip from a given file path
    public SoundManager(String path) {
        try {
            // Locate the audio file using the given path
            URL url = getClass().getResource(path);

            // Create an AudioInputStream for the audio file
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);

            // Get the audio format of the file
            AudioFormat format = audioInputStream.getFormat();

            // Create a DataLine.Info object to specify the audio clip format
            DataLine.Info info = new Info(Clip.class, format);

            // Obtain a Clip instance compatible with the specified format
            soundClip = (Clip) AudioSystem.getLine(info);

            // Open the audio clip and load the audio data
            soundClip.open(audioInputStream);

            // Add a LineListener to monitor the clip's playback events
            soundClip.addLineListener(this);
        } catch (Exception e) {
            // Print the stack trace in case of any exceptions
            e.printStackTrace();
        }
    }


    // This method is triggered when a line event occurs (e.g., playback stop)
    @Override
    public void update(LineEvent event) {
        if (event.getType().equals(LineEvent.Type.STOP)) {
            // Reset the clip's position to the beginning after it stops playing
            soundClip.setFramePosition(1);
        }
    }

    // Method to play the sound once
    public void play() {
        soundClip.start();
    }

    // Method to play the sound in an infinite loop
    public void loop() {
        soundClip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    // Method to stop playback and reset the clip's position
    public void stop() {
        soundClip.stop();
        soundClip.setFramePosition(1);
    }

    // Method to check if the sound is currently playing
    public boolean isPlaying() {
        return soundClip.isRunning();
    }
}

