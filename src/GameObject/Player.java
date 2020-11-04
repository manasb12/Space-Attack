package GameObject;

import java.awt.Graphics;
import java.awt.Rectangle;
import Main.Main;
import Input.KeyInput;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player extends GameObject {

    private int playerWidth = 64;
    private int playerHeight = 64;
    private GameObjectController goc;
    private KeyInput keyInput;
    public static BufferedImage playerPlane;
    public static int score = 0;

    public static int playerHealth = 150;

    public Player(int x, int y, GameObjectController goc, KeyInput keyInput) {
        super(x, y);
        this.goc = goc;
        this.keyInput = keyInput;
        try {
            playerPlane = ImageIO.read(getClass().getResource("/resources/PlayerPlane.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        //update the movement
        x += speedx;
        y += speedy;
        collision();
        if (movingRight) {
            speedx = 8;
        } else if (movingLeft) {
            speedx = -8;
        } else {
            speedx = 0;
        }
        if (Main.gameState == Main.GameState.PLAY) {
            score++;
            if (score >= 5000) {
                Main.wave = 2;
            }
            if (score >= 10000) {
                Main.wave = 3; //final wave - Boss Battle
            }
        }
    }

    private void collision() {
        if (x <= 0) {
            x = 0;
        }
        if (x >= Main.gameWidth - playerWidth) {
            x = Main.gameWidth - playerWidth;
        }
        for (int i = 0; i < goc.gameObject.size(); i++) {
            if (goc.gameObject.get(i) instanceof EnemyBullet) {
                if (goc.gameObject.get(i).getCollisionBounds().intersects(getCollisionBounds())) {
                    goc.removeGameObject(goc.gameObject.get(i));
                    playerHealth -= 5;
                    if (playerHealth == 0) {
                        Main.gameState = Main.GameState.ENDSCREEN;
                    }
                }
            }
        }
    }

    public void paintComponent(Graphics g) {
        g.drawImage(playerPlane, x, y, null);
    }

    public Rectangle getCollisionBounds() {
        return new Rectangle(x, y, playerWidth, playerHeight);
    }
}
