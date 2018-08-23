package model;

public class Monster extends Unit {

	int _monXPos;
	int _monYPos;
	Cell _mon;

	public Monster(int x, int y) {
		_monXPos = x;
		_monYPos = y;
		_mon = new Cell(_monXPos, _monYPos);
	}

	public Cell getMon() {
		return _mon;
	}

	public void chaseLogic0(int px, int py, int size) {
		int mx = _monXPos;
		int my = _monYPos;
		if (mx == 0 || mx == (size - 1) / 2 || mx == (size - 1)){
			if (my > py) {
				_monYPos = my - 1;
				_mon.setY(_monYPos);
				return;
			}
			if (my < py) {
				_monYPos = my + 1;
				_mon.setY(_monYPos);
				return;
			}
		}else {
			if (mx > px) {
				_monXPos = mx - 1;
				_mon.setX(_monXPos);
				return;
			}
			if (mx < px) {
				_monXPos = mx + 1;
				_mon.setX(_monXPos);
				return;
			}
		}
		if (my == 0 || my == (size - 1) / 2 || my == (size - 1)){
			if (mx > px) {
				_monXPos = mx - 1;
				_mon.setX(_monXPos);
				return;
			}
			if (mx < px) {
				_monXPos = mx + 1;
				_mon.setX(_monXPos);
				return;
			}
		}else {
			if (my > py) {
				_monYPos = my - 1;
				_mon.setY(_monYPos);
				return;
			}
			if (my < py) {
				_monYPos = my + 1;
				_mon.setY(_monYPos);
				return;
			}
		}
	}
	}
