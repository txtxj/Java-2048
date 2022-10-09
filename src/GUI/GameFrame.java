package GUI;

import Gameplay.BoardManager;
import Gameplay.KeyboardHandler;
import Global.Settings;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;
import java.util.Timer;

public class GameFrame extends JFrame {
	private JPanel panel;
	private JPanel boardPanel;
	private int tileCounter = 0;
	private Stack<Tile> tilePool;
	private final Timer timer;

	public GameFrame(String title) {
		this.setTitle(title);
		this.setContentPane(panel);
		this.setSize(Settings.getInstance().windowSize.getD());
		this.setDefaultCloseOperation(GameFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.resizeWithInsets();
		this.addKeyListener(new KeyboardHandler());
		this.timer = new Timer();
	}

	private Int2 calculateTilePosition(Int2 index) {
		return Settings.getInstance().blockSize.mul(index.swap()).add(Settings.getInstance().padding);
	}

	private void resizeWithInsets() {
		setVisible(true);
		Insets insets = this.getInsets();
		int width = Settings.getInstance().windowSize.x + insets.left + insets.right;
		int height = Settings.getInstance().windowSize.y + insets.top + insets.bottom;
		setSize(width, height);
	}

	private void createTileAtPosition(int num, int x, int y) {
		Int2 pos = calculateTilePosition(new Int2(x, y));
		Tile tile = tilePool.pop();
		tile.setVal(num);
		tile.setVisible(true);
		tile.setBounds(pos.x, pos.y, Settings.getInstance().blockSize.getD());
		BoardManager.getInstance().dict[x][y] = tile.getIndex();
		BoardManager.getInstance().val[x][y] = num;
	}

	public void moveJLabelFromTo(int index, int x, int y, boolean destroy) {
		Tile tile = (Tile) boardPanel.getComponent(index);
		Int2 pos = calculateTilePosition(new Int2(x, y));
		Rebounder rebounder = new Rebounder(tile, pos.x, pos.y, Settings.getInstance().animationSlides, destroy);
		timer.schedule(rebounder, 0, Settings.getInstance().animationPeriod);
	}

	private void drawBoardPanel() {
		boardPanel = new JPanel();
		boardPanel.setLayout(null);
		tilePool = new Stack<>();

		for (int i = 0; i < 24; i++) {
			Tile tile = new Tile(this, tileCounter, 2, 0, 0, Settings.getInstance().blockSize);
			tile.setVisible(false);
			boardPanel.add(tile, tileCounter);
			tileCounter += 1;
			tilePool.push(tile);
		}

		randomCreate(2);
		randomCreate(2);
		randomCreate(4);
	}

	public void createUIComponents() {
		drawBoardPanel();
	}

	public void updateNumberAt(int index, int number) {
		Tile tile = (Tile) boardPanel.getComponent(index);
		tile.setVal(number);
	}

	public void randomCreate(int num) {
		if (tilePool.empty()) {
			return;
		}
		int index = (int)(Math.random() * BoardManager.getInstance().calculateSpace());
		if (num == 0) {
			num = ((int)(Math.random() * 2) + 1) << 1;
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (BoardManager.getInstance().dict[i][j] == -1) {
					if (index == 0) {
						createTileAtPosition(num, i, j);
						return;
					} else {
						index -= 1;
					}
				}
			}
		}
	}

	public void collect(Tile tile) {
		tile.setVisible(false);
		tilePool.push(tile);
	}

	public void swapTiles(int a, int b) {
		Tile first = (Tile) boardPanel.getComponent(a);
		Tile second = (Tile) boardPanel.getComponent(b);
		first.setIndex(b);
		second.setIndex(a);
		boardPanel.setComponentZOrder(first, b);
		boardPanel.setComponentZOrder(second, a);
	}
}
