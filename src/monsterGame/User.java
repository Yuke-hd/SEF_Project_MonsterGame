package monsterGame;

public class User {
	private String _userName;
	private char[] _pwd;
	private int _score;
	public User(String userName, char[] pwd) {
		_userName = userName;
		_pwd = pwd;
		_score = 0;
	}
	public String getUserName() {
		return _userName;
	}
	public void setUserName(String userName) {
		_userName = userName;
	}
	public char[] getPwd() {
		return _pwd;
	}
	public void setPwd(char[] pwd) {
		_pwd = pwd;
	}
	public int getScore() {
		return _score;
	}

}
