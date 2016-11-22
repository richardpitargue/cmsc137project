package game.client;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import game.client.gsm.GameStateManager;
import game.client.gsm.MenuState;

public class GamePanel extends JPanel implements Runnable, KeyListener, MouseListener {

	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 600;
	public static final int HEIGHT = 325;
	public static final int SCALE = 2;
	private static final int packetSize = 8192;
	private String username;
	
	private JTextArea chatBox;
	private JScrollPane chatScroll;
	
	private Thread gameThread;
	private boolean running = false;
	
	private BufferedImage image;
	private Graphics2D g;
	
	private GameStateManager gsm;
	
	// network shit
	private boolean connected = false;
	private DatagramChannel channel;
	private InetSocketAddress serverAddress;
	
	public GamePanel(String server, String username, int serverPort) {
		super();
		this.username = username;
		//setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setSize(WIDTH * SCALE, HEIGHT * SCALE);
		setLocation(0,0);
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		
		
		
		gsm = new GameStateManager();
		gsm.changeState(new MenuState(gsm), false);
		
		
		addKeyListener(this);
		addMouseListener(this);
		
		try
		{
			channel = DatagramChannel.open();
			channel.socket().bind(new InetSocketAddress(0));
			channel.configureBlocking(false);
			serverAddress = new InetSocketAddress(server,serverPort);
		}catch(Exception e){}
		
		
		setFocusable(true);
		requestFocus();
		start();
	}
	
	public void start() {
		running = true;
		gameThread = new Thread(this);
		gameThread.start();
		
		gsm.player = new Player(username, null);
		gsm.hooks = new ArrayList<Hook>();

		connect();
	}
	
	private void connect() {
			send();
			connected = true;
	}
	public void receive()
	{
		try
		{
			ByteBuffer buf = ByteBuffer.allocate(packetSize);
			
			if(channel.receive(buf) != null)
			{
				buf.clear();
				byte[] byt = new byte[buf.capacity()];
				buf.get(byt, 0, byt.length);
				
				ObjectInputStream iStream = new ObjectInputStream(new ByteArrayInputStream(byt));
				gsm.players = (ArrayList<Player>) iStream.readObject();
				
				for(Player p:gsm.players)
				{
					Hook hook = new Hook(p);
					if(!gsm.hooks.contains(hook))
						gsm.hooks.add(hook);
					else
					{
						gsm.hooks.set(gsm.hooks.indexOf(hook), hook);
					}
					
				}
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void send()
	{
		if(gsm.player.getChanged())
		{
		try
		{	
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			ObjectOutput oo = new ObjectOutputStream(bStream); 
			oo.writeObject(gsm.player);
			oo.close();
			byte[] buf = new byte[512]; 
			buf = bStream.toByteArray();
			channel.send(ByteBuffer.wrap(buf), serverAddress);
			gsm.player.setChanged(false);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		}
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
				//System.out.println("FPS: " + fps);
				fps = 0;
				lastFPSTime = 0;
			}
			
			update(delta);
			if(connected)
			{
				receive();
				send();
			}
			draw();
			render();
			fps++;
			
			try {
				Thread.sleep((System.nanoTime() - last + OPTIMAL_TIME) / 1000000);
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
	
	public void render() {
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		g2.dispose();
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
		this.grabFocus();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		gsm.mouseEntered(e);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		gsm.mouseExited(e);
	}

}
