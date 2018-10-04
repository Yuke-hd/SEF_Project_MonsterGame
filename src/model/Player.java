package model;

public class Player extends Unit{

	int _playerXPos;
	int _playerYPos;
	Cell _player;
	public Player(int x,int y) {
		super(x, y);
		_playerXPos =x;
		_playerYPos =y;
		_player = new Cell(_playerXPos, _playerYPos);
		
	}
	public int getX() {
		return _player.getX();
	}
	public void setX(int playerXPos) {
		_player.setX(playerXPos);
	}
	public int getY() {
		return _player.getY();
	}
	public void setY(int playerYPos) {
		_player.setY(playerYPos);
	}
	public Cell getPlayer() {
		return _player;
	}
	public void setPlayer(Cell _player) {
		this._player = _player;
	}
	
	

}
