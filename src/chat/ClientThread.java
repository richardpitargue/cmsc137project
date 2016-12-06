package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;

public class ClientThread extends Thread{

	Socket client;
	String username;
	String msg;
	BufferedReader in;
	PrintWriter out;
	Vector<Socket> clientSockets;
	Vector<String> clientNames;

	public ClientThread(Socket client, Vector<Socket> clientSockets, Vector<String> clientNames){
		try{
			this.client = client;
			this.clientSockets = clientSockets;
			this.clientNames = clientNames;
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			out = new PrintWriter(client.getOutputStream());

			this.username = in.readLine();			
							
			clientSockets.add(client);
			clientNames.add(this.username);
			start();
		}
		catch(Exception e){
			e.printStackTrace();
		}
   	}

   public void run(){
		try{
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			while(true){
				msg = in.readLine();
				for(Socket s:clientSockets){

						out = new PrintWriter(s.getOutputStream());
						out.println(msg);
						out.flush(); // flush() so that the message gets sent through the network immediately
						System.out.println("Broadcasted! " + msg + " to: " + s.toString());
					
				}
			}   
		} catch(IOException e){
			System.out.println("IO Exception");
		} catch(NullPointerException np){
			System.out.println(username + " connection's closed");
		} finally{
			try{
				client.close();
			} catch(Exception e){ }
		}
   }

}