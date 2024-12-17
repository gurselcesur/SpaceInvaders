package Utils;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

// This class manages audio playback using Java's Sound API
public class SoundManager {

    Clip soundClip; // The audio clip to be played
    URL soundURL[] = new URL[30];

    // Constructor to initialize and load a sound clip from a given file path
    public SoundManager() {
        //background musics
        soundURL[0] = getClass().getResource("/sound/Unorganic Asteroid Beat.wav");
        soundURL[1] = getClass().getResource("/sound/SpaceWeed319.wav");

        //sound effects
        soundURL[2] = getClass().getResource("/sound/PlayerShoot.wav");
        soundURL[3] = getClass().getResource("/sound/EnemyShoot.wav");
        soundURL[4] = getClass().getResource("/sound/BlasterCollisionSound.wav");

    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            soundClip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playSound(){
        soundClip.start();
    }
    public void loopSound(){
        soundClip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stopSound(){
        soundClip.stop();
    }

    /*

    --- for background ---

    public void playMusic(int i){
        SoundManager sound = new SoundManager();
        sound.setFile(int value);
        sound.play();
        sound.loop();
    }

    public void stopMusic(){
        sound.stop();
    }

        --- for sound effect ---
    public void playSE(int value){
    sound.setFile(value);
    sound.play();
    }
     */



}

