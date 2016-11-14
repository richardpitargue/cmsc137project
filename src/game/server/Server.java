package game.server;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import game.client.Player;

public class Server implements Runnable {
	
	public DatagramSocket serverSocket = null;
	public ObjectInputStream inStream = null;
	public ArrayList<Player> players;
	public List<Lobby> lobbies;
	public Boolean ingame = false;
	public int playerCount;

	public Server(int playerCount)
	{
		players = new ArrayList<Player>();
		lobbies = new ArrayList<Lobby>();
		this.playerCount = playerCount;
		try {
			serverSocket = new DatagramSocket(1234);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
//		try {
//			serverSocket = new DatagramSocket(1234);
//			while(currentPlayers < playerCount)
//			{
//				System.out.println("Waiting");
//				
//				byte buf[] = new byte[256];
//				DatagramPacket packet = new DatagramPacket(buf, buf.length);
//				serverSocket.receive(packet);
//				System.out.println("Connected");
//				ObjectInputStream iStream = new ObjectInputStream(new ByteArrayInputStream(buf));
//				Player player = (Player) iStream.readObject();
//				System.out.println("Object received = " + player.toString());
//				players[currentPlayers] = player;
//				players[currentPlayers].changeAddress(packet.getAddress());
//				currentPlayers++;
//				
//			}
//			System.out.println("Players Connected");
//		
//			System.out.println("Game Start");
//			while(true)
//			{
//				try{
//				}catch(Exception e){}
//				broadcast();
//				byte buf[] = new byte[512];
//				DatagramPacket packet = new DatagramPacket(buf, buf.length);
//				serverSocket.receive(packet);
//				ObjectInputStream iStream = new ObjectInputStream(new ByteArrayInputStream(buf));
//				players = (Player[]) iStream.readObject();
//				
//				
//			}
//		
//		
//		} catch (IOException | ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		while(true) {
			try {
				byte buf[] = new byte[512];
				DatagramPacket packet = new DatagramPacket(buf, buf.length);
				serverSocket.receive(packet);
				
				ObjectInputStream iStream = new ObjectInputStream(new ByteArrayInputStream(buf));
				
				Object obj = iStream.readObject();
				
				if(obj instanceof Lobby) {
					Lobby lobby = (Lobby) obj;
					//System.out.println("Object received = " + lobby.toString());
					lobbies.add(lobby);
				}
				
				if(obj instanceof Player && !ingame) {
					Player player = (Player) obj;
					//System.out.println("Object received = " + player.toString());
					player.changeAddress(packet.getAddress());
					players.add(player);
					
					//System.out.println("Current players connected: ");
					
					
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
//			System.out.println("test2");
			byte[] buf = new byte[512]; 
			buf = bStream.toByteArray();
//			for(int i = 0; i < currentPlayers; i++)
//			{		
//	        	DatagramPacket packet = new DatagramPacket(buf, buf.length, players[i].getAddress(), players[i].getPort());
//	        	serverSocket.send(packet);
////	        	System.out.println("sent2");   
//	        	
//			}
			for(Player p : players) {
				DatagramPacket packet = new DatagramPacket(buf, buf.length, p.getAddress(), p.getPort());
				System.out.println(p.getName() + " " + p.getX() + " " + p.getY());
	        	serverSocket.send(packet);
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
