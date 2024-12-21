package Controller;

import View.GameView;
import View.PauseScreen;
import View.GameRenderer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseController {
    private PauseScreen pauseScreen;
    private GameRenderer gameRenderer;
    private GameController gameController;
    private GameView gameView;

    public PauseController(PauseScreen pauseScreen, GameRenderer gameRenderer, GameController gameController){
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


}
