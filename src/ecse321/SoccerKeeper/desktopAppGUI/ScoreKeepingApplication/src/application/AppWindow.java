package ecse321.SoccerKeeper.desktopAppGUI.ScoreKeepingApplication.src.application;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.AbstractAction;
import javax.swing.Action;
import java.awt.Button;
import java.awt.Label;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;

public class AppWindow {

	private JFrame frame;

	private JTextField textField;
	private JPanel ScoreKeeper;
	private JPanel View;
	private JPanel LoginMenu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppWindow window = new AppWindow();
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
	public AppWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		final JPanel LoginMenu = new JPanel();
		LoginMenu.setToolTipText("");
		frame.getContentPane().add(LoginMenu, "name_226083077594956");
		LoginMenu.setLayout(null);
		
		JButton btnScorekeeper = new JButton("ScoreKeeper");
		btnScorekeeper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScoreKeeper.setVisible(true);
				View.setVisible(false);
				LoginMenu.setVisible(false);
			}
		});
		btnScorekeeper.setBounds(77, 127, 117, 59);
		LoginMenu.add(btnScorekeeper);
		
		JButton btnLeagueView = new JButton("League View");
		btnLeagueView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				View.setVisible(true);
				ScoreKeeper.setVisible(false);
				LoginMenu.setVisible(false);
			}
		});
		btnLeagueView.setBounds(254, 127, 117, 59);
		LoginMenu.add(btnLeagueView);
		
		textField = new JTextField();
		textField.setBounds(77, 198, 294, 42);
		LoginMenu.add(textField);
		textField.setColumns(10);
		
		final JPanel ScoreKeeper = new JPanel();
		frame.getContentPane().add(ScoreKeeper, "name_226083085305991");
		ScoreKeeper.setLayout(null);
		
		final JPanel View = new JPanel();
		View.setBackground(new Color(0, 153, 51));
		frame.getContentPane().add(View, "name_226083091755978");
		View.setLayout(null);
	}
	
	
}
