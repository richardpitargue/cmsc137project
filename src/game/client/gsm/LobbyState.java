package game.client.gsm;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import game.client.GamePanel;

public class LobbyState extends State {

	public LobbyState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void onEnter() {
		
	}

	@Override
	public void update(double delta) {
		// get available lobbies from server
	}

	@Override
	public void draw(Graphics2D g) {
		// list all available lobbies on gui
		g.clearRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		g.drawString("Welcome to the Lobby", 50, 10);
	}

	@Override
	public void onExit() {
		
	}

	@Override
	public void keyTyped(int keyCode) {
		
	}

	@Override
	public void keyPressed(int keyCode) {
		if(keyCode == KeyEvent.VK_ESCAPE) {
			gsm.pop();
		}
	}

	@Override
	public void keyReleased(int keyCode) {
		
	}

}
