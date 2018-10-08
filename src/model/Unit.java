package model;

import java.util.Random;

abstract public class Unit {
	int XPos;
	int YPos;

	public Unit(int gameSize) {
		rndPos(gameSize);
	}
	public Unit() {
	}

	public int getX() {
		return XPos;
	}

	public void setX(int x) {
		XPos = x;
	}

	public int getY() {
		return YPos;
	}

	public void setY(int y) {
		YPos = y;
	}

	public void moveUp() {
		XPos = XPos - 1;
	}

	public void moveDown() {
		XPos = XPos + 1;
	}

	public void moveLeft() {
		YPos = YPos - 1;
	}

	public void moveRight() {
		YPos = YPos + 1;
	}

	private void rndPos(int gameSize) {
		int x;
		Random random = new Random();
		XPos = random.nextInt(10);
		int[] numbers = { 0, (gameSize - 1) / 2, (gameSize - 1) };
		if (XPos == 0 || XPos == (gameSize - 1) / 2 || XPos == (gameSize - 1)) {
			YPos = (int) (Math.random() * 10);
		} else {
			x = random.nextInt(numbers.length);
			YPos = numbers[x];
		}
	}
}
