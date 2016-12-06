package game.server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import game.client.Player;

public class Lobby implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private List<Player> players;
	
	public Lobby(String name, Player host) {
		this.name = name;
		
		players = new ArrayList<Player>();
		players.add(host);
	}
	
	public void addPlayer(Player p) {
		if(!players.contains(p)) {
			players.add(p);
		}
	}
	
	public String getName() {
		return name;
	}
	
	public Player getHostPlayer() {
		return players.get(0);
	}
	
}
