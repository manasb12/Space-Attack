package Display;

import GameObject.Enemy;
import GameObject.Player;
import GameObject.Powerup;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import Main.Main;

public class HUD {

    //display health, power ups, score on the top
    public void update() {
    }

    public void paintComponent(Graphics g) {
        g.drawImage(Player.playerPlane, 60, 8, 32, 32, null); //player plane logo
        //Box boundary
        g.setColor(Color.BLACK);
        g.drawRect(100, 10, 150, 20);
        g.setColor(Color.GREEN.darker());
        g.fillRect(100, 10, 150, 20);
        g.setColor(Color.GREEN.brighter());
        g.fillRect(100, 10, Player.playerHealth, 20);
        g.setColor(Color.YELLOW.brighter());
        g.setFont(new Font("Impact", Font.BOLD, 18));
        g.drawString("Score: " + Player.score, 400, 30);
        g.drawString("POW: ", 400, 60);
        if (Powerup.powerup == 1) {
            g.drawImage(Powerup.healthpackImage, 465, 45, 32, 32, null);
        } else if (Powerup.powerup == 2) {
            g.drawImage(Powerup.weaponUpgradeImage, 465, 45, 32, 32, null);
        } else {
            g.drawImage(Powerup.bomb, 465, 45, 32, 32, null);
        }

    }
}
