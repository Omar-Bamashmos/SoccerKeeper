package ecse321.SoccerKeeper.desktopAppGUI.ScoreKeepingApplication.src.application;

import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;

import java.awt.CardLayout;

import javax.swing.JLayeredPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;

import java.awt.BorderLayout;

import javax.swing.SwingConstants;
import javax.swing.DropMode;

import javax.swing.JList;
import javax.swing.JComboBox;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;

import javax.swing.SpringLayout;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.JProgressBar;
import javax.swing.JPasswordField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import ecse321.SoccerKeeper.controller.*;

import javax.swing.JTable;
import javax.swing.border.CompoundBorder;
import javax.swing.UIManager;
import javax.swing.JScrollPane;

public class ScoreKeeperApp {

	private JFrame frame;
	private JFrame confirmationOption;
	private JPanel MainMenu;
	private JPanel ScoreKeeper;
	private String[] teamListHome = { "Select Team", "Marcel", "Rawad", "Vivien", "Omar"};
	private String[] homeRoster = { "Sam", "Marcel", "Rawad", "Vivien", "Omar"};
	private String[] awayTeamCards = { "", "", "", "", ""};
	private String[] homeTeamCards = { "", "", "", "", ""};
	private String[] leagueNames;
	private Season currentSeason;
	private League currentLeague;
	private String[] teamNames;
	private Team currentHomeTeam;
	private Team currentAwayTeam;
	private String[] homePlayersNames;
	private String[] awayPlayersNames;
	private Player[] homeTeamPlayers;
	private Player[] awayTeamPlayers;


	//	private Vector columnNamesV = new Vector(Arrays.asList(homeRoster));
	//	private Vector rowNamesV = new Vector(Arrays.asList(teamListHome));

	private Season[] seasonList;

	private String[] awayRoster = { "Thomas", "Garret", "Ben", "Mikal", "David"};
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
	private JPanel liveGameSelect;
	private JButton btnStartGame;
	private JLabel TimeLabel;
	private JPanel LiveGame;

	private JLabel label_1;
	private Timer gameTimer;
	private int timerCount = 0;
	private JProgressBar gameProgressBar;
	private JButton btn2ndHalf;
	private String time;
	private JLabel lblTimeCOUNTER;
	private JButton btnLiveShotOnTarget;
	private JButton btnAddLiveGoal;
	private JButton btnLiveFoul;
	private JButton btnAddYellowCard;
	private JButton btnShotOffTarget;
	private JButton btnRedCard;
	private JLabel lbluserCommand;
	private JButton btnBackButton;
	private JButton btnScoreKeeperOptions;
	private JButton btnTeamSelect;
	private JLabel lblhomeTeamName;
	private JLabel lnlawayTeamName;
	private JPanel finishConfirmation;
	private JList awayTeamRoster;
	private JList homeTeamRoster;
	private String liveCommand = "User Command";
	private int indexPicked = 0;
	private JPanel pastGame;
	private JTextField textField;
	private JPanel playerAnalysis;
	private JTable table;
	private JScrollPane scrollPane;


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

	public void clearListSelections(){
		awayTeamRoster.clearSelection();
		homeTeamRoster.clearSelection();
		liveCommand = "User Command";
		lbluserCommand.setText("\"User Command\"");
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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		//adding all season
		seasonList = Data.readingFromFile();


		frame = new JFrame();
		frame.setBounds(100, 100, 520, 377);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700,500);
		//frame.setResizable(false);
		frame.getContentPane().setLayout(new CardLayout(0, 0));

		MainMenu = new JPanel();
		MainMenu.setToolTipText("");
		frame.getContentPane().add(MainMenu, "name_253718586953810");
		GridBagLayout gbl_MainMenu = new GridBagLayout();
		gbl_MainMenu.columnWidths = new int[]{166, 132, 117, 0};
		gbl_MainMenu.rowHeights = new int[]{114, 80, 29, 0};
		gbl_MainMenu.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_MainMenu.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		MainMenu.setLayout(gbl_MainMenu);

