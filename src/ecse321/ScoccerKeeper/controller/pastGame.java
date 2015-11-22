package ecse321.ScoccerKeeper.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

/**
 * 
 * @author Omar
 * This class updates the teams and the players' records as a results 
 * of  past game
 */
public class pastGame implements gameKeeper {
	private Team teamOne;
	private Team teamTwo;
	private League league;
	private Calendar startTime;
	private Calendar dayOfGame;
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
	 * Past game mode idffers than live game mode in the way the timing is set up
	 * Here the timing is inputted by the user
	 * 
	 */
	
	public pastGame(League league, Team teamone, Team teamtwo,  Calendar Day, Calendar Time) {
		this.teamOne=teamone;
		this.teamTwo=teamtwo;
		this.league=league;
		startTime=Time;
		dayOfGame=Day;
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
	public int teamOneGoals () {
		return teamOneGoals;
	}
	public int teamTwoGoals () {
		return teamTwoGoals;
	}
}

