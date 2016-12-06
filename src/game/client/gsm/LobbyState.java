package game.client.gsm;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

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
		// list all available lobbies on GUI
		BufferedImage lobbyLabel;
		
		g.clearRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		try {
			lobbyLabel = ImageIO.read(getClass().getClassLoader().getResourceAsStream("lobby.png"));
			g.drawImage(lobbyLabel, 220, 0, 170, 30, null);
			
			g.drawString("PRESS ENTER TO CONTINUE", 215, 300);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onExit() {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_ESCAPE) {
			gsm.pop();
		} else if(keyCode == KeyEvent.VK_ENTER) {
			//gsm.changeState(new GameCreationState(gsm), false);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
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

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}

// package game.client.gsm;

// import java.awt.Graphics2D;
// import java.awt.event.KeyEvent;
// import java.awt.event.MouseEvent;
// import java.awt.image.BufferedImage;
// import java.io.IOException;

// import javax.imageio.ImageIO;

// import game.client.GamePanel;

// public class LobbyState extends State {

// 	public LobbyState(GameStateManager gsm) {
// 		super(gsm);
// 	}

// 	@Override
// 	public void onEnter() {
		
// 	}

// 	@Override
// 	public void update(double delta) {
// 		// get available lobbies from server
// 	}

// 	@Override
// 	public void draw(Graphics2D g) {
// 		// list all available lobbies on GUI
// 		BufferedImage lobbyLabel;
		
// 		g.clearRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
// 		try {
// 			lobbyLabel = ImageIO.read(getClass().getClassLoader().getResourceAsStream("lobby.png"));
// 			g.drawImage(lobbyLabel, 220, 0, 170, 30, null);
			
// 			g.drawString("PRESS ENTER TO CONTINUE", 215, 300);
// 		} catch (IOException e) {
// 			e.printStackTrace();
// 		}
// 	}

// 	@Override
// 	public void onExit() {
		
// 	}

// 	@Override
// 	public void keyTyped(KeyEvent e) {
		
// 	}

// 	@Override
// 	public void keyPressed(KeyEvent e) {
// 		int keyCode = e.getKeyCode();
// 		if(keyCode == KeyEvent.VK_ESCAPE) {
// 			gsm.pop();
// 		} else if(keyCode == KeyEvent.VK_ENTER) {
// 			gsm.changeState(new GameCreationState(gsm), false);
// 		}
// 	}

// 	@Override
// 	public void keyReleased(KeyEvent e) {
		
// 	}

// 	@Override
// 	public void mouseClicked(MouseEvent e) {
// 		// TODO Auto-generated method stub
		
// 	}

// 	@Override
// 	public void mousePressed(MouseEvent e) {
// 		// TODO Auto-generated method stub
		
// 	}

// 	@Override
// 	public void mouseReleased(MouseEvent e) {
// 		// TODO Auto-generated method stub
		
// 	}

// 	@Override
// 	public void mouseEntered(MouseEvent e) {
// 		// TODO Auto-generated method stub
		
// 	}

// 	@Override
// 	public void mouseExited(MouseEvent e) {
// 		// TODO Auto-generated method stub
		
// 	}

// 	@Override
// 	public void mouseDragged(MouseEvent e) {
// 		// TODO Auto-generated method stub
		
// 	}

// 	@Override
// 	public void mouseMoved(MouseEvent e) {
// 		// TODO Auto-generated method stub
		
// 	}

// }
