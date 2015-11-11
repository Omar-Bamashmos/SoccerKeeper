package ecse321.ScoccerKeeper.controller;

import java.lang.reflect.Array;

public class leagueAnalysis {
	
	private league leagueOfInterest;
	
	public leagueAnalysis (league league) {
		this.leagueOfInterest=league;
	}
	
	
	public Team[] getTopTeams () {
		Team[] topTeams=new Team[5];
		
		
		return topTeams;
		
		
	}
	
	public Player[] getTopPlayers () {
		Player[] topPlayers=new Player[5];
		int numberOfgoals;
		
		
		return topPlayers;
	}
	
	public Player[] getMostPenalizedPlayer() {
		Player[] penalizedPlayers=new Player[5];
		
		
		return penalizedPlayers;
	}
}
