package game.client;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Music {
	
	private int footStep = 0;
	
	public Music(){
		
	}
	
	public void playMusic(String filename)
	{
		try{
       	    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getClassLoader().getResourceAsStream(filename));
	       	Clip clip = AudioSystem.getClip();
	 	    clip.open(audioInputStream);
	 	    clip.start();
       	}catch(Exception eg){
       		eg.printStackTrace();
       	}
	}
	
	public void background(){
		
       	playMusic("battle_music.wav");
	}
	public void hookHit()
	{
		playMusic("hit.wav");
	}
	public void footStep()
	{
		switch(footStep)
		{
			case 0:playMusic("footstep0.wav");
				break;
			case 1:playMusic("footstep1.wav");
				break;
			case 2:playMusic("footstep2.wav");
				break;
		
		}
		footStep++;
		footStep = footStep%3;
	}
	public void musicStretch()
	{
		playMusic("stretch.wav");
	}
	
	
}
