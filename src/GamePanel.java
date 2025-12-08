package src;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
    private boolean play = false;
    private int score = 0;
    private int totalBricks = 21;
    
    private int level = 1;
    private final int MAX_LEVEL = 3;
    private boolean gameWon = false;

    private int messageTimer = 0;
    private final int MESSAGE_DURATION = 90;
    
    private String cheatBuffer = "";
    private boolean cheatActived = false;
    private String cheatText = "";
    
    private boolean speedUp = false;
    
    private Timer timer;
    private int delay = 8;

    private Paddle paddle;
    private Ball ball;
    private MapGenerator map;

    public GamePanel() {
        map = new MapGenerator(3, 7, level);
        paddle = new Paddle(310);
        ball = new Ball(120, 350, -1, -2);

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g) {
        // Background
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);

        // Drawing map
        map.draw((Graphics2D) g);

        // Borders
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);

        // Scores
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 25));
        //g.drawString("Score: " + score, 550, 30);
        if (score < 10) {
            g.drawString("Score: " + score, 590, 30);
        } else if (score >= 10 && score < 100) {
            g.drawString("Score: " + score, 580, 30);
        } else {
            g.drawString("Score: " + score, 560, 30);
        }
        
        // Paddle
        paddle.draw(g);

        // Ball
        ball.draw(g);

        // --- GAME OVER ---
        if (ball.getY() > 570) {
            play = false;
            ball.setDirX(0);
            ball.setDirY(0);
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Game Over, Scores: " + score, 190, 300);
        
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press Enter to Restart", 230, 350);
        }

        // --- VICTORY (TAMAT) ---
        if (gameWon) {
            g.setColor(Color.GREEN); // Warna hijau biar beda
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("YOU WIN!", 200, 300);
        
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("You Beat The Game! Final Score: " + score, 180, 350);
            g.drawString("Press Enter to Play Again", 230, 380);
        }
        
        if (cheatActived) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("serif", Font.BOLD | Font.ITALIC, 15));
            g.drawString(cheatText, 30, 30);
        }
        
        if (speedUp) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("serif", Font.BOLD, 25));
            g.drawString(ball.getSpeed() + "x", 30, 30);
        }
        
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (cheatActived) {
            messageTimer--; // Kurangi waktu
            if (messageTimer <= 0) {
                cheatActived = false; // Waktu habis, sembunyikan pesan
            }
        }
        
        if (speedUp) {
            messageTimer--; // Kurangi waktu
            if (messageTimer <= 0) {
                speedUp = false; // Waktu habis, sembunyikan pesan
            }
        }
        
        timer.start();
        if (play) {
            // Ball - Paddle Collision
            if (new Rectangle(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight()).intersects(paddle.getRect())) {
                ball.reverseY();
            }

            // Ball - Brick Collision
            A: for (int i = 0; i < map.bricks.length; i++) {
                for (int j = 0; j < map.bricks[0].length; j++) {
                    Brick b = map.bricks[i][j]; // Ambil objek bata

                    if (!b.isDestroyed) { // Cek jika bata belum hancur

                        Rectangle ballRect = new Rectangle(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());
                        Rectangle brickRect = b.getRect();

                        if (ballRect.intersects(brickRect)) {

                            b.hit(); // Pukul bata (HP berkurang)

                            // TAMBAHAN: Dapat skor kecil setiap kali pukul bata (walau belum hancur)
                            score += 5;
                            
                            if(b.isDestroyed) {
                                totalBricks--;
                                score += b.scoreValue; // Skor dinamis dari bata
                            }

                            // Logika Pantulan
                            if (ball.getX() + 19 <= brickRect.x || ball.getX() + 1 >= brickRect.x + brickRect.width) {
                                ball.reverseX();
                            } else {
                                ball.reverseY();
                            }

                            break A;
                        }
                    }
                }
            }

            ball.move();

            // Wall Collision
            if (ball.getX() < 0) {
                ball.reverseX();
            }
            if (ball.getY() < 0) {
                ball.reverseY();
            }
            if (ball.getX() > 670) {
                ball.reverseX();
            }
            
            if (totalBricks <= 0) {
                if (level < MAX_LEVEL) {
                    // --- LOGIKA NAIK LEVEL (Masih ada level selanjutnya) ---
                    level++;
        
                    // Reset posisi bola & paddle
                    ball.setX(120);
                    ball.setY(350);
                    ball.setDirX(-1);
                    ball.setDirY(-2);
        
                    // Tambah baris bata (Makin tinggi level, makin banyak baris)
                    int newRow = 3 + level; 
                    map = new MapGenerator(newRow, 7, level);
        
                    // Hitung ulang jumlah bata
                    totalBricks = newRow * 7;
        
                    // Opsional: Reset timer/speed agar tidak terlalu gila cepatnya
                    // timer.restart();

                } else {
                    // --- LOGIKA TAMAT (VICTORY) ---
                    play = false;
                    ball.setDirX(0);
                    ball.setDirY(0);
                    gameWon = true; // Tandai bahwa pemain sudah menamatkan game
                }
            }
        }

        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        boolean isCapslock = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
        
        if (isCapslock) {
            char keyChar =  e.getKeyChar();
            
            if (keyChar >= 33 && keyChar <= 126) {
                cheatBuffer += keyChar;
            
                if (cheatBuffer.length() > 6) {
                    cheatBuffer = cheatBuffer.substring(cheatBuffer.length() - 6);
                }
            
                if (cheatBuffer.endsWith("QWD")) {
                    if (play) {
                        totalBricks = 0;
                        for (int i = 0; i < map.bricks.length; i++) {
                            for (int j = 0; j < map.bricks[0].length; j++) {
                                map.bricks[i][j].isDestroyed = true;
                            }
                        }
                        cheatText = "CHEAT ACTIVED";
                        cheatActived = true;
                        messageTimer = MESSAGE_DURATION;
                        //System.out.println("CHEAT ACTIVATED");
                        cheatBuffer = "";
                    }
                }
            }
        }
        
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (play) {
                paddle.moveRight();
            } else if (!play && totalBricks > 0 && ball.getY() <= 570) {
                // Start game if not started but not game over
                play = true;
                paddle.moveRight();
            }
        }
        
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (play) {
                paddle.moveLeft();
            } else if (!play && totalBricks > 0 && ball.getY() <= 570) {
                play = true;
                paddle.moveLeft();
            }
        }
        
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!play) {
                play = true;
                gameWon = false; // Reset status menang
                level = 1;       // Kembali ke level 1
        
                ball.setX(120);
                ball.setY(350);
                ball.setDirX(-1);
                ball.setDirY(-2);
                score = 0;
                
                //delay = 8; // KEMBALIKAN KECEPATAN ASAL
                //timer.setDelay(delay);
                ball.normalSpeed();
                
                map = new MapGenerator(3, 7, level);
                //totalBricks = 3 * 7; // Hitung manual row * col awal
                totalBricks = map.bricks.length * map.bricks[0].length;
                
                repaint();
             }
        }
        
        if (e.getKeyCode() == KeyEvent.VK_OPEN_BRACKET) {
            if (play) {
                if (ball.getSpeed() > 1) {
                    ball.decreaseSpeed();
                    speedUp = true;
                    messageTimer = MESSAGE_DURATION;
                    //System.out.println("d" + ball.getSpeed());
                }
            }
        }
        
        if (e.getKeyCode() == KeyEvent.VK_CLOSE_BRACKET) {
            if (play) {
                if (ball.getSpeed() < 2) {
                    ball.increaseSpeed();
                    speedUp = true;
                    messageTimer = MESSAGE_DURATION;
                    //System.out.println("i" + ball.getSpeed());
                }
            }
        }
    }
}