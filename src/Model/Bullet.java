package Model;

public class Bullet {
    private int positionX; // X-coordinate of the bullet
    private int positionY; // Y-coordinate of the bullet
    private int speed; // Speed of the bullet
    private boolean isPlayerBullet; // True if the bullet is fired by the player

    private static final int BULLET_WIDTH = 5; // Width of the bullet
    private static final int BULLET_HEIGHT = 10; // Height of the bullet

    public Bullet(int positionX, int positionY, int speed, boolean isPlayerBullet) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.speed = speed;
        this.isPlayerBullet = isPlayerBullet;
    }

    // Update the position of the bullet
    public void update() {
        // Player bullets move upward; enemy bullets move downward
        positionY += (isPlayerBullet ? -speed : speed);
    }

    // Check if the bullet is out of bounds
    public boolean isOutOfBounds() {
        return positionY < 0 || positionY > 600; // Assuming the screen height is 600
    }

    // Collision detection with an enemy or player
    //  public boolean collidesWith(Entity entity) {
    //          int entityX = entity.getPositionX();
    //          int entityY = entity.getPositionY();
    //          int entityWidth = entity.getWidth();
    //          int entityHeight = entity.getHeight();

    //      // Check for overlap between the bullet and the entity
    //      return positionX < entityX + entityWidth &&
    //              positionX + BULLET_WIDTH > entityX &&
    //              positionY < entityY + entityHeight &&
    //              positionY + BULLET_HEIGHT > entityY;
    //  }

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
}