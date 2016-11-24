package game.client.gsm;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.client.Game;
import game.client.GamePanel;
import game.client.components.ClickableArea;

public class MenuState extends State {

	private BufferedImage bg = null;
	private BufferedImage logo = null;
	
	private boolean enterAnimation;
	private boolean exitAnimation;
	private int y;
	private int heightAnim;
	private int targetHeight;
	
	private ClickableArea option1;
	private ClickableArea option2;
	
	public MenuState(GameStateManager gsm) {
		super(gsm);
		
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
		
		option1 = new ClickableArea("button/option1.png", GamePanel.WIDTH / 2 - (160/2), 125 + logo.getHeight() + 13, 75, 75);
		option2 = new ClickableArea("button/option2.png", GamePanel.WIDTH / 2 - (160/2) + 85, 125 + logo.getHeight() + 13, 75, 75);
	}

	@Override
	public void onEnter() {
		enterAnimation = true;
		exitAnimation = false;
		y = 125 + logo.getHeight() + 8;
		heightAnim = 0;
		targetHeight = GamePanel.HEIGHT - (125 + logo.getHeight() + 10) - 8;
		Game.getGamePanel().disableListeners();
	}

	@Override
	public void update(double delta) {
		if(enterAnimation) {
			if(heightAnim <= targetHeight) {
				heightAnim += 5;
			} else {
				enterAnimation = false;
				Game.getGamePanel().enableListeners();
			}
		}
		
		if(exitAnimation) {
			if(heightAnim > 0) {
				heightAnim -= 5;
			} else {
				exitAnimation = false;
				Game.getGamePanel().enableListeners();
				gsm.pop();
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
		
		g.setColor(new Color(1f, 1f, 1f, 0.1f));
		if(enterAnimation || exitAnimation) {
			g.fillRect(0, y, GamePanel.WIDTH, heightAnim);
		} else {
			g.fillRect(0, y, GamePanel.WIDTH, targetHeight);
			option1.draw(g);
			option2.draw(g);
		}
	}

	@Override
	public void onExit() {
		Game.getGamePanel().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		if(keyCode == KeyEvent.VK_ESCAPE) {
			exitAnimation = true;
			heightAnim = targetHeight;
			Game.getGamePanel().disableListeners();
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
		if(option1.mousePressed(e)) {
			gsm.pop();
		}
		
		if(option2.mousePressed(e)) {
			System.out.println("OLOL");
		}
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
		option1.mouseMoved(e);
		option2.mouseMoved(e);
	}

}
