package ecse321.SoccerKeeper.controller;

public interface GameKeeper {
	//public  void addgame(Game game);
	void addShots(Shot shot, Player player, Team team);
	void addInfractions(Infraction infraction, Player player);
	int[] gameResults();
	int getTeamOneGoals();
}
