package Model;
import java.awt.*;

public interface Entity {
    void update();
    void draw(Graphics2D g2);
    int getX();
    int getY();
}
