package src;

import java.awt.Color;
import java.awt.Graphics;

public class Paddle extends GameObject {
    private int moveSpeed = 20;
    private int limitLeft = 10;
    private int limitRight = 600; // 700 width - 100 paddle width roughly

    public Paddle(int startX) {
        // Set y=550, width=100, height=8, color=Green ke Parent
        super(startX, 550, 100, 8, Color.GREEN);
    }

    public void moveRight() {
        if (x >= limitRight) {
            x = limitRight;
        } else {
            x += moveSpeed;
        }
    }

    public void moveLeft() {
        if (x <= limitLeft) {
            x = limitLeft;
        } else {
            x -= moveSpeed;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }
}
