package model;

public class Monster extends Unit {

	boolean mark = false;

	public boolean getMark() {
		return mark;
	}

	public void setMark(boolean mark) {
		this.mark = mark;
	}

	public Monster(int gameSize) {
		super(gameSize);
	}

	public void logic0(int px, int py, int size) {
		int mx = super.XPos;
		int my = super.YPos;
		if (mx == 0 || mx == (size - 1) / 2 || mx == (size - 1)) {
			if (my > py) {
				super.moveLeft();
				return;
			}
			if (my < py) {
				super.moveRight();
				return;
			}
		} else {
			if (mx > px) {
				super.moveUp();
				return;
			}
			if (mx < px) {
				super.moveDown();
				return;
			}
		}
		if (my == 0 || my == (size - 1) / 2 || my == (size - 1)) {
			if (mx > px) {
				super.moveUp();
				return;
			}
			if (mx < px) {
				super.moveDown();
				return;
			}
		} else {
			if (my > py) {
				super.moveLeft();
				return;
			}
			if (my < py) {
				super.moveRight();
				return;
			}
		}
	}


	public void logic1(int size) {
		int mx = super.XPos;
		int my = super.YPos;
		setMark(true);
		if (mx == 0 || mx == (size - 1) / 2 || mx == (size - 1)) {
			if (my > (size - 1) / 2) {
				super.moveLeft();
				return;
			} else if (my < (size - 1) / 2) {
				super.moveRight();
				return;
			}
		}
		if (my == 0 || my == (size - 1) / 2 || my == (size - 1)) {
			if (mx > (size - 1) / 2) {
				super.moveUp();
				return;
			} else if (mx < (size - 1) / 2) {
				super.moveDown();
				return;
			}
		}
	}

	public void logic2(int px, int py, int size) {
		int mx = super.XPos;
		int my = super.YPos;
		if (my == 0 || my == (size - 1) / 2 || my == (size - 1)) {
			if (mx > px) {
				super.moveUp();
				return;
			}
			if (mx < px) {
				super.moveDown();
				return;
			}
		} else {
			if (my > py) {
				super.moveLeft();
				return;
			}
			if (my < py) {
				super.moveRight();
				return;
			}
		}
		if (mx == 0 || mx == (size - 1) / 2 || mx == (size - 1)) {
			if (my > py) {
				super.moveLeft();
				return;
			}
			if (my < py) {
				moveRight();
				return;
			}
		} else {
			if (mx > px) {
				super.moveUp();
				return;
			}
			if (mx < px) {
				super.moveDown();
				return;
			}
		}

	}

	public void chase(int px, int py, int size) {
		int mx = super.XPos;
		int my = super.YPos;
		if (my == (size - 1) / 2 && mx == (size - 1) / 2) {
			setMark(false);
			logic0(px, py, size);
		} else {
			logic1(size);
		}
	}

}
