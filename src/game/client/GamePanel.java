package game.client;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import game.client.gsm.GameStateManager;
import game.client.gsm.TitleScreenState;

public class GamePanel extends JPanel implements Runnable, KeyListener, MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 600;
	public static final int HEIGHT = 325;
	public static final int SCALE = 2;
	
	private Thread gameThread;
	private boolean running = false;
	
	private BufferedImage image;
	private Graphics2D g;
	
	private GameStateManager gsm;
	
	public GamePanel() {
		super();
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setFocusable(true);
		requestFocus();
		
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		
		gsm = new GameStateManager();
		gsm.changeState(new TitleScreenState(gsm), false);
		
		enableListeners();
		start();
	}
	
	public void start() {
		running = true;
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void enableListeners() {
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	public void disableListeners() {
		removeKeyListener(this);
		removeMouseListener(this);
		removeMouseMotionListener(this);
	}

	@Override
	public void run() {
		final int TARGET_FPS = 60;
		final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
		long now, last = System.nanoTime(), updateLength, lastFPSTime = 0;
		int fps = 0;
		double delta;
		
		while(running) {
			now = System.nanoTime();
			updateLength = now - last;
			last = now;
			delta = updateLength / ((double) OPTIMAL_TIME);
			
			lastFPSTime += updateLength;
			if(lastFPSTime >= 1000000000) {
				System.out.println("FPS: " + fps);
				fps = 0;
				lastFPSTime = 0;
			}
			
			update(delta);
			draw();
			repaint();
			fps++;
			
			try {
				Thread.sleep(Math.abs(last - System.nanoTime() + OPTIMAL_TIME) / 1000000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void update(double delta) {
		gsm.update(delta);
	}
	
	public void draw() {
		gsm.draw(g);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		gsm.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		gsm.mouseClicked(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		gsm.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		gsm.mouseReleased(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		gsm.mouseEntered(e);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		gsm.mouseExited(e);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		gsm.mouseDragged(e);	
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		gsm.mouseMoved(e);
	}

}
