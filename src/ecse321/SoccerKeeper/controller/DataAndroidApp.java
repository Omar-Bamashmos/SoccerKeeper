/*package ecse321.SoccerKeeper.controller;

import android.content.Context;

import com.example.rawad663.ecse321.MainActivity;
import com.example.rawad663.ecse321.ScoreKeeper;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class DataAndroidApp{

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
     *//*

    public static void writingToFile(Context myContext){
        Season[] seasons = Season.getSeasons();
        String fileName = "data.csv";
        File dataFile = new File("data.csv");
        ArrayList<String[]> toPrint = new ArrayList<>();
        for(Season season: seasons){
            toPrint.add(new String[] {"season", season.getName()});
            for(League league: season.getLeagues()){
                toPrint.add(new String[] {"league", league.getLeagueName()});
                for(Team team: league.getTeams()){
                    toPrint.add(new String[] {"team", team.getName(), Integer.toString(team.getNumOfWins()), Integer.toString(team.getNumOfDraws()), Integer.toString(team.getNumOfLosses())});
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
            FileOutputStream fos = myContext.openFileOutput(fileName, Context.MODE_PRIVATE);

            CSVWriter cErase = new CSVWriter(new OutputStreamWriter(fos));
//            CSVWriter cErase = new CSVWriter(new FileWriter(dataFile, false), ',');
            cErase.flush();
            CSVWriter cWrite = new CSVWriter(new OutputStreamWriter(fos), ',');

            for(String[] lineToPrint: toPrint){
                cWrite.writeNext(lineToPrint);
            }
            cWrite.close();
        } catch (IOException e) {	e.printStackTrace();}
    }


    public static Season[] readingFromFile(Context myContext){
        Season[] result;
        //manual reset of data
        if(false){
            result = readingFromFileAsset(myContext);
            writingToFile(myContext);
            return result;
        }

        if(fileIsReal("data.csv", myContext)){
            result = readingFromInternalFile(myContext);
        }
        else{
            result = readingFromFileAsset(myContext);
        }
        return result;
    }

    public static Season[] readingFromFileAsset(Context myContext){	// UNCOMMENT ON ANDROID STUDIO
        CSVReader cRead = null;
        String[] nextLine;
        int j = -1;
//UNCOMMENT ON ANDROID STUDIO

        try {
            cRead = new CSVReader(new InputStreamReader(myContext.getAssets().open("data.csv"), "ISO-8859-1"), ',','"');

        } catch (IOException e) {	e.printStackTrace();}


        List<Season> seasons  = new ArrayList<>();
        try {
            Season currentSeason = null;
            League currentLeague = null;
            Team currentTeam = null;
            while ((nextLine = cRead != null ? cRead.readNext() : new String[0]) != null) {
                if(nextLine[0].equals("season") && nextLine[1]!=(null)){
                    j++;
                    seasons.add(j,new Season(nextLine[1]));
                    currentSeason = seasons.get(j);
                }
                if(nextLine[0].equals("league") && nextLine[1]!=(null)){
                    seasons.get(j).addLeague(new League(nextLine[1]));
                    currentLeague = currentSeason.getLeagues().get(currentSeason.getLeagues().size()-1);
                }
                if(nextLine[0].equals("team") && nextLine[1]!=(null)){
                    Team tempTeam = new Team(nextLine[1]);
                    tempTeam.setNumOfWins(Integer.valueOf(nextLine[2]));
                    tempTeam.setNumOfDraws(Integer.valueOf(nextLine[3]));
                    tempTeam.setNumOfLosses(Integer.valueOf(nextLine[4]));
                    seasons.get(j).getLeagues().get(seasons.get(j).getLeagues().size()-1).addTeams(tempTeam);
                    currentTeam = currentLeague.getTeams().get(currentLeague.getTeams().size()-1);
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
//                        seasons.get(j).getLeagues().get(seasons.get(j).getLeagues().size()-1).getTeams().get(seasons.get(j).getLeagues().get(seasons.get(j).getLeagues().size()-1).getTeams().size()-1).addPlayer(tempPlayer);
                    currentTeam.addPlayer(tempPlayer);
                }

            }
            cRead.close();
        } catch (IOException e) {	e.printStackTrace();}
        Season.setSeasons(seasons.toArray(new Season[seasons.size()]));
        return seasons.toArray(new Season[seasons.size()]);
    }

    public static Season[] readingFromInternalFile(Context myContext){	// UNCOMMENT ON ANDROID STUDIO
        CSVReader cRead = null;
        String[] nextLine;
        int j = -1;
//UNCOMMENT ON ANDROID STUDIO

        try {
            FileInputStream reader = myContext.openFileInput("data.csv");
            cRead = new CSVReader(new InputStreamReader(reader), ',','"');
        } catch (IOException e) {	e.printStackTrace();}


        List<Season> seasons  = new ArrayList<>();
        try {
            Season currentSeason = null;
            League currentLeague = null;
            Team currentTeam = null;
            while ((nextLine = cRead != null ? cRead.readNext() : new String[0]) != null) {
                if(nextLine[0].equals("season") && nextLine[1]!=(null)){
                    j++;
                    seasons.add(j,new Season(nextLine[1]));
                    currentSeason = seasons.get(j);
                }
                if(nextLine[0].equals("league") && nextLine[1]!=(null)){
                    seasons.get(j).addLeague(new League(nextLine[1]));
                    currentLeague = currentSeason.getLeagues().get(currentSeason.getLeagues().size()-1);
                }
                if(nextLine[0].equals("team") && nextLine[1]!=(null)){
                    Team tempTeam = new Team(nextLine[1]);
                    tempTeam.setNumOfWins(Integer.valueOf(nextLine[2]));
                    tempTeam.setNumOfDraws(Integer.valueOf(nextLine[3]));
                    tempTeam.setNumOfLosses(Integer.valueOf(nextLine[4]));
                    seasons.get(j).getLeagues().get(seasons.get(j).getLeagues().size()-1).addTeams(tempTeam);
                    currentTeam = currentLeague.getTeams().get(currentLeague.getTeams().size()-1);
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
//                        seasons.get(j).getLeagues().get(seasons.get(j).getLeagues().size()-1).getTeams().get(seasons.get(j).getLeagues().get(seasons.get(j).getLeagues().size()-1).getTeams().size()-1).addPlayer(tempPlayer);
                    currentTeam.addPlayer(tempPlayer);
                }

            }
            cRead.close();

        } catch (IOException e) {	e.printStackTrace();}
        Season.setSeasons(seasons.toArray(new Season[seasons.size()]));
        return seasons.toArray(new Season[seasons.size()]);
    }

    public static boolean fileIsReal(String fName, Context myContext){
        String path = myContext.getFilesDir().getAbsolutePath() + "/" + fName;
        File file = new File(path);
        return file.exists();
    }
}
/**/