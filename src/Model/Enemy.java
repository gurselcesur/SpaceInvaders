package Model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;


/**
 * The Enemy class represents an enemy entity in the game.
 * It handles loading the enemy sprite, updating its position,
 * and rendering it on the screen.
 */

public class Enemy extends EntityBase {
    private int speed;        // The speed at which the enemy moves
    private BufferedImage sprite; // The image used to represent the enemy

    // Constructor initializes the enemy's position, size, and movement speed
    public Enemy(int x, int y, int width, int height, int speed) {
        super(x, y, width, height); // Call the parent class constructor to initialize position and size
        this.speed = speed;         // Set the speed of the enemy

        loadSprite(); // Load the image for the enemy sprite
    }

    // Method to load the enemy sprite from resources
    private void loadSprite() {
        try {
            // Load the sprite image from the resources folder
            sprite = ImageIO.read(getClass().getResourceAsStream("resources/img/alien.png"));
        } catch (Exception e) {
            e.printStackTrace(); // If loading fails, print the stack trace for debugging
        }
    }

    // Update the enemy's position based on its speed
    @Override
    public void update() {
        y += speed; // Move the enemy downward by its speed value
    }

    // Draw the enemy on the screen
    @Override
    public void draw(Graphics2D g2) {
        // Check if the sprite is loaded successfully
        if (sprite != null) {
            // Draw the sprite at the enemy's current position with its specified size
            g2.drawImage(sprite, x, y, width, height, null);
        }
    }
}
