package model;

public class Cell {
	int xpos;
	int ypos;
	public Cell(int x,int y) {
		xpos = x;
		ypos = y;
	}

	public int getX() {
		return xpos;
	}
	
	public int getY() {
		return ypos;
	}
	
	public void setX(int x) {
		xpos=x;
	}
	
	public void setY(int y) {
		ypos=y;
	}
}
