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
