package Model;

import java.awt.Color;
import java.awt.Graphics2D;

public class Bullet extends EntityBase {
    private boolean isPlayerBullet; // Indicates if the bullet is fired by the player
    private final int bulletSpeed = 10;
    private Color bulletColor = Color.WHITE;

    public Bullet(int x, int y, int width, int height, boolean isPlayerBullet) {
        super(x, y, width, height);
        this.isPlayerBullet = isPlayerBullet;
    }

    // Moves the bullet upward if it's a player bullet or downward otherwise
    @Override
    public void update() {
        if (isPlayerBullet) {
            y -= bulletSpeed; // Player bullets move up
        } else {
            y += bulletSpeed; // Enemy bullets move down
        }
    }

    // Draws the bullet on the screen
    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(bulletColor);
        // Bullet body
        g2.fillRect(x, y + height / 4, width, height / 2);
        g2.fillOval(x, y, width, height / 2);
        g2.fillOval(x, y + height / 2, width, height / 2);

    }

    // Checks if the bullet collides with a given enemy
    public boolean collidesWithEnemy(Enemy enemy) {
        return x < enemy.getX() + enemy.getWidth() &&
                x + width > enemy.getX() &&
                y < enemy.getY() + enemy.getHeight() &&
                y + height > enemy.getY();
    }


    // Checks if the bullet collides with a given player
    public boolean collidesWithPlayer(Player player) {
        return x < player.getX() + player.getWidth() &&
                x + width > player.getX() &&
                y < player.getY() + player.getHeight() &&
                y + height > player.getY();
    }

    public boolean collidesWithBullet(Bullet bullet) {
        return x < bullet.getX() + bullet.getWidth() &&
                x + width > bullet.getX() &&
                y < bullet.getY() + bullet.getHeight() &&
                y + height > bullet.getY();
    }

    public boolean isOutOfBounds() {
        return y + height < 0 || y > 600; // Assuming the screen height is 600
    }

    public boolean isPlayerBullet() { return isPlayerBullet; }

    public void setBulletColor(Color bulletColor) {
        this.bulletColor = bulletColor;
    }
}