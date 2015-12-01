package ecse321.SoccerKeeper.controller;

//import java.awt.Image;

import java.util.ArrayList;

/**
 * @author Omar
 *         Team object contains the info of a team and a list of players in the team
 */
public class Team {
	private String name;
	//	private Image image;
	private ArrayList<Player> players = new ArrayList<>();
	private int numOfWins;
	private int numOfDraws;
	private int numOfLosses;
	private int numOfPoints;

	public Team(String teamName) {
		this.name = teamName;
		//this.image=teamImage;
		this.numOfDraws = 0;
		this.numOfWins = 0;
		this.numOfWins = 0;
	}

	public void addPlayer(Player player) {
		players.add(player);
		// sort the array list
	}

	public ArrayList<Player> getPlayers() {
		return this.players;
	}

	public String getName() {
		return this.name;
	}

	//	public Image getImage () {
	//		return this.image;
	//	}


	public int getNumOfWins() {
		return this.numOfWins;
	}

	public int getNumOfDraws() {
		return this.numOfDraws;
	}

	public int getNumOfLosses() {
		return this.numOfLosses;
	}

	public void setNumOfWins(int wins) {
		this.numOfWins = wins;
	}

	public void setNumOfDraws(int draws) {
		this.numOfDraws = draws;
	}

	public void setNumOfLosses(int losses) {
		this.numOfLosses = losses;
	}

	/**
	 * update the records of the team
	 */
	public void incrementNumOfWins() {
		this.numOfWins++;
	}

	/**
	 * update the records of the team
	 */
	public void incrementNumOfDraws() {
		this.numOfDraws++;
	}

	/**
	 * update the records of the team
	 */
	public void incrementNumOfLosses() {
		this.numOfLosses++;
	}

	public void resetCards() {
		for (Player player : this.getPlayers()) {
			player.resetCards();
		}
	}

	/**
	 * @return number of points
	 */
	public int getPoints() {
		this.numOfPoints = 3 * this.numOfWins + this.numOfDraws;
		return this.numOfPoints;
	}

	/**
	 * remove a player from the team
	 *
	 * @param player
	 */
	public void removePlayer(Player player) {

		this.players.remove(player);
	}


	/**
	 * @return noodle
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

	public Player getPlayerFromName(String name) {
		for (Player player : this.getPlayers()) {
			if (name.equals(player.getName())) {
				return player;
			}
		}
		return null;
	}

	/**
	 *
	 * @param name
	 * @return get Team from League Name
	 */

}
