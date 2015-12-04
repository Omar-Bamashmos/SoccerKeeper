package ecse321.SoccerKeeper.controller;

import java.util.ArrayList;

/**
 * This class is the main functional class in the system
 * It provides three methods to analyze the performance of the team or the players
 * It needs to know the league the user is interested in
 * @author Omar
 */
public class LeagueAnalysis {

	private League league;

	/**
	 * Constructor for LeagueAnalysis
	 * @param league to be analyzed
	 */
	public LeagueAnalysis (League league) {
		this.league=league;
	}

	/**
	 * Returns the top 25 (or less) of the teams present in the league analyzed
	 * @return team array of the top teams
	 */
	public Team[] getTopTeamsbyV(){
		Team[] topTeams;
		League currentLeague = this.league;
		ArrayList<Team> allTeams = new ArrayList<>();
		for (Team team : currentLeague.getTeams()) {

			allTeams.add(team);
		}
		if (allTeams.size() > 25) {
			topTeams = new Team[25];
		}
		else{
			topTeams = new Team[allTeams.size()];
		}
		Team mostPointsTeam = null;
		int count = 0;
		while(count<topTeams.length) {
			for (Team team : allTeams) {
				if (mostPointsTeam == null) {
					mostPointsTeam = team;
					continue;
				}
				if (team.getPoints() > mostPointsTeam.getPoints()) {
					mostPointsTeam = team;
				}
			}
			topTeams[count] = mostPointsTeam;
			allTeams.remove(mostPointsTeam);
			mostPointsTeam = null;
			count++;
		}
		return topTeams;
	}

	/**
	 * Returns a two dimensional object array with all the data for every top teams
	 * @return the array
	 */
	public Object[][] getTwoDimTopTeamsbyV(){
		//teamName, win, draw, loss, points
		Object[][] finalResult = new Object[getTopTeamsbyV().length][5];
		Team[] topTeams = this.getTopTeamsbyV();
		int i=0;
		for(Team team: topTeams){
			String[] tempTeam = {team.getName(), Integer.toString(team.getNumOfWins()), Integer.toString(team.getNumOfDraws()), Integer.toString(team.getNumOfLosses()), Integer.toString(team.getPoints())};
			finalResult[i] = tempTeam;
			i++;
		}
		return finalResult;
	}

	/**
	 * Returns the top 25 (or less) players present in the league analyzed
	 * @return an player array with the top players
	 */
	public Player[] getTopPlayersbyV() {
		Player[] topPlayers;
		League currentLeague = this.league;
		ArrayList<Player> allPlayers = new ArrayList<>();
		for (Team team : currentLeague.getTeams()) {
			for (Player player : team.getPlayers()) {
				allPlayers.add(player);
			}
		}
		if (allPlayers.size() > 25) {
			topPlayers = new Player[25];
		}
		else{
			topPlayers = new Player[allPlayers.size()];
		}
		Player mostGoalPlayer = null;
		int count = 0;
		while(count<topPlayers.length) {
			for (Player player : allPlayers) {
				if (mostGoalPlayer == null) {
					mostGoalPlayer = player;
					continue;
				}
				if (player.getNumOfGoals() > mostGoalPlayer.getNumOfGoals()) {
					mostGoalPlayer = player;
				}
			}
			topPlayers[count] = mostGoalPlayer;
			allPlayers.remove(mostGoalPlayer);
			mostGoalPlayer = null;
			count++;
		}
		return topPlayers;
	}

	/**
	 * Returns a two dimensional object array with all the data for every top players
	 * @return the array
	 */
	public Object[][] getTwoDimTopPlayersbyV(){
		//playerName, jerseyNumber, Goals, Shot on target, shots missed, fouls, yellow, red
		Object[][] finalResult = new Object[getTopTeamsbyV().length][8];
		Player[] topPlayers = this.getTopPlayersbyV();
		int i=0;
		for(Player player: topPlayers){
			String[] tempTeam = {player.getName(), Integer.toString(player.getJerseyNumber()), Integer.toString(player.getNumOfGoals()), Integer.toString(player.getNumOfShotsOnTarget()), Integer.toString(player.getNumOfShotsOffTarget()), Integer.toString(player.getNumOfFouls()), Integer.toString(player.getNumOfYellow()), Integer.toString(player.getNumOfRed())};
			finalResult[i] = tempTeam;
			i++;
		}
		return finalResult;
	}

	/**
	 * Returns the five players with most number of infractions
	 * @return mostPenalizedPlayers[]
	 * 
	 */
	public Player[] getMostPenalizedPlayers() {
		Player[] penalizedPlayers=new Player[5];
		ArrayList<Player> players=new ArrayList<>();
		Player tempPlayer1, tempPlayer2;
		ArrayList<Team> teams=league.getTeams();
		for(Team team: teams) {
			ArrayList<Player> playerTeam= team.getPlayers();
			for(Player player: playerTeam) 
				players.add(player);
		}

		for(Player player:players) {
			for(int i=0; i<5; i++) {
				if (player.getNumOfInfractions()>penalizedPlayers[i].getNumOfInfractions()|| penalizedPlayers[i]==null) {
					tempPlayer1=penalizedPlayers[i];
					penalizedPlayers[i]=player;
					for(int j=i+1; j<5; j++) {
						tempPlayer2=penalizedPlayers[j];
						penalizedPlayers[j]=tempPlayer1;
						tempPlayer1=tempPlayer2;
					}
				}
			}
		}
		return penalizedPlayers;
	}
}
