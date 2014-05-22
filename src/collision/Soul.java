package collision;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;


public class Soul {

	private int x, y;
	private Image image;
	boolean visible;
	private int width, height;
	private final int BOARD_WIDTH = 1600;
	private final int MISSILE_SPEED = 2;
	

	public Soul(int x, int y) {

		ImageIcon ii =
				new ImageIcon(this.getClass().getResource("ghoust.png"));
		image = ii.getImage();
		visible = true;
		width = image.getWidth(null);
		height = image.getHeight(null);
		this.x = x;
		this.y = y;
	}


	public Image getImage() {
		return image;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	public void move() {
		
		if(Hero.supermode) {
			if (Hero.x < Nakov.x) {
				x += MISSILE_SPEED;

				if (Hero.y < Nakov.y) {
					y += MISSILE_SPEED;
					if (y > 900 || x > 1600) {
						visible = false;
					}
				}
			}
			else {
				x -= MISSILE_SPEED;
				if (Hero.y > Nakov.y) {
					y -= MISSILE_SPEED;
					if (y < 0 || x < 0) {
						visible = false;
					}
				}
			}
		}
		else {
			if (Hero.x < Nakov.x) {
				y -= 1;
			}
			else {
				y -= 1;
			}
		}
	}


	public int getBOARD_WIDTH() {
		return BOARD_WIDTH;
	}
}