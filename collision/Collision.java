package collision;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Collision extends JFrame {
	public static int GAME_SPEED = 3;
	
    public Collision() {
        add(new Board());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1600, 900);
        setLocationRelativeTo(null);
        setTitle("Quesh");
        setResizable(false);
        setVisible(true);
        
    }

    public static void main(String[] args) {
        new Collision();
    }
}