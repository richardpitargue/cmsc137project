package game.client.gsm;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.client.GamePanel;

public class GameCreationState extends State {

	public GameCreationState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void onEnter() {
		
	}

	@Override
	public void update(double delta) {
		
	}

	@Override
	public void draw(Graphics2D g) {
		BufferedImage connectedPlayersLabel, optionsLabel;
		
		g.clearRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		try {
			connectedPlayersLabel = ImageIO.read(getClass().getClassLoader().getResourceAsStream("connected_players.png"));
			g.drawImage(connectedPlayersLabel, 50, 0, 250, 30, null);
			
			optionsLabel = ImageIO.read(getClass().getClassLoader().getResourceAsStream("options.png"));
			g.drawImage(optionsLabel, 400, 0, 150, 30, null);
			
			g.drawString("PRESS ENTER TO CONTINUE", 215, 300);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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
		} else if(keyCode == KeyEvent.VK_ENTER) {
			System.out.println("GAME START");
		}
	}

	@Override
	public void keyReleased(int keyCode) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
