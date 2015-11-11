package ecse321.ScoccerKeeper.controller;

import java.awt.List;
import java.util.ArrayList;

public class Player {
	private static String Name;
	private static int jerseynumber;
	private static String Nationality;
	private ArrayList<Shot> shots= new ArrayList<>();
	private ArrayList <Infraction>infractions= new ArrayList<>();
	
	public Player (String namePlayer, int JN, String nationality) {
		this.Name=namePlayer;
		this.jerseynumber=JN;
		this.Nationality=nationality;
	}
	
	public String getName() {
		return Name;
	}
	public int getJN() {
		return jerseynumber;
	}
	
	public void addShot(Shot shot) {
		shots.add(shot);
	}
	
	public void addInfraction(Infraction infraction) {
		infractions.add(infraction);
		
	}
	public ArrayList getShots(){
		return shots;
	}
	public ArrayList getInfractions() {
		return infractions;
	}
	

}
