package game.client.gsm;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public abstract class State {
	
	protected GameStateManager gsm;
	
	public State(GameStateManager gsm) {
		this.gsm = gsm;
	}
	
	public abstract void onEnter();
	public abstract void update(double delta);
	public abstract void draw(Graphics2D g); 
	public abstract void onExit();
	public abstract void keyTyped(int keyCode);
	public abstract void keyPressed(int keyCode);
	public abstract void keyReleased(int keyCode);
	public abstract void mouseClicked(MouseEvent e);
	public abstract void mousePressed(MouseEvent e);
	public abstract void mouseReleased(MouseEvent e);
	public abstract void mouseEntered(MouseEvent e);
	public abstract void mouseExited(MouseEvent e);
	public abstract void mouseDragged(MouseEvent e);
	public abstract void mouseMoved(MouseEvent e);
	
}
