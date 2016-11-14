package game.server;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.ArrayList;
import java.util.List;

import game.client.Player;

public class Server implements Runnable {
	
	public ObjectInputStream inStream = null;
	public ArrayList<Player> players;
	public List<Lobby> lobbies;
	public Boolean ingame = false;
	public int playerCount;
	private DatagramChannel channel;
	private static final int memorySize = 8192;
	private static final int serverPort = 1234;

	public Server(int playerCount)
	{
		players = new ArrayList<Player>();
		lobbies = new ArrayList<Lobby>();
		this.playerCount = playerCount;
		try {
			channel = DatagramChannel.open();
			channel.socket().bind(new InetSocketAddress(serverPort));
			channel.configureBlocking(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		while(true) {
			try {
				ByteBuffer buf = ByteBuffer.allocate(memorySize);
				
				SocketAddress senderAddress = channel.receive(buf);
				if(senderAddress == null)
					continue;
				buf.clear();
				byte[] byt = new byte[buf.capacity()];
				buf.get(byt, 0, byt.length);
				
				
				ObjectInputStream iStream = new ObjectInputStream(new ByteArrayInputStream(byt));
				
				Object obj = iStream.readObject();
				
				if(obj instanceof Lobby) {
					Lobby lobby = (Lobby) obj;
					lobbies.add(lobby);
				}
				
				if(obj instanceof Player && !ingame) {
					Player player = (Player) obj;
					if(players.contains(player))
						continue;
					for(Player p: players)
					{
						System.out.println(p.equals(player));
						if(p.equals(player))
							continue;
					}
					player.changeAddress(senderAddress);
					players.add(player);
					
					if(players.size() == playerCount)
					{
						ingame = true;
						broadcast();
						continue;
					}	
				}
				
				if(ingame)
				{
					Player player = (Player) obj;
					
					for(Player p: players)
					{
						if(p.equals(player))
						{
							p.setPlayer(player);
						}
					}
					broadcast();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private void broadcast() {
		// TODO Auto-generated method stub
		try{
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			ObjectOutput oo = new ObjectOutputStream(bStream); 
			oo.writeObject(players);
			oo.close();
			byte[] buf = new byte[512]; 
			buf = bStream.toByteArray();
	
			for(Player p : players) {
				System.out.println(p.getName() + " " + p.getX() + " " + p.getY());
				channel.send(ByteBuffer.wrap(buf), p.getAddress());
			}
			//System.out.println("Broadcasted");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		Server server = new Server(2);
		server.run();
	}

}
