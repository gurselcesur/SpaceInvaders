package Controller;

import Model.ScoreboardModel;
import View.GameView;
import View.PauseScreen;
import View.GameRenderer;
import View.ScoreboardView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseController {
    private PauseScreen pauseScreen;
    private GameRenderer gameRenderer;
    private GameController gameController;
    private GameView gameView;
    private ScoreboardModel scoreboardModel;

    public PauseController(PauseScreen pauseScreen, GameRenderer gameRenderer, GameController gameController){
        this.scoreboardModel = new ScoreboardModel();
        this.pauseScreen = pauseScreen;
        this.gameRenderer = gameRenderer;
        this.gameController = gameController;
        this.gameView = gameView;

        gameView.getPauseButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //gameController.pauseGame();
            }
        });
    }

    private void displayScoreboard(){
        // Show Scoreboard button: Display the scoreboard
        pauseScreen.getShowScoreboardButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                java.util.List<String> highscores = scoreboardModel.getHighscores();
                new ScoreboardController(new ScoreboardView(highscores), scoreboardModel);
            }
        });
    }


}
