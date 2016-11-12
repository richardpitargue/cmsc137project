package game.client;

import java.awt.Graphics2D;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JFrame;

import game.client.gsm.GameStateManager;
import game.client.gsm.State;

public class Game extends State implements Runnable{
	

	private DatagramSocket serverSocket = null;
	private boolean isConnected = false;
	private Player player;
	private Player[] players;
	private int port = 1234;
	private String server;

	
	public Game(GameStateManager gsm, String server) {
		super(gsm);
		this.server = server;
		
		// TODO Auto-generated constructor stub
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
        	DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
        	serverSocket.send(packet);
        	System.out.println("sent");
        }catch(Exception e){}
	}
	@Override
	public void run() {
		while(true)
		{
			while(!isConnected)
			{
			
				
				try {
					player = new Player("player",InetAddress.getByName(server),port);
					System.out.println("Connected");
					isConnected = true;
					send();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
					
			}
			System.out.println("I'm Connected");
			while(isConnected)
			{
				
			}
			
		
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEnter() {
		// TODO Auto-generated method stub
		run();
	}

	@Override
	public void update(double delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		g.clearRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		g.drawString("Welcome Client", 50, 10);
	}

	@Override
	public void onExit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(int keyCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(int keyCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(int keyCode) {
		// TODO Auto-generated method stub
		
	}
	

	public static void main(String[] args) {
		JFrame frame = new JFrame("Call of Pudge: Modern Hookfare");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(new GamePanel());
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
}
}
