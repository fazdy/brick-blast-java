package src;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MapGenerator {
    public Brick[][] bricks;
    public int brickWidth;
    public int brickHeight;

    public MapGenerator(int row, int col, int level) {
        initLevel(row, col, level);
    }

    private void initLevel(int row, int col, int level) {
        bricks = new Brick[row][col];
        brickWidth = 540 / col;
        brickHeight = 150 / row;

        for (int i = 0; i < bricks.length; i++) {
            for (int j = 0; j < bricks[0].length; j++) {
                
                int x = j * brickWidth + 80;
                int y = i * brickHeight + 50;

                // LOGIKA LEVELING DI SINI
                if (level == 1) {
                    // Level 1: Semua bata biasa (Kuning, 1 HP)
                    bricks[i][j] = new Brick(x, y, brickWidth, brickHeight, 1, Color.YELLOW, 5);
                
                } else if (level == 2) {
                    // Level 2: Baris genap bata keras (Merah, 2 HP), Ganjil biasa
                    if (i % 2 == 0) {
                         bricks[i][j] = new Brick(x, y, brickWidth, brickHeight, 2, Color.RED, 10);
                    } else {
                         bricks[i][j] = new Brick(x, y, brickWidth, brickHeight, 1, Color.YELLOW, 5);
                    }
                
                } else {
                    // Level 3+: Acak atau pola lain (Bisa dikreasikan)
                    //bricks[i][j] = new Brick(x, y, brickWidth, brickHeight, 3, Color.BLUE, 20);
                    
                    int layerType = i % 3;
                    
                    if (layerType == 0) {
                        // Baris ke-0, 3, 6, dst... (KERAS)
                        bricks[i][j] = new Brick(x, y, brickWidth, brickHeight, 3, Color.GRAY, 20);
                    } else if (layerType == 1) {
                        // Baris ke-1, 4, 7, dst... (SEDANG)
                        bricks[i][j] = new Brick(x, y, brickWidth, brickHeight, 2, Color.RED, 10);
                    } else {
                        // Baris ke-2, 5, 8, dst... (EMPUK)
                        bricks[i][j] = new Brick(x, y, brickWidth, brickHeight, 1, Color.YELLOW, 5);
                    }
                }
            }
        }
    }
    
    public void draw(Graphics2D g) {
        for (int i = 0; i < bricks.length; i++) {
            for (int j = 0; j < bricks[0].length; j++) {
                if (!bricks[i][j].isDestroyed) {
                    bricks[i][j].draw(g); // Delegasikan menggambar ke class Brick
                }
            }
        }
    }
}
