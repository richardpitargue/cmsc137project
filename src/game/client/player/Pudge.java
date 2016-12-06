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
	
	private double attackX;
	private double attackY;
	private double velX, velY;
	private double distX, distY;
	
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
			attackX += velX;
			attackY += velY;
			distX += Math.abs(velX);
			distY += Math.abs(velY);
			
			if(Math.hypot(distX, distY) >= 300) {
				attacking = false;
			}
		}
	}
		
	public void draw(Graphics2D g) {
		g.drawImage(sprite[direction][frame], x, y, width, height, null);
		if(attacking){
			g.drawImage(hook[direction], (int) attackX, (int) attackY, width, height, null);
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
		double moveSpeed = 4;
		
		attacking = true;
		maxAttackX = (x/2);
		maxAttackY = (y/2);
		distX = 0;
		distY = 0;
		
		
		double degrees  = Math.toDegrees(Math.atan2(maxAttackY - this.y, maxAttackX - this.x));
		
		if(degrees < 0){
	        degrees += 360;
	    }
		
		System.out.println(degrees + "DEGREEs");
		
		
		if(0 <= degrees && degrees < 45)
			this.move(Pudge.RIGHT);
		else if(45 <= degrees && degrees < 135)
			this.move(Pudge.DOWN);
		else if(135 <= degrees && degrees < 225)
			this.move(Pudge.LEFT);
		else if(225 <= degrees && degrees < 315)
			this.move(Pudge.UP);
		else if(315 <= degrees && degrees <= 360)
			this.move(Pudge.RIGHT);
		
		
		attackX = this.x;
		attackY = this.y;
		
		double a = (x/2) - attackX;
		double b = (y/2) - attackY;
		double angle = (float) java.lang.Math.hypot(a, b);
	    a /= angle;
	    b /= angle;
	    a *= moveSpeed;
	    b *= moveSpeed;
	    velX = a;
	    velY = b;
		
		//System.out.println("Click position (X, Y):  " + maxAttackX + ", " + maxAttackY);
		
		frame = (frame + 1) % 4;
	}
	
}
