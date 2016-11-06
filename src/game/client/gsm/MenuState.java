package game.client.gsm;

import java.awt.Graphics2D;

public class MenuState extends State {
	
	public MenuState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void onEnter() {
		
	}

	@Override
	public void update(double delta) {
		
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawString("PRESS [ENTER] TO CONTINUE", 50, 50);
	}

	@Override
	public void onExit() {
		
	}

}
