package game.server;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import chat.ClientThread;
import game.client.Player;

public class Server implements Runnable {
	
	public ArrayList<Player> players;
	public List<Lobby> lobbies;
	public Boolean ingame = false;
	public int playerCount;
	private DatagramChannel channel;
	private static final int memorySize = 8192;
	private static final int serverPort = 1234;
	private ByteBuffer buf;
	private byte[] byt;
	private ObjectInputStream iStream;
	private ByteArrayOutputStream bStream;
	private ObjectOutput oo;
	private Object obj;
	private Vector<Socket> clientSockets;
	private Vector<String> clientNames;
	private ServerSocket tcpServer;
	

	public Server(int playerCount)
	{
		players = new ArrayList<Player>();
		lobbies = new ArrayList<Lobby>();
		this.playerCount = playerCount;
		try {
			channel = DatagramChannel.open();
			channel.socket().bind(new InetSocketAddress(serverPort));
			channel.configureBlocking(false);
			tcpServer = new ServerSocket(serverPort + 1);
			clientSockets = new Vector<Socket>();
			clientNames = new Vector<String>();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		while(true) {
			try {
				if(!ingame)
				{
					Socket client = tcpServer.accept();
		            System.out.println("New client established!");
		            ClientThread c = new ClientThread(client, clientSockets, clientNames);
				}
				buf = ByteBuffer.allocate(200);
				
				SocketAddress senderAddress = channel.receive(buf);
				if(senderAddress == null)
					continue;
				buf.clear();
				byte[] byt = new byte[buf.capacity()];
				buf.get(byt, 0, byt.length);
				
				
				iStream = new ObjectInputStream(new ByteArrayInputStream(byt));
				
				obj = iStream.readObject();
				
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
			bStream = new ByteArrayOutputStream();
			oo = new ObjectOutputStream(bStream); 
			oo.writeObject(players);
			oo.close();
			byt = new byte[512]; 
			byt= bStream.toByteArray();
	
			for(Player p : players) {
				channel.send(ByteBuffer.wrap(byt), p.getAddress());
			}
			//System.out.println("Broadcasted");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) {
		Server server = new Server(4);
		server.run();
	}

}
