package ecse321.SoccerKeeper.controller;

//import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Stores the data of the leagues in the season
 * @author Omar
 *         
 */
public class Season {

	private static Season[] seasons;
	private String seasonName;
	private ArrayList<League> leagues = new ArrayList<>();
	
	/**
	 * Constructor for the season class.
	 * @param seasonName
	 */
	public Season(String seasonName) {
		this.seasonName = seasonName;
	}
	
	/**
	 * Adds a league to the current season.
	 * @param league
	 */
	public void addLeague(League league) {
		leagues.add(league);
	}

	/**
	 * Remove a league from the current season.
	 * @param league
	 */
    public void removeLeague(League league){
        leagues.remove(league);
    }

    /**
     * Returns the name of the current season.
     * @return season's name
     */
	public String getName() {
		return this.seasonName;
	}

	/**
	 * Returns a list of all the leagues in the current season.
	 * @return list of leagues
	 */
	public ArrayList<League> getLeagues() {
		return this.leagues;
	}

	
	/**
	 * Returns a String array containing all the league names of the current season.
	 * @return list of league names
	 */
	public String[] getLeaguesNames() {
		String[] result = new String[this.getLeagues().size()];
		int i = 0;
		for (League league : this.getLeagues()) {
			result[i] = league.getLeagueName();
			i++;
		}
		return result;
	}
	
	
	/**
	 * Sets the list of all the seasons.
	 * @param seasonList
	 */
	public static void setSeasons(Season[] seasonList) {
		seasons = seasonList;
	}

	/**
	 * Adds a season from its name. And updates the data file.
	 * @param seasonName
	 * @param myContext android parameter to access a file.
	 */
	public static void addSeasonByName(String seasonName, Context myContext){
        LinkedList<Season> seasonTemp = new LinkedList<>(Arrays.asList(Season.getSeasons()));
		Season newSeason = new Season(seasonName);

		for (League league : seasons[0].getLeagues()) {
			newSeason.addLeague(league);
		}
		newSeason.resetData();
		seasonTemp.addFirst(newSeason);
		seasons = seasonTemp.toArray(new Season[]{});
		Season.setSeasons(seasons);
		DataAndroidApp.writingToFile(myContext);
		DataAndroidApp.readingFromFile(myContext);
	}

	/**
	 * Removes a season from its name. And updates the data file.
	 * @param seasonToDeleteName
	 * @param myContext android parameter to access a file.
	 */
    public static void removeSeasonByName(String seasonToDeleteName, Context myContext){
        LinkedList<Season> seasonTemp = new LinkedList<>(Arrays.asList(Season.getSeasons()));

        seasonTemp.remove(Season.getSeasonFromName(seasonToDeleteName));
        seasons = seasonTemp.toArray(new Season[seasonTemp.size()]);
        Season.setSeasons(seasons);
        DataAndroidApp.writingToFile(myContext);
        DataAndroidApp.readingFromFile(myContext);
    }

	/**
	 * Adds a league from its name. And updates the data file.
	 * @param leagueName
	 * @param myContext android parameter to access a file.
	 */
    public void addLeagueByName(String leagueName, Context myContext){
        this.addLeague(new League(leagueName));
        DataAndroidApp.writingToFile(myContext);
        DataAndroidApp.readingFromFile(myContext);
    }

	/**
	 * Remove a league from its name. And updates the data file.
	 * @param leagueName
	 * @param myContext android parameter to access a file.
	 */
    public void removeLeagueByName(String leagueName, Context myContext){
        this.removeLeague(this.getLeagueFromName(leagueName));
        DataAndroidApp.writingToFile(myContext);
        DataAndroidApp.readingFromFile(myContext);
    }

    /**
     * Returns a String array with the names of all the teams in the current season.
     * @return list of all the teams' names
     */
	public String[] getTeamsNamesFromASeason() {
		ArrayList<League> allLeagues = this.getLeagues();
		int size = 0;
		HashMap<League, ArrayList<Team>> allTeams = new HashMap<League, ArrayList<Team>>();
		for (League league : allLeagues) {
			allTeams.put(league, league.getTeams());
			size+=league.getTeams().size();
		}
		String[] allTeamsNames = new String[size];
		int i = 0;
		for (League league : allLeagues) {
			for (Team team : allTeams.get(league)) {
				allTeamsNames[i] = team.getName();
				i++;
			}
		}
		List allTeamsSorted = Arrays.asList(allTeamsNames);
		System.out.println(allTeamsSorted.toString());
		Collections.sort(allTeamsSorted);
		String[] returnation = new String[allTeamsSorted.size()];
		allTeamsSorted.toArray(returnation);
		return returnation;
	}

    /**
     * Returns a String array with the names of all the players in the current season.
     * @return list of all the players' names
     */
	public String[] getPlayersNamesFromASeason() {
		ArrayList<League> allLeagues = this.getLeagues();
		int size = 0;
		HashMap<League, ArrayList<Team>> allTeams = new HashMap<League, ArrayList<Team>>();
		for (League league : allLeagues) {
			allTeams.put(league, league.getTeams());
			size+=league.getTeams().size();
		}
//		String[] allPlayersNames = new String[size];
		ArrayList<String> allPlayersNames = new ArrayList<>();
		int i = 0;

		for (League league : allLeagues) {
			for (Team team : allTeams.get(league)) {
				for(Player player: team.getPlayers()){
					allPlayersNames.add(player.getName());
				}

			}
		}
		Collections.sort(allPlayersNames);
		String[] returnation = new String[allPlayersNames.size()];
		allPlayersNames.toArray(returnation);
		return returnation;
	}

	/**
	 * Returns a league in the current season from the name of a team inside it.
	 * @param teamName
	 * @return league
	 */
	public League getLeagueFromTeamName(String teamName){
		ArrayList<League> allLeagues = this.getLeagues();
		HashMap<League, ArrayList<Team>> allTeams = new HashMap<League, ArrayList<Team>>();
		for (League league : allLeagues) {
			allTeams.put(league, league.getTeams());
		}
		for (League league : allLeagues) {
			for (Team team : allTeams.get(league)) {
				if(teamName.equals(team.getName())){
					return league;
				}
			}
		}
		return null;
	}
	
	/**
	 * Returns a list of all the seasons.
	 * @return list of seasons
	 */
	public static Season[] getSeasons() {
		return seasons;
	}
	
	/**
	 * Return a season present on the list of all the seasons from its name.
	 * @param name
	 * @return season
	 */
	public static Season getSeasonFromName(String name) {
		for (Season season : seasons) {
			if (season.getName().equals(name))
				return season;
		}
		return null;
	}
	
	/**
	 * Returns a list of all the seasons' names. 
	 * @return list of seasons' names
	 */
	public static String[] getSeasonsNames() {
		String[] result = new String[seasons.length];
		int i = 0;
		for (Season season : seasons) {
			result[i] = season.getName();
			i++;
		}
		return result;
	}
	
	
	/**
	 * Returns a league in the current season from its name.
	 * @param name
	 * @return league
	 */
	public League getLeagueFromName(String name) {
		for (League league : this.leagues) {
			if (league.getLeagueName().equals(name))
				return league;
		}
		return null;
	}
	
	/**
	 * Resets the data (teams' results, players' data) from all the seasons.
	 */
	public void resetData() {
		for (League league : this.leagues) {
			for (Team team : league.getTeams()) {
				team.setNumOfWins(0);
				team.setNumOfDraws(0);
				team.setNumOfLosses(0);
				for (Player player : team.getPlayers()) {
					player.resetDataPlayer();
				}
			}
		}
	}
}
