package Model;

import java.awt.Graphics2D;

// Abstract base class for entities in the Model package
public abstract class EntityBase implements Entity {
    // Entity's position and dimensions
    protected int x, y;

    // Constructor: Initializes the entity's position and dimensions
    public EntityBase(int x, int y) {
        this.x = x;          // X-coordinate of the entity
        this.y = y;          // Y-coordinate of the entity
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

    // Abstract method: Updates the state of the entity
    @Override
    public abstract void update();

    // Abstract method: Renders the entity using the given Graphics2D context
    @Override
    public abstract void draw(Graphics2D g2);


}