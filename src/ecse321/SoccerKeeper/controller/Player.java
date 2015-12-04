package ecse321.SoccerKeeper.controller;

import java.util.ArrayList;

/**
 * Player object contains the info of a player with all its data.
 * @author Vivien
 *
 */
public class Player {
	private  String name;
	private int jerseyNumber;
	private String nationality;
	private ArrayList<Shot> shots= new ArrayList<>();
	private ArrayList<Infraction>infractions= new ArrayList<>();
	private int yellowNumCurrent;
	private int redNumCurrent;

	/**
	 * Constructor for the player class.
	 * @param namePlayer
	 * @param jerseyNumber
	 * @param nationality
	 */
	public Player (String namePlayer, int jerseyNumber, String nationality) {
		this.name=namePlayer;
		this.jerseyNumber=jerseyNumber;
		this.nationality=nationality;
	}



	/**
	 * Returns the name of the current player.
	 * @return player's Name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Returns the jersey number of the current player.
	 * @return player's jersey number
	 */
	public int getJerseyNumber() {
		return this.jerseyNumber;
	}

	/**
	 * Adds a shot to the current player's data.
	 * @param shot
	 */
	public void addShot(Shot shot) {
		shots.add(shot);
	}



	/**
	 * Adds an infraction to the current player's data.
	 * @param infraction
	 */
	public void addInfraction(Infraction infraction) {
		infractions.add(infraction);

	}

	/**
	 * Returns a list of all the shots from the current player.
	 * @return list of shots
	 */
	public ArrayList<Shot> getShots(){
		return shots;
	}

	/**
	 * Returns a list of all the infractions committed by the current player.
	 * @return list of infractions
	 */
	public ArrayList<Infraction> getInfractions() {
		return infractions;
	}

	/**
	 * Returns the number of goals scored by the current player.
	 * @return number of goals
	 *
	 */
	public int getNumOfGoals() {
		int numOfGoals=0;
		if(shots==null)
			return 0;
		else {
			for(Shot shot:shots) {
				if(shot.equals(Shot.GOAL))
					numOfGoals++;
			}
		}
		return numOfGoals;
	}
	
	/**
	 * Returns the number of shots on target made by the current player.
	 * @return number of shots on target
	 */
	public int getNumOfShotsOnTarget() {
		int numOfGoals=0;
		if(shots==null)
			return 0;
		else {
			for(Shot shot:shots) {
				if(shot.equals(Shot.SAVED))
					numOfGoals++;
			}
		}
		return numOfGoals;
	}

	/**
	 * Returns the number of shots off target made by the current player.
	 * @return number of shots off target
	 */
	public int getNumOfShotsOffTarget() {
		int numOfGoals=0;
		if(shots==null)
			return 0;
		else {
			for(Shot shot:shots) {
				if(shot.equals(Shot.MISSED))
					numOfGoals++;
			}
		}
		return numOfGoals;
	}

	/**
	 * Sets the number of goals scored by the current player.
	 * @param numOfGoals
	 */
	public void setNumOfGoals(int numOfGoals){

		for(int i=0; i<numOfGoals; i++) {
			shots.add(Shot.GOAL);
		}
	}
	
	/**
	 * Resets all the temporary cards given to the current player during a game.
	 */
	public void resetCards(){
		yellowNumCurrent = 0;
		redNumCurrent = 0;
	}
	
	/**
	 * Resets all the current player's data.
	 */
	public void resetDataPlayer(){
		shots.clear();
		infractions.clear();
	}

	/**
	 * Returns the number of infractions committed by the current player.
	 * @return number of infractions
	 */
	public int getNumOfInfractions() {
		if (infractions==null)
			return 0;
		else
			return this.infractions.size();
	}
	
	
	/**
	 * Returns the number of fouls committed by the current player.
	 * @return number of fouls
	 */
	public int getNumOfFouls(){
		int i=0;
		for(Infraction infraction: infractions){

			if(infraction.equals(Infraction.FOUL)){
				i++;
			}
		}
		return i;
	}

	/**
	 * Returns the number of yellow cards received by the current player.
	 * @return number of yellow cards
	 */
	public int getNumOfYellow(){
		int i=0;
		for(Infraction infraction: infractions){

			if(infraction.equals(Infraction.YELLOW_CARD)){
				i++;
			}
		}
		return i;
	}

	/**
	 * Returns the number of red cards received by the current player.
	 * @return number of red cards
	 */
	public int getNumOfRed(){
		int i=0;
		for(Infraction infraction: infractions){

			if(infraction.equals(Infraction.RED_CARD)){
				i++;
			}
		}
		return i;
	}

	/**
	 * Returns a string containing the nationality of the current player.
	 * @return the nationality of the current player.
	 */
	public String getNationality(){
		return this.nationality;
	}

	/**
	 * Increment the number of yellow cards received by the current player during a match.
	 */
	public void incrementYellow(){
		this.yellowNumCurrent++;
	}

	/**
	 * Increment the number of red cards received by the current player during a match.
	 */
	public void incrementRed(){
		this.redNumCurrent++;
	}

	/**
	 * Returns the number of yellow cards that the current player has received during a match.
	 * @return number of yellow cards
	 */
	public int getYellow(){
		return yellowNumCurrent;
	}
	
	
	/**
	 * Returns the number of red cards that the current player has received during a match.
	 * @return number of red cards
	 */
	public int getRed(){
		return yellowNumCurrent;
	}


}