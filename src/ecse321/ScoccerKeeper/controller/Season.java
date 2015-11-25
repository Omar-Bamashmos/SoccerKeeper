package ecse321.ScoccerKeeper.controller;

import java.util.ArrayList;

/**
 * 
 * @author Omar
 * Stores the data of the leagues in the season
 */
public class Season{

	private static Season[] seasons;
	private String seasonName;
	private ArrayList<League> leagues=new ArrayList<>();

	public Season (String seasonName) {
		this.seasonName=seasonName;
	}

	public void addLeague (League league) {
		leagues.add(league);
	}

	public String getName() {
		return this.seasonName;
	}

	public ArrayList<League> getLeagues() {
		return this.leagues;
	}

	public static void setSeasons(Season[] seasonList){
		seasons = seasonList;
	}

	public static String[] getSeasonsNames(){
		String[] result = new String[seasons.length];
        int i = 0;
		for(Season season: seasons){
			result[i] = season.getName();
			i++;
		}
		return result;

	}
}
