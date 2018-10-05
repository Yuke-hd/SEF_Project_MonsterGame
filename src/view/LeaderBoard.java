package view;

import java.awt.Font;
import java.awt.Label;
import java.awt.TextArea;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JFrame;
import javax.swing.JPanel;

import control.SQL;
import model.User;

public class LeaderBoard {
	JFrame frame = new JFrame();

	public void show() {
		frame.setSize(300, 500);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		JPanel panel = new JPanel();
		frame.add(panel);
		placeComponents(panel);
		frame.setVisible(true);
	}

	private void placeComponents(JPanel panel) {
		panel.setLayout(null);
		panel.setAlignmentX(250);
		TextArea ta = new TextArea();
		ta.setBounds(40, 30, 230, 400);
		ta.setFont(new Font("TimesRoman", Font.PLAIN, 16));
		panel.add(ta);
		ta.setText(" #" +"\t" + "name" + "\t\t"+"score"+"\n\n");
		Label k = new Label("Leaderboard");
		k.setBounds(100, 10, 180, 25);
		panel.add(k);
		ArrayList<User> leader = SQL.importUser();
		Collections.sort(leader, new SortByScore());
		for (int i = 0; i < leader.size(); i++) {
			String name = leader.get(i).getUserName();
			int score = leader.get(i).getScore();
			ta.append(i+1 +":"+"\t" + name+"\t\t"+score+"\n");
			Label l = new Label(i+1 +":        " + name+"        "+score);
			l.setBounds(90, 30*(i+2), 180, 25);
			panel.add(l);
		}
	}
}

class SortByScore implements Comparator<User> {
	public int compare(User o1, User o2) {
		User s1 = o1;
		User s2 = o2;
		if (s1.getScore() > s2.getScore())
			return -1;
		return 1;
	}
}