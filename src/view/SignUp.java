package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import control.SQL;
import model.User;

public class SignUp extends JFrame {

	User _user;

	JFrame frame = new JFrame("Sign Up");

	public SignUp() {	}

	public void signUp0() {
		// SignUp signUp = new SignUp();
		// Setting the width and height of frame
		frame.setSize(350, 200);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		// frame.setDefaultCloseOperation();
		/*
		 * 创建面板，这个类似于 HTML 的 div 标签 我们可以创建多个面板并在 JFrame 中指定位置 面板中我们可以添加文本字段，按钮及其他组件。
		 */
		JPanel panel = new JPanel();
		// 添加面板
		frame.add(panel);
		/*
		 * 调用用户定义的方法并添加组件到面板
		 */
		placeComponents(panel);

		// 设置界面可见
		frame.setVisible(true);
	}

	private void placeComponents(JPanel panel) {
		panel.setLayout(null);

		// 创建 JLabel
		JLabel userLabel = new JLabel("User:");
		/*
		 * 这个方法定义了组件的位置。 setBounds(x, y, width, height) x 和 y 指定左上角的新位置，由 width 和 height
		 * 指定新的大小。
		 */
		userLabel.setBounds(10, 20, 80, 25);
		panel.add(userLabel);

		/*
		 * 创建文本域用于用户输入
		 */
		JTextField userText = new JTextField(20);
		userText.setBounds(100, 20, 165, 25);
		panel.add(userText);

		// 输入密码的文本域
		JLabel passwordLabel0 = new JLabel("Password:");
		passwordLabel0.setBounds(10, 50, 80, 25);
		panel.add(passwordLabel0);
		// 输入检验密码的文本域
		JLabel passwordLabel1 = new JLabel("Retype:");
		passwordLabel1.setBounds(10, 80, 80, 25);
		panel.add(passwordLabel1);
		/*
		 * 这个类似用于输入的文本域 但是输入的信息会以点号代替，用于包含密码的安全性
		 */
		JPasswordField passwordText0 = new JPasswordField(10);
		passwordText0.setBounds(100, 50, 165, 25);
		panel.add(passwordText0);
		JPasswordField passwordText1 = new JPasswordField(10);
		passwordText1.setBounds(100, 80, 165, 25);
		panel.add(passwordText1);
		// 创建注册按钮
		JButton signButton = new JButton("sign up");
		signButton.setBounds(130, 110, 90, 25);
		panel.add(signButton);
		// 注册信息

		signButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName = userText.getText();
				char[] pwd0 = passwordText0.getPassword();
				char[] pwd1 = passwordText1.getPassword();
				System.out.println(pwd0);
				System.out.println(pwd1);
				for (int i = 0; i < Login.control.userList.size(); i++) {
					if (userName.equals(Login.control.userList.get(i).getUserName())) {
						JOptionPane.showMessageDialog(panel, "user name already exist", "Sign up", JOptionPane.WARNING_MESSAGE);
						return;
					}
				}
				if (pwdCompare(pwd0, pwd1)) {
					User user = new User(userName, pwd0);
					_user = user;
					panel.remove(signButton);
					returnButton(panel);
					panel.repaint();
				} else {
					JOptionPane.showMessageDialog(panel, "password does not match", "Sign up", JOptionPane.WARNING_MESSAGE);
					return;
				}
				userText.setText("");
				passwordText0.setText("");
				passwordText1.setText("");
				System.out.println(_user.getUserName()+" regsited");
				Login.control.addUser(_user);
				SQL.insertUser(_user);
			}
		});

	}

	private void returnButton(JPanel panel) {

		JButton returnButton = new JButton("sign up success, click to return");
		returnButton.setBounds(50, 110, 250, 25);
		panel.add(returnButton);
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}

		});
	}

	public boolean pwdCompare(char[] pwd0, char[] pwd1) {
		if (pwd0.length != pwd1.length || pwd0.length == 0 || pwd1.length == 0) {
			return false;
		}
		for (int i = 0; i < pwd0.length; i++) {
			if (pwd0[i] != pwd1[i]) {
				return false;
			}
		}
		return true;
	}
}
