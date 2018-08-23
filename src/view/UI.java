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
		frame.setSize(1000, 1000);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
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
					g.drawImage(image, cellLsit.get(i).getX() * 80, cellLsit.get(i).getY() * 80, 50, 50, null);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				image = ImageIO.read(new File("res//body.png"));
				g.drawImage(image, player.getPlayer().getX() * 80, player.getPlayer().getY() * 80, 50, 50, null);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for (int i = 0; i < 2; i++) {
				try {
					image = ImageIO.read(new File("res//body4.png"));
					g.drawImage(image, monster[i].getMon().getX() * 80, monster[i].getMon().getY() * 80, 50, 50, null);
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
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			int xpos = player.getPlayer().getX();
			if (xpos == 0 || xpos == (_game.getSize() - 1) / 2 || xpos == (_game.getSize() - 1)) {
				if (player.getPlayer().getY() < _game.getSize() - 1) {
					player.getPlayer().setY(player.getPlayer().getY() + 1);
					System.out.println("down");
				}
			} else
				System.out.println("out of border");
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			int xpos = player.getPlayer().getX();
			if (xpos == 0 || xpos == (_game.getSize() - 1) / 2 || xpos == (_game.getSize() - 1)) {
				if (player.getPlayer().getY() > 0) {
					player.getPlayer().setY(player.getPlayer().getY() - 1);
					System.out.println("up");
				}
			} else
				System.out.println("out of border");
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			int ypos = player.getPlayer().getY();
			if (ypos == 0 || ypos == (_game.getSize() - 1) / 2 || ypos == (_game.getSize() - 1)) {
				if (player.getPlayer().getX() < _game.getSize() - 1) {
					player.getPlayer().setX(player.getPlayer().getX() + 1);
					System.out.println("to right");
				}
			} else
				System.out.println("out of border");
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			int ypos = player.getPlayer().getY();
			if (ypos == 0 || ypos == (_game.getSize() - 1) / 2 || ypos == (_game.getSize() - 1)) {
				if (player.getPlayer().getX() > 0) {
					player.getPlayer().setX(player.getPlayer().getX() - 1);
					System.out.println("to left");
				}
			} else
				System.out.println("out of border");
		} else {

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
		
		monster[0].chaseLogic0(player.getPlayer().getX(), player.getPlayer().getY(), _game.getSize());
		monster[1].chaseLogic0(player.getPlayer().getX(), player.getPlayer().getY(), _game.getSize());

	}
}
