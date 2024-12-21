package Model;

import Utils.SoundManager;

import java.awt.Color;
import java.awt.Graphics2D;

public class Enemy extends EntityBase {
    private int health;
    private boolean isAlive;
    private SoundManager soundManager;

    public Enemy(int x, int y, int width, int height, int health) {
        super(x, y, width, height);
        this.health = health;
        this.isAlive = true;
        soundManager = SoundManager.getInstance();
    }

    // Reduce the enemy's health and mark it as dead if health is 0
    public void takeDamage() {
        health--;
        if (health <= 0) {
            isAlive = false;
        }
        soundManager.enemyHitSound();
    }

    // Check if the enemy is still alive
    public boolean isAlive() {
        return isAlive;
    }

    // Updates enemy movement (placeholder for your enemy behavior)
    @Override
    public void update() {
        // Example: Enemies could move horizontally or downward periodically
    }

    // Draws the enemy on the screen
    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.RED);
        g2.fillRect(x, y, width, height);
    }
}