package Model;

import Controller.InputHandler;
import java.util.ArrayList;
import java.util.List;

public class GameState {
    private Player player;                // Player instance
    private List<Enemy> enemies;         // List of enemies
    private List<Bullet> bullets;        // List of bullets
    private boolean isGameOver;          // Flag for game-over state
    private int score;                   // Player's score

    public GameState(String username, InputHandler inputHandler) {
        player = new Player(username, inputHandler);
        enemies = new ArrayList<>();
        bullets = new ArrayList<>();
        isGameOver = false;
        score = 0;
        // initializeEnemies();
    }

    /**
     * Initialize enemies at the start of the game.
     */
    private void initializeEnemies() {
        for (int i = 0; i < 5; i++) { // 5 rows of enemies
            for (int j = 0; j < 10; j++) { // 10 enemies per row
                enemies.add(new Enemy(50 + j * 60, 50 + i * 40, 2)); // Example positions and speed
            }
        }
    }

    /**
     * Update game logic, including player, enemies, and bullets.
     */
    public void update() {
        if (isGameOver) return;

        // Update player
        player.update();

        // Update bullets
        // updateBullets();

        // Update enemies
        // updateEnemies();

        // Check for game-over condition
        if (player.getHealth() <= 0) {
            isGameOver = true; // Player lost
        } //else if (enemies.isEmpty()) {
          //  isGameOver = true; // Player won
        // }
    }

    /**
     * Update bullets' positions and handle collisions.

    private void updateBullets() {
        Iterator<Bullet> bulletIterator = bullets.iterator();
        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();
            bullet.update();

            // Remove bullets that go out of bounds
            if (bullet.isOutOfBounds()) {
                bulletIterator.remove();
            } else {
                // Check for collisions with enemies
                if (bullet.isPlayerBullet()) {
                    Iterator<Enemy> enemyIterator = enemies.iterator();
                    while (enemyIterator.hasNext()) {
                        Enemy enemy = enemyIterator.next();
                        if (bullet.collidesWith(enemy)) {
                            enemyIterator.remove();
                            bulletIterator.remove();
                            score += 10; // Increment score for destroying an enemy
                            break;
                        }
                    }
                } else {
                    // Check for collision with the player
                    if (bullet.collidesWith(player)) {
                        player.takeDamage(10); // Example damage amount
                        bulletIterator.remove();
                    }
                }
            }
        }
    } */

    /**
     * Update enemies' positions and actions.

    private void updateEnemies() {
        for (Enemy enemy : enemies) {
            enemy.update();

            // Check if the enemy reaches the player's position
            if (enemy.collidesWith(player)) {
                player.takeDamage(20); // Example damage for collision
                enemies.remove(enemy); // Remove the enemy
                break;
            }
        }
    } */

    // Getters and Setters
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public void addBullet(Bullet bullet) {
        bullets.add(bullet);
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public int getScore() {
        return score;
    }
}