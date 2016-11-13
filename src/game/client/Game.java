package game.client;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.ByteArrayInputStream;
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

public class Game{
//
//	public Game(GameStateManager gsm, String server, int port, int serverPort, String playerName) {
//		super(gsm);
//		this.player = new Player(playerName, null, port);
//		this.client = new Client(server,port,serverPort,this.player);
//	}
//	
//	
//
//	@Override
//	public void onEnter() {
//		// TODO Auto-generated method stub
//		//this.client.run();
//	}
//
//	@Override
//	public void update(double delta) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void draw(Graphics2D g) {
//		// TODO Auto-generated method stub
//
//		g.clearRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
//		if(client.players == null)
//		g.drawString("Welcome Client", 50, 10);
//		
//		if(client.players != null)
//		for(int i = 0; i < client.players.length; i++)
//		{
//			g.drawString(client.players[i].getName(), client.players[i].getX() + 50, client.players[i].getY() + 10);
//		}
//	}
//
//	@Override
//	public void onExit() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void keyTyped(int keyCode) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void keyPressed(int keyCode) {
//		// TODO Auto-generated method stub
//		System.out.println(keyCode);
//		
//		if(keyCode == KeyEvent.VK_W)
//			move("up");
//		if(keyCode == KeyEvent.VK_S)
//			move("down");
//		if(keyCode == KeyEvent.VK_A)
//			move("left");
//		if(keyCode == KeyEvent.VK_D)
//			move("right");
//	}
//
//	private void move(String move) {
//		// TODO Auto-generated method stub
//		
//		int i;
//		for(i = 0; i < client.players.length; i++)
//			if(client.players[i] == this.player)
//				break;
//		switch(move)
//		{
//		case "up":
//			client.players[i].setY(client.players[i].getY() - 1);
//			break;
//		case "down":
//			client.players[i].setY(client.players[i].getY() + 1);
//			break;
//		case "left":
//			client.players[i].setX(client.players[i].getX() - 1);
//			break;
//		case "right":
//			client.players[i].setX(client.players[i].getX() + 1);
//			break;
//		}
//		
//		System.out.println("Moved " + move);
//	}
//	@Override
//	public void keyReleased(int keyCode) {
//		// TODO Auto-generated method stub
//		
//	}
//	

	public static void main(String[] args) {
		JFrame frame = new JFrame("Call of Pudge: Modern Hookfare");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(new GamePanel("127.0.0.1", 1232, 1234));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
}
}
