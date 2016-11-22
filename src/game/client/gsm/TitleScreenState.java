package game.client.gsm;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.client.GamePanel;

public class TitleScreenState extends State {
	
	private BufferedImage bg = null;
	private BufferedImage logo = null;
	private BufferedImage pressEnter = null;
	private boolean showPressEnter = true;
	private int pressEnterCounter = 0;

	public TitleScreenState(GameStateManager gsm) {
		super(gsm);
		
		// load resources inside their own try/catch blocks
		try {
			bg = ImageIO.read(getClass().getClassLoader().getResource("bg/title.png"));
		} catch (IOException e) {
			System.err.println("File \"bg/title.png\" is missing.");
		}
		
		try {
			logo = ImageIO.read(getClass().getClassLoader().getResource("text/logo.png"));
		} catch (IOException e) {
			System.err.println("File \"text/logo.png\" is missing.");
		}
		
		try {
			pressEnter = ImageIO.read(getClass().getClassLoader().getResource("text/enter.png"));
		} catch (IOException e) {
			System.err.println("File \"text/enter.png\" is missing.");
		}
	}

	@Override
	public void onEnter() {
		
	}

	@Override
	public void update(double delta) {
		if(!showPressEnter) {
			if(pressEnterCounter < 30) {
				pressEnterCounter++;
			} else {
				pressEnterCounter = 0;
				showPressEnter = !showPressEnter;
			}
		} else {
			if(pressEnterCounter == 60) {
				pressEnterCounter = 0;
				showPressEnter = !showPressEnter;
			} else {
				pressEnterCounter++;
			}
		}
	}

	@Override
	public void draw(Graphics2D g) {
		if(bg != null) {
			g.drawImage(bg, 0, 0, null);
		}
		if(logo != null) {
			int x = (GamePanel.WIDTH / 2) - (logo.getWidth() / 2);
			g.drawImage(logo, x, 125, null);
		}
		if(pressEnter != null) {
			if(showPressEnter) {
				int x = (GamePanel.WIDTH / 2) - (pressEnter.getWidth() / 2);
				int y = (125 + logo.getHeight()) + (GamePanel.HEIGHT - (125 + logo.getHeight()))/2 - (pressEnter.getHeight() / 2);
				g.drawImage(pressEnter, x, y, null);
			}
		}
	}

	@Override
	public void onExit() {
		showPressEnter = true;
		pressEnterCounter = 0;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		if(keyCode == KeyEvent.VK_ENTER) {
			gsm.changeState(new MenuState(gsm), false);
		}
		
		if(keyCode == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
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
		
	}

}
