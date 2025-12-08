package src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

// Abstract karena kita tidak akan pernah membuat "GameObject" kosong, 
// tapi kita akan membuat "Ball" atau "Brick".
public abstract class GameObject {
    protected int x, y;
    protected int width, height;
    protected Color color;

    public GameObject(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    // Semua anak WAJIB punya method draw, tapi caranya beda-beda
    public abstract void draw(Graphics g);

    // Method ini sama untuk semua anak, jadi tulis sekali saja di sini (Inheritance Power!)
    public Rectangle getRect() {
        return new Rectangle(x, y, width, height);
    }
    
    // Getters umum
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}