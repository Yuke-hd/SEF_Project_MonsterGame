package monsterGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.WindowConstants;

import model.Grid;
import view.UI;

import java.util.*;

public class Login extends JFrame implements Serializable {
	public LinkedList<User> userList = new LinkedList<User>();
	JFrame frame;
	JPanel panel;
	private JTextField _userText = new JTextField(20);
	private JPasswordField _passwordText = new JPasswordField(20);
	static Login admin = new Login();

	public static void main(String[] args) throws IOException{
			admin.Login0();
	}

	public void Login0() throws IOException{
		// ���� JFrame ʵ��
		frame = new JFrame("Login");
		// Setting the width and height of frame
		frame.setLocationRelativeTo(null);
		frame.setSize(350, 200);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		WindowMonitor wMonitor = new WindowMonitor();
		frame.addWindowListener(wMonitor);
		/*
		 * ������壬��������� HTML �� </div> ��ǩ ���ǿ��Դ��������岢�� JFrame ��ָ��λ�� ��������ǿ�������ı��ֶΣ���ť�����������
		 */
		panel = new JPanel();
		// ������
		frame.add(panel);
		panel.setLocation(1000, 100);
		/*
		 * �����û�����ķ����������������
		 */
		admin.placeComponents(panel);
		// ���ý���ɼ�
		frame.setVisible(true);
		//importUser();
		for (int i = 0; i < userList.size(); i++) {
			System.out.println(userList.get(i).getUserName());
		}
	}

	private void placeComponents(JPanel panel) {

		/*
		 * ���ֲ���������߲��������� ������ò���Ϊ null
		 */
		panel.setLayout(null);

		// ���� JLabel
		JLabel userLabel = new JLabel("User:");
		/*
		 * ������������������λ�á� setBounds(x, y, width, height) x �� y ָ�����Ͻǵ���λ�ã��� width �� height
		 * ָ���µĴ�С��
		 */
		userLabel.setBounds(40, 20, 80, 25);
		panel.add(userLabel);

		/*
		 * �����ı��������û�����
		 */
		// JTextField userText = new JTextField(20);
		_userText.setBounds(130, 20, 165, 25);
		panel.add(_userText);

		// ����������ı���
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(40, 50, 80, 25);
		panel.add(passwordLabel);

		/*
		 * �����������������ı��� �����������Ϣ���Ե�Ŵ��棬���ڰ�������İ�ȫ��
		 */
		// JPasswordField passwordText = new JPasswordField(20);
		_passwordText.setBounds(130, 50, 165, 25);
		panel.add(_passwordText);

		// ������¼��ť
		JButton loginButton = new JButton("login");
		loginButton.setBounds(60, 90, 90, 25);
		panel.add(loginButton);

		// ����ע�ᰴť
		JButton signButton = new JButton("sign up");
		signButton.setBounds(180, 90, 90, 25);
		panel.add(signButton);

		// ����������
		Monitor0 sign = new Monitor0();
		signButton.addActionListener(sign);
		Monitor1 login = new Monitor1();
		loginButton.addActionListener(login);
	}

	class WindowMonitor implements WindowListener {
		@Override
		public void windowActivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosed(WindowEvent arg0) {
			
		}

		@Override
		public void windowClosing(WindowEvent arg0){
			ObjectOutputStream out;
			try {
				out = new ObjectOutputStream(new FileOutputStream("user.txt",true));
				out.writeObject(userList);
				out.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}

		@Override
		public void windowDeactivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowOpened(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}

	class Monitor0 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			SignUp signUp = new SignUp();
			signUp.signUp0();
			System.out.println("break1");
		}
	}

