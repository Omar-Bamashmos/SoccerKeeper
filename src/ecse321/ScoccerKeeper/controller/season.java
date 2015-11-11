package ecse321.ScoccerKeeper.controller;

import java.util.ArrayList;

public class season {
	
	private static String SeasonName;
	private ArrayList<league> leagues=new ArrayList<>();
	
	public season (String seasonName) {
		this.SeasonName=seasonName;
	}
	
	public void addLeague (league league) {
		leagues.add(league);
	}
	
	public ArrayList getLeagues() {
		return leagues;
	}
}
