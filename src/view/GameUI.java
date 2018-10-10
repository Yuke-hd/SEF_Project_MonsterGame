package view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import control.GameControl;
import control.SQL;
import model.Cell;
import model.Grid;
import model.Monster;
import model.Player;
import model.User;

public class GameUI extends JFrame implements KeyListener {
	User user;
	Grid gameGrid;
	gridPanel gameBoard = null;
	Player player;
	Monster[] monster = new Monster[2];
	JFrame frame = new JFrame();
	protected int stepCount;
	int gameTime;
	int diffculty=2;// 1=hard 2=normal 4=easy

	public GameUI(User user, int gameTime) {
		stepCount = 0;
		this.user = user;
		this.gameTime = gameTime;
	}

	public void startGame() {
		Grid game = new Grid(13);
		gameGrid = game;
		player = new Player(gameGrid.getSize());
		for (int x = 0; x < 2; x++) {
			monster[x] = new Monster(gameGrid.getSize());
		}
		gameBoard = new gridPanel();
		gameBoard.setAlignmentX(CENTER_ALIGNMENT);
		frame.setTitle(user.getUserName() + "'s session");
		frame.add(gameBoard);
		frame.addKeyListener(this);
		frame.setSize(gameGrid.getSize() * 55, gameGrid.getSize() * 60);
		frame.setLocationRelativeTo(null);
		frame.setResizable(true);
		frame.setVisible(true);
	}

	class gridPanel extends JPanel {
		int n = 15;
		int x = n ^ 2 - ((n - 3) / 2) ^ 2 * 4;
		Image image = null;

		public void paintComponent(Graphics g) {
			ArrayList<Cell> cellLsit = gameGrid.cellList;
			this.setLayout(null);
			g.drawString("score: " + stepCount, 100, 100);
			g.drawString("game time: " + gameTime, 100, 130);
			for (int i = 0; i < cellLsit.size(); i++) {
				try {
					image = ImageIO.read(new File("res//head2.png"));// cell
					g.drawImage(image, cellLsit.get(i).getX() * 50, cellLsit.get(i).getY() * 50, 50, 50, null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			try {
				image = ImageIO.read(new File("res//Alliance.png"));// player
				g.drawImage(image, player.getX() * 50, player.getY() * 50, 50, 50, null);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			for (int i = 0; i < 2; i++) {
				try {
					image = ImageIO.read(new File("res//Horde.png"));// monster
					g.drawImage(image, monster[i].getX() * 50, monster[i].getY() * 50, 50, 50, null);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		int x = 0;
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			x = 1;
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			x = 2;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			x = 3;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			x = 4;
		} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			x = 5;
		} else
			;
		if (x==5) {
			JOptionPane.showMessageDialog(gameBoard, "Game pause", "Game", JOptionPane.WARNING_MESSAGE);
			return;
		} else {
			GameControl.playerMovement(x, player, gameGrid);
		}
		
		stepCount++;
		if (stepCount % diffculty == 0) { //this line affect the speed of the monsters
			GameControl.chase(player,monster,gameGrid);
		}
		frame.add(gameBoard);
		frame.repaint();
		if (GameControl.checkWinner(player,monster)) {
			SQL.update(user.getUserName(), user.getScore() + stepCount);
			JOptionPane.showMessageDialog(gameBoard, "You Lose\n" + "score: " + stepCount, "Game",
					JOptionPane.WARNING_MESSAGE);
			frame.dispose();
		}
		if (stepCount == gameTime) {
			SQL.update(user.getUserName(), user.getScore() + stepCount);
			JOptionPane.showMessageDialog(gameBoard, "You won!\n" + "score: " + stepCount, "We have a winner here",
					JOptionPane.INFORMATION_MESSAGE);
			frame.dispose();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

}
