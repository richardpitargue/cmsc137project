package game.client;
import java.net.InetAddress;
import java.io.Serializable;

public class Player implements Serializable
{
	private static final long serialVersionUID = 45648976546542154L;
	private InetAddress address;
	private int port;
	private String name;
	private int x,y;
	private int frame;
	
	public Player(String name, InetAddress address, int port)
	{
		this.name = name;
		this.address = address;
		this.port = port;
		this.x = 0;
		this.y = 0;
	}
	
	public InetAddress getAddress()
	{
		return this.address;
	}
	
	public int getPort()
	{
		return this.port;
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
	public int getFrame()
	{
		return this.frame;
	}
	public void setFrame(int frame)
	{
		this.frame = frame;
	}
	public void changeAddress(InetAddress address)
	{
		this.address = address;
	}
	public String print()
	{
		return(name + " "+ x + " " + y + " " + address + " " + port);
	}
	
	public boolean equals(Object player) {
		if(player == null) {
			return false;
		}
		
		if(!(player instanceof Player)) {
			return false;
		}
		
		Player p2 = (Player) player;
		
		if(p2.address != address) {
			return false;
		} else if(p2.port != port) {
			return false;
		}
		
		return true;
	}
	
}
