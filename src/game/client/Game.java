package game.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import chat.ChatArea;



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
	public static final String username = "Benny";
	
	public Game()
	{
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
		
		
		chatBox = new JTextArea(5,20);
		chatBox.setEditable(false);
		chatScroll = new JScrollPane(chatBox);
		chatScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		frame.add(chatScroll);
		chatScroll.setSize(WIDTH * SCALE,100);
		chatScroll.setLocation(0, HEIGHT * SCALE);
		
		chatArea = new ChatArea(1,20, out, username);
		
		chatArea.setSize(WIDTH * SCALE,50);
		chatArea.setLocation(0, HEIGHT * SCALE + 100);
		frame.add(chatScroll);
		frame.add(chatArea);
		
		new Receiver();
		
	}
	
	public static void main(String[] args) {
		new Game();
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

class Receiver implements Runnable{
	// TODO Auto-generated method stub
	public Receiver()
	{
		run();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

			while(true){
				String msg = in.readLine();
				System.out.println(msg);
				chatBox.append(msg + "\n");
				chatBox.setCaretPosition(chatBox.getDocument().getLength());
			}
		}
		catch(Exception e){ e.printStackTrace();}
	}
}	

}



