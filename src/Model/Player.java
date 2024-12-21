package Model;

import Controller.InputHandler;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;

/**
 * Class representing the player's entity in the game
 * It handles loading the player sprite, updating its position,
 * and rendering it on the screen.
 */
public class Player extends EntityBase {
    // Player's attributes
    private String username;
    private int health;          // Current health of the player
    private int score;           // Player's score
    private int speed;           // Movement speed of the player
    private String direction;    // Current direction of the player ("default", "left", "right")

    // Sprites for different animations (default, left, right)
    private BufferedImage[] defaultSprites = new BufferedImage[5];
    private BufferedImage[] leftSprites = new BufferedImage[5];
    private BufferedImage[] rightSprites = new BufferedImage[5];

    private int spriteCounter = 0; // Counter for animating sprites
    private int spriteNum = 1;     // Current sprite frame number
    private InputHandler inputH;   // Reference to the input handler

    // Constructor: Initializes the player and its attributes
    public Player(String username, InputHandler inputH) {
        super(400, 450); // Call to the parent class constructor
        this.username = username;
        this.inputH = inputH;
        setDefaultValues(); // Set initial player attribute values
        loadSprites();      // Load player sprites for animations

        // Debugging: Confirm player initialization
        System.out.println("Player initialized at position (" + x + ", " + y + ")");
    }

    // Getters
    public int getHealth() {
        return health;
    }

    public int getScore() {
        return score;
    }

    // Sets the default values for the player's attributes
    private void setDefaultValues() {
        this.health = 100;       // Default health
        this.score = 0;          // Initial score
        this.speed = 5;          // Default movement speed
        this.direction = "default"; // Default direction

        // Debugging: Confirm default values
        System.out.println("Player default values set: Health = " + health + ", Speed = " + speed);
    }

    // Loads player sprites from the resources directory
    private void loadSprites() {
        try {
            for (int i = 0; i < 5; i++) {
                String default_path = System.getProperty("user.dir") + "/resources/img/space_ship/default/default" + (i + 1) + ".png";
                defaultSprites[i] = ImageIO.read(new FileInputStream(default_path));
                String left_path = System.getProperty("user.dir") + "/resources/img/space_ship/left/left" + (i + 1) + ".png";
                leftSprites[i] = ImageIO.read(new FileInputStream(left_path));
                String right_path = System.getProperty("user.dir") + "/resources/img/space_ship/right/right" + (i + 1) + ".png";
                rightSprites[i] = ImageIO.read(new FileInputStream(right_path));
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handles exceptions during sprite loading
            System.out.println("Error loading sprites for player.");
        }
    }

    // Updates the player's position and animations
    @Override
    public void update() {
        // Debugging: Show input states
        // System.out.println("Left pressed: " + inputH.leftPressed + ", Right pressed: " + inputH.rightPressed);

        // Update player direction and position based on input
        if (inputH.leftPressed) {
            direction = "left";
            x -= speed; // Move left
            if (x < 0) x = 0; // Prevent moving off the left edge
        } else if (inputH.rightPressed) {
            direction = "right";
            x += speed; // Move right
            if (x > 760) x = 760; // Prevent moving off the right edge (assuming player width = 40)
        } else {
            direction = "default"; // No direction (idle)
        }

        // Debugging: Log player's position and direction
        // System.out.println("Player position updated to (" + x + ", " + y + ") with direction: " + direction);

        // Handle sprite animation
        spriteCounter++;
        if (spriteCounter > 5) { // Change sprite every 5 updates
            spriteNum = (spriteNum % 5) + 1; // Loop through sprites (1-5)
            spriteCounter = 0;

            // Debugging: Log sprite animation
            // System.out.println("Sprite updated to frame: " + spriteNum);
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
            g2.drawImage(currentImage, x, y, null);
        } else {
            // Debugging: Log missing sprite
            System.out.println("No sprite available for direction: " + direction);
        }
    }

    // Reduces the player's health by the given damage amount
    public void takeDamage(int damage) {
        health -= damage; // Subtract damage from health
        if (health < 0) health = 0; // Ensure health doesn't go below zero

        // Debugging: Log health reduction
        System.out.println("Player took damage: " + damage + ", Health remaining: " + health);
    }

    // Increases the player's score by the given points
    public void addScore(int points) {
        score += points; // Add points to the score

        // Debugging: Log score increase
        System.out.println("Player score increased by " + points + ", Total score: " + score);
    }
}