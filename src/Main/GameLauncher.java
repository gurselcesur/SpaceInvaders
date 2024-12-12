package Main;

import Controller.GameController;
import Model.GameState;
import Utils.SoundManager;
import View.GameRenderer;

public class GameLauncher {
    public static void main(String[] args) {
        GameState model = new GameState();
        SoundManager soundManager = new SoundManager();
        GameRenderer renderer = new GameRenderer(model);
        GameController controller = new GameController(model, renderer, soundManager);




        controller.startGameLoop();
    }
}
