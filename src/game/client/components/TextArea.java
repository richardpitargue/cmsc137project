package game.client.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class TextArea {
	
	public static final int MAX_DEFAULT = 80;
	
	public final int x;
	public final int y;
	public final int width;
	public final int height;
	
	private String content = "";
	private BufferedImage image;
	private boolean hasFocus = false;
	
	public TextArea(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
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
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, width, height);
		g2.setColor(Color.BLACK);
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
	}

	public void keyReleased(KeyEvent e) {
		
	}
	
}
