package ecse321.SoccerKeeper.controller;

//import android.content.Context;

import java.util.ArrayList;

/**
 * This class is a component class that stores the data of the league
 * @author Vivien
 * 
 */
public class League {
	private String leagueName;
	private ArrayList<Team> teams = new ArrayList<>();


	/**
	 * @param leagueName Create a new league is only allowed to the manager mode
	 */
	public League(String leagueName) {
		this.leagueName = leagueName;
	}

	/**
	 * Returns the name of the league.
	 * @return league name
	 */
	public String getLeagueName() {
		return this.leagueName;
	}

	/**
	 * Adds a team to the current league.
	 * @param team adding team to the league teams list
	 */
	public void addTeam(Team team) {
		this.teams.add(team);
	}

	/**
	 * Removes a team from the current league
	 * @param team
	 */
	public void removeTeam(Team team){
		this.teams.remove(team);
	}


	/**
	 * Adds a team on the current league from the current league
	 * @param teamName
	 * @param myContext
	 */
	public void addTeamByName(String teamName, Context myContext){
		Team newTeam = new Team(teamName);
		this.addTeam(newTeam);
		DataAndroidApp.writingToFile(myContext);
		DataAndroidApp.readingFromFile(myContext);
	}

	/**
	 * Removes a team on the current league from the current league
	 * @param teamName
	 * @param myContext
	 */
	public void removeTeamByName(String teamName, Context myContext) {
		this.removeTeam(this.getTeamFromName(teamName));
		DataAndroidApp.writingToFile(myContext);
		DataAndroidApp.readingFromFile(myContext);
	}

	/**
	 * Returns all the teams from the current league
	 * @return list of teams
	 */
	public ArrayList<Team> getTeams() {
		return this.teams;
	}

	/**
	 * Returns a string array with all the team names
	 * @return team names
	 */
	public String[] getTeamNames() {
		String[] result = new String[getTeams().size()];
		int i = 0;
		for (Team team : this.getTeams()) {
			result[i] = team.getName();
			i++;
		}
		return result;
	}


	/**
	 * Returns a team in the current league from its name
	 * @param name of the team
	 * @return the team
	 */
	public Team getTeamFromName(String name) {
		for (Team team : teams) {
			if (name.equals(team.getName())) {
				return team;
			}
		}
		return null;
	}

	/**
	 * Resets the data (match results, player data) of all the players of the current league
	 */
	public void resetData() {
		for (Team team : this.getTeams()) {
			team.setNumOfWins(0);
			team.setNumOfDraws(0);
			team.setNumOfLosses(0);
			for (Player player : team.getPlayers()) {
				player.resetDataPlayer();
			}
		}
	}
}
