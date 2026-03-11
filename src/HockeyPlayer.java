import java.awt.*;

public class HockeyPlayer {
    String name;
    Image image;
    int xpos;
    int ypos;
    double dx;
    double dy;
    int width;
    int height;
    Rectangle hitbox;

    boolean up = false;
    boolean down = false;
    boolean left = false;
    boolean right = false;

    // creates the player and sets its tarting position and size
    public HockeyPlayer(int xposInput, int yposInput, double dxInput, double dyInput, int widthInput, int heightInput){
        xpos = xposInput;
        ypos = yposInput;
        dx = dxInput;
        dy = dyInput;
        width = widthInput;
        height = heightInput;
        hitbox = new Rectangle(xpos, ypos, width, height);
    }

    public void move(){ // moves the player depending on which arrow keys are pressed
        if (up){ // move up if the up arrow is pressed
            ypos = ypos - (int)dy;
        }
        if (down){ // move down if the down arrow is pressed
            ypos = ypos + (int)dy;

        }
        if (right) { // move right if the right arow is pressed
            xpos = xpos + (int) dx;
        }
        if (left) { // move left if the left arrow is pressed
            xpos = xpos - (int) dx;

        }

        if (xpos < 0){ // stops the player from moving off the left side of the screen
            xpos=0;
        }
        if (ypos < 0) { // stops the player from moving off the top of the screen
            ypos=0;

        }
        /*if(xpos<=0){
            xpos=899;

        }
        if(ypos<=0){
            ypos=599;
        }*/
        hitbox = new Rectangle(xpos,ypos,width,height); // updates hitbox
    }
}
