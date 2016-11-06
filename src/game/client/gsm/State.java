package game.client.gsm;

import java.awt.Graphics2D;

public abstract class State {
	
	protected GameStateManager gsm;
	
	public State(GameStateManager gsm) {
		this.gsm = gsm;
	}
	
	public abstract void onEnter();
	public abstract void update(double delta);
	public abstract void draw(Graphics2D g); 
	public abstract void onExit();
	
}
