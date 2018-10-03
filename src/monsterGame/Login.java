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
		// 创建 JFrame 实例
		frame = new JFrame("Login");
		// Setting the width and height of frame
		frame.setLocationRelativeTo(null);
		frame.setSize(350, 200);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		WindowMonitor wMonitor = new WindowMonitor();
		frame.addWindowListener(wMonitor);
		/*
		 * 创建面板，这个类似于 HTML 的 </div> 标签 我们可以创建多个面板并在 JFrame 中指定位置 面板中我们可以添加文本字段，按钮及其他组件。
		 */
		panel = new JPanel();
		// 添加面板
		frame.add(panel);
		panel.setLocation(1000, 100);
		/*
		 * 调用用户定义的方法并添加组件到面板
		 */
		admin.placeComponents(panel);
		// 设置界面可见
		frame.setVisible(true);
		//importUser();
		for (int i = 0; i < userList.size(); i++) {
			System.out.println(userList.get(i).getUserName());
		}
	}

	private void placeComponents(JPanel panel) {

		/*
		 * 布局部分我们这边不多做介绍 这边设置布局为 null
		 */
		panel.setLayout(null);

		// 创建 JLabel
		JLabel userLabel = new JLabel("User:");
		/*
		 * 这个方法定义了组件的位置。 setBounds(x, y, width, height) x 和 y 指定左上角的新位置，由 width 和 height
		 * 指定新的大小。
		 */
		userLabel.setBounds(40, 20, 80, 25);
		panel.add(userLabel);

		/*
		 * 创建文本域用于用户输入
		 */
		// JTextField userText = new JTextField(20);
		_userText.setBounds(130, 20, 165, 25);
		panel.add(_userText);

		// 输入密码的文本域
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(40, 50, 80, 25);
		panel.add(passwordLabel);

		/*
		 * 这个类似用于输入的文本域 但是输入的信息会以点号代替，用于包含密码的安全性
		 */
		// JPasswordField passwordText = new JPasswordField(20);
		_passwordText.setBounds(130, 50, 165, 25);
		panel.add(_passwordText);

		// 创建登录按钮
		JButton loginButton = new JButton("login");
		loginButton.setBounds(60, 90, 90, 25);
		panel.add(loginButton);

		// 创建注册按钮
		JButton signButton = new JButton("sign up");
		signButton.setBounds(180, 90, 90, 25);
		panel.add(signButton);

		// 创建监视器
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
				JOptionPane.showMessageDialog(Jpanel, "登陆成功 Login Success", "登陆验证", JOptionPane.INFORMATION_MESSAGE);
				_userText.setText("123");
				_passwordText.setText("123");
				UI instance = new UI(player);
				instance.startGame();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(Jpanel, "账号密码错误 Wrong Passwrod", "登陆验证", JOptionPane.WARNING_MESSAGE);
				_userText.setText("123");
				_passwordText.setText("123");
			}
			/*
			 * if (auth(userName, pwd)) { JOptionPane.showMessageDialog(Jpanel,
			 * "登陆成功 Login Success", "登陆验证", JOptionPane.INFORMATION_MESSAGE);
			 * _userText.setText("123"); _passwordText.setText("123"); // frame.dispose();
			 * UI instance = new UI(); int score = instance.startGame();
			 * System.out.println(score); } else { JOptionPane.showMessageDialog(Jpanel,
			 * "账号密码错误 Wrong Passwrod", "登陆验证", JOptionPane.WARNING_MESSAGE);
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
		try { // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw
			/* 读入TXT文件 */
			String pathname = "res//user.txt"; // 绝对路径或相对路径都可以，写入文件时演示相对路径
			File filename = new File(pathname); // 要读取以上路径的input.txt文件
			InputStreamReader reader = new InputStreamReader(new FileInputStream(filename)); // 建立一个输入流对象reader
			BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
			String line;
			// 网友推荐更加简洁的写法
			while ((line = br.readLine()) != null) {
				// 一次读入一行数据
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
		try { // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw
			/* 读入TXT文件 */
			String pathname = "res//user.txt"; // 绝对路径或相对路径都可以，写入文件时演示相对路径
			File filename = new File(pathname); // 要读取以上路径的input.txt文件
			InputStreamReader reader = new InputStreamReader(new FileInputStream(filename)); // 建立一个输入流对象reader
			BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
			String line;
			// 网友推荐更加简洁的写法
			while ((line = br.readLine()) != null) {
				// 一次读入一行数据
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