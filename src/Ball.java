package src;

import java.awt.Color;
import java.awt.Graphics;

public class Ball extends GameObject {
    private int dirX;
    private int dirY;
    private float speed = 1;

    public Ball(int startX, int startY, int startDirX, int startDirY) {
        // Size bola 20x20
        super(startX, startY, 20, 20, Color.WHITE);
        
        this.dirX = startDirX;
        this.dirY = startDirY;
    }

    public void move() {
        x += dirX * speed;
        y += dirY * speed;
    }

    public void increaseSpeed() {
        speed += 0.5;
    }
    
    public void decreaseSpeed() {
        if (speed > 1) {
            speed -= 0.5;
        }
    }
    
    public void normalSpeed() {
        speed = 1;
    }
    
    public float getSpeed() {
        return speed;
    }
    
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, width, height); // width & height disini adalah size (20)
    }

    public void reverseX() {
        dirX = -dirX;
    }

    public void reverseY() {
        dirY = -dirY;
    }

    public int getDirX() {
        return dirX;
    }

    public int getDirY() {
        return dirY;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDirX(int dx) {
        this.dirX = dx;
    }

    public void setDirY(int dy) {
        this.dirY = dy;
    }
}
