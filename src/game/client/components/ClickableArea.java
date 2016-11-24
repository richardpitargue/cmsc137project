package game.client.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

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
	private boolean active;
	
	public ClickableArea(BufferedImage image, int x1, int y1, int width, int height) {
		this.x = x1;
		this.y = y1;
		this.width = width;
		this.height = height;
		this.image = image;
		this.animationColor = new Color(0f, 0f, 0f, 0.3f);
		this.active = false;
	}
	
	public ClickableArea(String path, int x1, int y1, int width, int height) {
		this.x = x1;
		this.y = y1;
		this.width = width;
		this.height = height;
		try {
			this.image = ImageIO.read(getClass().getClassLoader().getResource(path));
		} catch (IOException e) {
			System.err.println("File \"" + path + "\" is missing.");
		}
		this.animationColor = new Color(0f, 0f, 0f, 0.3f);
		this.active = false;
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
		
		if(!active) {
			g.setColor(new Color(0f, 0f, 0f, 0.75f));
			g.fillRect(x, y, width, height);
		}
		
//		if(animate && clicking) {
//			g.setColor(animationColor);
//			g.fillRect(x, y, width, height);
//		}
		
		g.setColor(Color.WHITE);
	}
	
	public boolean mouseClicked(MouseEvent e) {
		int x = e.getX()/2;
		int y = e.getY()/2;
		
		// if inside bounds of the button
		if(x >= this.x && x <= (this.x + width) && y >= this.y && y <= (this.y + height)) {
			return true;
		}
		
		return false;
	}

	public boolean mousePressed(MouseEvent e) {
		int x = e.getX()/2;
		int y = e.getY()/2;
		
		// if inside bounds of the button
		if(x >= this.x && x <= (this.x + width) && y >= this.y && y <= (this.y + height)) {
			clicking = true;
			return true;
		}
		
		return false;
	}

	public boolean mouseReleased(MouseEvent e) {
		int x = e.getX()/2;
		int y = e.getY()/2;
		
		// if inside bounds of the button
		if(x >= this.x && x <= (this.x + width) && y >= this.y && y <= (this.y + height)) {
			return true;
		}
		
		return false;
	}

	
	public void mouseMoved(MouseEvent e) {
		int x = e.getX()/2;
		int y = e.getY()/2;
		
		// if inside bounds of the button
		if(x >= this.x && x <= (this.x + width) && y >= this.y && y <= (this.y + height)) {
			active = true;
			Game.getGamePanel().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		} else {
			active = false;
//			Game.getGamePanel().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
	}
	
}
