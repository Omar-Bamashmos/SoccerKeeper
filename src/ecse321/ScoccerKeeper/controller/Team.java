package ecse321.ScoccerKeeper.controller;

import java.awt.Image;
import java.util.ArrayList;

/**
 * 
 * @author Omar
 * Team object contains the info of a team and a list of players in the team
 */
public class Team {
	private String Name;
	private Image image;
	private ArrayList<Player>players=new ArrayList<>();
	private int numOfWins;
	private int numOfDraws;
	private int numOfPoints;
	
	public Team(String teamName) {
		this.Name=teamName;
		//this.image=teamImage;
		this.numOfDraws=0;
		this.numOfWins=0;
		
		
	}
	public void addPlayer(Player player) {
		players.add(player);
		// sort the array list
	}
	public ArrayList<Player> getPlayers (){
		return players;
	}
	public String getName() {
		return Name;
	}
	public Image getImage () {
		return image;
	}
	/**
	 * update the records of the team
	 */
	public  void incrementNumOfWins() {
		this.numOfWins++;
	}
	/**
	 * update the records of the team
	 */
	public void incrementNumOfDraws() {
		this.numOfDraws++;
	}
	/**
	 * 
	 * @return number of points
	 */
	public int getPoints() {
		return 3*numOfWins+1*numOfDraws;
	}
	/** remove a player from the team
	 * 
	 * @param player
	 */
	public void removePlayer(Player player) {

		players.remove(player);
	}
	
	

}
