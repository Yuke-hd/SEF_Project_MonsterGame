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
	public int getPlayerXPos() {
		return _playerXPos;
	}
	public void setPlayerXPos(int _playerXPos) {
		this._playerXPos = _playerXPos;
	}
	public int getPlayerYPos() {
		return _playerYPos;
	}
	public void setPlayerYPos(int _playerYPos) {
		this._playerYPos = _playerYPos;
	}
	public Cell getPlayer() {
		return _player;
	}
	public void setPlayer(Cell _player) {
		this._player = _player;
	}
	
	

}
