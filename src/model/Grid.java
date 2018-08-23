package model;

import java.util.ArrayList;

public class Grid {
	public ArrayList<Cell> cellList=new ArrayList<>();
	int _size;
	public Grid(int size) {
		_size = size;
		setUpGrid();
	}
	
	public int getSize() {
		return _size;
	}
	
	public void blankGrid() {
		for(int i=0; i<_size; i++){
			for(int j=0; j<_size; j++){
		System.out.print("   ");
			}
		}
	}
	public void setUpGrid() {
		int n = _size;
		int x = n*n-((n-3)/2*(n-3)/2)*4;
		System.out.println(x);
		//cellList = new Cell;
		for(int i=0; i<_size; i++){
			for(int j=0; j<_size; j++){
			if(i==0||j==0 ||i==n-1||j==n-1){
			System.out.print(" * ");
			Cell cell =new Cell(i,j);
			cellList.add(cell);
			}
			else if(i==n/2 ||j==n/2){
			System.out.print(" * ");
			Cell cell =new Cell(i,j);
			cellList.add(cell);
			} 
			else{
			System.out.print("   ");
			}
			}
			System.out.println();
			}
	}
	
}
