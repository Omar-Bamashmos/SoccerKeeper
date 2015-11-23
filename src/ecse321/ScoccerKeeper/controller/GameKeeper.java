package ecse321.ScoccerKeeper.controller;

public interface GameKeeper {
	//public  void addgame(Game game);
	public  void addShots(Shot shot, Player player, Team team);
	public void addInfractions(Infraction infraction, Player player);
	public int[] gameResults();
	public int getTeamOneGoals();
}
