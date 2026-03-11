import java.awt.*;

public class Goal {

    String name;
    Image image;
    int xpos;
    int ypos;
    int width;
    int height;
    Rectangle hitbox;

    // creates the goal and sets its position and size
    public Goal(int xposInput, int yposInput, int widthInput, int heightInput) {
        xpos = xposInput;
        ypos = yposInput;
        width = widthInput;
        height = heightInput;
        hitbox = new Rectangle(xpos, ypos, width, height);
    }

    // updates the hitbox
    public void move() {
        hitbox = new Rectangle(xpos, ypos, width, height);
    }
}