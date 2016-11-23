package game.client.gsm;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.client.GamePanel;
import game.client.Hook;
import game.client.Player;

public class GameCreationState extends State {
	
	
	private BufferedImage bg;
	private BufferedImage sprites[][];
	private BufferedImage hookSprite[];

	
	public GameCreationState(GameStateManager gsm) {
		super(gsm);
		
		sprites = new BufferedImage[4][4];
		hookSprite = new BufferedImage[4];
		try {
			bg = ImageIO.read(getClass().getClassLoader().getResourceAsStream("backgound.png"));
			for(int i = 0;  i < 4; i++)
				for(int j = 0; j < 4; j++)
					sprites[i][j] = ImageIO.read(getClass().getClassLoader().getResourceAsStream("sprite_" + i + "_" + j + ".png"));
			
			for(int i = 0; i < 4; i++)
				hookSprite[i] = ImageIO.read(getClass().getClassLoader().getResourceAsStream("hook_" + i + ".png"));
			
			
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
		for(Hook h: gsm.hooks)
		{
			if(h.active)
				h.draw(g, hookSprite);
				
			if(gsm.player.getName().compareTo(h.getName()) == 0)
				gsm.player.update(gsm.players, gsm.channel, gsm.serverAddress);
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
			if(!playerCollision(0))
			gsm.player.move(0);
		}
		if(keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT){
			if(!playerCollision(2))
			gsm.player.move(2);
		}
		if(keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN){
			if(!playerCollision(1))
			gsm.player.move(1);
		}
		if(keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT){
			if(!playerCollision(3))
			gsm.player.move(3);
		}
		//System.out.println(gsm.pudges.get(i).player.getName());	

	}
	
	public boolean playerCollision(int direction){
		int x = 0, y = 0;
		switch(direction){
			case 0: y = -10;
				break;
			case 1: y = 10;
				break;
			case 2: x = -10;
				break;
			case 3: x = 10;
				break;
		}
		
		Rectangle playerHitbox = new Rectangle(gsm.player.getX() + x, gsm.player.getY() + y, 50, 50);
		for(Player p : gsm.players)
		{
			if(p.equals(gsm.player)) continue;
			
			Rectangle player = new Rectangle(p.getX(), p.getY(), 50, 50);
			if(playerHitbox.intersects(player)){
				return true;
			}
			
		}
		
		return false;
	}
	

	@Override
	public void keyReleased(int keyCode) {
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println(e.getX());
		if(!gsm.player.attacking)
		{
			gsm.player.attack(e.getX(), e.getY());
			//hookPlayer();
		}
	}
	
//	public void hookPlayer(){
//		if(gsm.player.attacking){
//			Rectangle playerHitbox = new Rectangle(gsm.player.getX(), gsm.player.getY(), 50, 50);
//			
//			for(Hook h: gsm.hooks)
//			{
//				if(h.equals(gsm.hooks)) continue;
//				
//				Rectangle hook = new Rectangle((int) h.x, (int) h.y, 50, 50);
//				if(hook.intersects(playerHitbox)){
//					System.out.println("HOOKED");
//				}
//			}
//		}
//	}
	
	
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
