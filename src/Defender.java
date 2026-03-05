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

    public Defender(int xposInput, int yposInput, int dxInput, int dyInput, int widthInput, int heightInput) {
        xpos = xposInput;
        ypos = yposInput;
        dx = dxInput;
        dy = dyInput;
        width = widthInput;
        height = heightInput;
        hitbox = new Rectangle(xpos, ypos, width, height);
    }

    public void move() {
        xpos = xpos + dx;
        ypos = ypos + dy;

        if (ypos <= 0) {
            dy = -dy;
        }
        if (ypos >= 700) {
            dy = -dy;
        }

        hitbox = new Rectangle(xpos, ypos, width, height);
    }
}
