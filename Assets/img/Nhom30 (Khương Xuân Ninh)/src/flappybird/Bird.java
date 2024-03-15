package flappybird;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;



public class Bird {
	public float x, y, vx, vy;
    public static final int RAD = 35;
    public static Image img, backgroundd;
    public Bird() {
        x = FlappyBird.WIDTH/2;
        y = FlappyBird.HEIGHT/2;
        try {
            img = ImageIO.read(new File("New Forder2\\bird0.png"));
            backgroundd = ImageIO.read(new File("New Forder2\\background.png"));
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void physics() {
        x+=vx;
        y+=vy;
        vy+=0.5f;
    }
    public void update(Graphics g) {
    	g.drawImage(backgroundd,0,0 ,1280 , 780, null);
        g.drawImage(img, Math.round(x-RAD),Math.round(y-RAD),2*RAD,2*RAD, null);
    }
    public void jump() {
        vy = -10;
    }
    
    public void reset() {
        x = 1280/2;  
        y = 640/2+50 ;
        vx = vy = 0;
    }

}
