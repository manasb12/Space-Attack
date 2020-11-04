package GameObject;


import GameObject.GameObject;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import Main.Main;
import java.awt.Color;
import java.awt.Rectangle;
import java.io.File;

public class ScrollingBackground extends GameObject{
    
    private int bgY = 0;
    public static int speed = 1;
    
    private static BufferedImage backgroundImage;
    
    public ScrollingBackground(int x, int y) {
        super(x,y);
        try {
            backgroundImage = ImageIO.read(getClass().getResource("/resources/BG.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void update(){
        bgY+=speed;
        if (bgY >= Main.gameHeight) {
            bgY = 0;
        }
    }
    
    public void paintComponent(Graphics g) {
        g.drawImage(backgroundImage, 0, bgY, Main.gameWidth, Main.gameHeight, null);
        g.drawImage(backgroundImage, 0, bgY-Main.gameHeight, Main.gameWidth, Main.gameHeight, null);
    }    
    
    public Rectangle getCollisionBounds() {
        return null;
    }
}