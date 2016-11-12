package game.client.gsm;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import game.client.Game;
import game.client.GamePanel;
import game.server.GameServer;

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
		if(keyCode == KeyEvent.VK_S)
		{
			gsm.changeState(new GameServer(gsm,2), false);
		}
		if(keyCode == KeyEvent.VK_C)
		{
			gsm.changeState(new Game(gsm,"127.0.0.1"), false);
		}
	}

	@Override
	public void keyReleased(int keyCode) {
		
	}

}
