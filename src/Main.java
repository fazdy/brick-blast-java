package src;

import javax.swing.JFrame;

public class Main {
    // Constant agar mudah diakses file lain
    public static final int SCREEN_WIDTH = 700;
    public static final int SCREEN_HEIGHT = 600;

    public static void main(String[] args) {
        JFrame obj = new JFrame();
        GamePanel gamePlay = new GamePanel();

        obj.setBounds(10, 10, SCREEN_WIDTH, SCREEN_HEIGHT);
        obj.setTitle("Brick Breaker OOP Project");
        obj.setResizable(false);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Add gamePlay dulu baru setVisible
        obj.add(gamePlay);
        obj.setVisible(true); 
    }
}