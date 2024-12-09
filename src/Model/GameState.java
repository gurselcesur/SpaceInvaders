package Model;

import java.util.ArrayList;
import java.util.List;

public class GameState {

    private Player player;
    private List<Enemy> enemies;
    private List<Bullet> bullets;
    private boolean isGameOver;
    private int score;

    public GameState() {
        player = new Player(400, 550);
        enemies = new ArrayList<>();
        bullets = new ArrayList<>();
        isGameOver = false;
        score = 0;
        initializeEnemies();
    }

    private void initializeEnemies() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                //enemies.add(new Enemy(50 + j * 60, 50 + i * 40));
            }
        }
    }

    public void update() {
        // Update game logic
    }

    public Player getPlayer() {
        return player;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public int getScore() {
        return score;
    }
}
