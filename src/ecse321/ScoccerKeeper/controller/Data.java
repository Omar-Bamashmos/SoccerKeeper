package ecse321.ScoccerKeeper.controller;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

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
	public Data() {

	}

	public void writingToFile(Season[] seasons){
		File dataFile = new File("data.csv");
		ArrayList<String[]> toPrint = new ArrayList<String[]>();
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

	public Season[] readingFromFile(){
		File dataFile = new File("data.csv");
		Season[] seasons = null; // = new Season[100]
		CSVReader cRead = null;
		String[] nextLine;
		int j = -1;

		try {
			cRead = new CSVReader(new FileReader(dataFile), ',','"');
		} catch (IOException e) {	e.printStackTrace();}
		try {
			while ((nextLine = cRead.readNext()) != null) {
				if (nextLine != null) {
					if(nextLine[0].toString().equals("season") && !nextLine[1].equals(null)){
						j++;
						seasons[j] = new Season(nextLine[1].toString());
					}
					if(nextLine[0].toString().equals("league") && !nextLine[1].equals(null)){
						seasons[j].addLeague(new League(nextLine[1]));
					}
					if(nextLine[0].toString().equals("team") && !nextLine[1].equals(null)){
						Team tempTeam = new Team(nextLine[1]);
						seasons[j].getLeagues().get(seasons[j].getLeagues().size()-1).addTeams(tempTeam);
					}
					if(nextLine[0].toString().equals("player") && !nextLine[1].equals(null) 
							&& !nextLine[2].equals(null) && !nextLine[3].equals(null) && !nextLine[4].equals(null)
							&& !nextLine[5].equals(null) && !nextLine[6].equals(null) && !nextLine[7].equals(null)){
						Player tempPlayer = new Player(nextLine[1], Integer.valueOf(nextLine[2]), nextLine[3]);
						for(int i=0; i<Integer.valueOf(nextLine[4]); i++){
							//shots scored/goals
							tempPlayer.addShot(Shot.GOAL);;
						}
						for(int i=0; i<Integer.valueOf(nextLine[5]); i++){
							//shots saved
							tempPlayer.addShot(Shot.SAVED);;
						}
						for(int i=0; i<Integer.valueOf(nextLine[5]); i++){
							//shots missed
							tempPlayer.addShot(Shot.MISSED);;
						}
						for(int i=0; i<Integer.valueOf(nextLine[4]); i++){
							//foul
							tempPlayer.addInfraction(Infraction.FOUL);;
						}
						for(int i=0; i<Integer.valueOf(nextLine[5]); i++){
							//yellow Card
							tempPlayer.addInfraction(Infraction.YELLOW_CARD);;
						}
						for(int i=0; i<Integer.valueOf(nextLine[5]); i++){
							//red Card
							tempPlayer.addInfraction(Infraction.RED_CARD);;
						}
						seasons[j].getLeagues().get(seasons[j].getLeagues().size()-1).getTeams().get(seasons[j].getLeagues().get(seasons[j].getLeagues().size()-1).getTeams().size()-1).addPlayer(tempPlayer);
					}
				}
			}
			cRead.close();
		} catch (IOException e) {	e.printStackTrace();}
		return seasons;
	}

}