	class Monitor1 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JPanel Jpanel = new JPanel();
			String userName = _userText.getText();
			char[] pwd = _passwordText.getPassword();
			try {
				User player = auth0(userName, pwd);
				System.out.println(player.getUserName());
				JOptionPane.showMessageDialog(Jpanel, "��½�ɹ� Login Success", "��½��֤", JOptionPane.INFORMATION_MESSAGE);
				_userText.setText("123");
				_passwordText.setText("123");
				UI instance = new UI(player);
				instance.startGame();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(Jpanel, "�˺�������� Wrong Passwrod", "��½��֤", JOptionPane.WARNING_MESSAGE);
				_userText.setText("123");
				_passwordText.setText("123");
			}
			/*
			 * if (auth(userName, pwd)) { JOptionPane.showMessageDialog(Jpanel,
			 * "��½�ɹ� Login Success", "��½��֤", JOptionPane.INFORMATION_MESSAGE);
			 * _userText.setText("123"); _passwordText.setText("123"); // frame.dispose();
			 * UI instance = new UI(); int score = instance.startGame();
			 * System.out.println(score); } else { JOptionPane.showMessageDialog(Jpanel,
			 * "�˺�������� Wrong Passwrod", "��½��֤", JOptionPane.WARNING_MESSAGE);
			 * _userText.setText("123"); _passwordText.setText("123"); }
			 */
		}
	}

	public User auth0(String userName, char[] pwd) throws Exception {
		User user;
		for (int i = 0; i < userList.size(); i++) {
			if (userName.equals(userList.get(i).getUserName())) {
				char[] pwd0 = userList.get(i).getPwd();
				char[] pwd1 = pwd;
				user = userList.get(i);
				if (pwd0.length != pwd1.length || pwd0.length == 0 || pwd1.length == 0) {
					throw new Exception();
				}
				for (int j = 0; j < pwd0.length; j++) {
					if (pwd0[j] != pwd1[j]) {
						throw new Exception();
					}
				}
				return user;
			}
		}
		throw new Exception();
	}

	private void importUser() {
		String username;
		char[] pwd;
		int score;
		boolean auth = false;
		try { // ��ֹ�ļ��������ȡʧ�ܣ���catch��׽���󲢴�ӡ��Ҳ����throw
			/* ����TXT�ļ� */
			String pathname = "res//user.txt"; // ����·�������·�������ԣ�д���ļ�ʱ��ʾ���·��
			File filename = new File(pathname); // Ҫ��ȡ����·����input.txt�ļ�
			InputStreamReader reader = new InputStreamReader(new FileInputStream(filename)); // ����һ������������reader
			BufferedReader br = new BufferedReader(reader); // ����һ�����������ļ�����ת�ɼ�����ܶ���������
			String line;
			// �����Ƽ����Ӽ���д��
			while ((line = br.readLine()) != null) {
				// һ�ζ���һ������
				System.out.println(line);
				String[] linePart = line.split("\t");
				username = linePart[0];
				pwd = linePart[1].toCharArray();
				score = Integer.parseInt(linePart[2]);
				User user = new User(username, pwd, score);
				userList.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean auth(String username0, char[] pwd0) {
		String username;
		char[] pwd;
		boolean auth = false;
		try { // ��ֹ�ļ��������ȡʧ�ܣ���catch��׽���󲢴�ӡ��Ҳ����throw
			/* ����TXT�ļ� */
			String pathname = "res//user.txt"; // ����·�������·�������ԣ�д���ļ�ʱ��ʾ���·��
			File filename = new File(pathname); // Ҫ��ȡ����·����input.txt�ļ�
			InputStreamReader reader = new InputStreamReader(new FileInputStream(filename)); // ����һ������������reader
			BufferedReader br = new BufferedReader(reader); // ����һ�����������ļ�����ת�ɼ�����ܶ���������
			String line;
			// �����Ƽ����Ӽ���д��
			while ((line = br.readLine()) != null) {
				// һ�ζ���һ������
				System.out.println(line);
				String[] linePart = line.split("\t");
				username = linePart[0];
				System.out.println(username);
				pwd = linePart[1].toCharArray();
				System.out.println(pwd);
				if (username.equals(username0)) {
					for (int j = 0; j < pwd0.length; j++) {
						if (pwd0[j] != pwd[j]) {
							auth = false;
						}
					}
					auth = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return auth;
	}

	public void addUser(User user) {
		userList.addFirst(user);
		System.out.println(userList.size());
	}

	public User getUser() {
		return userList.get(0);
	}

}