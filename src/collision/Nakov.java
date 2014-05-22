package collision;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.RepaintManager;

public class Nakov {

	public static int dx;
	public int dy;
	public static int x;
	public static int y;
	public static int width;
	public static int height;
	public static boolean visible;
	public static Image NakovL;
	public static Image NakovR;
	public static ArrayList missiles;
	public static int lifes = 3;


	public  Nakov() {
		String nakovl = "NakovL.png";	
		ImageIcon ii = new ImageIcon(this.getClass().getResource(nakovl));
		NakovL = ii.getImage();
		String nakovR = "NakovR.png";	
		ImageIcon iii = new ImageIcon(this.getClass().getResource(nakovR));
		NakovR = iii.getImage();
		width = NakovL.getWidth(null);
		height = NakovL.getHeight(null);
		missiles = new ArrayList();
		visible = true;
		x = 40;
		y = 60;
	}


	public void move() {

		x += dx;
		y += dy;

		if (x < 1) {
			x = 1;
		}

		if (y < 1) {
			y = 1;
		}
		if (x > 1600 - width) {
			x = 1600 - width;
		}

		if (y > 900 - height) {
			y = 900 - height;
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	//public Image getImage() {
	///	return image;
	//}

	public ArrayList getMissiles() {
		return missiles;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean isVisible() {
		return visible;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_SPACE) {
			fire();
		}

		if (key == KeyEvent.VK_LEFT) {
			dx = -3;
		}

		if (key == KeyEvent.VK_RIGHT) {
			dx = 3;
		}

		if (key == KeyEvent.VK_UP) {
			dy = -3;
		}

		if (key == KeyEvent.VK_DOWN) {
			dy = 3;
		}
	}

	public void fire() {
		if (Hero.x < x) {
			missiles.add(new Missile(x, y + height/2));
		}
		else {
			missiles.add(new Missile(x + width, y + height/2));
		}
	}


	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			dx = 0;
		}

		if (key == KeyEvent.VK_RIGHT) {
			dx = 0;
		}

		if (key == KeyEvent.VK_UP) {
			dy = 0;
		}

		if (key == KeyEvent.VK_DOWN) {
			dy = 0;
		}
	}
}