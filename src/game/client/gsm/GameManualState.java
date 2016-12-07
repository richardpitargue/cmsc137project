package game.client.gsm;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.client.GamePanel;
import game.client.Music;

public class GameManualState extends State{
	
	public BufferedImage tutorial, pudge, wasd;
	public Music music = new Music();

	public GameManualState(GameStateManager gsm) {
		super(gsm);
		
		try {
			tutorial = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tutorial.png"));
			pudge = ImageIO.read(getClass().getClassLoader().getResourceAsStream("pudge.png"));
			wasd = ImageIO.read(getClass().getClassLoader().getResourceAsStream("wasd-arrow.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onEnter() {
		
	}

	@Override
	public void update(double delta) {
		
	}

	@Override
	public void draw(Graphics2D g) {
		
		g.clearRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		g.drawImage(tutorial, 210, 10, 200, 35, null);
		g.drawImage(pudge, -35, 210, 150, 150, null);
		g.drawImage(wasd, 350, 85, 200, 50, null);
		
		g.setColor(Color.WHITE);
		g.drawString("There will be 2 Teams: RED and BLUE", 40, 100);
		g.drawString("To move your player use:", 40, 130);
		g.setColor(Color.BLUE);
		g.setFont(new Font("default", Font.BOLD, 13));
		g.drawString("W,A,S,D or ARROW KEYS", 180, 130);
		
		g.setFont(new Font("default", Font.PLAIN, 12));
		g.setColor(Color.WHITE);
		g.drawString("To hook other players: AIM by using your mouse then LEFT-CLICK to launch a bloody hook ", 40, 160);
		g.drawString("The hook wil snag the first unit it encounters, dragging the unit back to your place", 70, 190);
		g.drawString("If it hooks a member of the other team, your team will gain a POINT.", 90, 210);
		
		g.drawString("First team to reach", 150, 240);
		g.setFont(new Font("default", Font.BOLD, 15));
		g.setColor(Color.BLUE);
		g.drawString("20 POINTS", 260, 240);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("default", Font.PLAIN, 12));
		g.drawString("wins the game!", 345, 240);
		
		g.setColor(Color.RED);
		g.drawString("That's all meat! Uh . . . I mean mate.", 70, 280);
		
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
			gsm.changeState(new MenuState(gsm), false);
		}
		
	}

	@Override
	public void keyReleased(int keyCode) {
		
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

}
