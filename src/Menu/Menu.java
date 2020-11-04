package Menu;

import GameObject.Enemy;
import GameObject.Player;
import Input.KeyInput;
import Main.Main;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Menu {

    private BufferedImage healthPackLogo = null;
    public static BufferedImage bomb = null;
    private BufferedImage tripleShotLogo = null;
    private BufferedImage playerPlaneLogo = null;
    private BufferedImage enemyPlaneLogo = null;
    private BufferedImage bulletLogo = null;
    private BufferedImage bg1 = null;
    private BufferedImage bg2 = null;

    private int playerPlaneY = Main.gameHeight;
    private int enemyPlaneY = -30;

    private boolean drawBullets = false;

    public Menu() {
        try {
            healthPackLogo = ImageIO.read(getClass().getResource("/resources/POW_Healthkit.png"));
            tripleShotLogo = ImageIO.read(getClass().getResource("/resources/POW_tripleShot.png"));
            bomb = ImageIO.read(getClass().getResource("/resources/bomb.png"));
            playerPlaneLogo = ImageIO.read(getClass().getResource("/resources/PlayerPlane.png"));
            enemyPlaneLogo = ImageIO.read(getClass().getResource("/resources/EnemyPlane1.png"));
            bulletLogo = ImageIO.read(getClass().getResource("/resources/Bullet1.png"));
            bg1 = ImageIO.read(getClass().getResource("/resources/BG.png"));
            bg2 = ImageIO.read(getClass().getResource("/resources/BG2.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void update() {
        if (Main.gameState == Main.GameState.MENU) {
            playerPlaneY -= 2;
            enemyPlaneY += 2;
            if (playerPlaneY <= 300) {
                playerPlaneY = 300;
                drawBullets = true; //draw bullets after the planes are drawn
                KeyInput.menuDone = true; //player can move on
            }
            if (enemyPlaneY >= 150) {
                enemyPlaneY = 150;
            }
        }
    }

    public void paintComponent(Graphics g) {
        if (Main.gameState == Main.GameState.MENU) {
            
            g.drawImage(bg1, 0, 0, Main.gameWidth+300, Main.gameHeight, null);
            g.setFont(new Font("Monotype Corsiva", Font.BOLD, 75));
            g.setColor(Color.WHITE);
            g.drawString("5PAC3 A++ACK!", 5, 100);
            g.drawImage(playerPlaneLogo, 150, playerPlaneY, 256, 256, null);
            if (drawBullets) {
                for (int i = 0; i < 5; i++) {
                    g.drawImage(bulletLogo, 270, 60 * i, 16, 32, null);  //draw bullets          
                }
            }
            g.drawImage(enemyPlaneLogo, 30, enemyPlaneY, 128, 128, null);
            g.drawImage(enemyPlaneLogo, 400, enemyPlaneY, 128, 128, null);
            g.setFont(new Font("Arial", Font.PLAIN, 22));
            g.drawString("Press <ENTER> to continue.", 140, 570);
            g.drawString("A Game by Manas Bansal", 155, 610);
        } else if (Main.gameState == Main.GameState.INSTRUCTIONS) {
            g.drawImage(bg1, 0, 0, Main.gameWidth, Main.gameHeight, null);
            g.setFont(new Font("Arial", Font.BOLD, 44));
            g.setColor(Color.WHITE);
            g.drawString("Controls", Main.gameWidth / 2 - 100, 70);
            g.drawString("Power-Ups", Main.gameWidth / 2 - 100, 300);
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString("LEFT ARROW KEY           MOVE LEFT", 50, 120);
            g.drawString("RIGHT ARROW KEY         MOVE RIGHT", 50, 150);
            g.drawString("SPACE                              SHOOT", 50, 180);
            g.drawString("B                                       BOMB ATTACK", 50, 210);
            g.drawImage(healthPackLogo, 50, 340, 48, 48, null);
            g.drawString("MAXIMIZE HEALTH TO 100%", 140, 365);
            g.drawImage(bomb, 50, 410, 48, 48, null);
            g.drawString("MASSIVE DESTRUCTION TO ENEMIES", 140, 435);
            g.drawImage(tripleShotLogo, 50, 480, 48, 48, null);
            g.drawString("WEAPON UPGRADE TO TRIPLE SHOT", 140, 505);
            g.drawString("PRESS <ENTER> TO BEGIN!", 130, 590);
        } else if (Main.gameState == Main.GameState.ENDSCREEN) {
            g.drawImage(bg2, 0, 0, Main.gameWidth, Main.gameHeight, null);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 62));
            g.drawString("GAME OVER!", Main.gameWidth / 2 - 200, 100);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("SCORE    :       " + Player.score, 160, 200);
            g.drawString("KILLS      :       " + Enemy.enemiesKilled, 160, 250);
            g.setFont(new Font("Arial", Font.BOLD, 22));
            g.drawString("PRESS  <ENTER> TO PLAY AGAIN!", 100, 350);
            g.drawString("PRESS  <ESCAPE> TO QUIT!", 130, 400);            
        }
    }
}
