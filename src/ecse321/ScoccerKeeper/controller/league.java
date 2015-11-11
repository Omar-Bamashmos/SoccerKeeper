package ecse321.ScoccerKeeper.controller;

import java.util.ArrayList;

public class league {
	private static String leagueName;
	private ArrayList<Team> teams=new ArrayList<>();
	private ArrayList<Game> games=new ArrayList<>();
	
	public league (String leaguename) {
		this.leagueName=leaguename;
	}
	
	public String getLeagueName () {
		return leagueName;
	}
	public void addTeams(Team team) {
		teams.add(team);
	}
	public ArrayList getTeams () {
		return teams;
	}

}
