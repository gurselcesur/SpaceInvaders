package View;

import javax.swing.*;
import java.awt.*;

public class PauseScreen extends JFrame {
    private JPanel pausePanel;
    private JButton resumeButton;
    private JButton mainMenuButton;
    private JButton exitButton;
    private JButton showScoreboardButton; // New button for showing scoreboard

    public PauseScreen() {
        pausePanel = new JPanel();
        pausePanel.setLayout(new BoxLayout(pausePanel, BoxLayout.Y_AXIS));
        pausePanel.setBackground(Color.DARK_GRAY);

        // Resume Button
        resumeButton = new JButton("Resume");
        styleButton(resumeButton);
        pausePanel.add(resumeButton);

        // Main Menu Button
        mainMenuButton = new JButton("Main Menu");
        styleButton(mainMenuButton);
        pausePanel.add(mainMenuButton);

        // Show Scoreboard Button
        showScoreboardButton = new JButton("Show Scoreboard");
        styleButton(showScoreboardButton);
        pausePanel.add(showScoreboardButton);

        // Exit Button
        exitButton = new JButton("Exit");
        styleButton(exitButton);
        pausePanel.add(exitButton);

        // Frame setup
        add(pausePanel);
        setTitle("Game Paused");
        setSize(300, 250); // Adjusted height for the new button
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Prevent closing directly
        setLocationRelativeTo(null);
        setResizable(false);
    }

    // Getters for buttons
    public JButton getResumeButton() {
        return resumeButton;
    }

    public JButton getMainMenuButton() {
        return mainMenuButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public JButton getShowScoreboardButton() { // Getter for the Show Scoreboard button
        return showScoreboardButton;
    }

    public JPanel getPausePanel() {
        return pausePanel;
    }

    // Utility to style buttons
    private void styleButton(JButton button) {
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(Color.LIGHT_GRAY);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setMaximumSize(new Dimension(200, 40));
    }
}