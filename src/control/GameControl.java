package control;

import javax.swing.JOptionPane;

import model.Grid;
import model.Monster;
import model.Player;

public class GameControl {

	public static void chase(Player player,Monster[] monster,Grid gameGrid) {
		int playerX = player.getX();
		int playerY = player.getY();
		int gameSize = gameGrid.getSize();
		int distance = Math.abs(monster[0].getX() - monster[1].getX())
				+ Math.abs(monster[0].getY() - monster[1].getY());
		System.out.println(distance);
		if (distance < 3 || monster[0].getMark()) {
			monster[0].chase(playerX, playerY, gameSize);
			monster[1].logic2(playerX, playerY, gameSize);
		} else {
			monster[0].logic0(playerX, playerY, gameSize);
			monster[1].logic2(playerX, playerY, gameSize);
		}
	}

	public static boolean checkWinner(Player player,Monster[] monster) {
		boolean stat = (player.getX() == monster[0].getX() && player.getY() == monster[0].getY())
				|| (player.getX() == monster[1].getX() && player.getY() == monster[1].getY());
		return stat;
	}

	public static void playerMovement(int opt, Player player, Grid gameGrid) {
		int xpos;
		int ypos;

		switch (opt) {
		case 1:
			xpos = player.getX();
			if (xpos == 0 || xpos == (gameGrid.getSize() - 1) / 2 || xpos == (gameGrid.getSize() - 1)) {
				if (player.getY() < gameGrid.getSize() - 1) {
					player.moveRight();
					System.out.println("to right");
					break;
				}

			} else
				System.out.println("out of border");
			return;

		case 2:
			xpos = player.getX();
			if (xpos == 0 || xpos == (gameGrid.getSize() - 1) / 2 || xpos == (gameGrid.getSize() - 1)) {
				if (player.getY() > 0) {
					player.moveLeft();
					System.out.println("to left");
					break;
				}
			} else
				System.out.println("out of border");
			return;
		case 3:
			ypos = player.getY();
			if (ypos == 0 || ypos == (gameGrid.getSize() - 1) / 2 || ypos == (gameGrid.getSize() - 1)) {
				if (player.getX() < gameGrid.getSize() - 1) {
					player.moveDown();
					System.out.println("down");
					break;
				}
			} else
				System.out.println("out of border");
			return;
		case 4:
			ypos = player.getY();
			if (ypos == 0 || ypos == (gameGrid.getSize() - 1) / 2 || ypos == (gameGrid.getSize() - 1)) {
				if (player.getX() > 0) {
					player.moveUp();
					System.out.println("up");
					break;
				}
			} else
				System.out.println("out of border");
			return;
		default:
			return;
		}
	}
}
