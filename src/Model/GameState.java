package Model;

import Controller.InputHandler;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class GameState {
    private Player player;               // Player instance
    private List<Enemy> enemies;         // List of enemies
    private List<Bullet> bullets;        // List of bullets
    private boolean isGameOver;          // Flag for game-over state
    private int score;                   // Player's score
    private long lastBulletTime = 0;     // Tracks the time the last bullet was fired
    private static final int BULLET_COOLDOWN = 300; // Cooldown in milliseconds
    private int enemyBulletDamage = 15;
    private Random random = new Random(); // Random instance for enemy shooting

    public GameState(String username, InputHandler inputHandler) {
        player = new Player(username, inputHandler);
        enemies = new ArrayList<>();
        bullets = new ArrayList<>();
        isGameOver = false;
        score = 0;
        initializeEnemies();
    }

    /**
     * Initializes enemies at the start of the game.
     */
    private void initializeEnemies() {
        int enemyWidth = 40;  // Example width of each enemy
        int enemyHeight = 30; // Example height of each enemy
        int rows = 4;         // Number of rows of enemies
        int cols = 6;         // Number of enemies per row
        int spacingX = 20;    // Horizontal spacing
        int spacingY = 15;    // Vertical spacing

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int x = 50 + col * (enemyWidth + spacingX);
                int y = 50 + row * (enemyHeight + spacingY);
                enemies.add(new Enemy(x, y, enemyWidth, enemyHeight, 2)); // Pass all required arguments
            }
        }
        System.out.println("Enemies initialized: " + enemies.size());
    }

    /**
     * Updates the game state, including player, bullets, and enemies.
     */
    public void update() {
        if (isGameOver) return;

        // Update player actions
        player.update();

        // Handle shooting with cooldown
        long currentTime = System.currentTimeMillis();
        if (player.getInputHandler().shootPressed && currentTime - lastBulletTime >= BULLET_COOLDOWN) {
            bullets.add(player.shootBullet());
            lastBulletTime = currentTime; // Update the last bullet time
        }

        //Handle enemy shooting
        handleEnemyShooting();


        // Update bullets and handle collisions
        updateBullets();

        // Update enemies and their behavior
        updateEnemies();

        // Check if the game is over
        checkGameOver();
    }


    /**
     * Updates the bullets' positions and handles their interactions.
     */
    private void updateBullets() {
        Iterator<Bullet> bulletIterator = bullets.iterator();
        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();
            bullet.update();

            // Remove bullets that are out of bounds
            if (bullet.isOutOfBounds()) {
                bulletIterator.remove();
                continue;
            }

            // Check for collisions with enemies
            if (bullet.isPlayerBullet()) {
                handleBulletEnemyCollisions(bulletIterator, bullet);
            }
            // Check for collisions with player
            else if (!bullet.isPlayerBullet() && player.collidesWithBullet(bullet)) {
                player.takeDamage(enemyBulletDamage);
                bulletIterator.remove(); // Remove the bullet after collision
                System.out.println("Player hit! Remaining health: " + player.getHealth());

                checkGameOver();
            }
        }
    }

    /**
     * Handles random enemy shooting with a chance.
     */
    private void handleEnemyShooting() {
        int shootChance = 5; // 5% chance for a random enemy to shoot
        if (random.nextInt(100) < shootChance) {
            Enemy shooter = getRandomAliveEnemy();
            if (shooter != null) {
                bullets.add(shooter.shootBullet());
                System.out.println("Enemy shot a bullet from position (" + shooter.getX() + ", " + shooter.getY() + ")");
            }
        }
    }

    /**
     * Gets a random alive enemy.
     * @return a random alive enemy, or null if no enemies are alive
     */
    private Enemy getRandomAliveEnemy() {
        List<Enemy> aliveEnemies = enemies.stream().filter(Enemy::isAlive).toList();
        if (!aliveEnemies.isEmpty()) {
            return aliveEnemies.get(random.nextInt(aliveEnemies.size()));
        }
        return null;
    }


    /**
     * Handles collisions between player bullets and enemies.
     */
    private void handleBulletEnemyCollisions(Iterator<Bullet> bulletIterator, Bullet bullet) {
        for (Iterator<Enemy> enemyIterator = enemies.iterator(); enemyIterator.hasNext(); ) {
            Enemy enemy = enemyIterator.next();
            if (enemy.isAlive() && bullet.collidesWithEnemy(enemy)) {
                enemy.takeDamage(); // Reduce enemy health
                bulletIterator.remove(); // Remove the bullet
                score += 10; // Increment score for destroying an enemy

                if (!enemy.isAlive()) {
                    enemyIterator.remove();
                    System.out.println("Enemy destroyed at position (" + enemy.getX() + ", " + enemy.getY() + ")");
                    System.out.println("Enemy destroyed! Score: " + score);
                }
                break; // Exit after handling collision
            }
        }
    }

    /**
     * Handles collisions between bullets and the player.
     */
    private void handleBulletPlayerCollisions() {
        Iterator<Bullet> bulletIterator = bullets.iterator();

        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();

            // Check if the bullet is an enemy bullet and collides with the player
            if (!bullet.isPlayerBullet() && player.collidesWithBullet(bullet)) {
                player.takeDamage(enemyBulletDamage); // Reduce player's health by bullet damage
                bulletIterator.remove(); // Remove the bullet after collision

                System.out.println("Player hit! Remaining health: " + player.getHealth());

                checkGameOver();
            }
        }
    }

    //Handle collision between player bullets and enemies.
    private void handlePlayerEnemyCollisions() {
        for (Iterator<Enemy> enemyIterator = enemies.iterator(); enemyIterator.hasNext(); ) {
            Enemy enemy = enemyIterator.next();
            if (enemy.isAlive() && player.collidesWith(enemy)) {
                enemy.takeDamage(); // Reduce enemy health
                score += 10; // Increment score for destroying an enemy
                player.takeDamage(10);
                    if (!enemy.isAlive()) {
                        enemyIterator.remove();
                        System.out.println("Enemy destroyed at position (" + enemy.getX() + ", " + enemy.getY() + ")");
                    }
                    break; // Exit after handling collision
            }
        }
    }


    /**
     * Updates enemies' positions and interactions.
     */
    private void updateEnemies() {
        for (Enemy enemy : enemies) {
            if (enemy.isAlive()) {
                enemy.update();
            }
        }
        handlePlayerEnemyCollisions();
    }

    /**
     * Checks for game-over conditions.
     */
    private void checkGameOver() {
        if (player.getHealth() <= 0) {
            isGameOver = true;
            System.out.println("Game Over! Player health reached 0.");
        } else if (enemies.stream().noneMatch(Enemy::isAlive)) {
            isGameOver = true;
            System.out.println("Game Over! You win!");
        }
    }

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