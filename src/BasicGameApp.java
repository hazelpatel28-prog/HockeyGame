//vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv DON'T CHANGE! vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
// Graphics Libraries

/*
Author: Hazel Patel
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
public class BasicGameApp implements Runnable, KeyListener, MouseListener {

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 700;


    //Variable Definition Section
    //You can set their initial values too

    // Array that has the two background images
    Image[] backgrounds = new Image[2];

    // the hockey player controlled by the arrow keys
    HockeyPlayer player;

    // the puck that the player shoots towards the goal
    Puck puck;

    // the goal the puck needs to reach
    Goal goal;

    // the defender that moves up and down to block the puck
    Defender defender;

    Font myFont;
    int score = 0;
    boolean gameOver = false;
    boolean playing = true;
    boolean hasPuck = false;
    double a,b,c,am,bm,cm;
    int backgroundNum = 0;



    // Initialize your variables and construct your program objects here.
    public BasicGameApp() { // BasicGameApp constructor
        setUpGraphics();

        //variable and objects
        //create (construct) the objects needed for the game
        backgrounds[0] = Toolkit.getDefaultToolkit().getImage("HockeyRink.jpg");
        backgrounds[1] = Toolkit.getDefaultToolkit().getImage("rink2.png");


        player = new HockeyPlayer(150,300,6,6,100,100);
        player.name = "Hazel";
        player.image = Toolkit.getDefaultToolkit().getImage("HockeyPlayer.png");

        puck = new Puck(400,350,0,0, 50, 50);
        puck.name = "Puck";
        puck.aliveImage = Toolkit.getDefaultToolkit().getImage("Puck.png");
        puck.deadImage = Toolkit.getDefaultToolkit().getImage("PuckDeadImage.png");

        goal = new Goal(900,300,115,115);
        goal.name = "Goal";
        goal.image = Toolkit.getDefaultToolkit().getImage("Goal.png");

        defender = new Defender(650,200,0,5,120,120);
        defender.name = "Defender";
        defender.image = Toolkit.getDefaultToolkit().getImage("Defender.png");

    }
    // end BasicGameApp constructor

    public void moveThings() { // moves all objects in the game each frame
        if (playing) {
            player.move();
            puck.move();
            goal.move();
            defender.move();
            //call the move() code for each object  -


        }
    }

    public void checkCollisions(){ // checks collisions between the player, puck, goal, and defender
        if (player.hitbox.intersects(puck.hitbox)) { // if the player touches the puck, the player now has the puck
            hasPuck = true;  // remember the state
        }

        if (hasPuck) { // if the player has the puck, keep the puck attatched to the stick
            puck.xpos = player.xpos + player.width;
            puck.ypos = player.ypos + player.height/2;
            puck.hitbox = new Rectangle((int)puck.xpos, (int)puck.ypos, puck.width, puck.height);
            puck.dx = 0;
            puck.dy = 0;
        }

        if (puck.hitbox.intersects(goal.hitbox)) {   // if the puck reaches the goal, the score increases and resets the puck back to original position
            score = score + 1;

            puck.xpos = 400;
            puck.ypos = 350;
            puck.dx = 0;
            puck.dy = 0;
            puck.isAlive = true;

            hasPuck = false;

            puck.hitbox = new Rectangle((int)puck.xpos,(int) puck.ypos, puck.width, puck.height);
        }

        if (puck.hitbox.intersects(defender.hitbox)) { // if the puck hits the defender, the game ends
            puck.isAlive = false;
            playing = false;
            gameOver = true;

            puck.dx = 0;
            puck.dy = 0;
        }
    }

    //Paints things on the screen using bufferStrategy
    private void render() { // draws all images and text on the screen
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        //draw the images
        // Signature: drawImage(Image img, int x, int y, int width, int height, ImageObserver observer)
        g.drawImage(backgrounds[backgroundNum], 0,0,WIDTH,HEIGHT,null);
        g.drawImage(player.image, player.xpos, player.ypos, player.width, player.height, null);
        g.drawImage(goal.image, goal.xpos, goal.ypos, goal.width, goal.height, null);
        g.drawImage(defender.image, defender.xpos, defender.ypos, defender.width, defender.height, null);

        g.setFont(myFont);
        g.setColor(Color.BLACK);
        g.drawString("Score: " + score, 20, 40);

        if (puck.isAlive == true) {
            g.drawImage(puck.aliveImage,(int) puck.xpos, (int)puck.ypos, puck.width, puck.height, null);
        } else {
            g.drawImage(puck.deadImage,(int) puck.xpos, (int)puck.ypos, puck.width, puck.height, null);
        }

        if (gameOver) {
            g.setFont(new Font("Arial", Font.BOLD, 120));
            g.setColor(Color.BLACK);
            g.drawString("GAME OVER", 150, 350);
        }
/*
        System.out.println("disx"+Math.pow(goal.xpos-puck.xpos,2));
        a=Math.pow(goal.xpos-puck.xpos,2);
        b=Math.pow(goal.ypos-puck.ypos,2);
        c=Math.pow(a+b,.5);
        am=(Math.pow(a,.5)*c)/5;
        bm=(Math.pow(b,.5)*c)/5;
        System.out.println("disy"+Math.pow(goal.xpos-puck.xpos,0.5));

 */
        // Keep the code below at the end of render()
        g.dispose();
        bufferStrategy.show();
    }














    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
//vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv DON'T CHANGE! vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;
    public BufferStrategy bufferStrategy;

    // PSVM: This is the code that runs first and automatically
    public static void main(String[] args) {
        BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
        new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method
    }

    // main thread
    // this is the code that plays the game after you set things up
    public void run() {
        //for the moment we will loop things forever.
        while (true) {
            moveThings();  //move all the game objects
            checkCollisions(); // Check for collisions
            render();  // paint the graphics
            pause(10); // sleep for 10 ms
        }
    }

    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time ) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }

    private Image getImage(String filename){
        return Toolkit.getDefaultToolkit().getImage(filename);
    }

    //Graphics setup method
    private void setUpGraphics() {
        frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);
        canvas.addKeyListener(this);
        canvas.addMouseListener(this);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");

        myFont = new Font("Arial", Font.BOLD, 24);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) { // controls key presses for movement of player and background switching
        System.out.println(e.getKeyCode());
        int key = e.getKeyCode();
        // arrow keys move player
        if (key == 38) {player.up = true;} // up
        else if (key == 37) {player.left = true;} // left
        else if (key == 40)  {player.down = true;} // down
        else if (key == 39) {player.right = true;} // right

        if (key == 16) { // press shift to change background
            if (backgroundNum == 0){
                backgroundNum = 1;
            }
            else {
                backgroundNum = 0;
            }
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == 38) {player.up = false;} // up arrow: 38
        else if (key == 37){player.left = false;} // left arrow: 37
        else if (key == 40){player.down = false;} // down arrow: 40
        else if (key == 39){player.right = false;} // right arrow: 39

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        if (goal.hitbox.contains(mouseX, mouseY)){


        }
            //else {

    }

    @Override
    public void mouseReleased(MouseEvent e) { // shoots the puck toward the goal when the mouse is released
        int mouseX = e.getX();
        int mouseY = e.getY();
        System.out.println("test");
        //puck.xpos=puck.xpos+100;

        System.out.println("mouseX:" +mouseX);
        System.out.println("mouseY:" +mouseY);

        if (goal.hitbox.contains(mouseX, mouseY) && hasPuck){ // only shoot if the player has the puck and the goal is clicked
            hasPuck = false;
            puck.xpos = puck.xpos + 60;
            a=Math.pow(goal.xpos-puck.xpos,2);
            b=Math.pow(goal.ypos-puck.ypos,2);
            c=Math.pow(a+b,.5);
            am=(Math.pow(a,.5)*5)/c;
            bm=(Math.pow(b,.5)*5)/c;
            System.out.println("ammmmm"+am);
            System.out.println("bbbbbmmmm"+bm);


            puck.dx = am;

            if (puck.ypos > goal.ypos) {
                puck.dy = -bm;   
            }
            else {
                puck.dy = bm;
            }

            puck.hitbox = new Rectangle((int)puck.xpos, (int)puck.ypos, puck.width, puck.height);
        }
            //else {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
}

