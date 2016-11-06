package game.client.gsm;

import java.awt.Graphics2D;
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
	
	public void changeState(State nextState, boolean shouldPop) {
		if(states.size() > 0) {
			states.peek().onExit();
		}
		
		if(states.size() > 0 && shouldPop) {
			states.pop();
		}
		
		states.push(nextState);
		states.peek().onEnter();
	}
	
}
