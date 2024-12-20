package Model;

import Controller.InputHandler;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;


/**
 * Class representing the player's entity in the game
 * It handles loading the player sprite, updating its position,
 * and rendering it on the screen.
 */
public class Player extends EntityBase {
    // Player's attributes
    private int health;          // Current health of the player
    private int score;           // Player's score
    private int speed;           // Movement speed of the player
    private String direction;    // Current direction of the player ("default", "left", "right")

    // Sprites for different animations (default, left, right)
    private BufferedImage[] defaultSprites = new BufferedImage[5];
    private BufferedImage[] leftSprites = new BufferedImage[5];
    private BufferedImage[] rightSprites = new BufferedImage[5];

    private InputHandler inputH; // Handles player input
    private int spriteCounter = 0; // Counter for animating sprites
    private int spriteNum = 1;     // Current sprite frame number

    // Constructor: Initializes the player and its attributes
    public Player(int x, int y, int width, int height, InputHandler inputH) {
        super(x, y, width, height); // Call to the parent class constructor
        this.inputH = inputH;

        setDefaultValues(); // Set initial player attribute values
        loadSprites();      // Load player sprites for animations
    }

    // Sets the default values for the player's attributes
    private void setDefaultValues() {
        this.health = 100;       // Default health
        this.score = 0;          // Initial score
        this.speed = 5;          // Default movement speed
        this.direction = "default"; // Default direction
    }

    // Loads player sprites from the resources directory
    private void loadSprites() {
        try {
            for (int i = 0; i < 5; i++) {
                defaultSprites[i] = ImageIO.read(getClass().getResourceAsStream("resources/img/space ship/default/default" + (i + 1) + ".png"));
                leftSprites[i] = ImageIO.read(getClass().getResourceAsStream("resources/img/space ship/left/left" + (i + 1) + ".png"));
                rightSprites[i] = ImageIO.read(getClass().getResourceAsStream("resources/img/space ship/right/right" + (i + 1) + ".png"));
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handles exceptions during sprite loading
        }
    }

    // Updates the player's position and animations
    @Override
    public void update() {
        // Update player direction and position based on input
        if (inputH.leftPressed) {
            direction = "left";
            x -= speed; // Move left
        } else if (inputH.rightPressed) {
            direction = "right";
            x += speed; // Move right
        } else {
            direction = "default"; // No direction (idle)
        }

        // Handle sprite animation
        spriteCounter++;
        if (spriteCounter > 5) { // Change sprite every 5 updates
            spriteNum = (spriteNum % 5) + 1; // Loop through sprites (1-5)
            spriteCounter = 0;
        }
    }

    // Draws the player on the screen based on its current state
    @Override
    public void draw(Graphics2D g2) {
        BufferedImage currentImage = null;

        // Select the appropriate sprite based on the player's direction
        switch (direction) {
            case "left" -> currentImage = leftSprites[spriteNum - 1];
            case "right" -> currentImage = rightSprites[spriteNum - 1];
            default -> currentImage = defaultSprites[spriteNum - 1];
        }

        // Draw the current sprite on the screen
        if (currentImage != null) {
            g2.drawImage(currentImage, x, y, width, height, null);
        }
    }

    // Reduces the player's health by the given damage amount
    public void takeDamage(int damage) {
        health -= damage; // Subtract damage from health
        if (health < 0) health = 0; // Ensure health doesn't go below zero
    }

    // Increases the player's score by the given points
    public void addScore(int points) {
        score += points; // Add points to the score
    }
}