package game.client.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import game.client.Game;

public class TextArea {
	
	public static final int MAX_DEFAULT = 80;
	
	public final int x;
	public final int y;
	public final int width;
	public final int height;
	
	private Color borderColor;
	private int borderWidth;
	private Color backgroundColor;
	private Color textColor;
	
	private String content = "";
	private BufferedImage image;
	private boolean hasFocus = false;
	
	public TextArea(int x, int y, int width, int height, Color borderColor, int borderWidth, Color backgroundColor, Color textColor) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.borderColor = borderColor;
		this.borderWidth = borderWidth;
		this.backgroundColor = backgroundColor;
		this.textColor = textColor;
		this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	}
	
	public void setFocus(boolean hasFocus) {
		this.hasFocus = hasFocus;
	}
	
	public boolean hasFocus() {
		return hasFocus;
	}
	
	public String getText() {
		return content;
	}
	
	public void update() {
		
	}
	
	public void draw(Graphics2D g) {
		Graphics g2 = image.getGraphics();
		g2.setColor(backgroundColor);
		g2.fillRect(0, 0, width, height);
		
		if(borderColor != null && borderWidth > 0) {
			g2.setColor(borderColor);
			for(int i = 0; i < borderWidth; i++) {
				g2.drawRect(i, i, (width-i*2), (height-i*2));
			}
		}
		
		g2.setColor(textColor);
		g2.drawString(content, 10, 10);
		g2.dispose();
		
		g.drawImage(image, x, y, null);
	}

	public void keyTyped(KeyEvent e) {
		
	}

	public void keyPressed(KeyEvent e) {
		
		if(!hasFocus) return;
		
		int keyCode = e.getKeyCode();
		
		if(content.length() < MAX_DEFAULT) {
			if(keyCode >= KeyEvent.VK_A && keyCode <= KeyEvent.VK_Z) {
				content += e.isShiftDown() ? KeyEvent.getKeyText(keyCode) : KeyEvent.getKeyText(keyCode).toLowerCase();
			}
			
			if(keyCode == KeyEvent.VK_SPACE) {
				content += " ";
			}
		} 
		
		if(keyCode == KeyEvent.VK_BACK_SPACE) {
			if(content.length() > 0) {
				content = content.substring(0, content.length() - 1);
			}
		}
		
		if(keyCode == KeyEvent.VK_ENTER) {
			content = "";
			hasFocus = false;
		}
		
		if(keyCode == KeyEvent.VK_ESCAPE) {
			hasFocus = false;
		}
	}

	public void keyReleased(KeyEvent e) {
		
	}
	
	public void mouseClicked(MouseEvent e) {
		int x = e.getX()/2;
		int y = e.getY()/2;
		
		// if inside bounds of the button
//		if(x >= this.x && x <= (this.x + width) && y >= this.y && y <= (this.y + height)) {
//			hasFocus = true;
//			Game.getGamePanel().setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
//		} else {
//			hasFocus = false;
//			Game.getGamePanel().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
//		}
	}
	
	public void mouseMoved(MouseEvent e) {
		int x = e.getX()/2;
		int y = e.getY()/2;
		
		// if inside bounds of the button
//		if(x >= this.x && x <= (this.x + width) && y >= this.y && y <= (this.y + height)) {
//			if(hasFocus) {
//				Game.getGamePanel().setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
//			} else {
//				Game.getGamePanel().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//			}
//		} else {
//			Game.getGamePanel().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
//		}
	}
	
}
