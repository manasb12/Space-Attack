package Main;

import GameObject.ScrollingBackground;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Graphics;
import GameObject.GameObjectController;
import javax.swing.JPanel;
import GameObject.Player;
import Input.KeyInput;
import java.util.Random;
import Menu.Menu;
//import audio.Sound;
import java.awt.Color;
import java.awt.Font;

public class Main extends JPanel implements Runnable {

    private JFrame frame;
    private Thread thread; //Game thread
    private boolean gameRunning = false; //game flag
    public static int gameWidth = 550;
    public static int gameHeight = 650;
    private String gameTitle = "Island Attack!";
    private int bgY = 0;
    private static Random random = new Random();
    public static int enemyCount = 1; //keep count of current enemies spawning
    public static int enemyKilled = 0; //keep count of how many enemies are dead
    public static int wave = 1;

    //CLASS OBJECTS
    private GameObjectController goc = new GameObjectController();
    private KeyInput keyInput;
    private Menu menu = new Menu();
    //private Sound sound;

    public static enum GameState {
        MENU, INSTRUCTIONS, PLAY, ENDSCREEN;
    }
    public static GameState gameState = GameState.MENU;

    public Main() {
        init();
    }

    private void init() {
        createWindow();
        setFocusable(true);
        requestFocus();
        keyInput = new KeyInput(goc);
        addKeyListener(keyInput);
        //sound = new Sound();
        loadLevel();
    }

    private void loadLevel() {
        goc.addGameObject(new ScrollingBackground(0, 0));
        goc.addGameObject(new Player(getWidth() / 2, getHeight() - 100, goc, keyInput));
        goc.addEnemy(enemyCount);
    }

    private void createWindow() {
        frame = new JFrame(gameTitle);
        frame.setSize(gameWidth, gameHeight);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        setPreferredSize(new Dimension(gameWidth, gameHeight));
        setMaximumSize(new Dimension(gameWidth, gameHeight));
        setMinimumSize(new Dimension(gameWidth, gameHeight));
        frame.add(this);
        frame.pack();
    }

    private void start() { //start the thread
        if (gameRunning) {
            return;
        }
        gameRunning = true;
        thread = new Thread(this, "GameThread");
        thread.start();
    }

    private void stop() { //stop the thread
        if (!gameRunning) {
            return;
        }
        gameRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (gameState == GameState.PLAY) {
            goc.update(); //update the all the game objects
            if (enemyKilled >= enemyCount) {
                enemyCount++;
                enemyKilled = 0;
                if (wave == 1) {
                    if (enemyCount >= 5) {
                        enemyCount = random.nextInt((3 - 2) + 1) + 2;
                    }
                } else if (wave == 2) {
                    if (enemyCount >= 2) {
                        enemyCount = 2;
                    }
                } else if (wave == 3) {
                    if (enemyCount >= 1) {
                        enemyCount = 1;
                    }
                }
                goc.addEnemy(enemyCount);
            }
        } else if (gameState == GameState.MENU
                || gameState == GameState.INSTRUCTIONS
                || gameState == GameState.ENDSCREEN) {
            menu.update();
        }
    }

    public void paintComponent(Graphics g) {
        g.clearRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
        if (gameState == GameState.PLAY) {
            goc.paintComponent(g); //draw all the game objects
            g.setFont(new Font("Impact", Font.BOLD, 44));
            g.setColor(Color.YELLOW);
            g.drawString("Wave " + wave, gameWidth / 2 - 90, 90);
            g.drawImage(Menu.bomb, 20, 550, 64, 64, null);
            g.drawImage(Menu.bomb, 60, 550, 64, 64, null);
            g.drawImage(Menu.bomb, 100, 550, 64, 64, null);
        } else {
            menu.paintComponent(g);
        }
    }

    public void run() {
        while (gameRunning) {
            update();
            repaint(); //after every update, repaint the graphic
            try {
                Thread.sleep(15); //game loop timer at 60 fps
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static int getEnemyCount() {
        return enemyCount;
    }

    public static void setEnemyCount(int enemyCount) {
        Main.enemyCount = enemyCount;
    }

    public static int getEnemyKilled() {
        return enemyKilled;
    }

    public static void setEnemyKilled(int enemyKilled) {
        Main.enemyKilled = enemyKilled;
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.start();
    }
}