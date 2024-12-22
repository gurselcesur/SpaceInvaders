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
    private ScoreboardModel scoreboardModel; // Reference to the ScoreboardModel
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

        mainMenu.getLowerSoundButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lowerSound();
            }
        });

        mainMenu.getHigherSoundButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                raiseSound();
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
        gameState = new GameState(username, inputHandler); // Model
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
        // Fetch highscores from the model
        java.util.List<String> highscores = scoreboardModel.getHighscores();

        // Transition to ScoreboardView with ScoreboardController
        new ScoreboardController(new ScoreboardView(highscores), scoreboardModel);
        mainMenu.dispose(); // Close the Main Menu window
    }

    // Handle the Lower Sound button click event.
    private void lowerSound() {
        mainMenu.getSoundManager().decreaseVolume();
    }

    // Handle the Higher Sound button click event.
    private void raiseSound() {
        mainMenu.getSoundManager().increaseVolume();
    }
}
