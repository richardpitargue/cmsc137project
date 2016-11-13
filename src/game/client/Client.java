package game.client;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Client implements Runnable {

	private DatagramSocket serverSocket = null;
	private boolean isConnected = false;
	public Player player;
	public Player[] players;
	private int port;
	private int serverPort;
	private String server;
	
	public Client(String server, int port, int serverPort, Player player)
	{
		this.server = server;
		this.port = port;
		this.serverPort = serverPort;
		this.player = player;
	}
	public void send()
	{
		try{
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			ObjectOutput oo = new ObjectOutputStream(bStream); 
			oo.writeObject(this.player);
			oo.close();
			System.out.println("test");
			byte[] buf = new byte[256]; 
			buf = bStream.toByteArray();
        	InetAddress address = InetAddress.getByName(server);
        	
        	DatagramPacket packet = new DatagramPacket(buf, buf.length, address, serverPort);
        	serverSocket.send(packet);
        	System.out.println("sent");
        }catch(Exception e){e.printStackTrace();}
	}
	@Override
	public void run() {
		while(true)
		{
			while(!isConnected)
			{
			
				
				try {
					serverSocket = new DatagramSocket(port);
					System.out.println("Connected");
					isConnected = true;
					send();
				} catch (SocketException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
					
			}
			System.out.println("I'm Connected");
			while(isConnected)
			{
				
				try
				{
					byte buf[] = new byte[512];
					DatagramPacket packet = new DatagramPacket(buf, buf.length);
					serverSocket.receive(packet);
					System.out.println("Update!");
					ObjectInputStream iStream = new ObjectInputStream(new ByteArrayInputStream(buf));
					players = (Player[]) iStream.readObject();
					System.out.println(players.length);
					
					ByteArrayOutputStream bStream = new ByteArrayOutputStream();
					ObjectOutput oo = new ObjectOutputStream(bStream); 
					oo.writeObject(players);
					oo.close();
					buf = new byte[256]; 
					buf = bStream.toByteArray();
		        	InetAddress address = InetAddress.getByName(server);
		        	
		        	packet = new DatagramPacket(buf, buf.length, address, serverPort);
		        	serverSocket.send(packet);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			
		
		}
		// TODO Auto-generated method stub
		
	}

}
