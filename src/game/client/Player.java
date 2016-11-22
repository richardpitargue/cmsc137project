package game.client;
import java.net.SocketAddress;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Player implements Serializable
{
	private static final long serialVersionUID = 45648976546542154L;
	private SocketAddress address;
	private String name;
	private int x,y;
	private double hookX, hookY;
	private double hookDistX, hookDistY;
	private double hookVelX, hookVelY;
	private double hookTotalD;
	private static final int width = 50;
	private static final int height = 50;
	private int frame;
	private int direction;
	private boolean changed;
	public boolean attacking;
	
	public Player(String name, SocketAddress address)
	{
		this.name = name;
		this.address = address;
		this.x = 110;
		this.y = 110;
		this.frame = 0;
		this.direction = 0;
		this.changed = true;
		this.hookX = 0;
		this.hookY = 0;
		this.hookDistX = 0;
		this.hookDistY = 0;
		this.hookVelX = 0;
		this.hookVelY = 0;
		this.attacking = false;
	}
	
	public void setPlayer(Player player)
	{
		this.x = player.getX();
		this.y = player.getY();
		this.frame = player.getFrame();
		this.direction = player.getDirection();
		this.attacking = player.getAttacking();
		this.hookX = player.getHookX();
		this.hookY = player.getHookY();
		this.hookDistX = player.getHookDistX();
		this.hookDistY = player.getHookDistY();
		this.hookVelX = player.getHookVelX();
		this.hookVelY = player.getHookVelY();
	}
	public double getHookTotalD()
	{
		return this.hookTotalD;
	}
	public boolean getAttacking()
	{
		return this.attacking;
	}
	public double getHookVelX()
	{
		return this.hookVelX;
	}
	public double getHookVelY()
	{
		return this.hookVelY;
	}
	public double getHookDistX()
	{
		return this.hookDistX;
	}
	public double getHookDistY()
	{
		return this.hookDistY;
	}
	public double getHookX()
	{
		return this.hookX;
	}
	public double getHookY()
	{
		return this.hookY;
	}
	public void setAttacking(boolean attacking)
	{
		this.attacking = attacking;
	}
	
	public void setChanged(boolean val)
	{
		this.changed = val;
	}
	public boolean getChanged()
	{
		return changed;
	}
	public SocketAddress getAddress()
	{
		return this.address;
	}
	public void setFrame(int frame)
	{
		this.frame = frame;
	}
	public void setDireciton(int direction)
	{
		this.direction = direction;
	}
	public int getFrame()
	{
		return this.frame;
	}
	public int getDirection()
	{
		return this.direction;
	}
	public String getName()
	{
		return this.name;
	}
	public int getX()
	{
		return this.x;
	}
	public int getY()
	{
		return this.y;
	}
	public void setX(int x)
	{
		this.x = x;
	}
	public void setY(int y)
	{
		this.y = y;
	}
	
	public void changeAddress(SocketAddress address)
	{
		this.address = address;
	}
	
	public boolean equals(Object player) {
		
		Player p2 = (Player) player;
		if(name.compareTo(p2.getName()) == 0)
			return true;
		else
			return false;
		
	}
	
	public void draw(Graphics2D g, BufferedImage[][] sprites) {
	
		g.drawImage(sprites[direction][frame], x, y, width, height, null);
		

	}
	
	public void move(int direction) {
		
		if(!attacking)
		{
			switch(direction) {
				case 0:
					this.direction = 0;
					if(y!=0)
						y-=5;
					break;
				case 1:
					this.direction = 2;
					if(y!=275)
						y+=5;
					break;
				case 2:
					this.direction = 3;
					if(x != 0 && x != 340)
						x-=5;
					break;
				case 3:
					this.direction = 1;
					if(x != 550 && x != 210)
						x+=5;
					break;
			}
			frame = (frame + 1) % 4;
			changed = true;
		}
	}
	
	public void update()
	{
		if(attacking)
		{
			hookX += hookVelX;
			hookY += hookVelY;
			hookDistX += Math.abs(hookVelX);
			hookDistY += Math.abs(hookVelY);
			
			hookTotalD = Math.hypot(hookDistX, hookDistY);
			if( hookTotalD >= 300)
			{
				attacking = false;
				changed = true;
			}
			
			
			changed = true;
		}
		
		
		
	}
	
	public void attack(int mouseX, int mouseY)
	{
		if(!attacking)
		{
			
			double degrees = Math.toDegrees(Math.atan2(mouseY/2 - y,mouseX/2 - x));
			
			if(degrees < 0)
				degrees += 360;
			
			if(0 <= degrees && degrees < 45)
				direction = 1;
			else if(45 <= degrees && degrees < 135)
				direction = 2;
			else if(135 <= degrees && degrees < 225)
				direction = 3;
			else if(225 <= degrees && degrees < 315)
				direction = 0;
			else if(315 <= degrees && degrees <= 360)
				direction = 1;
			
			System.out.println("Attacking");
			
			
			hookX = x;
			hookY = y;
			
			hookDistX = 0;
			hookDistY = 0;
			
			hookVelX = (mouseX/2) - x;
			hookVelY = (mouseY/2) - y;
			
			double hypot = Math.hypot(hookVelX, hookVelY);
			
			hookVelX /= hypot;
			hookVelY /= hypot;
			
			hookVelX *= 4;
			hookVelY *= 4;
			
			attacking = true;
			changed = true;
		}
	}
	
}