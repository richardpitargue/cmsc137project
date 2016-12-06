package game.client.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import game.client.Game;

public class ClickableArea {
	
	public final int x;
	public final int y;
	public final int width;
	public final int height;
	
	private BufferedImage image;
	private boolean clicking = false;
	private boolean animate = false;
	private Color animationColor;
	
	public ClickableArea(BufferedImage image, int x1, int y1, int width, int height) {
		this.x = x1;
		this.y = y1;
		this.width = width;
		this.height = height;
		this.image = image;
		this.animationColor = new Color(0f, 0f, 0f, 0.3f);
	}
	
	public void setAnimation(boolean animate) {
		this.animate = animate;
	}
	
	public void setAnimationColor(Color animationColor) {
		this.animationColor = animationColor;
	}
	
	public void draw(Graphics2D g) {
		if(image != null) {
			g.drawImage(image, x, y, null);
		}
		
		if(animate && clicking) {
			g.setColor(animationColor);
			g.fillRect(x, y, width, height);
		}
		
		g.setColor(Color.WHITE);
	}
	
	public void mouseClicked(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		int x = e.getX()/2;
		int y = e.getY()/2;
		
		// if inside bounds of the button
		if(x >= this.x && x <= (this.x + width) && y >= this.y && y <= (this.y + height)) {
			clicking = true;
		}
	}

	public void mouseReleased(MouseEvent e) {
		int x = e.getX()/2;
		int y = e.getY()/2;
		
		// if inside bounds of the button
		if(x >= this.x && x <= (this.x + width) && y >= this.y && y <= (this.y + height)) {
			clicking = false;
		}
	}

	
	public void mouseMoved(MouseEvent e) {
		int x = e.getX()/2;
		int y = e.getY()/2;
		
		// if inside bounds of the button
		if(x >= this.x && x <= (this.x + width) && y >= this.y && y <= (this.y + height)) {
			Game.getGamePanel().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		} else {
			Game.getGamePanel().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
	}
	
}
