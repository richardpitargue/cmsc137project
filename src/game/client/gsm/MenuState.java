package game.client.gsm;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.client.GamePanel;

public class MenuState extends State {
	
	public MenuState(GameStateManager gsm) {
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
		BufferedImage img;
		try {
			img = ImageIO.read(getClass().getClassLoader().getResourceAsStream("callOfPudge.jpg"));
			g.drawImage(img, 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// g.drawString("PRESS [ENTER] TO CONTINUE", 50, 50);
		
	}

	@Override
	public void onExit() {
		
	}

	@Override
	public void keyTyped(int keyCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(int keyCode) {
		if(keyCode == KeyEvent.VK_ENTER) {
			gsm.changeState(new LobbyState(gsm), false);
		}
	}

	@Override
	public void keyReleased(int keyCode) {
		// TODO Auto-generated method stub
		
	}

}
