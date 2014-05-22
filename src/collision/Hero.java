package collision;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

public class Hero {
	public static String hero = "Hacker.png";
	public static boolean heroSpowned = false;
	public static int dx;
	public static int dy;
	public static int x;
	public static int y;
	public static int direction = 0;
	public static int directionCount = 0;
	public static int width;
	public static int height;
	public static boolean visible;
	public static boolean up = false;
	public static Image image;
	public static ArrayList Souls;
	public static boolean forward = true;
	public static boolean supermode = false;

	public Hero(int x, int y) {
		ImageIcon ii = new ImageIcon(this.getClass().getResource(hero));
		image = ii.getImage();
		width = image.getWidth(null);
		height = image.getHeight(null);
		Souls = new ArrayList();
		visible = true;
		this.x = x;
		this.y = y;
	}
	public static int randInt(int min, int max) {

		Random rand = new Random();

		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}

	public static void move() {


		if (Board.rise() == 0 && heroSpowned == false) {
			x = 1600 - width;
			heroSpowned = true;
		}

		if (x == 0) {
			forward = false;
		}
		if (x + width == 1600) {
			forward = true;
		}
		if (forward) {
			x -= 1;
		}
		else {
			x += 1;
		}
		if (y > 900 - height/2) {       	
			up = true;
		}
		else if (y < 0 + height/2) {       	
			up = false;
		}

		directionCount++;
		direction += randInt(-20, 20);

		if (directionCount == 50) {			
			directionCount = 0;
			if (direction < 0) {
				up = true;
				direction = 0;
			}
			else {
				up = false;
				direction = 0;
			}
		}

		if (up) {
			y -= 1;
		}
		else {
			y += 1;
		}
	}

	public static int getX() {
		return x;
	}

	public ArrayList getMissles() {      
		return Souls;
	}

	public void heroFire() {
		Souls.add(new Soul(x, y + height/2));		
	}

	public static int getY() {
		return y;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public Image getImage() {
		return image;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
}