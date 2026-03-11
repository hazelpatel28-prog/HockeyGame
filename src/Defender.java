import java.awt.*;

public class Defender {
    String name;
    Image image;
    int xpos;
    int ypos;
    int dx;
    int dy;
    int width;
    int height;
    Rectangle hitbox;

    // creates the defender and sets its starting position and size
    public Defender(int xposInput, int yposInput, int dxInput, int dyInput, int widthInput, int heightInput) {
        xpos = xposInput;
        ypos = yposInput;
        dx = dxInput;
        dy = dyInput;
        width = widthInput;
        height = heightInput;
        hitbox = new Rectangle(xpos, ypos, width, height);
    }

    public void move() { // moves the defender up and down on the screen
        xpos = xpos + dx;
        ypos = ypos + dy;

        if (ypos <= 0) { // if the defender reaches the top of the screen, it reverses its direction
            dy = -dy;
        }
        if (ypos >= 700) { // if the defender reaches the bottom of the screen, it reverses its direction

            dy = -dy;
        }

        hitbox = new Rectangle(xpos, ypos, width, height); // updates the hitbox
    }
}
