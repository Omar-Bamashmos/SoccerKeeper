package ecse321.ScoccerKeeper.controller;

import java.awt.List;
import java.util.ArrayList;

/**
 * 
 * @author Omar
 * 
 * This class stores the data of the player object
 *
 */
public class Player {
	private  String Name;
	private int jerseynumber;
	private static String Nationality;
	private ArrayList<Shot> shots= new ArrayList<>();
	private ArrayList <Infraction>infractions= new ArrayList<>();
	
	public Player (String namePlayer, int JN, String nationality) {
		this.Name=namePlayer;
		this.jerseynumber=JN;
		this.Nationality=nationality;
	}
	/**
	 * 
	 * @return player's Name
	 */
	public String getName() {
		return Name;
	}
	
	/**
	 * 
	 * @return player's jersey number
	 */
	public int getJN() {
		return jerseynumber;
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
			if(shot.equals(Shot.Goal))
				numOfGoals++;
		}
		}
		return numOfGoals;	
			
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
	
}
