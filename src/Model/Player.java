package Model;

import Controller.InputHandler;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player {
    // Attributes
    private String name;
    private int health;
    private int score;
    private int positionX; // Horizontal position
    private int positionY; // Vertical position
    private int speed;     // Movement speed

    public BufferedImage default1, default2, default3, default4, default5;
    public BufferedImage left1, left2, left3, left4, left5;
    public BufferedImage right1, right2, right3, right4, right5;
    public String direction;

    InputHandler inputH;

    int spriteCounter = 0;
    int spriteNum = 1;

    // Constructor
    public Player(/* GameRenderer gr, InputHandler inputH */) {
        this.inputH = inputH;

        setDefaultValues(); // Set default attributes for the player.
        getPlayerImage();   // Load player graphics (images).
    }

    /**
     * Sets the default values for the player attributes like health, score, position, and speed.
     */
    public void setDefaultValues() {
        this.health = 100;   // Default health
        this.score = 0;      // Initial score
        this.positionX = 400; // Default horizontal position
        this.positionY = 550; // Default vertical position
        this.speed = 5;      // Default movement speed
    }

    /**
     * Loads all player images from the resources folder. These images represent
     * the player's appearance in different states or directions.
     */
    public void getPlayerImage() {
        try {
            default1 = ImageIO.read(getClass().getResourceAsStream("resources /img/space ship/default/default1.png"));
            default2 = ImageIO.read(getClass().getResourceAsStream("resources /img/space ship/default/default2.png"));
            default3 = ImageIO.read(getClass().getResourceAsStream("resources /img/space ship/default/default3.png"));
            default4 = ImageIO.read(getClass().getResourceAsStream("resources /img/space ship/default/default4.png"));
            default5 = ImageIO.read(getClass().getResourceAsStream("resources /img/space ship/default/default5.png"));

            left1 = ImageIO.read(getClass().getResourceAsStream("resources /img/space ship/left/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("resources /img/space ship/left/left2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("resources /img/space ship/left/left3.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("resources /img/space ship/left/left4.png"));
            left5 = ImageIO.read(getClass().getResourceAsStream("resources /img/space ship/left/left5.png"));

            right1 = ImageIO.read(getClass().getResourceAsStream("resources /img/space ship/right/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("resources /img/space ship/right/right2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("resources /img/space ship/right/right3.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("resources /img/space ship/right/right4.png"));
            right5 = ImageIO.read(getClass().getResourceAsStream("resources /img/space ship/right/right5.png"));
        } catch (Exception e) {
            e.printStackTrace(); // Handle any errors that occur while loading images.
        }
    }

    /**
     * Updates the player's state based on input and handles animations.
     * - Moves the player left or right.
     * - Updates the sprite (animation frame) based on movement.
     */
    public void update() {
        // Check for input to move the player
        if (inputH.leftPressed == true) {
            direction = "left";
            positionX -= speed; // Move left
        } else if (inputH.rightPressed == true) {
            direction = "right";
            positionX += speed; // Move right
        } else {
            direction = "default"; // No movement
        }

        // Handle sprite animation
        spriteCounter++;
        if (spriteCounter > 5) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 3;
            } else if (spriteNum == 3) {
                spriteNum = 4;
            } else if (spriteNum == 4) {
                spriteNum = 5;
            }
            spriteCounter = 0; // Reset sprite counter
        }
    }

    /**
     * Draws the player on the screen using the appropriate sprite
     * based on the direction and current animation frame.
     *
     * @param g2 The Graphics2D object used for drawing.
     */
    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        // Select the correct image based on the direction and spriteNum
        switch (direction) {
            case "default":
                if (spriteNum == 1) {
                    image = default1;
                }
                if (spriteNum == 2) {
                    image = default2;
                }
                if (spriteNum == 3) {
                    image = default3;
                }
                if (spriteNum == 4) {
                    image = default4;
                }
                if (spriteNum == 5) {
                    image = default5;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                if (spriteNum == 3) {
                    image = left3;
                }
                if (spriteNum == 4) {
                    image = left4;
                }
                if (spriteNum == 5) {
                    image = left5;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                if (spriteNum == 3) {
                    image = right3;
                }
                if (spriteNum == 4) {
                    image = right4;
                }
                if (spriteNum == 5) {
                    image = right5;
                }
                break;
        }

        // Draw the selected image at the player's position
        // g2.drawImage(image, positionX, positionY, null); // Uncomment this when implemented
    }

    /**
     * Simulates the player shooting a bullet.
     * Includes placeholder logic for handling bullet firing.
     */
    public void shoot() {
        System.out.println("Player shot a bullet!"); // Log for debug
        // Logic for firing a bullet can be added here.
    }

    /**
     * Decreases the player's health by the specified damage amount.
     * If health drops to 0 or below, the player is defeated.
     *
     * @param damage The amount of damage taken.
     */
    public void takeDamage(int damage) {
        this.health -= damage; // Decrease health
        if (this.health <= 0) {
            this.health = 0; // Ensure health does not go below 0
            System.out.println(name + " has been defeated!");
        }
    }

    /**
     * Adds points to the player's score.
     *
     * @param points The number of points to add to the score.
     */
    public void addScore(int points) {
        this.score += points; // Increase score
    }

    /**
     * Displays the player's current status in the console.
     */
    public void displayStatus() {
        System.out.println("Player: " + name);
        System.out.println("Health: " + health);
        System.out.println("Score: " + score);
        System.out.println("Position: (" + positionX + ", " + positionY + ")");
    }
}
