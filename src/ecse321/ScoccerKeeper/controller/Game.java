package ecse321.ScoccerKeeper.controller;


import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class Game {
	
	private String gameName;
	private Team teamOne;
	private Team teamTwo;
	private Date gameDate;
	private Time startTime;
	private ArrayList<Shot> shots=new ArrayList<>();
	private ArrayList<Infraction> infractions=new ArrayList<>();
	
	public Game(Team team1, Team team2, Date date, Time time) {
		this.teamOne=team1;
		this.teamTwo=team2;
		this.gameName=teamOne.getName()+" VS "+teamTwo.getName();
		this.gameDate=date;
		this.startTime=time;
	}
	
	public void addShot(Shot shot) {
		shots.add(shot);
	}
	
	public ArrayList getShots() {
		return shots;
	}
	public void addInfractions(Infraction infraction) {
		infractions.add(infraction);
	}
	
	public ArrayList getInfractions() {
		return infractions;
	}
	
	

}
