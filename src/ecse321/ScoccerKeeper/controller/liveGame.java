package ecse321.ScoccerKeeper.controller;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
/**
 * 
 * @author Omar
 * 
 * This class manages the events of a lives game
 *
 */
public class liveGame implements gameKeeper {
	private Team teamOne;
	private Team teamTwo;
	private LocalTime startTime;
	private LocalDate dayOfGame;
	private int teamOneGoals;
	private int teamTwoGoals;
	
	/**
	 * 
	 * @param teamone
	 * @param teamtwo
	 * two teams that are playing the game passed from the controller
	 * Selected by the user
	 * 
	 */
	
	public liveGame(Team teamone, Team teamtwo) {
		this.teamOne=teamone;
		this.teamTwo=teamtwo;
		startTime=LocalTime.now();
		dayOfGame=LocalDate.now();
	}
	/**
	 * add shots to the player record
	 * If the shot is goal, it adds it to the game score
	 */
	
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
	 * When the user terminate the game
	 * This method is clalled to display the final result 
	 * it also updates the team record
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
	//Following methods are meant to display golas on the view
	public int teamOneGoals () {
		return teamOneGoals;
	}
	public int teamTwoGoals () {
		return teamTwoGoals;
	}
}
	
		
	



