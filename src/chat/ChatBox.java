package chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JTextArea;

public class ChatBox extends JTextArea {
	
	Socket client;
	
	public ChatBox(int rows, int cols, Socket client)
	{
		super(rows,cols);
		this.client = client;
		Receiver r = new Receiver();
		r.run();
	}

	
	class Receiver implements Runnable{
		// TODO Auto-generated method stub
		public Receiver()
		{
			
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try{
				BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

				while(true){
					String msg = in.readLine();
					System.out.println(msg);
					append(msg + "\n");
				}
			}
			catch(Exception e){ e.printStackTrace();}
		}
	}
	
	

}
