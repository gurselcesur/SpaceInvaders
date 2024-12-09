package Model;

public class Player {
    // Attributes
    private String name;
    private int health;
    private int score;
    private int positionX; // Horizontal position
    private int positionY; // Vertical position
    private int speed;     // Movement speed

    // Constructor
    public Player(String name, int startX, int startY) {
        this.name = name;
        this.health = 100;   // Default health
        this.score = 0;      // Initial score
        this.positionX = startX;
        this.positionY = startY;
        this.speed = 5;      // Default speed
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    // Methods
    public void moveLeft() {
        this.positionX -= speed;
    }

    public void moveRight() {
        this.positionX += speed;
    }

    public void shoot() {
        System.out.println("Player shot a bullet!");
        // Logic for firing a bullet
    }

    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health <= 0) {
            this.health = 0;
            System.out.println(name + " has been defeated!");
        }
    }

    public void addScore(int points) {
        this.score += points;
    }

    // Display Player Status
    public void displayStatus() {
        System.out.println("Player: " + name);
        System.out.println("Health: " + health);
        System.out.println("Score: " + score);
        System.out.println("Position: (" + positionX + ", " + positionY + ")");
    }
}
