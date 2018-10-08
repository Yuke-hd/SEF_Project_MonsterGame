package model;

public class User {
	private String _userName;
	private char[] _pwd;
	private int _score;
	private Player _player;
	public User(String userName, char[] pwd) {
		_userName = userName;
		_pwd = pwd;
		_score = 0;
		_player= new Player();
	}
	
	public User(String userName, char[] pwd,int score) {
		_userName = userName;
		_pwd = pwd;
		_score = score;
		_player= new Player();
	}
	
	/**
	 * @return the _player
	 */
	public Player getPlayer() {
		return _player;
	}

	/**
	 * @param _player the _player to set
	 */
	public void setPlayer(Player _player) {
		this._player = _player;
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
	public String getPwdString() {
		
		return String.valueOf(_pwd);
	}
	public void setPwd(char[] pwd) {
		_pwd = pwd;
	}
	public int getScore() {
		return _score;
	}
	public void setScore(int score) {
		_score=score;
	}

}
