package game.client.gsm;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import game.client.GamePanel;
import game.client.Player;
import game.client.player.Pudge;

public class GameCreationState extends State {
	
	
	private BufferedImage connectedPlayersLabel, optionsLabel, bg;

	public GameCreationState(GameStateManager gsm) {
		super(gsm);
		
		for(int i = 0; i <gsm.players.size();i++) {
			gsm.pudges.add(new Pudge(gsm.players.get(i),50,50));
		}
		try {
			bg = ImageIO.read(getClass().getClassLoader().getResourceAsStream("backgound.png"));
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
			for(Pudge p : gsm.pudges)
				p.draw(g);
			
			
//			connectedPlayersLabel = ImageIO.read(getClass().getClassLoader().getResourceAsStream("connected_players.png"));
//			g.drawImage(connectedPlayersLabel, 50, 0, 250, 30, null);
//			
//			optionsLabel = ImageIO.read(getClass().getClassLoader().getResourceAsStream("options.png"));
//			g.drawImage(optionsLabel, 400, 0, 150, 30, null);
			
//			g.drawString("PRESS ENTER TO CONTINUE", 215, 300);
		
	}

	@Override
	public void onExit() {
		
	}

	@Override
	public void keyTyped(int keyCode) {
		
	}

	@Override
	public void keyPressed(int keyCode) {
		System.out.println(keyCode);
		for(int i = 0; i < gsm.pudges.size(); i++)
		{
			System.out.println(gsm.pudges.get(i).player.toString());
			System.out.println(gsm.player.toString());
			if(gsm.pudges.get(i).player.getName().compareTo(gsm.player.getName()) == 0)
			{
				if(keyCode == KeyEvent.VK_ESCAPE) {
					gsm.pop();
				} else if(keyCode == KeyEvent.VK_ENTER) {
					System.out.println("GAME START");
				}
				
				if(keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
					gsm.pudges.get(i).move(Pudge.UP, gsm.player);
				}
				if(keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT){
					gsm.pudges.get(i).move(Pudge.LEFT, gsm.player);
				}
				if(keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN){
					gsm.pudges.get(i).move(Pudge.DOWN, gsm.player);
				}
				if(keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT){
					gsm.pudges.get(i).move(Pudge.RIGHT, gsm.player);
				}
				System.out.println(gsm.pudges.get(i).player.getName());
				break;
			}
				
		}
		
	}

	@Override
	public void keyReleased(int keyCode) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Click position (X, Y):  " + e.getX() + ", " + e.getY());
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
