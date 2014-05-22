package collision;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.text.StyledEditorKit.ForegroundAction;


public class Board extends JPanel implements ActionListener {

	private Timer timer;
	private Nakov nakov;
	private Hero hero;
	public static ArrayList<Student> students;
	private int heroLife = 20;
	private int heroStart = 100000;
	private boolean ingame;
	private boolean heroDown;
	private int B_WIDTH;
	private int B_HEIGHT;
	
	



	public static int randInt(int min, int max) {

		Random rand = new Random();

		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}


	private int[][] pos = { 
			
			{randInt(1000, 10000), randInt(50, 800)},
			{randInt(1000, 10000), randInt(50, 800)},
			{randInt(1000, 10000), randInt(50, 800)},
			{randInt(1000, 10000), randInt(50, 800)},
			{randInt(1000, 10000), randInt(50, 800)},
			{randInt(1000, 10000), randInt(50, 800)},
			{randInt(1000, 10000), randInt(50, 800)},
			{randInt(1000, 10000), randInt(50, 800)},
			{randInt(1000, 10000), randInt(50, 800)},
			{randInt(1000, 10000), randInt(50, 800)},
			{randInt(1000, 10000), randInt(50, 800)},
			{randInt(1000, 10000), randInt(50, 800)},
			{randInt(1000, 10000), randInt(50, 800)},
			{randInt(1000, 10000), randInt(50, 800)},
			{randInt(1000, 10000), randInt(50, 800)},
			{randInt(1000, 10000), randInt(50, 800)},
			{randInt(1000, 10000), randInt(50, 800)},
			{randInt(1000, 10000), randInt(50, 800)},
			{randInt(1000, 10000), randInt(50, 800)},
			{randInt(1000, 10000), randInt(50, 800)},
					 
	};

	public Board() {

		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(Color.BLACK);

		setDoubleBuffered(true);
		ingame = true;

		setSize(1600, 900);

		nakov = new Nakov();

		initstudents();
		inithero();


		timer = new Timer(5, this);
		timer.start();
		
	}

	public void addNotify() {
		super.addNotify();
		B_WIDTH = getWidth();
		B_HEIGHT = getHeight();   
	}

	public void initstudents() {
		students = new ArrayList<Student>();

		for (int i=0; i<pos.length; i++ ) {
			students.add(new Student(pos[i][0], pos[i][1]));
		}
	}
	public void inithero() {
		hero = new Hero(heroStart, 500); 
	}

