package ecse321.SoccerKeeper.controller;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * 
 * @author Omar
 * This clas is the main functional class in the system
 * It provides three methods to analyze the performance of the team or th players
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
	public Team[] getTopTeams () {
		Team[] topTeams=new Team[5];
		ArrayList <Team> teams=league.getTeams();

		for(int i=0; i<5 && teams.size()>0; i++) {
			topTeams[i]=teams.get(0);
			for(Team team: teams) {
				if(team.getPoints()>topTeams[i].getPoints())
					topTeams[i]=team;
			}
			teams.remove(topTeams[i]);
		}
		return topTeams;


	}
	/**
	 * 
	 * @return topPlayer[]
	 * 
	 * the method rreurns the best five players in the league absed on the number of goals
	 * It uses the getnuOf goals method in the plyer object
	 */

	public Player[] getTopPlayers () {
		Player[] topPlayers=new Player[5];
		ArrayList<Player> players=new ArrayList<>();
		ArrayList<Team> teams=league.getTeams();

		for(Team team: teams) {
			for(Player player: team.getPlayers()) 
				players.add(player);
		}
		for(int i=0; i<5&& players.size()>0; i++) {
			topPlayers[i]=players.get(0);
			for(Player player: players) {
				if(player.getNumOfGoals()>topPlayers[i].getNumOfGoals())
					topPlayers[i]=player;
			}
			players.remove(topPlayers[i]);
		}	
		return topPlayers;
	}
	/**
	 * 
	 * @return mostPenalizedPlayers[]
	 * returns the five players with most number of infractions
	 */

	public Player[] getMostPenalizedPlayer() {
		Player[] penalizedPlayers=new Player[5];
		ArrayList<Player> players=new ArrayList<>();
		ArrayList<Team> teams=league.getTeams();

		for(Team team: teams) {
			for(Player player: team.getPlayers()) 
				players.add(player);
		}
		for(int i=0; i<5&& players.size()>0; i++) {
			penalizedPlayers[i]=players.get(0);
			for(Player player: players) {
				if(player.getNumOfInfractions()>penalizedPlayers[i].getNumOfInfractions())
					penalizedPlayers[i]=player;
			}
			players.remove(penalizedPlayers[i]);
		}	
		return penalizedPlayers;
	}


}
