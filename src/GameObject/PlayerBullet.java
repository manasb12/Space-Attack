package GameObject;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class PlayerBullet extends GameObject{
    
    private int bulletWidth = 8;
    private int bulletHeight = 16;
    
    private BufferedImage bulletImage = null;
    private GameObjectController goc; 
    
    public PlayerBullet(int x, int y, GameObjectController goc) {
        super(x,y);
        try {
            bulletImage = ImageIO.read(getClass().getResource("/resources/bullet1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.goc = goc;
    }
    
    public void update() {
        speedy = -10;
        x += speedx;
        y += speedy;
    }
         
    public void paintComponent(Graphics g) {
        if (Math.signum(speedy) == -1) {
            g.drawImage(bulletImage, x,y,bulletWidth, bulletHeight,null);
        } else {
            g.drawImage(bulletImage, x, y, bulletWidth, -bulletHeight,null);
        }
    }
    
    public Rectangle getCollisionBounds() {
        return new Rectangle(x,y,bulletWidth, bulletHeight);
    }    
}