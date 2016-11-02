import java.net.*;
import java.io.*;
import java.util.*;

class ChatServer{
	static Vector<Socket> clientSockets;
	static Vector<String> clientNames;

	ChatServer() throws Exception{
		ServerSocket server = new ServerSocket(3000);
		clientSockets = new Vector<Socket>();
		clientNames = new Vector<String>();
			
			while(true){
				try{
		            Socket client = server.accept();
		            System.out.println("New client established!");
		            ClientThread c = new ClientThread(client);
		        } catch(Exception e){
		            System.out.println("Connection Error");
		        }
			}
	}

   public static void main(String [] args) throws Exception{
	  ChatServer chat = new ChatServer();
   }


	class ClientThread extends Thread{

		Socket client;
		String username;
		String msg;
		BufferedReader in;
		PrintWriter out;

		public ClientThread(Socket client){
			try{
				this.client = client;
				in = new BufferedReader(new InputStreamReader(client.getInputStream()));
				out = new PrintWriter(client.getOutputStream());

				out.println("Enter your Username: ");	// asks the username of the client
				out.flush();

				this.username = in.readLine();			// recieves the username from the client
				out.println("Hello " + this.username);
				out.flush();							// sends a greeting to the client

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
				msg = in.readLine();
				while(msg.compareTo("EXIT")!=0){

					for(int i = 0; i < clientSockets.size(); i++){
						if(clientNames.elementAt(i).compareTo(this.username) != 0){
							in = new BufferedReader(new InputStreamReader(client.getInputStream()));
							out = new PrintWriter( ((Socket)clientSockets.elementAt(i)).getOutputStream());
							out.println(this.username + ": " + msg);
							out.flush(); // flush() so that the message gets sent through the network immediately
						}
					}
		
					
					msg = in.readLine();
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

}

