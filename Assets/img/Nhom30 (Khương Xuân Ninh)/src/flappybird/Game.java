package flappybird;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import javax.swing.JPanel;


public class Game extends JPanel {

	
	
	private static final long serialVersionUID = 1L;
	private Bird bird;
    private ArrayList<Rectangle> rects;
    private FlappyBird fb;
    private Font scoreFont, pauseFont;
    public static final int PIPE_W = 100, PIPE_H = 60;
    

    public Game(FlappyBird fb, Bird bird, ArrayList<Rectangle> rects) {
        this.fb = fb;
        this.bird = bird;
        this.rects = rects;
        scoreFont = new Font("Comic Sans MS", Font.BOLD, 18);
        pauseFont = new Font("Arial", Font.BOLD, 48);
        
        }
    
    @Override
    public void paintComponent(Graphics g) {

        bird.update(g);
        for(Rectangle r : rects) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.GREEN);
            g2d.fillRect(r.x, r.y, r.width, r.height);
          
        }
        g.setFont(scoreFont);
        g.setColor(Color.BLACK);
        g.drawString("Score: "+fb.getScore(), 10, 20);
        
        if(fb.paused()) {
            g.setFont(pauseFont);
            g.setColor(new Color(0,0,0,170));
            g.drawString("Dừng", FlappyBird.WIDTH/2-70, FlappyBird.HEIGHT/2-70);
            g.drawString("SPACE ĐỂ CHƠI", FlappyBird.WIDTH/2-200, FlappyBird.HEIGHT-120);
        }
    }
}