		JButton btnScorekeeper = new JButton("ScoreKeeper");
		btnScorekeeper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScoreKeeper.setVisible(true);
				MainMenu.setVisible(false);

			}
		});
		GridBagConstraints gbc_btnScorekeeper = new GridBagConstraints();
		gbc_btnScorekeeper.insets = new Insets(0, 0, 5, 5);
		gbc_btnScorekeeper.gridx = 0;
		gbc_btnScorekeeper.gridy = 1;
		MainMenu.add(btnScorekeeper, gbc_btnScorekeeper);

		JButton btnManager = new JButton("Manager");
		GridBagConstraints gbc_btnManager = new GridBagConstraints();
		gbc_btnManager.insets = new Insets(0, 0, 5, 0);
		gbc_btnManager.gridx = 2;
		gbc_btnManager.gridy = 1;
		MainMenu.add(btnManager, gbc_btnManager);

		ScoreKeeper = new JPanel();
		frame.getContentPane().add(ScoreKeeper, "name_253723920031894");
		GridBagLayout gbl_ScoreKeeper = new GridBagLayout();
		gbl_ScoreKeeper.columnWidths = new int[]{19, 140, 156, 62, 141, 0};
		gbl_ScoreKeeper.rowHeights = new int[]{34, 27, 152, 138, 0};
		gbl_ScoreKeeper.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_ScoreKeeper.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		ScoreKeeper.setLayout(gbl_ScoreKeeper);

		btnLiveGame = new JButton("Live Game");
		btnLiveGame.setForeground(Color.RED);
		btnLiveGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScoreKeeper.setVisible(false);
				liveGameSelect.setVisible(true);
			}
		});
		btnLiveGame.setBackground(Color.WHITE);
		btnLiveGame.setVisible(false);

		btnPlayerAnalysis = new JButton("Player Analysis");
		btnPlayerAnalysis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScoreKeeper.setVisible(false);
				playerAnalysis.setVisible(true);
			}
		});
		btnPlayerAnalysis.setVisible(false);

		String[] seasonNames = Season.getSeasonsNames();
		LinkedList<String> tempStringSeasons = new LinkedList<>(Arrays.asList(seasonNames));
		tempStringSeasons.addFirst("Select Season");
		seasonNames = tempStringSeasons.toArray(new String[]{});
		final String lastSeasonName = seasonNames[1];
		seasonSelect = new JComboBox(seasonNames);
		seasonSelect.setSelectedIndex(0);
		seasonSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
				String seasonNum = (String)cb.getSelectedItem();
				if(seasonNum.equals("Select Season")){
					leagueSelect.setVisible(false);
					btnLiveGame.setVisible(false);
					btnPastGame.setVisible(false);
					btnLeagueAnalysis.setVisible(false);
					btnPlayerAnalysis.setVisible(false);
				} else if(seasonNum.equals(lastSeasonName)){
					currentSeason = Season.getSeasonFromName(seasonNum);

					leagueNames = currentSeason.getLeaguesNames();
					LinkedList<String> tempStringLeagues = new LinkedList<>(Arrays.asList(leagueNames));
					tempStringLeagues.addFirst("Select League");
					leagueNames = tempStringLeagues.toArray(new String[]{});
					btnLiveGame.setEnabled(true);
					leagueSelect.setVisible(true);
					DefaultComboBoxModel model = new DefaultComboBoxModel(leagueNames);
					leagueSelect.setModel(model);
					leagueSelect.setSelectedIndex(0);
				} else {
					currentSeason = Season.getSeasonFromName(seasonNum);

					leagueNames = currentSeason.getLeaguesNames();
					LinkedList<String> tempStringLeagues = new LinkedList<>(Arrays.asList(leagueNames));
					tempStringLeagues.addFirst("Select League");
					leagueNames = tempStringLeagues.toArray(new String[]{});
					btnLiveGame.setEnabled(false);
					leagueSelect.setVisible(true);
					DefaultComboBoxModel model = new DefaultComboBoxModel(leagueNames);
					leagueSelect.setModel(model);
					leagueSelect.setSelectedIndex(0);
				}
			}
		});

		btnBackButton = new JButton("<- Sign In");
		btnBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScoreKeeper.setVisible(false);
				MainMenu.setVisible(true);
				liveGameSelect.setVisible(false);
				LiveGame.setVisible(false);
			}
		});
		GridBagConstraints gbc_btnBackButton = new GridBagConstraints();
		gbc_btnBackButton.anchor = GridBagConstraints.WEST;
		gbc_btnBackButton.gridwidth = 2;
		gbc_btnBackButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnBackButton.gridx = 0;
		gbc_btnBackButton.gridy = 0;
		ScoreKeeper.add(btnBackButton, gbc_btnBackButton);

		GridBagConstraints gbc_seasonSelect = new GridBagConstraints();
		gbc_seasonSelect.anchor = GridBagConstraints.NORTHWEST;
		gbc_seasonSelect.insets = new Insets(0, 0, 5, 5);
		gbc_seasonSelect.gridx = 1;
		gbc_seasonSelect.gridy = 1;
		ScoreKeeper.add(seasonSelect, gbc_seasonSelect);

		btnLeagueAnalysis = new JButton("League Analysis");
		btnLeagueAnalysis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLeagueAnalysis.setVisible(false);
		GridBagConstraints gbc_btnLeagueAnalysis = new GridBagConstraints();
		gbc_btnLeagueAnalysis.fill = GridBagConstraints.BOTH;
		gbc_btnLeagueAnalysis.insets = new Insets(0, 0, 5, 5);
		gbc_btnLeagueAnalysis.gridheight = 2;
		gbc_btnLeagueAnalysis.gridwidth = 2;
		gbc_btnLeagueAnalysis.gridx = 2;
		gbc_btnLeagueAnalysis.gridy = 1;
		ScoreKeeper.add(btnLeagueAnalysis, gbc_btnLeagueAnalysis);
		GridBagConstraints gbc_btnPlayerAnalysis = new GridBagConstraints();
		gbc_btnPlayerAnalysis.fill = GridBagConstraints.BOTH;
		gbc_btnPlayerAnalysis.insets = new Insets(0, 0, 5, 0);
		gbc_btnPlayerAnalysis.gridheight = 2;
		gbc_btnPlayerAnalysis.gridx = 4;
		gbc_btnPlayerAnalysis.gridy = 1;
		ScoreKeeper.add(btnPlayerAnalysis, gbc_btnPlayerAnalysis);

		leagueSelect = new JComboBox();
		leagueSelect.setVisible(false);
		leagueSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
				String leagueSelected = (String)cb.getSelectedItem();
				if(leagueSelected.equals("Select League")){
					btnLiveGame.setVisible(false);
					btnPastGame.setVisible(false);
					btnLeagueAnalysis.setVisible(false);
					btnPlayerAnalysis.setVisible(false);
				} else{
					System.out.println("Selected League: "+leagueSelected);
					currentLeague = currentSeason.getLeagueFromName(leagueSelected);
					btnLiveGame.setVisible(true);
					btnPastGame.setVisible(true);
					btnLeagueAnalysis.setVisible(true);
					btnPlayerAnalysis.setVisible(true);
					teamNames = currentLeague.getTeamNames();
					LinkedList<String> tempStringTeams = new LinkedList<>(Arrays.asList(teamNames));
					tempStringTeams.addFirst("Select Team");
					teamNames = tempStringTeams.toArray(new String[]{});
					DefaultComboBoxModel modelHomeTeams = new DefaultComboBoxModel(teamNames);
					DefaultComboBoxModel modelAwayTeams = new DefaultComboBoxModel(teamNames);
					awayTeamSelect.setModel(modelAwayTeams);
					homeTeamSelect.setModel(modelHomeTeams);
				}
			}
		});
		GridBagConstraints gbc_leagueSelect = new GridBagConstraints();
		gbc_leagueSelect.anchor = GridBagConstraints.NORTH;
		gbc_leagueSelect.fill = GridBagConstraints.HORIZONTAL;
		gbc_leagueSelect.insets = new Insets(0, 0, 5, 5);
		gbc_leagueSelect.gridx = 1;
		gbc_leagueSelect.gridy = 2;
		ScoreKeeper.add(leagueSelect, gbc_leagueSelect);
		GridBagConstraints gbc_btnLiveGame = new GridBagConstraints();
		gbc_btnLiveGame.fill = GridBagConstraints.BOTH;
		gbc_btnLiveGame.insets = new Insets(0, 0, 0, 5);
		gbc_btnLiveGame.gridwidth = 2;
		gbc_btnLiveGame.gridx = 1;
		gbc_btnLiveGame.gridy = 3;
		ScoreKeeper.add(btnLiveGame, gbc_btnLiveGame);

		btnPastGame = new JButton("Past Game");
		btnPastGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScoreKeeper.setVisible(false);
				liveGameSelect.setVisible(false);
				pastGame.setVisible(true);

			}
		});
		btnPastGame.setVisible(false);
		GridBagConstraints gbc_btnPastGame = new GridBagConstraints();
		gbc_btnPastGame.fill = GridBagConstraints.BOTH;
		gbc_btnPastGame.gridwidth = 2;
		gbc_btnPastGame.gridx = 3;
		gbc_btnPastGame.gridy = 3;
		ScoreKeeper.add(btnPastGame, gbc_btnPastGame);

		liveGameSelect = new JPanel();
		liveGameSelect.setVisible(false);
		frame.getContentPane().add(liveGameSelect, "name_261421914189281");
		GridBagLayout gbl_liveGameSelect = new GridBagLayout();
		gbl_liveGameSelect.columnWidths = new int[]{24, 208, 217, 237, 0};
		gbl_liveGameSelect.rowHeights = new int[]{0, 43, 27, 149, 97, 157, 0};
		gbl_liveGameSelect.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_liveGameSelect.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		liveGameSelect.setLayout(gbl_liveGameSelect);


		awayTeamSelect = new JComboBox();
		awayTeamSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
				awayTeam = (String)cb.getSelectedItem();
				if(!cb.equals("Select Team")){
					currentAwayTeam = currentLeague.getTeamFromName(awayTeam);
					awayPlayersNames = currentAwayTeam.getPlayersNames();
					DefaultComboBoxModel modelAwayPlayers = new DefaultComboBoxModel(awayPlayersNames);
					awayTeamRoster.setModel(modelAwayPlayers);
					awayTeamRoster.setSelectedIndex(0);
				}
				lnlawayTeamName.setText(awayTeam);
				updateStartColor();
			}
		});

		homeTeamSelect = new JComboBox();
		homeTeamSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
				homeTeam = (String)cb.getSelectedItem();
				if(!cb.equals("Select Team")){
					currentHomeTeam = currentLeague.getTeamFromName(homeTeam);
					homePlayersNames = currentHomeTeam.getPlayersNames();
					DefaultComboBoxModel modelHomePlayers = new DefaultComboBoxModel(homePlayersNames);
					homeTeamRoster.setModel(modelHomePlayers);
					homeTeamRoster.setSelectedIndex(0);
				}
				lblhomeTeamName.setText(homeTeam);
				updateStartColor();
			}
		});

		btnScoreKeeperOptions = new JButton("<- ScoreKeeper Options");
		btnScoreKeeperOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScoreKeeper.setVisible(true);
				MainMenu.setVisible(false);
				liveGameSelect.setVisible(false);
				LiveGame.setVisible(false);
			}
		});
		GridBagConstraints gbc_btnScoreKeeperOptions = new GridBagConstraints();
		gbc_btnScoreKeeperOptions.gridwidth = 2;
		gbc_btnScoreKeeperOptions.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnScoreKeeperOptions.insets = new Insets(0, 0, 5, 5);
		gbc_btnScoreKeeperOptions.gridx = 0;
		gbc_btnScoreKeeperOptions.gridy = 0;
		liveGameSelect.add(btnScoreKeeperOptions, gbc_btnScoreKeeperOptions);
		GridBagConstraints gbc_homeTeamSelect = new GridBagConstraints();
		gbc_homeTeamSelect.anchor = GridBagConstraints.NORTH;
		gbc_homeTeamSelect.insets = new Insets(0, 0, 5, 5);
		gbc_homeTeamSelect.gridx = 1;
		gbc_homeTeamSelect.gridy = 2;
		liveGameSelect.add(homeTeamSelect, gbc_homeTeamSelect);

		JLabel lblVs = new JLabel("V.S");
		lblVs.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblVs = new GridBagConstraints();
		gbc_lblVs.insets = new Insets(0, 0, 5, 5);
		gbc_lblVs.gridx = 2;
		gbc_lblVs.gridy = 2;
		liveGameSelect.add(lblVs, gbc_lblVs);
		GridBagConstraints gbc_awayTeamSelect = new GridBagConstraints();
		gbc_awayTeamSelect.anchor = GridBagConstraints.NORTH;
		gbc_awayTeamSelect.insets = new Insets(0, 0, 5, 0);
		gbc_awayTeamSelect.gridx = 3;
		gbc_awayTeamSelect.gridy = 2;
		liveGameSelect.add(awayTeamSelect, gbc_awayTeamSelect);

		btnStartGame = new JButton("Start");
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(homeTeam.equals("Select Team") || awayTeam.equals("Select Team") || homeTeam.equals(awayTeam)){ //checks to make sure two teams are correctly selected
					ScoreKeeper.setVisible(false);
					MainMenu.setVisible(false);
					liveGameSelect.setVisible(true);
					LiveGame.setVisible(false);
				}else{
					currentHomeTeam = currentLeague.getTeamFromName(homeTeam);
					currentAwayTeam = currentLeague.getTeamFromName(awayTeam);
					ScoreKeeper.setVisible(false);
					MainMenu.setVisible(false);
					liveGameSelect.setVisible(false);
					LiveGame.setVisible(true); //shows live game panel
					gameTimer.restart(); //should also start timer
				}
			}
		});
		btnStartGame.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnStartGame.setForeground(new Color(255, 0, 0));
		btnStartGame.setVisible(true);

		TimeLabel = new JLabel("00:00");
		TimeLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		TimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_TimeLabel = new GridBagConstraints();
		gbc_TimeLabel.anchor = GridBagConstraints.SOUTH;
		gbc_TimeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_TimeLabel.gridx = 2;
		gbc_TimeLabel.gridy = 4;
		liveGameSelect.add(TimeLabel, gbc_TimeLabel);
		GridBagConstraints gbc_btnStartGame = new GridBagConstraints();
		gbc_btnStartGame.insets = new Insets(0, 0, 0, 5);
		gbc_btnStartGame.gridx = 2;
		gbc_btnStartGame.gridy = 5;
		liveGameSelect.add(btnStartGame, gbc_btnStartGame);

		LiveGame = new JPanel();
		frame.getContentPane().add(LiveGame, "name_265429526509258");
		GridBagLayout gbl_LiveGame = new GridBagLayout();
		gbl_LiveGame.columnWidths = new int[]{226, 8, 155, 134, 153, -1, 227, 0};
		gbl_LiveGame.rowHeights = new int[]{20, 44, 0, 35, 0, 98, 98, 98, 98, 0};
		gbl_LiveGame.columnWeights = new double[]{1.0, 1.0, 0.0, 1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_LiveGame.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		LiveGame.setLayout(gbl_LiveGame);

		btnTeamSelect = new JButton("<- Team Select");
		btnTeamSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScoreKeeper.setVisible(false);
				MainMenu.setVisible(false);
				liveGameSelect.setVisible(true);
				LiveGame.setVisible(false);
				gameTimer.stop();
				timerCount = 0;
				btn2ndHalf.setVisible(false);
				btn2ndHalf.setText("2nd Half");
				lblTimeCOUNTER.setText("00:00");
				gameProgressBar.setValue(0);

			}
		});
		btnTeamSelect.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		GridBagConstraints gbc_btnTeamSelect = new GridBagConstraints();
		gbc_btnTeamSelect.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnTeamSelect.insets = new Insets(0, 0, 5, 5);
		gbc_btnTeamSelect.gridx = 0;
		gbc_btnTeamSelect.gridy = 0;
		LiveGame.add(btnTeamSelect, gbc_btnTeamSelect);

		gameProgressBar = new JProgressBar(0,10);
		gameProgressBar.setBackground(Color.white);
		gameProgressBar.setForeground(Color.GREEN);

		gameProgressBar.setValue(0);
		GridBagConstraints gbc_gameProgressBar = new GridBagConstraints();
		gbc_gameProgressBar.fill = GridBagConstraints.HORIZONTAL;
		gbc_gameProgressBar.anchor = GridBagConstraints.NORTH;
		gbc_gameProgressBar.insets = new Insets(0, 0, 5, 5);
		gbc_gameProgressBar.gridwidth = 5;
		gbc_gameProgressBar.gridx = 1;
		gbc_gameProgressBar.gridy = 0;
		LiveGame.add(gameProgressBar, gbc_gameProgressBar);

		btn2ndHalf = new JButton("2nd Half");
		btn2ndHalf.setVisible(false);
		btn2ndHalf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btn2ndHalf.getText().equals("2nd Half")){
					gameTimer.start();
					btn2ndHalf.setVisible(false);
				}else {

					Object[] options = {"Cancel",
					"Yes, confirm"};

					int s =  JOptionPane.showOptionDialog(
							finishConfirmation,"Are you sure you want to confirm Live Game information? "
									+ "\nConfirmation will process game data into League and Player Analysis",
									"Game confirmation",
									JOptionPane.YES_NO_OPTION,
									JOptionPane.QUESTION_MESSAGE,
									null,
									options,
									options[1]);
					//needs to confirm game data and submit to server
					if (s > 0){ //if Yes, confirm is selected
						//upload data to server
						ScoreKeeper.setVisible(true);
						MainMenu.setVisible(false);
						liveGameSelect.setVisible(false);
						LiveGame.setVisible(false);
						gameTimer.stop();
						timerCount = 0;
						btn2ndHalf.setVisible(false);
						btn2ndHalf.setText("2nd Half");
						lblTimeCOUNTER.setText("00:00");
						gameProgressBar.setValue(0); //reset all page components
						Data.writingToFile();
						currentHomeTeam.resetCards();
						currentAwayTeam.resetCards();
						System.out.println("Confirmation Given");



					}else{//close window and do nothing
						System.out.println("Confirmation Cancelled");
					}
				}
			}
		});
		GridBagConstraints gbc_btn2ndHalf = new GridBagConstraints();
		gbc_btn2ndHalf.insets = new Insets(0, 0, 5, 5);
		gbc_btn2ndHalf.gridx = 3;
		gbc_btn2ndHalf.gridy = 1;
		LiveGame.add(btn2ndHalf, gbc_btn2ndHalf);



		lblTimeCOUNTER = new JLabel("00:00");
		GridBagConstraints gbc_lblTimeCOUNTER = new GridBagConstraints();
		gbc_lblTimeCOUNTER.insets = new Insets(0, 0, 5, 5);
		gbc_lblTimeCOUNTER.gridx = 3;
		gbc_lblTimeCOUNTER.gridy = 2;
		LiveGame.add(lblTimeCOUNTER, gbc_lblTimeCOUNTER);



		JLabel lblhomeTeamScore = new JLabel("0");
		lblhomeTeamScore.setFont(new Font("Lucida Grande", Font.PLAIN, 27));
		GridBagConstraints gbc_lblhomeTeamScore = new GridBagConstraints();
		gbc_lblhomeTeamScore.fill = GridBagConstraints.VERTICAL;
		gbc_lblhomeTeamScore.insets = new Insets(0, 0, 5, 5);
		gbc_lblhomeTeamScore.gridx = 2;
		gbc_lblhomeTeamScore.gridy = 3;
		LiveGame.add(lblhomeTeamScore, gbc_lblhomeTeamScore);

		label_1 = new JLabel("|");
		label_1.setFont(new Font("Monotype Sorts", Font.PLAIN, 25));
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 3;
		gbc_label_1.gridy = 3;
		LiveGame.add(label_1, gbc_label_1);

		JLabel lblawayTeamScore = new JLabel("0");
		lblawayTeamScore.setFont(new Font("Lucida Grande", Font.PLAIN, 27));
		GridBagConstraints gbc_lblawayTeamScore = new GridBagConstraints();
		gbc_lblawayTeamScore.fill = GridBagConstraints.VERTICAL;
		gbc_lblawayTeamScore.insets = new Insets(0, 0, 5, 5);
		gbc_lblawayTeamScore.gridx = 4;
		gbc_lblawayTeamScore.gridy = 3;
		LiveGame.add(lblawayTeamScore, gbc_lblawayTeamScore);

		gameTimer = new Timer(1000, new ActionListener() { //timer Used for game progress
			public void actionPerformed(ActionEvent e) {
				long s = (timerCount) % 60;
				long m = ((timerCount) / 60) % 60;
				time = String.format("%02d:%02d", m, s);
				lblTimeCOUNTER.setText(time);


				if (timerCount == 10){
					gameTimer.stop();
					gameProgressBar.setValue(timerCount);
					btn2ndHalf.setText("Finish");
					btn2ndHalf.setVisible(true);
				}else if(timerCount == 5){
					btn2ndHalf.setVisible(true);
					gameTimer.stop();
					gameProgressBar.setValue(timerCount);
					timerCount++;
				}else{

					gameProgressBar.setValue(timerCount);
					timerCount++;
				}

			}
		});

		lblhomeTeamName = new JLabel("Home Team");
		GridBagConstraints gbc_lblhomeTeamName = new GridBagConstraints();
		gbc_lblhomeTeamName.anchor = GridBagConstraints.EAST;
		gbc_lblhomeTeamName.fill = GridBagConstraints.VERTICAL;
		gbc_lblhomeTeamName.insets = new Insets(0, 0, 5, 5);
		gbc_lblhomeTeamName.gridx = 0;
		gbc_lblhomeTeamName.gridy = 3;
		LiveGame.add(lblhomeTeamName, gbc_lblhomeTeamName);

		lnlawayTeamName = new JLabel("Away Team");
		GridBagConstraints gbc_lnlawayTeamName = new GridBagConstraints();
		gbc_lnlawayTeamName.anchor = GridBagConstraints.WEST;
		gbc_lnlawayTeamName.insets = new Insets(0, 0, 5, 0);
		gbc_lnlawayTeamName.gridx = 6;
		gbc_lnlawayTeamName.gridy = 3;
		LiveGame.add(lnlawayTeamName, gbc_lnlawayTeamName);

		lbluserCommand = new JLabel("\"User Command\"");
		GridBagConstraints gbc_lbluserCommand = new GridBagConstraints();
		gbc_lbluserCommand.gridwidth = 3;
		gbc_lbluserCommand.insets = new Insets(0, 0, 5, 5);
		gbc_lbluserCommand.gridx = 2;
		gbc_lbluserCommand.gridy = 4;
		LiveGame.add(lbluserCommand, gbc_lbluserCommand);

		btnAddLiveGoal = new JButton("Goal");
		btnAddLiveGoal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearListSelections();
				lbluserCommand.setText("\"Select Scorer\"");
				liveCommand = "Goal";
				System.out.println("Pressed: "+liveCommand); 
			}
		});
		GridBagConstraints gbc_btnAddLiveGoal = new GridBagConstraints();
		gbc_btnAddLiveGoal.fill = GridBagConstraints.BOTH;
		gbc_btnAddLiveGoal.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddLiveGoal.gridx = 2;
		gbc_btnAddLiveGoal.gridy = 5;
		LiveGame.add(btnAddLiveGoal, gbc_btnAddLiveGoal);

		btnLiveFoul = new JButton("Foul");
		btnLiveFoul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearListSelections();
				lbluserCommand.setText("\"Select Player who commited foul\"");
				liveCommand = "Foul";
			}
		});
		GridBagConstraints gbc_btnLiveFoul = new GridBagConstraints();
		gbc_btnLiveFoul.fill = GridBagConstraints.BOTH;
		gbc_btnLiveFoul.insets = new Insets(0, 0, 5, 5);
		gbc_btnLiveFoul.gridx = 4;
		gbc_btnLiveFoul.gridy = 5;
		LiveGame.add(btnLiveFoul, gbc_btnLiveFoul);

		btnLiveShotOnTarget = new JButton("Shot on Target");
		GridBagConstraints gbc_btnLiveShotOnTarget = new GridBagConstraints();
		gbc_btnLiveShotOnTarget.fill = GridBagConstraints.BOTH;
		gbc_btnLiveShotOnTarget.insets = new Insets(0, 0, 5, 5);
		gbc_btnLiveShotOnTarget.gridx = 2;
		gbc_btnLiveShotOnTarget.gridy = 6;
		LiveGame.add(btnLiveShotOnTarget, gbc_btnLiveShotOnTarget);

		btnAddYellowCard = new JButton("Yellow Card");
		btnAddYellowCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearListSelections();
				lbluserCommand.setText("\"Select Player to give Yellow Card\"");
				liveCommand = "Yellow";
			}
		});
		GridBagConstraints gbc_btnAddYellowCard = new GridBagConstraints();
		gbc_btnAddYellowCard.fill = GridBagConstraints.BOTH;
		gbc_btnAddYellowCard.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddYellowCard.gridx = 4;
		gbc_btnAddYellowCard.gridy = 6;
		LiveGame.add(btnAddYellowCard, gbc_btnAddYellowCard);

		homeTeamRoster = new JList();
		homeTeamRoster.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		GridBagConstraints gbc_homeTeamRoster = new GridBagConstraints();
		gbc_homeTeamRoster.gridheight = 4;
		gbc_homeTeamRoster.fill = GridBagConstraints.BOTH;
		gbc_homeTeamRoster.insets = new Insets(0, 0, 0, 5);
		gbc_homeTeamRoster.gridx = 0;
		gbc_homeTeamRoster.gridy = 5;		
		homeTeamRoster.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e) {
				if ( !homeTeamRoster.getValueIsAdjusting() && homeTeamRoster.getSelectedIndex() >=0 ){ //need to check for index in the bounds of the List, otherwise you get random errors
					if(liveCommand.equals("User Command")){


					}else if(liveCommand.equals("Goal")){
						String playerName = homeTeamRoster.getSelectedValue().toString();
						Player playerSelected = currentHomeTeam.getPlayerFromName(playerName);
						playerSelected.addShot(Shot.GOAL);
						int playerScoredIndex = homeTeamRoster.getSelectedIndex();
						int goals = Integer.parseInt(lblhomeTeamScore.getText());
						String newScore = String.valueOf(goals+1);
						lblhomeTeamScore.setText(newScore);

					}else if(liveCommand.equals("Yellow")){
						String playerName = homeTeamRoster.getSelectedValue().toString();
						Player playerSelected = currentHomeTeam.getPlayerFromName(playerName);
						playerSelected.addInfraction(Infraction.YELLOW_CARD);
						//						playerSelected.getYellow();

					}else if(liveCommand.equals("Red")){
						String playerName = homeTeamRoster.getSelectedValue().toString();
						Player playerSelected = currentHomeTeam.getPlayerFromName(playerName);
						playerSelected.addInfraction(Infraction.RED_CARD);

					}
					clearListSelections();
				}






			}

		});
		LiveGame.add(homeTeamRoster, gbc_homeTeamRoster);

		btnShotOffTarget = new JButton("Shot off Target");
		GridBagConstraints gbc_btnShotOffTarget = new GridBagConstraints();
		gbc_btnShotOffTarget.fill = GridBagConstraints.BOTH;
		gbc_btnShotOffTarget.insets = new Insets(0, 0, 5, 5);
		gbc_btnShotOffTarget.gridx = 2;
		gbc_btnShotOffTarget.gridy = 7;
		LiveGame.add(btnShotOffTarget, gbc_btnShotOffTarget);

		awayTeamRoster = new JList();
		awayTeamRoster.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		GridBagConstraints gbc_awayTeamRoster = new GridBagConstraints();
		gbc_awayTeamRoster.gridheight = 4;
		gbc_awayTeamRoster.fill = GridBagConstraints.BOTH;
		gbc_awayTeamRoster.gridx = 6;
		gbc_awayTeamRoster.gridy = 5;
		awayTeamRoster.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e) {
				if ( !awayTeamRoster.getValueIsAdjusting() && awayTeamRoster.getSelectedIndex() >=0 ){ //need to check for index in the bounds of the List, otherwise you get random errors
					if(liveCommand.equals("User Command")){


					}else if(liveCommand.equals("Goal")){
						String playerName = awayTeamRoster.getSelectedValue().toString();
						Player playerSelected = currentAwayTeam.getPlayerFromName(playerName);
						playerSelected.addShot(Shot.GOAL);
						int playerScoredIndex = awayTeamRoster.getSelectedIndex();
						int goals = Integer.parseInt(lblawayTeamScore.getText());
						String newScore = String.valueOf(goals+1);
						lblawayTeamScore.setText(newScore);

					}else if(liveCommand.equals("Yellow")){
						String playerName = awayTeamRoster.getSelectedValue().toString();
						Player playerSelected = currentAwayTeam.getPlayerFromName(playerName);
						playerSelected.addInfraction(Infraction.YELLOW_CARD);
						playerSelected.incrementYellow();

						awayTeamRoster.setCellRenderer(new MyListRenderer());

					}else if(liveCommand.equals("Red")){
						String playerName = awayTeamRoster.getSelectedValue().toString();
						Player playerSelected = currentAwayTeam.getPlayerFromName(playerName);
						playerSelected.addInfraction(Infraction.RED_CARD);
						playerSelected.incrementRed();
						awayTeamRoster.setCellRenderer(new MyListRenderer());

					}


					clearListSelections();
				}
				System.out.println();
			}

		});
		LiveGame.add(awayTeamRoster, gbc_awayTeamRoster);

		btnRedCard = new JButton("Red Card");
		btnRedCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearListSelections();
				lbluserCommand.setText("\"Select Player to give Red Card\"");
				liveCommand = "Red";

			}
		});
		GridBagConstraints gbc_btnRedCard = new GridBagConstraints();
		gbc_btnRedCard.fill = GridBagConstraints.BOTH;
		gbc_btnRedCard.insets = new Insets(0, 0, 5, 5);
		gbc_btnRedCard.gridx = 4;
		gbc_btnRedCard.gridy = 7;
		LiveGame.add(btnRedCard, gbc_btnRedCard);

		pastGame = new JPanel();
		frame.getContentPane().add(pastGame, "name_102414914441451");
		pastGame.setLayout(null);



		textField = new JTextField();
		textField.setBounds(264, 44, 130, 26);
		pastGame.add(textField);
		textField.setColumns(10);

		playerAnalysis = new JPanel();
		frame.getContentPane().add(playerAnalysis, "name_143112215966162");
		GridBagLayout gbl_playerAnalysis = new GridBagLayout();
		gbl_playerAnalysis.columnWidths = new int[]{196, 0};
		gbl_playerAnalysis.rowHeights = new int[]{29, 100, 0};
		gbl_playerAnalysis.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_playerAnalysis.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		playerAnalysis.setLayout(gbl_playerAnalysis);

		JButton button = new JButton("<- ScoreKeeper Options");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScoreKeeper.setVisible(true);
				playerAnalysis.setVisible(false);
				MainMenu.setVisible(false);
				liveGameSelect.setVisible(false);
				LiveGame.setVisible(false);
			}
		});
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.anchor = GridBagConstraints.NORTHWEST;
		gbc_button.insets = new Insets(0, 0, 5, 0);
		gbc_button.gridx = 0;
		gbc_button.gridy = 0;
		playerAnalysis.add(button, gbc_button);

		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		playerAnalysis.add(scrollPane, gbc_scrollPane);

		Object [] columns = {"Name", "Goals", "Fouls", "Yellow", "Red"};
		DefaultTableModel tModel = new DefaultTableModel();
		tModel.setColumnIdentifiers(columns);


		table = new JTable();
		table.setRowSelectionAllowed(false);
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(false);
		table.setShowGrid(false);
		table.setModel(tModel);
		scrollPane.setViewportView(table);
		table.setFillsViewportHeight(true);
		table.setEnabled(false);

		//Object [4]


	}

	class MyListRenderer extends DefaultListCellRenderer
	{

		public Component getListCellRendererComponent( JList list,
				Object value, int index, boolean isSelected,
				boolean cellHasFocus )
		{

			Component renderComponent = super.getListCellRendererComponent(
					list, value, index, isSelected, cellHasFocus);



//			System.out.println("BLAH");
//
//			for(int i = 0; i < awayPlayersNames.length; i++){
//				//check if player at index has a yellow card and highlights it
//				System.out.println(awayPlayersNames[i]+" has: "+currentAwayTeam.getPlayerFromName(awayPlayersNames[i]).getYellow()+" Yellow card");
//				if(currentAwayTeam.getPlayerFromName(awayPlayersNames[i]).getYellow() == 1){
//					renderComponent.setBackground(Color.yellow);
//					System.out.println("SET YELLOW");
//				} else {
//					renderComponent.setBackground(Color.white);
//					System.out.println("SET WHITE");
//				}
////				if(currentAwayTeam.getPlayerFromName(awayPlayersNames[i]).getRed() == 1  || currentAwayTeam.getPlayerFromName(awayPlayersNames[i]).getYellow() == 2)
////					renderComponent.setBackground(Color.red);
//
//			}

			if(currentAwayTeam.getPlayerFromName(awayPlayersNames[index]).getYellow() == 1){
				renderComponent.setBackground(Color.yellow);
				System.out.println("SET YELLOW");
			}
//			if(currentAwayTeam.getPlayerFromName(awayPlayersNames[index]).getRed() == 1  || currentAwayTeam.getPlayerFromName(awayPlayersNames[index]).getYellow() == 2){
//				renderComponent.setBackground(Color.RED);
//				System.out.println("SET Red");
//			}


			return renderComponent ;



		}
	}
}
