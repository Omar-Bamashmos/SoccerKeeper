package ecse321.ScoccerKeeper.controller;

import java.util.Calendar;

/**
 * 
 * @author Omar
 * This class updates the teams and the players' records as a results 
 * of  past game
 */
public class PastGame implements GameKeeper {
	private Team teamOne;
	private Team teamTwo;
	private League league;
	private Calendar startTime;
	private Calendar gameDay;
	private int teamOneGoals;
	private int teamTwoGoals;

	/**
	 * 
	 * @param league
	 * @param teamone
	 * @param teamtwo
	 * @param Day
	 * @param Time
	 * 
	 * Past game mode differs than live game mode in the way the timing is set up
	 * Here the timing is inputed by the user
	 * 
	 */

	public PastGame(League league, Team teamOne, Team teamTwo,  Calendar day, Calendar time) {
		this.teamOne=teamOne;
		this.teamTwo=teamTwo;
		this.league=league;
		this.startTime=time;
		this.gameDay=day;
	}

	public void addShots(Shot shot, Player player, Team team) {
		player.addShot(shot);
		if(shot==Shot.Goal) {
			if(team==teamOne)
				teamOneGoals++;
			else
				teamTwoGoals++;
		}	
	}

	public void addInfractions(Infraction infraction, Player player) {
		player.addInfraction(infraction);
	}

	/**
	 * returns the results of a game hwne the user terminates the session
	 * upadates the teams reacords
	 */
	public int[] gameResults() {
		int[] result=new int[2];
		//team one: team on left hand side of the view
		//team two: rght hand side
		result[0]=teamOneGoals;
		result[1]=teamTwoGoals;
		//add to recods
		if(teamOneGoals>teamTwoGoals)
			teamOne.incrementNumOfWins();
		else if (teamOneGoals<teamTwoGoals)
			teamTwo.incrementNumOfWins();
		else {
			teamTwo.incrementNumOfDraws();
			teamOne.incrementNumOfDraws();
		}
		return result;
	}

	//Following methods are meant to display goals on the view
	public int getTeamOneGoals() {
		return teamOneGoals;
	}

	public int getTeamTwoGoals() {
		return teamTwoGoals;
	}

	public League getLeague(){
		return this.league;
	}

	public Calendar getGameDay(){
		return this.gameDay;
	}

	public Calendar getStartTime(){
		return this.startTime;
	}
}

