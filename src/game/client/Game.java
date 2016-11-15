package game.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import chat.ChatArea;
import chat.ChatBox;



public class Game{
	
	private static JTextArea chatBox;
	private static JScrollPane chatScroll;
	private static ChatArea chatArea;
	private static Socket client;
	static BufferedReader in;
	static PrintWriter out;
	public static final int WIDTH = 600;
	public static final int HEIGHT = 325;
	public static final int SCALE = 2;
	public static final String username = "Benny2";
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Call of Pudge: Modern Hookfare");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(WIDTH * SCALE, HEIGHT * SCALE + 150);
		frame.setLayout(null);
		GamePanel p = new GamePanel("127.0.0.1",username,1234);
		
		
		frame.add(p);
		connect();
		
		
		//frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		
		chatBox = new ChatBox(5,20,client);
		chatBox.setEditable(false);
		chatScroll = new JScrollPane(chatBox);
		chatBox.setSize(WIDTH * SCALE,100);
		chatBox.setLocation(0, HEIGHT * SCALE);
		
		chatArea = new ChatArea(1,20, out);
		
		chatArea.setSize(WIDTH * SCALE,50);
		chatArea.setLocation(0, HEIGHT * SCALE + 100);
		frame.add(chatBox);
		frame.add(chatArea);
		
		
	
	}
	
public static void connect()
{
	try {
		client = new Socket("127.0.0.1", 1235);
		out = new PrintWriter(client.getOutputStream());
		out.println(username);
		out.flush();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

	
}

