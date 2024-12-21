package Model;

import java.awt.Graphics2D;

// Abstract base class for entities in the Model package
public abstract class EntityBase implements Entity {
    // Entity's position and dimensions
    protected int x, y, width, height;

    // Constructor: Initializes the entity's position and dimensions
    public EntityBase(int x, int y, int width, int height) {
        this.x = x;              // X-coordinate of the entity
        this.y = y;              // Y-coordinate of the entity
        this.width = width;      // Width of the entity
        this.height = height;    // Height of the entity
    }

    // Getter method for the X-coordinate of the entity
    @Override
    public int getX() {
        return x;
    }

    // Getter method for the Y-coordinate of the entity
    @Override
    public int getY() {
        return y;
    }

    // Getter method for the width of the entity
    public int getWidth() {
        return width;
    }

    // Getter method for the height of the entity
    public int getHeight() {
        return height;
    }

    // Abstract method: Updates the state of the entity
    @Override
    public abstract void update();

    // Abstract method: Renders the entity using the given Graphics2D context
    @Override
    public abstract void draw(Graphics2D g2);
}