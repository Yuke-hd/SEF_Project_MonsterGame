package model;

public class Monster extends Unit {

	int _monXPos;
	int _monYPos;
	boolean mark=false;
	public boolean getMark() {
		return mark;
	}

	public void setMark(boolean mark) {
		this.mark = mark;
	}
	Cell _mon;

	public Monster(int x, int y) {
		super(x, y);
		_monXPos = x;
		_monYPos = y;
		_mon = new Cell(_monXPos, _monYPos);
	}

	public Cell getMon() {
		return _mon;
	}

	public void logic0(int px, int py, int size) {
		int mx = _monXPos;
		int my = _monYPos;
		if (mx == 0 || mx == (size - 1) / 2 || mx == (size - 1)){
			if (my > py) {
				moveLeft();
				return;
			}
			if (my < py) {
				moveRight();
				return;
			}
		}else {
			if (mx > px) {
				moveUp();
				return;
			}
			if (mx < px) {
				moveDown();
				return;
			}
		}
		if (my == 0 || my == (size - 1) / 2 || my == (size - 1)){
			if (mx > px) {
				moveUp();
				return;
			}
			if (mx < px) {
				moveDown();
				return;
			}
		}else {
			if (my > py) {
				moveLeft();
				return;
			}
			if (my < py) {
				moveRight();
				return;
			}
		}
	}
	
	private void moveUp() {
		_monXPos = _monXPos - 1;
		_mon.setX(_monXPos);
	}
	
	private void moveDown() {
		_monXPos = _monXPos + 1;
		_mon.setX(_monXPos);
	}
	private void moveLeft() {
		_monYPos = _monYPos - 1;
		_mon.setY(_monYPos);		
	}

	private void moveRight() {
		_monYPos = _monYPos + 1;
		_mon.setY(_monYPos);
	}

	public void logic1(int size) {
		int mx = _monXPos;
		int my = _monYPos;
		setMark(true);
		if (mx == 0 || mx == (size - 1) / 2 || mx == (size - 1)){
			if (my>(size - 1) / 2) {
				_monYPos = my - 1;
				_mon.setY(_monYPos);
				return;
			}else if (my<(size - 1) / 2) {
				_monYPos = my + 1;
				_mon.setY(_monYPos);
				return;
			}
		}
		if (my == 0 || my == (size - 1) / 2 || my == (size - 1)){
			if (mx>(size - 1) / 2) {
				_monXPos = mx - 1;
				_mon.setX(_monXPos);
				return;
			}else if (mx<(size - 1) / 2) {
				_monXPos = mx + 1;
				_mon.setX(_monXPos);
				return;
			}
		}
		
		
	}
	public void chase(int px, int py, int size) {
		int mx = _monXPos;
		int my = _monYPos;
		if (my==(size - 1) / 2&&mx==(size - 1) / 2) {
			setMark(false);
			logic0(px, py, size);
		}else {
			logic1(size);
		}
	}
	
	}
