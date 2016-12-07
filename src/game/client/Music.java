package game.client;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Music {
	
	private int footStep = 0;
	private static Clip clip;
	
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
	public void mainmenu(boolean isPlaying){
		
		try{
	 	    if(isPlaying){
	 	    	AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getClassLoader().getResourceAsStream("main-menu.wav"));
	 	       	clip = AudioSystem.getClip();
	 	 	    clip.open(audioInputStream);
	 	    	clip.loop(Clip.LOOP_CONTINUOUSLY);
	 	    	clip.start();
	 	    } else clip.stop();
       	}catch(Exception eg){
       		eg.printStackTrace();
       	}
	}
	
	public void background(boolean isPlaying){
		
		try{
	 	    if(isPlaying){
	 	    	AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getClassLoader().getResourceAsStream("battle_music.wav"));
	 	       	clip = AudioSystem.getClip();
	 	 	    clip.open(audioInputStream);
	 	    	clip.loop(Clip.LOOP_CONTINUOUSLY);
	 	    	clip.start();
	 	    } else clip.stop();
       	}catch(Exception eg){
       		eg.printStackTrace();
       	}
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
