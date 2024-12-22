package Controller;

import Model.GameState;
import Model.ScoreboardModel;
import View.GameRenderer;
import View.GameView;
import View.MainMenu;
import View.ScoreboardView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuController {
    private MainMenu mainMenu;
    public ScoreboardModel scoreboardModel; // Reference to the ScoreboardModel
    private GameState gameState;

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

        // Create game objects
        InputHandler inputHandler = new InputHandler();
        gameState = new GameState(username, inputHandler,scoreboardModel); // Model
        System.out.println("Starting game for user: " + username);
        GameRenderer gameRenderer = new GameRenderer(gameState, inputHandler); // View
        GameView gameView = new GameView(username, gameState, gameRenderer, mainMenu); // View
        new GameController(gameState, gameRenderer, gameView, username); // Controller

        mainMenu.dispose(); // Close the Main Menu window
    }

    /**
     * Handle the Show Scoreboard button click event.
     */
    private void showScoreboard() {
        // Stop background music before showing the scoreboard
        mainMenu.getSoundManager().stopBackgroundMusic();

        // Fetch the latest highscores from the model
        java.util.List<String> highscores = scoreboardModel.getHighscores();

        // Create a new ScoreboardView with the latest data
        ScoreboardView scoreboardView = new ScoreboardView(highscores);

        // Transition to the updated ScoreboardView with a new ScoreboardController
        new ScoreboardController(scoreboardView, scoreboardModel, mainMenu);

        // Display the updated scoreboard
        scoreboardView.setVisible(true);

        // Close the Main Menu window
        mainMenu.dispose();
    }

    // Handle the Lower Sound button click event.
    private void lowerSound() {
        mainMenu.getSoundManager().decreaseVolume();
    }

    // Handle the Higher Sound button click event.
    private void raiseSound() {
        mainMenu.getSoundManager().increaseVolume();
    }

    public ScoreboardModel getScoreboardModel() {
        return scoreboardModel;
    }
}
