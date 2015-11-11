package ecse321.ScoccerKeeper.controller;

import java.awt.Image;
import java.util.ArrayList;

public class Team {
	private static String Name;
	private static Image image;
	private ArrayList<Player>players=new ArrayList<>();
	
	public Team(String teamName, Image teamImage) {
		this.Name=teamName;
		this.image=teamImage;
	}
	public void addPlayer(Player player) {
		players.add(player);
		// sort the array list
	}
	
	public ArrayList getPlayers (){
		return players;
	}
	public String getName() {
		return Name;
	}
	public Image getImage () {
		return image;
	}

}
