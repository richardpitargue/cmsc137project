package game.client.gsm;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;
import java.util.ArrayList;
import java.util.Stack;

import game.client.Player;
import game.client.Hook;

public class GameStateManager {
	
	private Stack<State> states;
	public ArrayList<Player> players;
	public ArrayList<Hook> hooks;
	public String server = new String();
	public int port;
	public int serverPort;
	public Player player;
	public DatagramChannel channel;
	public InetSocketAddress serverAddress;
	
	public GameStateManager() {
		states = new Stack<State>();
	}
	
	public void update(double delta) {
		states.peek().update(delta);
	}
	
	public void draw(Graphics2D g) {
		states.peek().draw(g);
	}
	
	public void pop() {
		states.peek().onExit();
		states.pop();
	}
	
	public void changeState(State nextState, boolean shouldPop) {
		if(states.size() > 0) {
			states.peek().onExit();
		}
		
		if(states.size() > 0 && shouldPop) {
			State poppedState = states.pop();
			poppedState.onExit();
		}
		
		states.push(nextState);
		states.peek().onEnter();
	}
	
	public void keyTyped(KeyEvent e) {
		states.peek().keyTyped(e);
	}

	public void keyPressed(KeyEvent e) {
		states.peek().keyPressed(e);
	}

	public void keyReleased(KeyEvent e) {
		states.peek().keyReleased(e);
	}
	
	public void mouseClicked(MouseEvent e) {
		states.peek().mouseClicked(e);
	}

	public void mousePressed(MouseEvent e) {
		states.peek().mousePressed(e);
	}

	public void mouseReleased(MouseEvent e) {
		states.peek().mouseReleased(e);
	}

	public void mouseEntered(MouseEvent e) {
		states.peek().mouseEntered(e);
	}

	public void mouseExited(MouseEvent e) {
		states.peek().mouseExited(e);
	}
	
	public void mouseDragged(MouseEvent e) {
		states.peek().mouseDragged(e);	
	}

	public void mouseMoved(MouseEvent e) {
		states.peek().mouseMoved(e);
	}
	
}
