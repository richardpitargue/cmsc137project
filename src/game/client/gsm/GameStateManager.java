package game.client.gsm;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.Stack;

public class GameStateManager {
	
	private Stack<State> states;
	
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
		states.peek().keyTyped(e.getKeyCode());
	}

	public void keyPressed(KeyEvent e) {
		states.peek().keyPressed(e.getKeyCode());
	}

	public void keyReleased(KeyEvent e) {
		states.peek().keyReleased(e.getKeyCode());
	}
	
}
