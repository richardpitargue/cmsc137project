package game.client.gsm;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.Timer;

import game.client.GamePanel;
import game.client.Hook;
import game.client.Music;
import game.client.Player;

public class GameCreationState extends State {
	
	
	private BufferedImage bg;
	private BufferedImage sprites[][];
	private BufferedImage hookSprite[];
	private int team1,team2;
	private Music music;

	
	public GameCreationState(GameStateManager gsm) {
		super(gsm);
		
		this.music = new Music();
		music.background(true);
		music.gameBegins();
		
		sprites = new BufferedImage[4][4];
		hookSprite = new BufferedImage[4];
		team1 = 0;
		team2 = 0;
		gsm.tempPlayers = gsm.players;
		
		ActionListener updater = new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				gsm.player.update(gsm.players, gsm.channel, gsm.serverAddress);
			}
	
		};
		
		ActionListener musicPlayer = new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				for(int i = 0; i < gsm.players.size(); i++)
				{
					if((gsm.players.get(i).getX() != gsm.tempPlayers.get(i).getX() || gsm.players.get(i).getY() != gsm.tempPlayers.get(i).getY()) && (!gsm.players.get(i).isHooked))
						music.footStep();
					
					
					if(gsm.players.get(i).attackSound != -1 && gsm.tempPlayers.get(i).attackSound == -1)
						music.attack(gsm.players.get(i).attackSound);
					
					if(gsm.players.get(i).hitSound != -1 && gsm.tempPlayers.get(i).hitSound == -1)
						music.hit(gsm.players.get(i).hitSound);
					
					if(gsm.players.get(i).hookedOne != null && gsm.tempPlayers.get(i).hookedOne == null)
						music.hookHit();
				}
				
				gsm.tempPlayers = new ArrayList<Player>(gsm.players);
			}
	
		};

		
		Timer broadcaster = new Timer(20, updater);
		broadcaster.start();
		Timer musicPlayerTimer = new Timer(20, musicPlayer);
		musicPlayerTimer.start();
		
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
		team1 = 0;
		team2 = 0;
		for(Player p : gsm.players)
		{
			if(p.team)
				g.setColor(Color.BLUE);
			else
				g.setColor(Color.RED);
			g.setFont(new Font("default", Font.PLAIN, 12));
			g.drawString(p.getName(), p.getX() + 5, p.getY() - 5);
			p.draw(g, sprites);
			
			if(p.team)
				team1+=p.score;
			else
				team2+=p.score;
			
			if(team1 >= 20){
				music.background(false);
				gsm.changeState(new WinnerState(gsm, "BLUE"), false);
			} else if(team2 >= 20){
				music.background(false);
				gsm.changeState(new WinnerState(gsm, "RED"), false);
			}
		}
		
		g.setColor(Color.BLUE);
		g.setFont(new Font("default", Font.BOLD, 20));
		g.drawString(Integer.toString(team1), 120, 20);
		g.setColor(Color.RED);
		g.drawString(Integer.toString(team2), 470, 20);
		
		for(Hook h: gsm.hooks)
		{
			if(h.active)
				h.draw(g, hookSprite);
				
			
				
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
		
		Rectangle playerHitbox = new Rectangle(gsm.player.getX() + x, gsm.player.getY() + y, 10, 10);
		for(Player p : gsm.players)
		{
			if(p.equals(gsm.player)) continue;
			
			Rectangle player = new Rectangle(p.getX(), p.getY(), 10, 10);
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
		if(!gsm.player.attacking && !gsm.player.isHooked)
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
