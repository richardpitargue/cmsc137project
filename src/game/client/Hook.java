package game.client;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Hook implements Serializable{

	private static final long serialVersionUID = 45648976546542154L;
	private int width;
	private int height;
	public double x;
	public double y;
	private double distX;
	private double distY;
	public double totalD;
	private double velX;
	private double velY;
	private double moveSpeed = 4;
	public boolean active;
	
	
	public Hook(int width, int height)
	{
		this.width = width;
		this.height = height;
		this.active = false;
		this.x = 0;
		this.y = 0;
	}
	
	public void draw(Graphics2D g, BufferedImage hookSprite)
	{
		if(active)
		{
			g.drawImage(hookSprite,(int) x, (int) y, width,height, null);
			update();
		}
	}
	public void update()
	{
		if(active)
		{
			x += velX;
			y += velY;
			distX += Math.abs(velX);
			distY += Math.abs(velY);
			
			totalD = Math.hypot(distX, distY);
			if( totalD >= 300)
			{
				active = false;
				Player.attacking = false;
			}
			
			Player.setChanged(true);
		}
		
	}
	
	public boolean attack(int x, int y, int playerX, int playerY)
	{
		this.x = playerX;
		this.y = playerY;
		active = true;
		distX = 0;
		distY = 0;
		
		velX = (x/2) - playerX;
		velY = (y/2) - playerY;
		
		double hypot = Math.hypot(velX, velY);
		
		velX /= hypot;
		velY /= hypot;
		
		velX *= moveSpeed;
		velY *= moveSpeed;
		
		return true;
	}
	
}
