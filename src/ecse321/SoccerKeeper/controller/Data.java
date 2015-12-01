
package ecse321.SoccerKeeper.controller;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Data{

	/*	File format:
	 * 	numberOfSeasons
	 * 	season, seasonName
	 * 	league, leagueName
	 * 	team, teamName
	 * 	player, playerName1, jerseyNumber, nationality, shots scored (goals), shots saved, shots missed, fouls, yellowCard, redCard
	 *  player, playerName2, jerseyNumber, nationality, shots scored (goals), shots saved, shots missed, fouls, yellowCard, redCard
	 * 	league, leagueName
	 * 	team, teamName
	 * 	player, playerName3, jerseyNumber, nationality, shots scored (goals), shots saved, shots missed, fouls, yellowCard, redCard
	 *  player, playerName4, jerseyNumber, nationality, shots scored (goals), shots saved, shots missed, fouls, yellowCard, redCard
	 * 	season, seasonName
	 * 	league, leagueName
	 * 	team, teamName
	 * 	player, playerName5, jerseyNumber, nationality, shots scored (goals), shots saved, shots missed, fouls, yellowCard, redCard
	 *
	 */

	public static void writingToFile(){
		Season[] seasons = Season.getSeasons();
		File dataFile = new File("data.csv");
		ArrayList<String[]> toPrint = new ArrayList<>();
		for(Season season: seasons){
			toPrint.add(new String[] {"season", season.getName()});
			for(League league: season.getLeagues()){
				toPrint.add(new String[] {"league", league.getLeagueName()});
				for(Team team: league.getTeams()){
					toPrint.add(new String[] {"team", team.getName()});
					for(Player player: team.getPlayers()){
						int goals = 0;
						int saves = 0;
						int missed = 0;
						int fouls = 0;
						int yellow_cards = 0;
						int red_cards = 0;
						for(Shot shot: player.getShots()){
							if(shot.equals(Shot.GOAL)){
								goals++;
							}
							else if(shot.equals(Shot.SAVED)){
								saves++;
							}
							else{
								missed++;
							}
						}
						for(Infraction infraction: player.getInfractions()){
							if(infraction.equals(Infraction.FOUL)){
								fouls++;
							}
							else if(infraction.equals(Infraction.YELLOW_CARD)){
								yellow_cards++;
							}
							else{
								red_cards++;
							}
						}
						toPrint.add(new String[] {"player", player.getName(), Integer.toString(player.getJerseyNumber()), player.getNationality(), Integer.toString(goals), Integer.toString(saves), Integer.toString(missed), Integer.toString(fouls), Integer.toString(yellow_cards), Integer.toString(red_cards)});
					}
				}
			}
		}

		try {
			CSVWriter cErase = new CSVWriter(new FileWriter(dataFile, false), ',');
			cErase.flush();
			CSVWriter cWrite = new CSVWriter(new FileWriter(dataFile, true), ',');

			for(String[] lineToPrint: toPrint){
				cWrite.writeNext(lineToPrint);
			}
			cWrite.close();
		} catch (IOException e) {	e.printStackTrace();}
	}

	public static Season[] readingFromFile(){

		File dataFile = new File("data.csv");
		CSVReader cRead = null;
		String[] nextLine;
		int j = -1;

		try {
			cRead = new CSVReader(new FileReader(dataFile), ',','"');

		} catch (IOException e) {	e.printStackTrace();}
		List<Season> seasons  = new ArrayList<>();
		try {

			while ((nextLine = cRead != null ? cRead.readNext() : new String[0]) != null) {
				if(nextLine[0].equals("season") && nextLine[1]!=(null)){
					j++;
					seasons.add(j,new Season(nextLine[1]));
				}
				if(nextLine[0].equals("league") && nextLine[1]!=(null)){
					seasons.get(j).addLeague(new League(nextLine[1]));
				}
				if(nextLine[0].equals("team") && nextLine[1]!=(null)){
					Team tempTeam = new Team(nextLine[1]);
					seasons.get(j).getLeagues().get(seasons.get(j).getLeagues().size()-1).addTeams(tempTeam);
				}
				if(nextLine[0].equals("player") && nextLine[1]!=(null)
						&& nextLine[2]!=(null) && nextLine[3]!=(null) && nextLine[4]!=(null)
						&& nextLine[5]!=(null) && nextLine[6]!=(null) && nextLine[7]!=(null)){
					Player tempPlayer = new Player(nextLine[1], Integer.valueOf(nextLine[2]), nextLine[3]);
					for(int i=0; i<Integer.valueOf(nextLine[4]); i++){
						//shots scored/goals
						tempPlayer.addShot(Shot.GOAL);
					}
					for(int i=0; i<Integer.valueOf(nextLine[5]); i++){
						//shots saved
						tempPlayer.addShot(Shot.SAVED);
					}
					for(int i=0; i<Integer.valueOf(nextLine[5]); i++){
						//shots missed
						tempPlayer.addShot(Shot.MISSED);
					}
					for(int i=0; i<Integer.valueOf(nextLine[4]); i++){
						//foul
						tempPlayer.addInfraction(Infraction.FOUL);
					}
					for(int i=0; i<Integer.valueOf(nextLine[5]); i++){
						//yellow Card
						tempPlayer.addInfraction(Infraction.YELLOW_CARD);
					}
					for(int i=0; i<Integer.valueOf(nextLine[5]); i++){
						//red Card
						tempPlayer.addInfraction(Infraction.RED_CARD);
					}
					seasons.get(j).getLeagues().get(seasons.get(j).getLeagues().size()-1).getTeams().get(seasons.get(j).getLeagues().get(seasons.get(j).getLeagues().size()-1).getTeams().size()-1).addPlayer(tempPlayer);
				}
			}
			cRead.close();
		} catch (IOException e) {	e.printStackTrace();}
		Season.setSeasons(seasons.toArray(new Season[seasons.size()]));
		return seasons.toArray(new Season[seasons.size()]);
	}
}

