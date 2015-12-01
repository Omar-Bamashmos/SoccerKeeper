package ecse321.SoccerKeeper.controller;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Season now = new Season("2015-2016");
		now.addLeague(new League("Liga BBVA"));
		Team temp = new Team("Barcelona");
		Player player1 = new Player("Messi", 10, "Argentine");
		temp.addPlayer(player1);
		now.getLeagues().get(0).addTeams(temp);

		Season now2 = new Season("2013-2014");
		now2.addLeague(new League("Liga BBVA"));
		Team temp2 = new Team("Barcelona");
		Player player2 = new Player("Mascherano", 15, "Argentine");
		temp2.addPlayer(player2);
		now2.getLeagues().get(0).addTeams(temp2);

		Data.writingToFile();
		Data.readingFromFile();
		Season.getSeasonsNames();
	}

}