package Controller;

import Model.GameState;
import Model.ScoreboardModel;
import Utils.SoundManager;
import View.*;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class GameController {
    private final GameRenderer gameRenderer;
    private final GameView gameView;
    private final GameState gameState;
    private final ScoreboardModel scoreboardModel;
    private final String username;

    private boolean isPaused = false;
    private final SoundManager soundManager;

    public GameController(GameState gameState, GameRenderer gameRenderer, GameView gameView, String username) {
        this.gameState = gameState;
        this.gameRenderer = gameRenderer;
        this.gameView = gameView;
        this.username = username;
        this.soundManager = SoundManager.getInstance();
        this.scoreboardModel = new ScoreboardModel();

        // Attach action listener to Pause button
        gameView.getPauseButton().addActionListener(e -> pauseGame());

        // Start the game loop
        startGameLoop();
    }

    /**
     * Starts the game loop, which continuously updates the game and refreshes the view.
     */
    private void startGameLoop() {
        new Thread(() -> {
            while (!gameState.isGameOver()) {
                if (!isPaused) {
                    // Update the game state
                    gameState.update();

                    // Refresh the game view
                    SwingUtilities.invokeLater(gameRenderer::repaint);
                }
                try {
                    Thread.sleep(16); // Limit frame rate (~60 FPS)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            handleGameOver();
        }).start();
    }

    /**
     * Handle the game-over logic.
     */
    private void handleGameOver() {
        System.out.println("Game Over!");
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(gameView, "Game Over! Your score: " + gameState.getScore());
            new MainMenuController(new MainMenu(), scoreboardModel); // Transition to main menu

            gameView.dispose();
        });
    }

    /**
     * Handle the Pause button click event.
     */
    private void pauseGame() {
        isPaused = true;

        SwingUtilities.invokeLater(() -> {
            PauseScreen pauseScreen = new PauseScreen();

            gameView.getPauseButton().setEnabled(false);

            // Resume the game when Resume button is clicked
            pauseScreen.getResumeButton().addActionListener(e -> {
                pauseScreen.dispose();
                resumeGame();
            });

            // Show Scoreboard when Show Scoreboard button is clicked
            pauseScreen.getShowScoreboardButton().addActionListener(e -> {
                pauseScreen.setVisible(false); // Hide the pause screen
                showScoreboard();
                SwingUtilities.invokeLater(() -> {
                    gameView.getPauseButton().setEnabled(true); // Re-enable the pause button
                    gameView.requestFocus(); // Restore focus to the game view
                });
            });

            // Return to Main Menu when Main Menu button is clicked
            pauseScreen.getMainMenuButton().addActionListener(e -> {
                pauseScreen.dispose();
                gameView.dispose();
                new MainMenuController(new MainMenu(), scoreboardModel);
                gameView.getPauseButton().setEnabled(true);
            });

            // Exit the game when Exit button is clicked
            pauseScreen.getExitButton().addActionListener(e -> System.exit(0));

            pauseScreen.setVisible(true);
        });
    }

    /**
     * Show the scoreboard.
     */
    private void showScoreboard() {
        SwingUtilities.invokeLater(() -> {
            // Create the scoreboard view
            ScoreboardView scoreboardView = new ScoreboardView(scoreboardModel.getHighscores());
            new ScoreboardController(scoreboardView, scoreboardModel, this);

            // Ensure the scoreboard appears in the foreground
            scoreboardView.setAlwaysOnTop(true); // Temporarily set to always on top
            scoreboardView.toFront();            // Bring to the front
            scoreboardView.requestFocus();       // Request focus for input

            // Add a listener to handle when the scoreboard is closed
            scoreboardView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    gameView.getPauseButton().setEnabled(true); // Re-enable the pause button
                    SwingUtilities.invokeLater(() -> gameRenderer.requestFocusInWindow()); // Restore game focus
                }
            });
            scoreboardView.setVisible(true);
        });
    }

    /**
     * Resume the game by closing the pause screen and continuing the game loop.
     */
    private void resumeGame() {
        gameView.getPauseButton().setEnabled(true);
        isPaused = false;
    }
    
    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }


}