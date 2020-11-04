package Input;

import java.awt.event.KeyAdapter;
import GameObject.GameObjectController;
import GameObject.Player;
import GameObject.GameObject;
import java.awt.event.KeyEvent;
import Main.Main;
import GameObject.Enemy;
import GameObject.PlayerBullet;
import GameObject.Powerup;
import audio.Sound;

public class KeyInput extends KeyAdapter {

    private GameObjectController goc;

    public static boolean menuDone = false;

    public boolean playerShoots = false;
    public static boolean powerup2Achieved = false;
    public static boolean bPressed = false;

    public KeyInput(GameObjectController goc) {
        this.goc = goc;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (Main.gameState == Main.GameState.PLAY) {
            for (int i = 0; i < goc.gameObject.size(); i++) {
                GameObject object = goc.gameObject.get(i);
                if (object instanceof Player) {
                    if (key == KeyEvent.VK_RIGHT) {
                        object.setMovingRight(true); //move right 
                    } else if (key == KeyEvent.VK_LEFT) {
                        object.setMovingLeft(true); //move left
                    } else if (key == KeyEvent.VK_SPACE) {//player shoots                        
                        goc.addGameObject(new PlayerBullet(object.getX() + 32, object.getY(), goc));
                        Sound.playSound(Sound.playerShooting); //play sound
                        if (Powerup.powerup == 2 && Powerup.powerupTouched) {
                            goc.addGameObject(new PlayerBullet(object.getX() + 10, object.getY(), goc));
                            goc.addGameObject(new PlayerBullet(object.getX() + 30, object.getY(), goc));
                            goc.addGameObject(new PlayerBullet(object.getX() + 60, object.getY(), goc));
                        }
                    } else if (key == KeyEvent.VK_B) {
                        bPressed = true;
                    }
                }
            }
        }
        //When pressed Enter, start the game
        if (Main.gameState == Main.GameState.MENU) {
            if (menuDone) { //prevent the player from skipping menu animation 
                if (key == KeyEvent.VK_ENTER) {
                    Main.gameState = Main.GameState.INSTRUCTIONS;
                }
            } else {
                Main.gameState = Main.GameState.MENU;
            }
        } else if (Main.gameState == Main.GameState.INSTRUCTIONS) {
            if (key == KeyEvent.VK_ENTER) {
                Main.gameState = Main.GameState.PLAY;
            }
        } else if (Main.gameState == Main.GameState.ENDSCREEN) {
            if (key == KeyEvent.VK_ENTER) {
                Player.playerHealth = 100;
                Player.score = 0;
                Enemy.currPowerup = 0;
                Enemy.enemiesKilled = 0;
                Main.wave = 1;
                Main.gameState = Main.GameState.PLAY;
            } else if (key == KeyEvent.VK_ESCAPE) {
                System.exit(0);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { //when key released, stop moving
        int key = e.getKeyCode();
        for (int i = 0; i < goc.gameObject.size(); i++) {
            GameObject object = goc.gameObject.get(i);
            if (object instanceof Player) {
                if (key == KeyEvent.VK_RIGHT) {
                    object.setMovingRight(false);
                } else if (key == KeyEvent.VK_LEFT) {
                    object.setMovingLeft(false);
                } else if (key == KeyEvent.VK_B) {
                    //Bomb.bombExplodes = false;
                } else if (key == KeyEvent.VK_SPACE) {
                    playerShoots = false;
                }
            }
        }
    }
}
