package game.client;

import javax.swing.JFrame;

public class Game {
	
	private static GamePanel gamePanel;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Call of Pudge: Modern Hookfare");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		gamePanel = new GamePanel();
		frame.add(gamePanel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public static GamePanel getGamePanel() {
		return gamePanel;
	}
	
}
