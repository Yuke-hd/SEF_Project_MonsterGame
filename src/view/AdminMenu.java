package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import control.SQL;
import model.User;

public class AdminMenu {
	JFrame frame = new JFrame();
	JPanel panel;
	JTextField gameTimeBox;
	JComboBox<String> comboBox;
	User currentUser;
	ArrayList<User> userlist;
	
	public AdminMenu(User admin) {
		currentUser=admin;
	}
	

	public void show() {
		frame.setTitle("admin menu");
		frame.setSize(300, 300);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		panel = new JPanel();
		frame.add(panel);
		placeComponents(panel);
		frame.setVisible(true);
	}

	private void placeComponents(JPanel panel) {
		panel.removeAll();
		panel.setLayout(null);
		panel.setAlignmentX(250);
		gameTimeBox = new JTextField();
		gameTimeBox.setBounds(60, 60, 90, 25);
		panel.add(gameTimeBox);
		
		JButton gameTimeButton = new JButton("set game time");
		gameTimeButton.setBounds(60, 90, 150, 25);
		panel.add(gameTimeButton);
		Monitor0 setGameTime = new Monitor0();
		gameTimeButton.addActionListener(setGameTime);

		placeComboBox();
		panel.add(comboBox);
		JButton deleteButton = new JButton("delete");
		deleteButton.setBounds(60, 150, 90, 25);
		Monitor2 delete = new Monitor2();
		deleteButton.addActionListener(delete);
		panel.add(deleteButton);
		
		JButton play = new JButton("What Does This Button Do?");
		play.setBounds(30, 180, 200, 25);
		panel.add(play);
		Monitor1 startGame = new Monitor1();
		play.addActionListener(startGame);
	}

	private void placeComboBox() {
		comboBox = new JComboBox<String>();
		userlist=SQL.importUser();
		for (int i = 0; i<userlist.size();i++) {
			String u = userlist.get(i).getUserName();
			if (!u.equals("admin")) 
			comboBox.addItem(u);
		}
		comboBox.setBounds(60, 120, 90, 25);
	}
	
	class Monitor0 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JPanel panel = new JPanel();
			int time;
			try {
				time = Integer.parseInt(gameTimeBox.getText());
				JOptionPane.showMessageDialog(panel, "Game time set to "+time, "INFO", JOptionPane.INFORMATION_MESSAGE);
				Login.control.setGameTime(time);
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(panel, "please enter a number", "Warning", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	class Monitor1 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			GameUI game = new GameUI(currentUser, Login.control.getGameTime());
			game.startGame();
		}
	}
	class Monitor2 implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			SQL.deleteUser((String) comboBox.getSelectedItem());
			userlist=SQL.importUser();
			placeComponents(panel);
			panel.repaint();
		}
	}
}
