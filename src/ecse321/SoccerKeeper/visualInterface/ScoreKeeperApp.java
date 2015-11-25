package ecse321.SoccerKeeper.visualInterface;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ecse321.SoccerKeeper.controller.Data;
import ecse321.SoccerKeeper.controller.Season;

public class ScoreKeeperApp {

	private JFrame frame;
	private JPanel MainMenu;
	private JPanel ScoreKeeper;
	private static String[] seasonOptions = { "Select Season", "2015-16", "2014-15", "2013-14", "2012-13", "2011-12" };
	private static String[] leagueOptions = { "Select League", "La Liga", "Premier League", "Lige 1", "MLS"};
	private static String[] teamListHome = { "Select Team", "Marcel", "Rawad", "Vivien", "Omar"};
	private String homeTeam = "Select Team";
	private String awayTeam = "Select Team";
	private JComboBox seasonSelect;
	private JComboBox leagueSelect;
	private JComboBox homeTeamSelect;
	private JComboBox awayTeamSelect;
	private JButton btnLiveGame;
	private JButton btnPastGame;
	private JButton btnLeagueAnalysis;
	private JButton btnPlayerAnalysis;
	private JPanel liveGame;
	private JButton btnStartGame;


	/**
	 * Custom Methods.
	 */
	public void updateStartColor(){
		if(homeTeam.equals("Select Team") || awayTeam.equals("Select Team") || homeTeam.equals(awayTeam)){ //checks to make sure two teams are correctly selected
			btnStartGame.setForeground(new Color(255, 0, 0));
		}else{
			btnStartGame.setForeground(new Color(0, 153, 51));
		}
	}


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					ScoreKeeperApp window = new ScoreKeeperApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ScoreKeeperApp() {
		Data.readingFromFile();

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 520, 377);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));

		MainMenu = new JPanel();
		MainMenu.setToolTipText("");
		frame.getContentPane().add(MainMenu, "name_253718586953810");
		MainMenu.setLayout(null);

		JButton btnScorekeeper = new JButton("ScoreKeeper");
		btnScorekeeper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScoreKeeper.setVisible(true);
				MainMenu.setVisible(false);

			}
		});
		btnScorekeeper.setBounds(64, 114, 117, 29);
		MainMenu.add(btnScorekeeper);

		JButton btnManager = new JButton("Manager");
		btnManager.setBounds(313, 114, 117, 29);
		MainMenu.add(btnManager);

		ScoreKeeper = new JPanel();
		frame.getContentPane().add(ScoreKeeper, "name_253723920031894");
		ScoreKeeper.setLayout(null);

		leagueSelect = new JComboBox(leagueOptions);
		leagueSelect.setBounds(41, 73, 141, 27);
		leagueSelect.setSelectedIndex(0);
		leagueSelect.setVisible(false);
		leagueSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
				String seasonNum = (String)cb.getSelectedItem();
				if(seasonNum.equals("Select League")){
					btnLiveGame.setVisible(false);
					btnPastGame.setVisible(false);
					btnLeagueAnalysis.setVisible(false);
					btnPlayerAnalysis.setVisible(false);
				}else{
					btnLiveGame.setVisible(true);
					btnPastGame.setVisible(true);
					btnLeagueAnalysis.setVisible(true);
					btnPlayerAnalysis.setVisible(true);
				}
			}
		});
		ScoreKeeper.add(leagueSelect);

		seasonOptions = Season.getSeasonsNames();
		LinkedList<String> seasonOptionsList = new LinkedList<>(Arrays.asList(seasonOptions));
		seasonOptionsList.addFirst("Select season");
		seasonSelect = new JComboBox(seasonOptionsList.toArray());
		seasonSelect.setSelectedIndex(0);
		seasonSelect.setBounds(41, 34, 141, 27);
		seasonSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
				String seasonNum = (String)cb.getSelectedItem();
				if(seasonNum.equals("Select Season")){
					leagueSelect.setVisible(false);
				}else{
					leagueOptions = Season.getSeasonFromName(seasonNum).getLeaguesNames();
//					leagueSelect.setSelectedItem(leagueOptions);
					leagueSelect.validate();
					leagueSelect.setVisible(true);
				}
			}
		});

		ScoreKeeper.add(seasonSelect);

		btnLiveGame = new JButton("Live Game");
		btnLiveGame.setForeground(Color.RED);
		btnLiveGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScoreKeeper.setVisible(false);
				liveGame.setVisible(true);
			}
		});
		btnLiveGame.setBackground(Color.WHITE);
		btnLiveGame.setBounds(56, 186, 189, 138);
		btnLiveGame.setVisible(false);
		ScoreKeeper.add(btnLiveGame);

		btnPastGame = new JButton("Past Game");
		btnPastGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPastGame.setBounds(273, 186, 189, 138);
		btnPastGame.setVisible(false);
		ScoreKeeper.add(btnPastGame);

		btnLeagueAnalysis = new JButton("League Analysis");
		btnLeagueAnalysis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLeagueAnalysis.setBounds(194, 34, 141, 138);
		btnLeagueAnalysis.setVisible(false);
		ScoreKeeper.add(btnLeagueAnalysis);

		btnPlayerAnalysis = new JButton("Player Analysis");
		btnPlayerAnalysis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPlayerAnalysis.setBounds(347, 34, 141, 139);
		btnPlayerAnalysis.setVisible(false);
		ScoreKeeper.add(btnPlayerAnalysis);

		liveGame = new JPanel();
		liveGame.setVisible(false);
		frame.getContentPane().add(liveGame, "name_261421914189281");
		liveGame.setLayout(null);

		JLabel lblVs = new JLabel("V.S");
		lblVs.setBounds(223, 47, 44, 16);
		lblVs.setHorizontalAlignment(SwingConstants.CENTER);
		liveGame.add(lblVs);

		homeTeamSelect = new JComboBox(teamListHome);
		homeTeamSelect.setBounds(63, 43, 131, 27);
		homeTeamSelect.setSelectedIndex(0);
		homeTeamSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
				homeTeam = (String)cb.getSelectedItem();
				updateStartColor();
			}
		});
		liveGame.add(homeTeamSelect);


		awayTeamSelect = new JComboBox(teamListHome);
		awayTeamSelect.setBounds(298, 43, 131, 27);
		awayTeamSelect.setSelectedIndex(0);
		awayTeamSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
				awayTeam = (String)cb.getSelectedItem();
				updateStartColor();
			}
		});
		liveGame.add(awayTeamSelect);

		btnStartGame = new JButton("Start");
		btnStartGame.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnStartGame.setForeground(new Color(255, 0, 0));
		btnStartGame.setBounds(192, 242, 117, 51);
		btnStartGame.setVisible(true);



		liveGame.add(btnStartGame);


	}
}
