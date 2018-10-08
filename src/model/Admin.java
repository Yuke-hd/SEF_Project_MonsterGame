package model;

import view.Login;

public class Admin extends User{

	public Admin(String userName, char[] pwd,int score) {
		super(userName, pwd,score);
	}

	public void setGameTime(int gameTime) {
		Login.control.setGameTime(gameTime);
	}
}
