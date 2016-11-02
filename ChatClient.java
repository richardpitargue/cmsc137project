import java.net.*;
import java.io.*;

public class ChatClient{

	public static void main(String [] args){
		try{

			String serverName = args[0]; 					//get IP address of server from first parameter
			Socket client = new Socket(serverName, 3000);	// open a client and connect to server
			
			try{
				// send data to the server
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
				PrintWriter out = new PrintWriter(client.getOutputStream());
				
				String msg = in.readLine(); 		// receives "Enter username:" from server
				System.out.println(msg);		

				String username = br.readLine();
				out.println(username);				
				out.flush();						// sends the username to the server

				msg = in.readLine();				// receives "Hello <username>" from server
				System.out.println(msg);

				Receiver rec = new Receiver(client);
				rec.start();
			    try{				 
					msg = br.readLine();
					while(msg.compareTo("EXIT") != 0){
		                out.println(msg); 			// sends message to server
		                out.flush(); 				// flush() so that the message gets sent through the network immediately
		                msg = br.readLine();
					}
			    }
			    catch(IOException e){
			        System.err.print("IO Exception");
			    }

			} catch (IOException e){
		        System.err.print("IO Exception");
		    }
		} catch(IOException e){
			System.out.println("Cannot find Server");
		} catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Usage: java ChatClient <server ip>");
		}
	}
	
}

class Receiver extends Thread{ // prints the message from other clients (from the server)
	Socket server;

	Receiver(Socket server){
		this.server = server;
	}

	public void run(){	
		try{
			BufferedReader in = new BufferedReader(new InputStreamReader(this.server.getInputStream()));

			while(true){
				String msg = in.readLine();
				System.out.println(msg);
			}
		}
		catch(Exception e){ }
	}
}