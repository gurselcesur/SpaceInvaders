package View;

import javax.swing.*;
import java.awt.*;

public class PauseScreen extends JFrame {
    private JPanel pausePanel;
    private JButton resumeButton;
    private JButton mainMenuButton;
    private JButton exitButton;

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

        // Exit Button
        exitButton = new JButton("Exit");
        styleButton(exitButton);
        pausePanel.add(exitButton);


        add(pausePanel);
        setTitle("Game Paused");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Prevent closing directly
        setLocationRelativeTo(null);
        setResizable(false);


    }

    public JButton getResumeButton() {
        return resumeButton;
    }

    public JButton getMainMenuButton() {
        return mainMenuButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public JPanel getPausePanel() {
        return pausePanel;
    }

    private void styleButton(JButton button) {
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(Color.LIGHT_GRAY);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setMaximumSize(new Dimension(200, 40));
    }
}