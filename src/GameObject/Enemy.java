package GameObject;

import java.awt.Graphics;
import java.awt.Rectangle;
import Main.Main;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

public class Enemy extends GameObject {

    private int enemyWidth = 64, enemyHeight = 64;
    private int enemyHealth = 10;
    private GameObjectController goc;
    private BufferedImage enemyPlane1;
    private int interval = 100;

    private Random random = new Random();
    private int intervalRange = 50;
    private int speedy = random.nextInt(2) + 1;
    public static int enemiesKilled = 0;
    
    public static boolean powerup = false;
    public static int currPowerup;
    
    private int powerupMaxRange = 20;

    public Enemy(int x, int y, GameObjectController goc) {
        super(x, y);
        this.goc = goc;
        try {
            enemyPlane1 = ImageIO.read(getClass().getResource("/resources/EnemyPlane1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (Main.wave == 3) {
            y += 1;
        } else {
            y += speedy;
        }
        fireBulletCheck();
        fireBullet();
        collision();
    }

    private void collision() {

        //3 in 20 chances to get powerups randomly
        if (random.nextInt(powerupMaxRange) + 1 == 1) { //((max-min)+1)+min
            currPowerup = 1;
            powerup = true;
        } else if (random.nextInt(powerupMaxRange) + 1 == 2) {
            currPowerup = 2;
            powerup = true;
        } else if (random.nextInt(powerupMaxRange) + 1 == 3) {
            currPowerup = 3;
            powerup = true;
        } else {
            currPowerup = 0;
            powerup = false;
        }

        if (y > Main.gameHeight) {
            x = random.nextInt(Main.gameWidth - 64);
            y = -10;
        }

        for (int i = 0; i < goc.gameObject.size(); i++) {
            GameObject secondObject = goc.gameObject.get(i);
            if (secondObject instanceof PlayerBullet) {
                if (getCollisionBounds().intersects(secondObject.getCollisionBounds())) {
                    if (Main.wave == 1) {
                        goc.removeGameObject(this);
                        goc.removeGameObject(secondObject);
                        Main.setEnemyKilled(Main.getEnemyKilled() + 1);
                        enemiesKilled++;
                    } else if (Main.wave == 2) {
                        goc.removeGameObject(secondObject);
                        enemyHealth -= 2; //enemy takes 2 damage per hit
                        if (enemyHealth == 0) {
                            goc.removeGameObject(this);
                            Main.setEnemyKilled(Main.getEnemyKilled() + 1);
                            enemiesKilled++;
                        }
                    } else if (Main.wave == 3) {
                        goc.removeGameObject(secondObject);
                        enemyHealth -= 1;
                        if (enemyHealth == 0) {
                            goc.removeGameObject(this);
                            enemiesKilled++;
                            Main.gameState = Main.gameState.ENDSCREEN;
                            // Main.setEnemyKilled(Main.getEnemyKilled() + 1);
                        }
                    }
                    if (powerup) {
                        goc.addGameObject(new Powerup(x, y,goc,currPowerup));
                    }
                }
            }
        }
    }

    private void fireBullet() {
        if (interval < intervalRange) {
            return;
        } else {
            interval = 0;
            if (Main.wave == 1) {
                goc.addGameObject(new EnemyBullet(x + 32, y + 35, goc));
            } else if (Main.wave == 2) {
                goc.addGameObject(new EnemyBullet(x + 35, y + 80, goc));
                goc.addGameObject(new EnemyBullet(x + 85, y + 80, goc));
            } else if (Main.wave == 3) {
                goc.addGameObject(new EnemyBullet(x + 40, y + 80, goc));
                goc.addGameObject(new EnemyBullet(x + 120, y + 80, goc));
                goc.addGameObject(new EnemyBullet(x + 200, y + 80, goc));
            }
        }
    }

    private void fireBulletCheck() {
        interval++;
        if (interval > intervalRange) {
            interval = intervalRange;
        }
    }

    public void paintComponent(Graphics g) {
        if (Main.wave == 1) {
            g.drawImage(enemyPlane1, x, y, enemyWidth, enemyHeight, null);
        } else if (Main.wave == 2) {
            g.drawImage(enemyPlane1, x, y, 128, 128, null);
        } else if (Main.wave == 3) {
            g.drawImage(enemyPlane1, x, y, 250, 250, null);
        }
    }

    public Rectangle getCollisionBounds() {
        if (Main.wave == 1) {
            return new Rectangle(x, y, enemyWidth, enemyHeight);
        } else if (Main.wave == 2) {
            return new Rectangle(x, y, 128, 128);
        } else if (Main.wave == 3) {
            return new Rectangle(x, y, 250, 250);
        }
        return new Rectangle(x, y, enemyWidth, enemyHeight);
    }
}