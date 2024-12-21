package Controller;

import Model.ScoreboardModel;
import Utils.SoundManager;
import View.ScoreboardView;
import View.MainMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScoreboardController {
    private ScoreboardView scoreboardView;
    private ScoreboardModel scoreboardModel;
    private SoundManager soundManager;

    public ScoreboardController(ScoreboardView scoreboardView, ScoreboardModel scoreboardModel) {
        this.scoreboardView = scoreboardView;
        this.scoreboardModel = scoreboardModel;
        soundManager = SoundManager.getInstance();

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
        scoreboardView.dispose(); // Close the ScoreboardView
    }
}