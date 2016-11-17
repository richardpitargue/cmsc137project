package game.client.gsm;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.client.GamePanel;
import game.client.components.ClickableArea;
import game.client.components.TextArea;

public class MenuState extends State {
	
//	private ClickableArea startButton;
//	private TextArea chatInput;
	
    private BufferedImage logo;
    private BufferedImage logo2;
	private BufferedImage background;
	
	public MenuState(GameStateManager gsm) {
		super(gsm);
//		startButton = new ClickableArea(null, 50, 50, 50, 25);
//		startButton.setAnimation(true);
//		startButton.setAnimationColor(new Color(1f, 1f, 1f, 0.1f));
//		
//		chatInput = new TextArea(0, 200, 600, 125);
		try {
			logo = ImageIO.read(getClass().getClassLoader().getResourceAsStream("logo.png"));
			logo2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("logo2.png"));
			background = ImageIO.read(getClass().getClassLoader().getResourceAsStream("callOfPudge.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onEnter() {
		
	}

	@Override
	public void update(double delta) {
//		chatInput.update();
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(background, 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
		g.drawImage(logo, 55, 5, 500, 50, null);
		g.drawImage(logo2, 105, 55, 400, 25, null);
		
		g.drawString("PRESS ENTER TO CONTINUE", 215, 300);
//		g.setColor(Color.BLACK);
//		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
//		
//		startButton.draw(g);
//		chatInput.draw(g);
		
	}

	@Override
	public void onExit() {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
//		if(chatInput.hasFocus()) {
//			chatInput.keyPressed(e);
//		} else if(e.getKeyCode() == KeyEvent.VK_ENTER) {
//			chatInput.setFocus(true);
//		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			gsm.changeState(new LobbyState(gsm), false);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
//		startButton.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
//		startButton.mouseReleased(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
//		startButton.mouseMoved(e);
	}

}