	public static int rise() {
		int ninjaz = students.size();
		return ninjaz;
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;

		if (ingame) {

			if (nakov.isVisible())
				if (Nakov.x < Hero.x) {
					g2d.drawImage(Nakov.NakovL, nakov.getX(), nakov.getY(),
							this);
				}
				else {
					g2d.drawImage(Nakov.NakovR, nakov.getX(), nakov.getY(),
							this);
				}
				

			ArrayList<?> ms = nakov.getMissiles();

			for (int i = 0; i < ms.size(); i++) {
				Missile m = (Missile)ms.get(i);
				g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
			}

			ArrayList<?> mbs = hero.getMissles();

			for (int i = 0; i < mbs.size(); i++) {
				Soul mb = (Soul)mbs.get(i);
				g2d.drawImage(mb.getImage(), mb.getX(), mb.getY(), this);
			}

			for (int i = 0; i < students.size(); i++) {
				Student a = students.get(i);
				if (a.isVisible())
					g2d.drawImage(a.getImage(), a.getX(), a.getY(), this);
			}


			if (hero.isVisible())
				g2d.drawImage(hero.getImage(), hero.getX(), hero.getY(), this);
			
			Font small = new Font("Helvetica", Font.BOLD, B_WIDTH/100);
			FontMetrics metr = this.getFontMetrics(small);
			g2d.setColor(Color.ORANGE);
			g2d.setFont(small);	
			g2d.drawString("Nakov Lifes left: " + Nakov.lifes, 515,25);

			if (hero.supermode){

				small = new Font("Helvetica", Font.BOLD, B_WIDTH/100);
				metr = this.getFontMetrics(small);
				g2d.setColor(Color.ORANGE);
				g2d.setFont(small);	
				g2d.drawString("Students start to get angry !!!", 515,55);
			}

			if (students.size() > 0) {
				small = new Font("Helvetica", Font.BOLD, B_WIDTH/100);
				metr = this.getFontMetrics(small);
				g2d.setColor(Color.CYAN);
				g2d.drawString("Student souls left: " + students.size(), 35, 25);				

			}
			else if (students.size() == 0){

				small = new Font("Helvetica", Font.BOLD, B_WIDTH/100);
				metr = this.getFontMetrics(small);
				g2d.setColor(Color.ORANGE);
				g2d.setFont(small);			
				g2d.drawString("hero strengh: " + heroLife, 35, 25);

			}
			
		}

		else if (heroDown){
			String msg = "Nakov Wins!";
			Font small = new Font("Helvetica", Font.BOLD, B_WIDTH/20);
			FontMetrics metr = this.getFontMetrics(small);
			g.setColor(Color.ORANGE);
			g.setFont(small);
			g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2,
					B_HEIGHT / 2);
		}
		else {
			String msg = "The Evel is defeated!";
			Font small = new Font("Helvetica", Font.BOLD, B_WIDTH/20);
			FontMetrics metr = this.getFontMetrics(small);
			g.setColor(Color.GREEN);
			g.setFont(small);
			g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2,
					B_HEIGHT / 2);
		}

		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}
	
	@SuppressWarnings("unchecked")
	public void ninjaFire(Rectangle r) {
		hero.Souls.add(new Soul(r.x, r.y + r.height/2));		
	}

	public void actionPerformed(ActionEvent e) {

		if (heroDown) {
			ingame = false;
		}

		ArrayList<?> ms = nakov.getMissiles();

		for (int i = 0; i < ms.size(); i++) {
			Missile m = (Missile) ms.get(i);
			if (m.isVisible()) 
				m.move();
			else ms.remove(i);
		}

		for (int i = 0; i < students.size(); i++) {
			Student a = students.get(i);
			if (a.isVisible()) 
				a.move();
			else students.remove(i);
		}

		ArrayList<?> mbs = hero.getMissles();

		for (int i = 0; i < mbs.size(); i++) {
			Soul mb = (Soul) mbs.get(i);
			if (mb.isVisible()) 
				mb.move();
			else mbs.remove(i);
		}

		Hero.move();
		nakov.move();
		checkCollisions();
		repaint();  
	}

	public void checkCollisions() {
		if ((students.size() <= 5 && students.size() != 0) ||heroLife <= 10 ) {
			hero.supermode = true;
		}
		Rectangle r3 = nakov.getBounds();		
		Rectangle r5 = hero.getBounds();
		ArrayList<?> mbs = hero.getMissles();
		
		for (int i = 0; i < mbs.size(); i++) {
			Soul mb = (Soul) mbs.get(i);
			Rectangle r6 = mb.getBounds();
			
			if (r6.intersects(r3)) {
				mb.setVisible(false);
				Nakov.lifes--;
			}
		}
		
		if (Nakov.lifes == 0) {
			nakov.setVisible(false);
			ingame = false;
		}
		
		if (students.size() == 1) {
			Nakov.lifes = 3;
		}
		
		if (r5.intersects(r3)) {
			hero.setVisible(false);
			Nakov.lifes = 0;
		}

		for (int j = 0; j<students.size(); j++) {
			Student a = students.get(j);
			Rectangle r2 = a.getBounds();


			if (r3.intersects(r2)) {
				a.setVisible(false);
				Nakov.lifes--;
			}	
		}

		ArrayList<?> ms = nakov.getMissiles();

		for (int i = 0; i < ms.size(); i++) {
			Missile m = (Missile) ms.get(i);

			Rectangle r1 = m.getBounds();			
			Rectangle r4 = hero.getBounds(); 

			if (r4.intersects(r1)) {
				heroLife--;
				hero.heroFire();
				m.setVisible(false);
			}
			if (heroLife == 0) {
				hero.setVisible(false);
				ingame = false;
				heroDown = true;
			}

			for (int j = 0; j<students.size(); j++) {
				Student a = students.get(j);
				Rectangle r2 = a.getBounds();

				if (r1.intersects(r2)) {
					m.setVisible(false);
					a.setVisible(false);
					heroLife++;
					ninjaFire(a.getBounds());
				}
			}
		}
	}


	private class TAdapter extends KeyAdapter {

		public void keyReleased(KeyEvent e) {
			nakov.keyReleased(e);
		}

		public void keyPressed(KeyEvent e) {
			nakov.keyPressed(e);
		}
	}
}