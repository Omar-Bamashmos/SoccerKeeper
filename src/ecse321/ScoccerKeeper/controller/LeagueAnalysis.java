package ecse321.ScoccerKeeper.controller;

import java.util.ArrayList;

/**
 * 
 * @author Omar
 * This class is the main functional class in the system
 * It provides three methods to analyze the performance of the team or the players
 * It needs to know the league the user is interested in
 *
 */

public class LeagueAnalysis {

	private League league;

	public LeagueAnalysis (League league) {
		this.league=league;
	}

	/**
	 * 
	 * @return topTeams
	 * It returns the top top five team in the league
	 * based on the number of points each team has
	 * 3 points per win, 1 per draw and zero per loss
	 */
	public Team[] getTopTeams(){
		Team[] topTeams=new Team[5];
		Team tempTeam, tempTeam2;
		ArrayList<Team> teams=league.getTeams();
		for(Team team: teams) {
			for(int i=0; i<5; i++) {
				if (team.getPoints()>topTeams[i].getPoints() || topTeams[i]==null) {
					tempTeam=topTeams[i];
					topTeams[i]=team;
					for(int j=i+1; j<5; j++) {
						tempTeam2=topTeams[j];
						topTeams[j]=tempTeam;
						tempTeam=tempTeam2;
					}
				}
			}
		}	

		return topTeams;

	}

	/**
	 * 
	 * @return topPlayer[]
	 * 
	 * the method returns the best five players in the league based on the number of goals
	 * It uses the getnuOf goals method in the player object
	 */
	public Player[] getTopPlayers () {
		Player[] topPlayers=new Player[5];
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
				if (player.getNumOfGoals()>topPlayers[i].getNumOfGoals()|| topPlayers[i]==null) {
					tempPlayer1=topPlayers[i];
					topPlayers[i]=player;
					for(int j=i+1; j<5; j++) {
						tempPlayer2=topPlayers[j];
						topPlayers[j]=tempPlayer1;
						tempPlayer1=tempPlayer2;
					}
				}
			}
		}
		return topPlayers;
	}

	/**
	 * 
	 * @return mostPenalizedPlayers[]
	 * returns the five players with most number of infractions
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
