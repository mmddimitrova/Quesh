package collision;

import javax.swing.JFrame;

public class Collision extends JFrame {

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