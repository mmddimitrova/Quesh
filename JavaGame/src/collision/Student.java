package collision;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;


public class Student {

	private String craft = "Study.png";

	private int x;
	private int y;
	private int width;
	private int height;
	private boolean visible;
	private Image image;

	public Student(int x, int y) {
		ImageIcon ii = new ImageIcon(this.getClass().getResource(craft));
		image = ii.getImage();
		width = image.getWidth(null);
		height = image.getHeight(null);
		visible = true;
		this.x = x;
		this.y = y;
	}


	public void move() {
		if (x < 0) 
			x = 1600;
		x -= Collision.GAME_SPEED;
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

	public Image getImage() {
		return image;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
}