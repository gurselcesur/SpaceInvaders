package View;

import Model.GameState;
import com.sun.tools.javac.Main;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameView extends JFrame {
    private JButton pauseButton; // Button to pause the game
    private GameState gameState; // Reference to the game state
    private GameRenderer gameRenderer; // The game renderer
    private Clip backgroundMusicClip;// Clip for background music
    private MainMenu mainMenu;
    private FloatControl volumeControl; // Control for adjusting volume


    public GameView(String username, GameState gameState, GameRenderer gameRenderer, MainMenu mainMenu) {
        this.gameState = gameState;
        this.gameRenderer = gameRenderer;
        this.mainMenu = mainMenu;
        this.volumeControl = mainMenu.getVolumeControl();

        // Frame setup
        setTitle("Space Invaders");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centers the window on the screen
        setResizable(false);
        setLayout(new BorderLayout()); // Use BorderLayout for better layout management

        // Add the top panel with username and pause button
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(Color.DARK_GRAY);

        JLabel usernameLabel = new JLabel("Player: " + username);
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        usernameLabel.setForeground(Color.WHITE);

        pauseButton = new JButton("Pause");
        pauseButton.setFont(new Font("Arial", Font.BOLD, 16));
        pauseButton.setBackground(Color.LIGHT_GRAY);
        pauseButton.setFocusPainted(false);

        topPanel.add(usernameLabel);
        topPanel.add(Box.createHorizontalGlue()); // Spacer to push pause button to the right
        topPanel.add(pauseButton);
        add(topPanel, BorderLayout.NORTH);

        // Add GameRenderer as the central game panel
        add(gameRenderer, BorderLayout.CENTER);
        // Ensure GameRenderer gains focus
        SwingUtilities.invokeLater(gameRenderer::requestFocusInWindow);

        // Make the frame visible
        setVisible(true);
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
            }

            backgroundMusicClip.loop(Clip.LOOP_CONTINUOUSLY); // Loop continuously
            backgroundMusicClip.start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public JButton getPauseButton() {
        return pauseButton;
    }
}