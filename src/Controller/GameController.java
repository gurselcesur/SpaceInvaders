package Controller;

import Model.GameState;
import Model.ScoreboardModel;
import View.GameRenderer;
import View.GameView;
import View.MainMenu;
import View.PauseScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController {
    private GameRenderer gameRenderer;
    private GameView gameView;
    private String username;
    private boolean isPaused = false;

    public GameController(GameState gameState, GameRenderer gameRenderer, GameView gameView, String username) {
        this.gameRenderer = gameRenderer;
        this.username = username;

        // Attach action listener to Pause button
        gameView.getPauseButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pauseGame();
            }
        });
    }

    /**
     * Handle the Pause button click event.
     */
    public void pauseGame() {
        isPaused = true;
        SwingUtilities.invokeLater(() -> {
            PauseScreen pauseScreen = new PauseScreen();
            pauseScreen.getResumeButton().addActionListener(e -> {
                pauseScreen.dispose();
                resumeGame();
            });

            pauseScreen.getMainMenuButton().addActionListener(e -> {
                pauseScreen.dispose();
                //gameRenderer.dispose();
                new MainMenuController(new MainMenu(), new ScoreboardModel());
            });
            pauseScreen.getExitButton().addActionListener(e -> System.exit(0));
            pauseScreen.setVisible(true);

        });

        // Bu kodu sonra oyun koduna eklememiz gerekecek:
        //*********************************************************
//        private boolean paused = false;
//
//        public void gameLoop() {
//            while (true) {
//                if (!paused) {
//                    // Update game state
//                    updateGameLogic();
//                    renderGame();
//                }
//                try {
//                    Thread.sleep(16); // Limit frame rate (~60 FPS)
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
        //*********************************************************

    }

    public void resumeGame() {
        isPaused = false;
    }
}