package Model;

import java.awt.*;

public class Bullet {
    private int positionX;           // X-coordinate of the bullet
    private int positionY;           // Y-coordinate of the bullet
    private int speed;               // Speed of the bullet
    private boolean isPlayerBullet;  // True if the bullet is fired by the player
    private int width;               // Width of the bullet
    private int height;              // Height of the bullet
    private Color color;             // Color of the bullet for rendering

    /**
     * Constructor to initialize the bullet.
     *
     * @param positionX      X-coordinate of the bullet's initial position.
     * @param positionY      Y-coordinate of the bullet's initial position.
     * @param speed          Speed of the bullet.
     * @param isPlayerBullet True if the bullet is fired by the player.
     */
    public Bullet(int positionX, int positionY, int speed, boolean isPlayerBullet) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.speed = speed;
        this.isPlayerBullet = isPlayerBullet;

        // Default dimensions and color
        this.width = 5;  // Default width
        this.height = 10; // Default height
        this.color = isPlayerBullet ? Color.WHITE : Color.RED; // White for player, red for enemies
    }

    /**
     * Updates the position of the bullet based on its speed and direction.
     */
    public void update() {
        // Player bullets move upward; enemy bullets move downward
        positionY += (isPlayerBullet ? -speed : speed);
    }

    /**
     * Renders the bullet on the screen.
     *
     * @param g2 The Graphics2D object used for rendering.
     */
    public void draw(Graphics2D g2) {
        g2.setColor(color);
        g2.fillRect(positionX, positionY, width, height);
    }

    /**
     * Checks if the bullet is out of the screen bounds.
     *
     * @return True if the bullet is out of bounds.
     */
    public boolean isOutOfBounds() {
        return positionY < 0 || positionY > 600; // Assuming the screen height is 600
    }

    /**
     * Checks if the bullet collides with an entity.
     *
     * @param entity The entity to check for collision.
     * @return True if the bullet collides with the entity.

    public boolean collidesWith(Entity entity) {
        int entityX = entity.getPositionX();
        int entityY = entity.getPositionY();
        int entityWidth = entity.getWidth();
        int entityHeight = entity.getHeight();

        // Check for overlap between the bullet and the entity
        return positionX < entityX + entityWidth &&
                positionX + width > entityX &&
                positionY < entityY + entityHeight &&
                positionY + height > entityY;
    }*/

    // Getters and Setters
    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public boolean isPlayerBullet() {
        return isPlayerBullet;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}