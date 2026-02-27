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

    public HockeyPlayer(int xposInput, int yposInput, double dxInput, double dyInput, int widthInput, int heightInput){
        xpos = xposInput;
        ypos = yposInput;
        dx = dxInput;
        dy = dyInput;
        width = widthInput;
        height = heightInput;
        hitbox = new Rectangle(xpos, ypos, width, height);
    }

    public void move(){
        if (up){
            ypos = ypos - (int)dy;
        }
        if (down){
            ypos = ypos + (int)dy;

        }
        if (right) {
            xpos = xpos + (int) dx;
        }
        if (left) {
            xpos = xpos - (int) dx;

        }

        if (xpos < 0){
            xpos=0;
        }
        if (ypos < 0) {
            ypos=0;

        }
        /*if(xpos<=0){
            xpos=899;

        }
        if(ypos<=0){
            ypos=599;
        }*/
        hitbox = new Rectangle(xpos,ypos,width,height);
    }
}
