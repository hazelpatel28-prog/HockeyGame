import java.awt.*;

public class Puck {
    String name;
    double xpos;
    double ypos;
    double dx;
    double dy;
    int width;
    int height;
    Rectangle hitbox;
    Image aliveImage;
    Image deadImage;
    boolean isAlive = true;

    // creates the puck and sets its starting position and size
    public Puck(int xposInput, int yposInput, int dxInput, int dyInput, int widthInput, int heightInput){
        xpos = xposInput;
        ypos = yposInput;
        dx = dxInput;
        dy = dyInput;
        width = widthInput;
        height = heightInput;
        hitbox = new Rectangle((int)xpos, (int)ypos, width, height);
    }

    public void move() {
        // for now, puck doesn't move
        xpos=(xpos+dx);
        ypos=ypos+dy;

        // update the hitbox
        hitbox = new Rectangle((int)xpos, (int)ypos, width, height);
    }
}
