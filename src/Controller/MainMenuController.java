package Controller;

import Model.ScoreboardModel;
import View.GameRenderer;
import View.MainMenu;
import View.ScoreboardView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuController {
    private MainMenu mainMenu;
    private ScoreboardModel scoreboardModel; // Reference to the ScoreboardModel

    public MainMenuController(MainMenu mainMenu, ScoreboardModel scoreboardModel) {
        this.mainMenu = mainMenu;
        this.scoreboardModel = scoreboardModel;

        // Attach action listeners to buttons
        mainMenu.getStartGameButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        mainMenu.getShowScoreboardButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showScoreboard();
            }
        });
    }

    /**
     * Handle the Start Game button click event.
     */
    private void startGame() {
        String username = mainMenu.getUsernameField().getText().trim();

        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(mainMenu, "Please enter your username to start the game!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Transition to GameRenderer and GameController
        System.out.println("Starting game for user: " + username);
        new GameController(new GameRenderer(username), username);
        mainMenu.dispose(); // Close the Main Menu window
    }

    /**
     * Handle the Show Scoreboard button click event.
     */
    private void showScoreboard() {
        // Fetch highscores from the model
        java.util.List<String> highscores = scoreboardModel.getHighscores();

        // Transition to ScoreboardView with ScoreboardController
        new ScoreboardController(new ScoreboardView(highscores), scoreboardModel);
        mainMenu.dispose(); // Close the Main Menu window
    }

}