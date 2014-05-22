package collision;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;


public class Soul {

	private int x, y;
	private Image image;
	boolean visible;
	private int width, height;
	private final int BOARD_WIDTH = 1600;
	
	

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
				x += Collision.GAME_SPEED * 2;

				if (Hero.y < Nakov.y) {
					y += Collision.GAME_SPEED * 2;
					if (y > 900 || x > 1600) {
						visible = false;
					}
				}
			}
			else {
				x -= Collision.GAME_SPEED * 2;
				if (Hero.y > Nakov.y) {
					y -= Collision.GAME_SPEED * 2;
					if (y < 0 || x < 0) {
						visible = false;
					}
				}
			}
		}
		else {
			if (Hero.x < Nakov.x) {
				y -= Collision.GAME_SPEED;
			}
			else {
				y -= Collision.GAME_SPEED;
			}
		}
	}


	public int getBOARD_WIDTH() {
		return BOARD_WIDTH;
	}
}