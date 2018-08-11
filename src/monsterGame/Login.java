package monsterGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.util.*;

public class Login extends JFrame {
	LinkedList<User> userList = new LinkedList<User>();
	JFrame _frame = new JFrame("Login Example");
	JPanel _panel = new JPanel();
	private JTextField _userText = new JTextField(20);
	private JPasswordField _passwordText = new JPasswordField(20);
	static Login admin = new Login();

	public static void main(String[] args) {
		User administrator = new User("admin", "password".toCharArray());
		admin.userList.add(administrator);
		// ���� JFrame ʵ��
		JFrame frame = new JFrame("Login");
		// Setting the width and height of frame
		frame.setLocationRelativeTo(null);
		frame.setSize(350, 200);
		frame.setResizable(false); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/*
		 * ������壬��������� HTML �� </div> ��ǩ ���ǿ��Դ��������岢�� JFrame ��ָ��λ�� ��������ǿ�������ı��ֶΣ���ť�����������
		 */
		JPanel panel = new JPanel();
		// ������
		frame.add(panel);
		panel.setLocation(1000, 100);
		/*
		 * �����û�����ķ����������������
		 */
		admin.placeComponents(panel);
		// ���ý���ɼ�
		frame.setVisible(true);
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
			if (auth(userName, pwd)) {
				JOptionPane.showMessageDialog(Jpanel, "��½�ɹ�", "��½��֤", JOptionPane.INFORMATION_MESSAGE);
				_userText.setText("");_passwordText.setText("");
			}else {
				JOptionPane.showMessageDialog(Jpanel, "�˺��������", "��½��֤", JOptionPane.WARNING_MESSAGE);
				_userText.setText("");_passwordText.setText("");
			}
		} 
	}

	private boolean auth(String userName, char[] pwd) {
		for (int i = 0; i < userList.size(); i++) {
			if (userName.equals(userList.get(i).getUserName())) {
				char[] pwd0 = userList.get(i).getPwd();
				char[] pwd1 = pwd;
				if (pwd0.length != pwd1.length || pwd0.length == 0 || pwd1.length == 0) {
					return false;
				}
				for (int j = 0; j < pwd0.length; j++) {
					if (pwd0[j] != pwd1[j]) {
						return false;
					}
				}
				return true;
			} else
				return false;
		}
		return false;
	}

	public void addUser(User user) {
		userList.addFirst(user);
		System.out.println(userList.size());
	}
}