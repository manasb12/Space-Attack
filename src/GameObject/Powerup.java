package GameObject;

import audio.Sound;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Powerup extends GameObject {

    private int puWidth = 32;
    private int puHeight = 32;

    public static BufferedImage healthpackImage;
    public static BufferedImage weaponUpgradeImage;
    public static BufferedImage bomb;

    public static int powerup;
    public static boolean powerupTouched = false;

    private GameObjectController goc;

    public Powerup(int x, int y, GameObjectController goc, int powerup) {
        super(x, y);
        try {
            healthpackImage = ImageIO.read(getClass().getResource("/resources/POW_HealthKit.png"));
            weaponUpgradeImage = ImageIO.read(getClass().getResource("/resources/POW_tripleShot.png"));
            bomb = ImageIO.read(getClass().getResource("/resources/Bomb.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.goc = goc;
        this.powerup = powerup;
    }

    public void update() {
        y += 3;
        collision();
    }

    private void collision() {
        for (int i = 0; i < goc.gameObject.size(); i++) {
            GameObject secondObject = goc.gameObject.get(i);
            if (secondObject instanceof Player) {
                if (secondObject.getCollisionBounds().intersects(getCollisionBounds())) {
                    powerupTouched = true;
                    Sound.playSound(Sound.powerup);
                    goc.removeGameObject(this);
                    if (powerup == 1) {
                        //max health
                        Player.playerHealth = 150;
                    }
                }
            }
        }
    }

    public void paintComponent(Graphics g) {
        if (powerup == 1) {
            g.drawImage(healthpackImage, x, y, puWidth, puHeight, null);
        }
        if (powerup == 2) {
            g.drawImage(weaponUpgradeImage, x, y, puWidth, puHeight, null);
        }
        if (powerup == 3) {
            g.drawImage(bomb, x, y, puWidth, puHeight, null);
        }
    }

    public Rectangle getCollisionBounds() {
        return new Rectangle(x, y, puWidth, puHeight);
    }
}
