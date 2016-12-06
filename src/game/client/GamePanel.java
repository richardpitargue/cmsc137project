package game.client;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
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
import javax.swing.Timer;

import game.client.gsm.GameStateManager;
import game.client.gsm.MenuState;

public class GamePanel extends JPanel implements Runnable, KeyListener, MouseListener, MouseMotionListener {

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
		addMouseMotionListener(this);
		
		try
		{
			gsm.channel = DatagramChannel.open();
			gsm.channel.socket().bind(new InetSocketAddress(0));
			gsm.channel.configureBlocking(false);
			gsm.serverAddress = new InetSocketAddress(server,serverPort);
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
			ActionListener sender = new ActionListener()
					{

						@Override
						public void actionPerformed(ActionEvent arg0) {
							// TODO Auto-generated method stub
							send();
							receive();
						}
				
					};
			
					
			Timer timer = new Timer(1, sender);
			timer.start();
			connected = true;
	}
	public void receive()
	{
		try
		{
			ByteBuffer buf = ByteBuffer.allocate(packetSize);
			
			if(gsm.channel.receive(buf) != null)
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
					
					if(gsm.player.equals(p))
							gsm.player.setPlayer(p);
					
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
				gsm.channel.send(ByteBuffer.wrap(buf), gsm.serverAddress);
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

	@Override
	public void mouseDragged(MouseEvent e) {
		gsm.mouseDragged(e);	
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		gsm.mouseMoved(e);
	}

}
