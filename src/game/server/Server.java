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

import game.client.Player;

public class Server implements Runnable {
	
	public DatagramSocket serverSocket = null;
	public ObjectInputStream inStream = null;
	public int playerCount;
	public int currentPlayers = 0;
	public Player[] players;

	public Server(int playerCount)
	{
		this.playerCount = playerCount;
		this.players = new Player[playerCount];
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub

			try {
				serverSocket = new DatagramSocket(1234);
				while(currentPlayers < playerCount)
				{
					System.out.println("Waiting");
					
					byte buf[] = new byte[256];
					DatagramPacket packet = new DatagramPacket(buf, buf.length);
					serverSocket.receive(packet);
					System.out.println("Connected");
					ObjectInputStream iStream = new ObjectInputStream(new ByteArrayInputStream(buf));
					Player player = (Player) iStream.readObject();
					System.out.println("Object received = " + player.toString());
					players[currentPlayers] = player;
					players[currentPlayers].changeAddress(packet.getAddress());
					currentPlayers++;
					
				}
				System.out.println("Players Connected");
			
				System.out.println("Game Start");
				while(true)
				{
					try{
					}catch(Exception e){}
					broadcast();
					byte buf[] = new byte[512];
					DatagramPacket packet = new DatagramPacket(buf, buf.length);
					serverSocket.receive(packet);
					ObjectInputStream iStream = new ObjectInputStream(new ByteArrayInputStream(buf));
					players = (Player[]) iStream.readObject();
					
					
				}
			
			
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	private void broadcast() {
		// TODO Auto-generated method stub
		try{
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			ObjectOutput oo = new ObjectOutputStream(bStream); 
			oo.writeObject(players);
			
			System.out.println("test2");
			byte[] buf = new byte[512]; 
			buf = bStream.toByteArray();
			for(int i = 0; i < currentPlayers; i++)
			{		
	        	DatagramPacket packet = new DatagramPacket(buf, buf.length, players[i].getAddress(), players[i].getPort());
	        	serverSocket.send(packet);
	        	System.out.println("sent2");   
	        	
			}
			System.out.println("Broadcasted");
			oo.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
