package View;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ScoreboardView extends JFrame {
    private JButton closeButton;

    public ScoreboardView(List<String> highscores) {
        // Frame setup
        setTitle("Scoreboard");
        setSize(700, 500);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;
        setLocation(x, y);
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Title label
        JLabel titleLabel = new JLabel("Highscores");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(150, 20, 400, 50);
        add(titleLabel);

        // Scoreboard area
        JTextArea scoresArea = new JTextArea();
        scoresArea.setEditable(false);
        scoresArea.setFont(new Font("Arial", Font.PLAIN, 16));

        // Populate scores
        StringBuilder scoresText = new StringBuilder();
        for (String score : highscores) {
            scoresText.append(score).append("\n");
        }
        scoresArea.setText(scoresText.toString());

        // Wrap the JTextArea with a JScrollPane
        JScrollPane scrollPane = new JScrollPane(scoresArea);
        scrollPane.setBounds(150, 80, 400, 300); // Set the size and position
        add(scrollPane);

        // Return to Main Menu button
        closeButton = new JButton("Close");
        closeButton.setFont(new Font("Arial", Font.BOLD, 16));
        closeButton.setBounds(250, 400, 200, 40);
        add(closeButton);

        // Make the frame visible
        setVisible(true);
    }

    public JButton getReturnToMainMenuButton() {
        return closeButton;
    }
}