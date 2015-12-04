package ecse321.SoccerKeeper.controller;

//import java.awt.Image;

//import android.content.Context;

import java.util.ArrayList;

/**
 * Team object contains the info of a team and a list of players in the team.
 * @author Omar
 *         
 */
public class Team {
	private String name;
	//	private Image image;
	private ArrayList<Player> players = new ArrayList<>();
	private int numOfWins;
	private int numOfDraws;
	private int numOfLosses;
	private int numOfPoints;

	/**
	 * Constructor for the team class.
	 * @param teamName
	 */
	public Team(String teamName) {
		this.name = teamName;
		//this.image=teamImage;
		this.numOfDraws = 0;
		this.numOfWins = 0;
		this.numOfWins = 0;
	}

	/**
	 * Adds a player in the current team.
	 * @param player
	 */
	public void addPlayer(Player player) {
		players.add(player);
		// sort the array list
	}

	/**
	 * Removes a player from the current team.
	 *
	 * @param player
	 */
	public void removePlayer(Player player) {
		this.players.remove(player);
	}

	/**
	 * Adds a player to the current team from its name, jersey number and nationality.
	 * @param playerName
	 * @param jerseyNumber
	 * @param nationality
	 * @param myContext android parameter to access a file.
	 */
	public void addPlayerByName(String playerName, int jerseyNumber, String nationality, Context myContext){
		this.addPlayer(new Player(playerName, jerseyNumber, nationality));
		DataAndroidApp.writingToFile(myContext);
		DataAndroidApp.readingFromFile(myContext);
	}

	/**
	 * Removes a player from the current team from its name.
	 * @param playerName
	 * @param myContext android parameter to access a file.
	 */
	public void removePlayerByName(String playerName, Context myContext){
		this.removePlayer(this.getPlayerFromName(playerName));
		DataAndroidApp.writingToFile(myContext);
		DataAndroidApp.readingFromFile(myContext);
	}

	/**
	 * Returns a list of all the players in the current team.
	 * @return list of players
	 */
	public ArrayList<Player> getPlayers() {
		return this.players;
	}

	/**
	 * Returns the name of the current team.
	 * @return name of the team
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Returns the number of wins of the current team.
	 * @return number of wins
	 */
	public int getNumOfWins() {
		return this.numOfWins;
	}

	/**
	 * Returns the number of draws of the current team.
	 * @return number of draws
	 */
	public int getNumOfDraws() {
		return this.numOfDraws;
	}

	/**
	 * Returns the number of losses of the current team.
	 * @return number of losses
	 */
	public int getNumOfLosses() {
		return this.numOfLosses;
	}
	
	/**
	 * Sets the number of wins of the current team.
	 * @param wins
	 */
	public void setNumOfWins(int wins) {
		this.numOfWins = wins;
	}

	/**
	 * Sets the number of draws of the current team.
	 * @param draws
	 */
	public void setNumOfDraws(int draws) {
		this.numOfDraws = draws;
	}

	/**
	 * Sets the number of losses of the current team.
	 * @param losses
	 */
	public void setNumOfLosses(int losses) {
		this.numOfLosses = losses;
	}

	/**
	 * Increments the number of wins of the current team by 1.
	 */
	public void incrementNumOfWins() {
		this.numOfWins++;
	}

	/**
	 * Increments the number of draws of the current team by 1.
	 */
	public void incrementNumOfDraws() {
		this.numOfDraws++;
	}

	/**
	 * Increments the number of losses of the current team by 1.
	 */
	public void incrementNumOfLosses() {
		this.numOfLosses++;
	}

	/**
	 * Resets the cards received by a player during a match for all the players of the current team.
	 */
	public void resetCards() {
		for (Player player : this.getPlayers()) {
			player.resetCards();
		}
	}

	/**
	 * Returns the number of points of the current team.
	 * @return number of points
	 */
	public int getPoints() {
		this.numOfPoints = 3 * this.numOfWins + this.numOfDraws;
		return this.numOfPoints;
	}


	/**
	 * Returns a list of all the names of the players.
	 * @return list of players' names
	 */
	public String[] getPlayersNames() {
		String[] result = new String[this.getPlayers().size()];
		int i = 0;
		for (Player player : this.getPlayers()) {
			result[i] = player.getName();
			i++;
		}
		return result;
	}

	/**
	 * Returns a player in the current team from its name.
	 * @param name
	 * @return player
	 */
	public Player getPlayerFromName(String name) {
		for (Player player : this.getPlayers()) {
			if (name.equals(player.getName())) {
				return player;
			}
		}
		return null;
	}
}
