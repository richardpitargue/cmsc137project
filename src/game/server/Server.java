package game.server;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

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
		while(true)
		{
			try {
				DatagramSocket serverSocket = new DatagramSocket(1234);
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
					players[currentPlayers++] = player;
				}
				System.out.println("Players Connected");
			
			
			
			
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
