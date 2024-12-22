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
    private MainMenu mainMenu;

    // ScoreboardController Constructor for Main Menu
    public ScoreboardController(ScoreboardView scoreboardView, ScoreboardModel scoreboardModel, MainMenu mainMenu) {
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

    // ScoreboardController Constructor for GameView
    public ScoreboardController(ScoreboardView scoreboardView,ScoreboardModel scoreboardModel,GameController gameController) {
        this.scoreboardView = scoreboardView;
        this.scoreboardModel = scoreboardModel;

        // Attach action listener to the resume the game
        scoreboardView.getReturnToMainMenuButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeScoreboard();
                gameController.setPaused(false);
            }
        });
    }

    /**
     * Handle the transition back to the Main Menu.
     */
    private void returnToMainMenu() {
        new MainMenuController(new MainMenu(), scoreboardModel); // Pass the model to MainMenuController
        scoreboardView.dispose();
    }

    private void closeScoreboard() {
        scoreboardView.dispose();
    }
}