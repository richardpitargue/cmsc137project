package game.client.gsm;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.client.GamePanel;
import game.client.Music;

public class WinnerState extends State{

	public BufferedImage bg, pudge, victory, red, blue, trophy;	
	public Music music = new Music();
	public String winningTeam;
	
	public WinnerState(GameStateManager gsm, String winningTeam) {
		super(gsm);
		this.winningTeam = winningTeam;
		
		music.winning(true);
		try {
			bg = ImageIO.read(getClass().getClassLoader().getResourceAsStream("winning-page.png"));
			victory = ImageIO.read(getClass().getClassLoader().getResourceAsStream("victory.png"));
			trophy = ImageIO.read(getClass().getClassLoader().getResourceAsStream("trophy.png"));
			red = ImageIO.read(getClass().getClassLoader().getResourceAsStream("red-team.png"));
			blue = ImageIO.read(getClass().getClassLoader().getResourceAsStream("blue-team.png"));
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
		g.drawImage(bg, 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
		g.drawImage(trophy, 220, 150, 150, 150, null);
		g.drawImage(victory, 310, 20, 200, 40, null);
		if(winningTeam == "RED") g.drawImage(red, 70, 20, 220, 40, null);
		else g.drawImage(blue, 70, 20, 220, 40, null);
	}

	@Override
	public void onExit() {
		
	}

	@Override
	public void keyTyped(int keyCode) {
		
	}

	@Override
	public void keyPressed(int keyCode) {
		
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
