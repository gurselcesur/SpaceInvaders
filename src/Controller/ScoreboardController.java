package Controller;

import Model.ScoreboardModel;
import Utils.SoundManager;
import View.GameView;
import View.ScoreboardView;
import View.MainMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScoreboardController {
    private ScoreboardView scoreboardView;
    private ScoreboardModel scoreboardModel;
    private GameView gameView;

    public ScoreboardController(ScoreboardView scoreboardView, ScoreboardModel scoreboardModel, GameView gameView) {
        this.scoreboardView = scoreboardView;
        this.scoreboardModel = scoreboardModel;
        this.gameView = gameView;

        // Attach action listener to the Return to Main Menu button
        scoreboardView.getReturnToMainMenuButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnToMainMenu();
            }
        });
    }

    public ScoreboardController(ScoreboardView scoreboardView, ScoreboardModel scoreboardModel) {
        this.scoreboardView = scoreboardView;
        this.scoreboardModel = scoreboardModel;
        // Attach action listener to the Return to Main Menu button
        scoreboardView.getReturnToMainMenuButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnToMainMenu();
            }
        });
    }

    /**
     * Handle the transition back to the Main Menu.
     */
    private void returnToMainMenu() {
        new MainMenuController(new MainMenu(), scoreboardModel); // Pass the model to MainMenuController
        if(gameView.isGameViewOn()){
            scoreboardView.dispose(); // Close the ScoreboardView
            gameView.setGameViewOn(false);
            gameView.dispose();
        }else{
            scoreboardView.dispose();
        }
    }
}