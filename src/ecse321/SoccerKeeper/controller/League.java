package ecse321.SoccerKeeper.controller;

import java.util.ArrayList;

/**
 * 
 * @author Omar
 * 
 * This class is a component class that stores the data of the league
 * it has the list of the team playing in the league
 *
 */

public class League {
	private String leagueName;
	private ArrayList<Team> teams=new ArrayList<>();


	/**
	 * 
	 * @param leagueName
	 * Create a new league is only allowed to the manager mode
	 */
	public League (String leagueName) {
		this.leagueName=leagueName;
	}

	/**
	 * 
	 * @return league name
	 */
	public String getLeagueName() {
		return this.leagueName;
	}

	/**
	 * 
	 * @param team
	 * adding teams to the league teams list
	 */
	public void addTeams(Team team) {
		this.teams.add(team);
	}

	/**
	 * 
	 * @return list of teams
	 */
	public ArrayList<Team> getTeams() {
		return this.teams;
	}

	/**
	 *
	 * @return team names
	 */

	public String[] getTeamNames(){
		String[] result = new String[getTeams().size()];
		int i=0;
		for(Team team: this.getTeams()){
			result[i] = team.getName();
			i++;
		}
		return result;
	}

	public Team getTeamFromName(String name){
		for(Team team: teams){
			if(name.equals(team.getName())){
				return team;
			}
		}
		return null;
	}

}
