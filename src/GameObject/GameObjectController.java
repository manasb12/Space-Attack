package GameObject;

import java.util.LinkedList;
import java.awt.Graphics;
import java.awt.Rectangle;
import Main.Main;
import java.util.Random;
import Display.HUD;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

public class GameObjectController {

    //put all of the game objects in a list
    public LinkedList<GameObject> gameObject = new LinkedList<>();
    //public ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    Random random = new Random();

    private HUD hud = new HUD();

    public void update() {
        for (int i = 0; i < gameObject.size(); i++) {
            //update all of the game objects from the list
            gameObject.get(i).update();
        }
        hud.update();
    }

    public void paintComponent(Graphics g) {
        g.clearRect(0, 0, Main.gameWidth, Main.gameHeight);
        for (int i = 0; i < gameObject.size(); i++) {
            //draw all of the game objects from the list
            gameObject.get(i).paintComponent(g);
        }
        hud.paintComponent(g);
    }

    public Rectangle getCollisionBounds() {
        return null;
    }

    public void addGameObject(GameObject gameObj) { //add an object to the list
        gameObject.add(gameObj);
    }

    public void removeGameObject(GameObject gameObj) { //remove an object from the list
        gameObject.remove(gameObj);
    }

    public void addEnemy(int enemyCount) {
        for (int i = 0; i < enemyCount; i++) {
            int x = random.nextInt(Main.gameWidth - 128);
            int y = -10;
            addGameObject(new Enemy(x, y, this));
        }
    }
}
