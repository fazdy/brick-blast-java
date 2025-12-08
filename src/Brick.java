package src;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

// EXTENDS GAMEOBJECT (Ini kuncinya!)
public class Brick extends GameObject {
    public int hp;           // Nyawa bata (1 = sekali pukul hancur)
    public int scoreValue;   // Skor yang didapat
    public boolean isDestroyed = false;

    // Constructor Dasar
    public Brick(int x, int y, int width, int height, int hp, Color color, int scoreValue) {
        // Oper data dasar ke Constructor Bapak (GameObject)
        super(x, y, width, height, color);
        
        this.hp = hp;
        this.scoreValue = scoreValue;
    }

    @Override
    public void draw(Graphics g) {
        if (!isDestroyed) {
            // Kita perlu cast ke Graphics2D untuk border tebal
            Graphics2D g2d = (Graphics2D) g;
            
            // Variabel 'color', 'x', 'y' diambil dari Parent (GameObject)
            g2d.setColor(color);
            g2d.fillRect(x, y, width, height);

            // Border
            g2d.setStroke(new BasicStroke(3));
            g2d.setColor(Color.black);
            g2d.drawRect(x, y, width, height);
        }
    }

    public void hit() {
        hp--;
        if (hp <= 0) {
            isDestroyed = true;
        } else {
            color = color.darker();
        }
    }
}