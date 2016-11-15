package game.client;
import java.net.InetAddress;
import java.net.SocketAddress;

import javax.imageio.ImageIO;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

public class Player implements Serializable
{
	private static final long serialVersionUID = 45648976546542154L;
	private SocketAddress address;
	private String name;
	private int x,y;
	private static final int width = 50;
	private static final int height = 50;
	private int frame;
	private int direction;
	private boolean changed;
	
	public Player(String name, SocketAddress address)
	{
		this.name = name;
		this.address = address;
		this.x = 0;
		this.y = 0;
		this.frame = 0;
		this.direction = 0;
		this.changed = true;
	}
	
	public void setPlayer(Player player)
	{
		this.x = player.getX();
		this.y = player.getY();
		this.frame = player.getFrame();
		this.direction = player.getDirection();
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
		try{
		g.drawImage(sprites[direction][frame], x, y, width, height, null);
		}catch(Exception e){}
	}
	public void move(int direction) {

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
		setChanged(true);
	}
	
}
