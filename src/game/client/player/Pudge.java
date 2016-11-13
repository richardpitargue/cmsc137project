package game.client.player;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Pudge {
	
	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;

	private int x;
	private int y;
	private int width;
	private int height;
	private int frame;
	private int direction;
	
	private int attackX;
	private int attackY;
	private int maxAttackX;
	private int maxAttackY;
	private boolean attacking;
	
	private BufferedImage[][] sprite;
	private BufferedImage[] hook;
	
	public Pudge(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.frame = 0;
		this.direction = 0;
		this.attacking = false;
		
		//load image here
		sprite = new BufferedImage[4][4];
		hook = new BufferedImage[4];
		try {
			for(int i = 0; i < 4; i++ )
				hook[i] = ImageIO.read(getClass().getClassLoader().getResourceAsStream("hook_" + i + ".png"));
			
			for(int i = 0;  i < 4; i++)
				for(int j = 0; j < 4; j++)
					sprite[i][j] = ImageIO.read(getClass().getClassLoader().getResourceAsStream("sprite_" + i + "_" + j + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		if(attacking) {
			if(maxAttackX-this.x > 0) {
				attackX += 5;
			} else {
				attackX -= 5;
			}
			
			if(maxAttackY-this.y > 0) {
				attackY += 5;
			} else {
				attackY -= 5;
			}
			
			if(attackX >= maxAttackX && attackY >= maxAttackY) {
				attacking = false;
			}
		}
	}
		
	public void draw(Graphics2D g) {
		g.drawImage(sprite[direction][frame], x, y, width, height, null);
		if(attacking){
			g.drawImage(hook[direction], attackX, attackY, width, height, null);
		}
	}
	
	public void move(int direction) {
		
		switch(direction) {
			case UP:
				this.direction = 0;
				if(y!=0)
					y -= 5;
				break;
			case DOWN:
				this.direction = 2;
				if(y!=275)
					y += 5;
				break;
			case LEFT:
				this.direction = 3;
				if(x != 0 && x != 340)
					x -= 5;
				break;
			case RIGHT:
				this.direction = 1;
				if(x != 550 && x != 210)
					x += 5;
				break;
		}
		frame = (frame + 1) % 4;
	}
	
	public void attack(int x, int y) {
		attacking = true;
		maxAttackX = (x/2);
		maxAttackY = (y/2);
		attackX = this.x;
		attackY = this.y;
		
		System.out.println("Click position (X, Y):  " + maxAttackX + ", " + maxAttackY);
		
		frame = (frame + 1) % 4;
	}
	
}
