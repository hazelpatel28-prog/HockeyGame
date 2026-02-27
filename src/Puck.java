import java.awt.*;

public class Puck {
    String name;
    Image image;
    int xpos;
    int ypos;
    double dx;
    double dy;
    int width;
    int height;
    Rectangle hitbox;
    boolean isAlive = true;

    public Puck(int xposInput, int yposInput, double dxInput, double dyInput, int widthInput, int heightInput){
        xpos = xposInput;
        ypos = yposInput;
        dx = dxInput;
        dy = dyInput;
        width = widthInput;
        height = heightInput;
        hitbox = new Rectangle(xpos, ypos, width, height);
    }

    public void move() {
        // for now, puck doesn't move
        hitbox = new Rectangle(xpos, ypos, width, height);
    }
}
