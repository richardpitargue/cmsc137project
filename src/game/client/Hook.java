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
	public double totalD;
	private String name;
	public boolean active;
	private int direction;
	
	
	public Hook(Player p)
	{
		this.width = 50;
		this.height = 50;
		this.name = p.getName();
		this.active = p.getAttacking();
		this.x = p.getHookX();
		this.y = p.getHookY();
		this.direction = p.getDirection();
	}
	
	@Override
	public boolean equals(Object obj)
	{
		Hook h = (Hook) obj;
		if(name.compareTo(h.getName()) == 0 )
			return true;
		else
			return false;
	}
	public String getName()
	{
		return this.name;
	}
	public void draw(Graphics2D g, BufferedImage[] hookSprite)
	{
		g.drawImage(hookSprite[direction], (int)x, (int)y, width, height, null);
	}
	
	
	
}
