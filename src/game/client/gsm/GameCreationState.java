package game.client.gsm;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.client.GamePanel;
import game.client.Player;

public class GameCreationState extends State {
	
	
	private BufferedImage connectedPlayersLabel, optionsLabel, bg;
	private BufferedImage sprites[][];
	
	public GameCreationState(GameStateManager gsm) {
		super(gsm);
		sprites = new BufferedImage[4][4];
		try {
			bg = ImageIO.read(getClass().getClassLoader().getResourceAsStream("backgound.png"));
			for(int i = 0;  i < 4; i++)
				for(int j = 0; j < 4; j++)
					sprites[i][j] = ImageIO.read(getClass().getClassLoader().getResourceAsStream("sprite_" + i + "_" + j + ".png"));
			
			
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
		for(Player p : gsm.players)
		{
			g.drawString(p.getName(), p.getX() + 5, p.getY() - 5);
			p.draw(g, sprites);
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
		//System.out.println(keyCode);
		if(keyCode == KeyEvent.VK_ESCAPE) {
			gsm.pop();
		} else if(keyCode == KeyEvent.VK_ENTER) {
			//System.out.println("GAME START");
		}

		if(keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
			gsm.player.move(0);
		}
		if(keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT){
			gsm.player.move(2);
		}
		if(keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN){
			gsm.player.move(1);
		}
		if(keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT){
			gsm.player.move(3);
		}
		//System.out.println(gsm.pudges.get(i).player.getName());
		
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
