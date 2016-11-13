package game.client.player;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Pudge {
	
	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;

	private int x;
	private int y;
	private int width;
	private int height;
	private int frame;
	private int direction;
	private BufferedImage[][] sprite;
	
	public Pudge(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.frame = 0;
		this.direction = 0;
		
		//load image here
		sprite = new BufferedImage[4][4];
		try {
			for(int i = 0;  i < 4; i++)
				for(int j = 0; j < 4; j++)
					sprite[i][j] = ImageIO.read(getClass().getClassLoader().getResourceAsStream("sprite_" + i + "_" + j + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	public void draw(Graphics2D g) {
		g.drawImage(sprite[direction][frame], x, y, width, height, null);
	}
	
	public void move(int direction) {
		
		
		
		switch(direction) {
			case UP:
				this.direction = 0;
				if(y!=0)
					y -= 5;
				break;
			case DOWN:
				this.direction = 2;
				if(y!=275)
					y += 5;
				break;
			case LEFT:
				this.direction = 3;
				if(x != 0 && x != 340)
					x -= 5;
				break;
			case RIGHT:
				this.direction = 1;
				if(x != 550 && x != 210)
					x += 5;
				break;
		}
		frame = (frame + 1) % 4;
	}
	
}
