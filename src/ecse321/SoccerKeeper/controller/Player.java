package ecse321.SoccerKeeper.controller;

import java.util.ArrayList;

/**
 *
 * @author Omar
 *
 * This class stores the data of the player object
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

	public Player (String namePlayer, int jerseyNumber, String nationality) {
		this.name=namePlayer;
		this.jerseyNumber=jerseyNumber;
		this.nationality=nationality;
	}



	/**
	 *
	 * @return player's Name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 *
	 * @return player's jersey number
	 */
	public int getJerseyNumber() {
		return this.jerseyNumber;
	}

	/**
	 *
	 * @param shot
	 * add a shot to the player record
	 */
	public void addShot(Shot shot) {
		shots.add(shot);
	}



	/**
	 *
	 * @param infraction
	 * add infraction to the player record
	 */
	public void addInfraction(Infraction infraction) {
		infractions.add(infraction);

	}

	/**
	 *
	 * @return list of shots
	 */
	public ArrayList<Shot> getShots(){
		return shots;
	}

	/**
	 *
	 * @return list of infractions
	 */
	public ArrayList<Infraction> getInfractions() {
		return infractions;
	}

	/**
	 *
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


	public void setNumOfGoals(int numOfGoals){

		for(int i=0; i<numOfGoals; i++) {
			shots.add(Shot.GOAL);
		}
	}

	public void resetCards(){
		yellowNumCurrent = 0;
		redNumCurrent = 0;
	}

	public void resetDataPlayer(){
		shots.clear();
		infractions.clear();
	}

	/**
	 *
	 * @return number of infractions in the player record
	 */
	public int getNumOfInfractions() {
		if (infractions==null)
			return 0;
		else
			return this.infractions.size();
	}

	public int getNumOfFouls(){
		int i=0;
		for(Infraction infraction: infractions){

			if(infraction.equals(Infraction.FOUL)){
				i++;
			}
		}
		return i;
	}

	public int getNumOfYellow(){
		int i=0;
		for(Infraction infraction: infractions){

			if(infraction.equals(Infraction.YELLOW_CARD)){
				i++;
			}
		}
		return i;
	}

	public int getNumOfRed(){
		int i=0;
		for(Infraction infraction: infractions){

			if(infraction.equals(Infraction.RED_CARD)){
				i++;
			}
		}
		return i;
	}

	public String getNationality(){
		return this.nationality;
	}


	public void incrementYellow(){
		this.yellowNumCurrent++;
	}

	public void incrementRed(){
		this.redNumCurrent++;
	}

	public int getYellow(){
		return yellowNumCurrent;
	}

	public int getRed(){
		return yellowNumCurrent;
	}


}