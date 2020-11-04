package GameObject;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
    
    protected int x, y; //position
    protected float speedx, speedy; //speeds
    protected boolean movingRight, movingLeft;
    
    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    //every game object will update, render and have collision box    
    public abstract void update();
    public abstract void paintComponent(Graphics g);
    public abstract Rectangle getCollisionBounds();
    
    public int getX() {
        return x;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public int getY() {
        return y;
    }        
    
    public void setY(int y) {
        this.y = y;
    }
    
    public float getSpeedx() {
        return speedx;
    }
    
    public void setSpeedx(float speedx) {
        this.speedx = speedx;
    }
    
    public float getSpeedy() {
        return speedy;
    }
    
    public void setSpeedy(float speedy) {
        this.speedy = speedy;
    }
    
    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }
    
    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }
}