package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;


public class MainMenu extends JFrame {
    private JTextField usernameField;    // Text field for username input
    private JButton startGameButton;    // Start Game button
    private JButton showScoreboardButton;// Show Scoreboard button
    private Clip backgroundMusicClip;// Clip for background music
    private JButton lowerSoundButton;
    private JButton higherSoundButton;
    private FloatControl volumeControl; // Control for adjusting volume


    public MainMenu() {
        // Frame setup
        setTitle("Space Invaders");
        setSize(700, 500);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;
        setLocation(x, y);
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Welcome user label
        JLabel welcomeLabel = new JLabel("Welcome to Space Invaders!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setBounds(150, 50, 400, 50);
        add(welcomeLabel);

        // Username label
        JLabel usernameLabel = new JLabel("Enter your username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        usernameLabel.setBounds(200, 150, 200, 30);
        add(usernameLabel);

        // Username text field
        usernameField = new JTextField();
        usernameField.setBounds(400, 150, 150, 30);
        add(usernameField);

        // Start Game button
        startGameButton = new JButton("Start Game");
        startGameButton.setFont(new Font("Arial", Font.BOLD, 16));
        startGameButton.setBounds(250, 250, 200, 40);
        add(startGameButton);

        // Show Scoreboard button
        showScoreboardButton = new JButton("Show Scoreboard");
        showScoreboardButton.setFont(new Font("Arial", Font.BOLD, 16));
        showScoreboardButton.setBounds(250, 320, 200, 40);
        add(showScoreboardButton);

        // Lower sound button
        lowerSoundButton = new JButton("Lower Sound");
        lowerSoundButton.setFont(new Font("Arial", Font.BOLD, 14));
        lowerSoundButton.setBounds(150, 400, 150, 40);
        add(lowerSoundButton);

        // Higher sound button
        higherSoundButton = new JButton("Higher Sound");
        higherSoundButton.setFont(new Font("Arial", Font.BOLD, 14));
        higherSoundButton.setBounds(400, 400, 150, 40);
        add(higherSoundButton);

        // Play background music
        playBackgroundMusic("resources/sound/SpaceWeed319.wav");

        // Make the frame visible
        setVisible(true);
    }
    //getter & setters
    public JButton getLowerSoundButton() {
        return lowerSoundButton;
    }

    public void setLowerSoundButton(JButton lowerSoundButton) {
        this.lowerSoundButton = lowerSoundButton;
    }
    public JButton getHigherSoundButton() {
        return higherSoundButton;
    }

    public void setHigherSoundButton(JButton higherSoundButton) {
        this.higherSoundButton = higherSoundButton;
    }
    public FloatControl getVolumeControl() {
        return volumeControl;
    }

    public void setVolumeControl(FloatControl volumeControl) {
        this.volumeControl = volumeControl;
    }

    // Method to play background music
    private void playBackgroundMusic(String filePath) {
        try {
            File musicFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
            backgroundMusicClip = AudioSystem.getClip();
            backgroundMusicClip.open(audioStream);

            // Retrieve the volume control after opening the clip
            if (backgroundMusicClip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                volumeControl = (FloatControl) backgroundMusicClip.getControl(FloatControl.Type.MASTER_GAIN);
                volumeControl.setValue(-20);
            }

            backgroundMusicClip.loop(Clip.LOOP_CONTINUOUSLY); // Loop continuously
            backgroundMusicClip.start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Adjust volume
    public void increaseVolume() {
        if (volumeControl != null) {
            float newVolume = volumeControl.getValue() + 10.0f; // Increase by 2 decibels
            volumeControl.setValue(Math.min(newVolume, volumeControl.getMaximum()));
            System.out.println(newVolume);
            System.out.println("Volume increased to: " + newVolume);
        }else{
            System.out.println("Volume control is not initialized.");
        }
    }

    public void decreaseVolume() {
        if (volumeControl != null) {
            float newVolume = volumeControl.getValue() - 10.0f; // Decrease by 2 decibels
            volumeControl.setValue(Math.max(newVolume, volumeControl.getMinimum()));
            System.out.println(newVolume);
            System.out.println("Volume decreased to: " + newVolume);
        }else{
            System.out.println("Volume control is not initialized.");
        }
    }



    // Stop music when the application closes
    @Override
    public void dispose() {
        if (backgroundMusicClip != null && backgroundMusicClip.isRunning()) {
            backgroundMusicClip.stop();
            backgroundMusicClip.close();
        }
        super.dispose();
    }

    // Getter methods
    public JTextField getUsernameField() {
        return usernameField;
    }

    public JButton getStartGameButton() {
        return startGameButton;
    }

    public JButton getShowScoreboardButton() {
        return showScoreboardButton;
    }

}