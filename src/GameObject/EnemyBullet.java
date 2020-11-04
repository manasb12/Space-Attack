package GameObject;

import Main.Main;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;


public class EnemyBullet extends GameObject {
    
    private int eBulletWidth = 8;
    private int eBulletHeight = 16;
    
    private BufferedImage eBulletImage = null;
    private GameObjectController goc;
    
    Random random = new Random();
    public EnemyBullet(int x, int y, GameObjectController goc) {
        super(x,y);
        try {
            eBulletImage = ImageIO.read(getClass().getResource("/resources/EnemyBullet.png"));    
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.goc = goc;
    }
    
    public void update() {
        speedy = 8; //go downwards
        y += speedy;
        collision();
    }
    
    private void collision() {
        if (y >= Main.gameHeight-eBulletHeight) {
            goc.removeGameObject(this);
        }
    }
    
    public void paintComponent(Graphics g) {
        g.drawImage(eBulletImage, x, y, eBulletWidth, eBulletHeight, null);
    }
    
    public Rectangle getCollisionBounds() {
        return new Rectangle(x,y,eBulletWidth, eBulletHeight);
    }
}