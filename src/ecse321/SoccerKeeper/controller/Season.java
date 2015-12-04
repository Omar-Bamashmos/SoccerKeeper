package ecse321.SoccerKeeper.controller;

//import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Omar
 *         Stores the data of the leagues in the season
 */
public class Season {

	private static Season[] seasons;
	private String seasonName;
	private ArrayList<League> leagues = new ArrayList<>();

	public Season(String seasonName) {
		this.seasonName = seasonName;
	}

	public void addLeague(League league) {
		leagues.add(league);
	}

    public void removeLeague(League league){
        leagues.remove(league);
    }

	public String getName() {
		return this.seasonName;
	}

	public ArrayList<League> getLeagues() {
		return this.leagues;
	}

	public String[] getLeaguesNames() {
		String[] result = new String[this.getLeagues().size()];
		int i = 0;
		for (League league : this.getLeagues()) {
			result[i] = league.getLeagueName();
			i++;
		}
		return result;
	}

	public static void setSeasons(Season[] seasonList) {
		seasons = seasonList;
	}

//	public static void addSeasonByName(String seasonName, Context myContext){
//        LinkedList<Season> seasonTemp = new LinkedList<>(Arrays.asList(Season.getSeasons()));
//		Season newSeason = new Season(seasonName);
//
//		for (League league : seasons[0].getLeagues()) {
//			newSeason.addLeague(league);
//		}
//		newSeason.resetData();
//		seasonTemp.addFirst(newSeason);
//		seasons = seasonTemp.toArray(new Season[]{});
//		Season.setSeasons(seasons);
//		DataAndroidApp.writingToFile(myContext);
//		DataAndroidApp.readingFromFile(myContext);
//	}
//
//    public static void removeSeasonByName(String seasonToDeleteName, Context myContext){
//        LinkedList<Season> seasonTemp = new LinkedList<>(Arrays.asList(Season.getSeasons()));
//
//        seasonTemp.remove(Season.getSeasonFromName(seasonToDeleteName));
//        seasons = seasonTemp.toArray(new Season[seasonTemp.size()]);
//        Season.setSeasons(seasons);
//        DataAndroidApp.writingToFile(myContext);
//        DataAndroidApp.readingFromFile(myContext);
//    }
//
//    public void addLeagueByName(String leagueName, Context myContext){
//        this.addLeague(new League(leagueName));
//        DataAndroidApp.writingToFile(myContext);
//        DataAndroidApp.readingFromFile(myContext);
//    }
//
//    public void removeLeagueByName(String leagueName, Context myContext){
//        this.removeLeague(this.getLeagueFromName(leagueName));
//        DataAndroidApp.writingToFile(myContext);
//        DataAndroidApp.readingFromFile(myContext);
//    }

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

	public static Season[] getSeasons() {
		return seasons;
	}

	public static Season getSeasonFromName(String name) {
		for (Season season : seasons) {
			if (season.getName().equals(name))
				return season;
		}
		return null;
	}

	public static String[] getSeasonsNames() {
		String[] result = new String[seasons.length];
		int i = 0;
		for (Season season : seasons) {
			result[i] = season.getName();
			i++;
		}
		return result;
	}

	public League getLeagueFromName(String name) {
		for (League league : this.leagues) {
			if (league.getLeagueName().equals(name))
				return league;
		}
		return null;
	}

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
