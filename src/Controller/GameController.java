package Controller;

import Model.*;
import View.*;
import Utils.*;

public class GameController {
    private final GameState gameState;
    private final GameRenderer gameRenderer;
    private final SoundManager soundManager;
    private boolean running = true;

    public GameController(GameState gameState, GameRenderer gameRenderer, SoundManager soundManager) {
        this.gameState = gameState;
        this.gameRenderer = gameRenderer;
        this.soundManager = soundManager;

        // Attach input handling
        gameRenderer.addKeyListener(new InputHandler(this));
    }

    // Getters and Setters
    public GameState getGameState() {
        return gameState;
    }
    public GameRenderer getGameRenderer() {
        return gameRenderer;
    }
    public SoundManager getSoundManager() {
        return soundManager;
    }

    public void startGameLoop() {
        final double nsPerTick = 1000000000.0 / 60.0; // 60 updates per second
        long lastTime = System.nanoTime();
        double delta = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;

            while (delta >= 1) {
                update();
                delta--;
            }

            gameRenderer.render(gameState);
        }
    }

    private void update() {
        gameState.update(); // Update game logic
        if (gameState.isGameOver()) {
            System.out.println("Game Over! Final Score: " + gameState.getScore());
            stopGame();
        }
    }

    public void stopGame() {
        running = false;
    }

    // Input handling methods
    public void movePlayerLeft() {
        gameState.getPlayer().moveLeft();
    }

    public void movePlayerRight() {
        gameState.getPlayer().moveRight();
    }

    public void playerShoot() {
        gameState.getPlayer().shoot();
    }
}
