package Controller;

import Model.GameState;
import Model.ScoreboardModel;
import Utils.SoundManager;
import View.*;
import javax.swing.*;

public class GameController {
    private final GameRenderer gameRenderer;
    private final GameView gameView;
    private final GameState gameState;
    private final String username;
    private boolean isPaused = false;
    private SoundManager soundManager;

    public GameController(GameState gameState, GameRenderer gameRenderer, GameView gameView, String username) {
        this.gameState = gameState;
        this.gameRenderer = gameRenderer;
        this.gameView = gameView;
        this.username = username;
        soundManager = SoundManager.getInstance();

        // Attach action listener to Pause button
        gameView.getPauseButton().addActionListener(e -> pauseGame());

        // Start the game loop
        startGameLoop();
    }

    /**
     * Starts the game loop, which continuously updates the game and refreshes the view.
     */
    private void startGameLoop() {
        // System.out.println("Starting game loop for user: " + username);
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
            // Handle game-over logic here if needed
            System.out.println("Game Over!");
        }).start();
    }

    /**
     * Handle the Pause button click event.
     */
    private void pauseGame() {
        isPaused = true;

        // Display the pause screen
        SwingUtilities.invokeLater(() -> {
            PauseScreen pauseScreen = new PauseScreen();

            // Resume the game when Resume button is clicked
            pauseScreen.getResumeButton().addActionListener(e -> {
                pauseScreen.dispose();
                resumeGame();
            });



            // Return to Main Menu when Main Menu button is clicked
            pauseScreen.getMainMenuButton().addActionListener(e -> {
                pauseScreen.dispose();
                gameView.dispose();
                new MainMenuController(new MainMenu(), new ScoreboardModel());
            });

            // Exit the game when Exit button is clicked
            pauseScreen.getExitButton().addActionListener(e -> System.exit(0));
            pauseScreen.setVisible(true);
        });
    }

    /**
     * Resume the game by closing the pause screen and continuing the game loop.
     */
    private void resumeGame() {
        isPaused = false;
    }
}