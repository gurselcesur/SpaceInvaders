package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    private JTextField usernameField;    // Text field for username input
    private JButton startGameButton;    // Start Game button
    private JButton showScoreboardButton; // Show Scoreboard button

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

        // Make the frame visible
        setVisible(true);
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