package flappybird;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;



public class FlappyBird implements ActionListener, KeyListener {
    
    public static final int FPS = 60, WIDTH = 1280, HEIGHT = 780;
    
    private Bird bird;
    private JFrame jframe;
    private JPanel jpanel;
    private ArrayList<Rectangle> rects;
    private int time, scroll;
    private Timer t;
    private int Diem;
    
    private boolean paused;
    
    public void RunGame() {
        jframe = new JFrame("Flappy Bird");
        bird = new Bird();
        rects = new ArrayList<Rectangle>();
        jpanel = new Game(this, bird, rects);
      
        
        jframe.setSize(WIDTH, HEIGHT);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setVisible(true);
        jframe.addKeyListener(this);
        
        paused = true;
        
        t = new Timer(1000/FPS, this);
        t.start();
    }
    public static void main(String[] args) {
    	 new FlappyBird().RunGame();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        jpanel.repaint();
        if(!paused) {
            bird.physics();
            if(scroll % 90 == 0) {
                Rectangle r = new Rectangle(WIDTH, 0, Game.PIPE_W, (int) ((Math.random()*HEIGHT)/5f + (0.2f)*HEIGHT));
                int h2 = (int) ((Math.random()*HEIGHT)/5f + (0.3f)*HEIGHT);
                Rectangle r2 = new Rectangle(WIDTH, HEIGHT - h2, Game.PIPE_W, h2);
                rects.add(r);
                rects.add(r2);
            }
            ArrayList<Rectangle> toRemove = new ArrayList<Rectangle>();
            boolean game = true;
            for(Rectangle r : rects) {
                r.x-=3;
                if(r.x + r.width <= 0) {
                    toRemove.add(r);
                }
                if(r.contains(bird.x, bird.y)) {
                    JOptionPane.showMessageDialog(jframe, "You lose!\n"+"Your score was: "+time+".");
                    game = false;
                    Diem = time;
                    
                    try {
                        
                        File f = new File("Diem.txt");
                        FileWriter fw = new FileWriter(f);
                        fw.write("Diem Cua nguoi choi:" + Diem);
                        fw.close();
                      } catch (IOException ex) {
                        System.out.println("Loi ghi file: " + ex);
                    }
                }
            }
            rects.removeAll(toRemove);
            if(game) {
            	time++;
            }
            scroll++;

            if(bird.y > HEIGHT || bird.y+Bird.RAD < 0) {
                game = false;
            }

            if(!game) {
                rects.clear();
                bird.reset();
                time = 0;
                scroll = 0;
                paused = true;
            }
        }
        else {
            
        }
    }
    
    public int getScore() {
        return time;
    }
    
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_UP) {
            bird.jump();
        }
        else if(e.getKeyCode()==KeyEvent.VK_SPACE) {
            paused = false;
        }
    }
    public void keyReleased(KeyEvent e) {
        
    }
    public void keyTyped(KeyEvent e) {
        
    }
    
    public boolean paused() {
        return paused;
    }
}
