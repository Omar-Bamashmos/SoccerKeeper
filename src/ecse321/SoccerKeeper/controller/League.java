package ecse321.SoccerKeeper.controller;

//import android.content.Context;

import java.util.ArrayList;

/**
 * @author Omar
 *         <p/>
 *         This class is a component class that stores the data of the league
 *         it has the list of the team playing in the league
 */

public class League {
    private String leagueName;
    private ArrayList<Team> teams = new ArrayList<>();


    /**
     * @param leagueName Create a new league is only allowed to the manager mode
     */
    public League(String leagueName) {
        this.leagueName = leagueName;
    }

    /**
     * @return league name
     */
    public String getLeagueName() {
        return this.leagueName;
    }

    /**
     * @param team adding team to the league teams list
     */
    public void addTeam(Team team) {
        this.teams.add(team);
    }

    public void removeTeam(Team team){
        this.teams.remove(team);
    }

//    public void addTeamByName(String teamName, Context myContext){
//        Team newTeam = new Team(teamName);
//        this.addTeam(newTeam);
//        DataAndroidApp.writingToFile(myContext);
//        DataAndroidApp.readingFromFile(myContext);
//    }
//
//    public void removeTeamByName(String teamName, Context myContext) {
//        this.removeTeam(this.getTeamFromName(teamName));
//        DataAndroidApp.writingToFile(myContext);
//        DataAndroidApp.readingFromFile(myContext);
//    }

    /**
     * @return list of teams
     */
    public ArrayList<Team> getTeams() {
        return this.teams;
    }

    /**
     * @return team names
     */

    public String[] getTeamNames() {
        String[] result = new String[getTeams().size()];
        int i = 0;
        for (Team team : this.getTeams()) {
            result[i] = team.getName();
            i++;
        }
        return result;
    }

    public Team getTeamFromName(String name) {
        for (Team team : teams) {
            if (name.equals(team.getName())) {
                return team;
            }
        }
        return null;
    }


    public void resetData() {
        for (Team team : this.getTeams()) {
            team.setNumOfWins(0);
            team.setNumOfDraws(0);
            team.setNumOfLosses(0);
            for (Player player : team.getPlayers()) {
                player.resetDataPlayer();
            }
        }
    }
}
