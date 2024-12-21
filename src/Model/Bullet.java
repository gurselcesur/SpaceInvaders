package Model;

import java.awt.Color;
import java.awt.Graphics2D;

public class Bullet extends EntityBase {
    private boolean isPlayerBullet; // Indicates if the bullet is fired by the player

    public Bullet(int x, int y, int width, int height, boolean isPlayerBullet) {
        super(x, y, width, height);
        this.isPlayerBullet = isPlayerBullet;
    }

    // Moves the bullet upward if it's a player bullet or downward otherwise
    @Override
    public void update() {
        if (isPlayerBullet) {
            y -= 10; // Player bullets move up
        } else {
            y += 10; // Enemy bullets move down
        }
    }

    // Draws the bullet on the screen
    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.YELLOW);
        g2.fillRect(x, y, width, height);
    }

    // Checks if the bullet collides with a given enemy
    public boolean collidesWith(Enemy enemy) {
        return x < enemy.getX() + enemy.getWidth() &&
                x + width > enemy.getX() &&
                y < enemy.getY() + enemy.getHeight() &&
                y + height > enemy.getY();
    }

    public boolean isOutOfBounds() {
        return y + height < 0 || y > 600; // Assuming the screen height is 600
    }

    public boolean isPlayerBullet() {
        return isPlayerBullet;
    }
}