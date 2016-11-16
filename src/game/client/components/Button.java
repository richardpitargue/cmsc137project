package game.client.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import game.client.Game;

public class Button {
	
	public final int x;
	public final int y;
	public final int width;
	public final int height;
	
	private BufferedImage image;
	private String text;
	private boolean clicking = false;

	public Button(int x1, int y1, int width, int height) {
		this.x = x1;
		this.y = y1;
		this.width = width;
		this.height = height;
		this.image = null;
		this.text = null;
	}
	
	public Button(BufferedImage image, int x1, int y1, int width, int height) {
		this.x = x1;
		this.y = y1;
		this.width = width;
		this.height = height;
		this.image = image;
		this.text = null;
	}
	
	public Button(String text, int x1, int y1, int width, int height) {
		this.x = x1;
		this.y = y1;
		this.width = width;
		this.height = height;
		this.image = null;
		this.text = text;
	}
	
	public void draw(Graphics2D g) {
		if(!clicking) {
			g.clearRect(x, y, width, height);
		}
		
		if(image != null) {
			g.drawImage(image, x, y, null);
		} else {
			g.setColor(Color.GRAY);
			g.fillRect(x, y, width, height);
			g.setColor(Color.BLACK);
			g.drawRect(x, y, width, height);
			g.setColor(Color.WHITE);
			
			if(text != null) {
				int tx = width / 2 - (text.length() / 2);
				int ty = height / 2;
				g.drawString(text, x + tx, y + ty);
			}
		}
		
		if(clicking) {
			g.setColor(new Color(0f, 0f, 0f, 0.3f));
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
