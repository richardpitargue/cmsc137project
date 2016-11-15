package game.client;

import javax.swing.JFrame;

public class Game{
	public static void main(String[] args) {
		JFrame frame = new JFrame("Call of Pudge: Modern Hookfare");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(new GamePanel("127.0.0.1", 1234));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
}
}
