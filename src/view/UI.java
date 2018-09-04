package view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Cell;
import model.Grid;
import model.Monster;
import model.Player;

public class UI extends JFrame implements KeyListener {
	Grid _game;
	gridPanel mp = null;
	int rnd1;
	int rnd2;
	Player player;
	Monster[] monster = new Monster[2];
	JFrame frame = new JFrame("UI");

	public UI() {
		Grid game = new Grid(11);
		_game = game;
		rndPos();
		player = new Player(rnd1, rnd2);
		for (int x = 0; x < 2; x++) {
			rndPos();
			monster[x] = new Monster(rnd1, rnd2);
		}
		mp = new gridPanel();
		frame.add(mp);
		frame.addKeyListener(this);
		frame.setSize(600, 600);
		frame.setLocationRelativeTo(null);
		frame.setResizable(true);
		frame.setVisible(true);
	}

	class gridPanel extends JPanel {
		int n = 11;
		int x = n ^ 2 - ((n - 3) / 2) ^ 2 * 4;
		Image image = null;

		public void paintComponent(Graphics g) {
			ArrayList<Cell> cellLsit = _game.cellList;
			this.setLayout(null);
			for (int i = 0; i < cellLsit.size(); i++) {
				// System.out.println("X:" + cellLsit.get(i).getX() + ",Y:" +
				// cellLsit.get(i).getY());
				try {
					image = ImageIO.read(new File("res//head2.png"));
					g.drawImage(image, cellLsit.get(i).getX() * 50, cellLsit.get(i).getY() * 50, 50, 50, null);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				image = ImageIO.read(new File("res//Alliance.png"));//player
				g.drawImage(image, player.getPlayer().getX() * 50, player.getPlayer().getY() * 50, 50, 50, null);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for (int i = 0; i < 2; i++) {
				try {
					image = ImageIO.read(new File("res//Horde.png"));//monster
					g.drawImage(image, monster[i].getMon().getX() * 50, monster[i].getMon().getY() * 50, 50, 50, null);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

	}

	public void rndPos() {
		int x;
		Random random = new Random();
		rnd1 = random.nextInt(10);
		int[] numbers = { 0, (_game.getSize() - 1) / 2, (_game.getSize() - 1) };
		if (rnd1 == 0 || rnd1 == (_game.getSize() - 1) / 2 || rnd1 == (_game.getSize() - 1)) {
			rnd2 = (int) (Math.random() * 10);
		} else {
			x = random.nextInt(numbers.length);
			rnd2 = numbers[x];
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

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

		}else
			;
		int xpos;
		int ypos;

		switch (x) {
		case 1:
			xpos = player.getPlayer().getX();
			if (xpos == 0 || xpos == (_game.getSize() - 1) / 2 || xpos == (_game.getSize() - 1)) {
				if (player.getPlayer().getY() < _game.getSize() - 1) {
					player.getPlayer().setY(player.getPlayer().getY() + 1);
					System.out.println("down");break;
				}

			} else
				System.out.println("out of border");return;
			
		case 2:
			xpos = player.getPlayer().getX();
			if (xpos == 0 || xpos == (_game.getSize() - 1) / 2 || xpos == (_game.getSize() - 1)) {
				if (player.getPlayer().getY() > 0) {
					player.getPlayer().setY(player.getPlayer().getY() - 1);
					System.out.println("up");break;
				}
			} else
				System.out.println("out of border");return;
		case 3:
			ypos = player.getPlayer().getY();
			if (ypos == 0 || ypos == (_game.getSize() - 1) / 2 || ypos == (_game.getSize() - 1)) {
				if (player.getPlayer().getX() < _game.getSize() - 1) {
					player.getPlayer().setX(player.getPlayer().getX() + 1);
					System.out.println("to right");break;
				}
			} else
				System.out.println("out of border");return;
		case 4:
			ypos = player.getPlayer().getY();
			if (ypos == 0 || ypos == (_game.getSize() - 1) / 2 || ypos == (_game.getSize() - 1)) {
				if (player.getPlayer().getX() > 0) {
					player.getPlayer().setX(player.getPlayer().getX() - 1);
					System.out.println("to left");break;
				}
			} else
				System.out.println("out of border");return;
		case 5:
			JOptionPane.showMessageDialog(mp, "Game pause", "Game", JOptionPane.WARNING_MESSAGE);
			return;
		default:
			return;
		}
		chase();
		frame.add(mp);
		frame.repaint();

		if ((player.getPlayer().getX() == monster[0].getMon().getX()
				&& player.getPlayer().getY() == monster[0].getMon().getY())
				|| (player.getPlayer().getX() == monster[1].getMon().getX()
						&& player.getPlayer().getY() == monster[1].getMon().getY())) {
			JOptionPane.showMessageDialog(mp, "You Lose", "Game", JOptionPane.WARNING_MESSAGE);
			frame.dispose();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void chase() {
		int distance = Math.abs(monster[0].getMon().getX() - monster[1].getMon().getX())
				+ Math.abs(monster[0].getMon().getY() - monster[1].getMon().getY());
		System.out.println(distance);
		if (distance < 3 || monster[0].getMark()) {
			monster[0].chase(player.getPlayer().getX(), player.getPlayer().getY(), _game.getSize());
		} else
			monster[0].logic0(player.getPlayer().getX(), player.getPlayer().getY(), _game.getSize());
		monster[1].logic0(player.getPlayer().getX(), player.getPlayer().getY(), _game.getSize());

	}
}
