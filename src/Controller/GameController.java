package Controller;

import View.GameRenderer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController {
    private GameRenderer gameRenderer;
    private String username;

    public GameController(GameRenderer gameRenderer, String username) {
        this.gameRenderer = gameRenderer;
        this.username = username;

        // Attach action listener to Pause button
        gameRenderer.getPauseButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pauseGame();
            }
        });
    }

    /**
     * Handle the Pause button click event.
     */
    private void pauseGame() {
        System.out.println("Game paused for user: " + username);
        // Transition to a Pause screen (to be implemented)
    }
}