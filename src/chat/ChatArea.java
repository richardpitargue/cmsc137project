package chat;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.PrintWriter;

import javax.swing.JTextArea;

public class ChatArea extends JTextArea implements KeyListener{
	
	private PrintWriter out;
	public ChatArea(int rows, int cols, PrintWriter out)
	{
		super(rows,cols);
		this.out = out;
		this.addKeyListener(this);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode() == KeyEvent.VK_ENTER)
		{
			arg0.consume();
			out.println(this.getText());
			out.flush();
			this.setText(null);
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
