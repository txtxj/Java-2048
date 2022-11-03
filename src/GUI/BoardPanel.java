package GUI;

import Gameplay.GameManager;
import Global.Settings;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Timer;

public class BoardPanel extends JPanel {

	private int tileCounter = 0;
	private LinkedList<Tile> tilePool;
	private Timer timer;

	public BoardPanel() {
		this.Initiate();
	}

	private Int2 calculateTilePosition(Int2 index) {
		return Settings.getInstance().blockSize.mul(index.swap()).add(Settings.getInstance().blockSize.sub(Settings.getInstance().blockInnerSize).div(2));
	}

	private void createTileAtPosition(int num, int x, int y) {
		Int2 pos = calculateTilePosition(new Int2(x, y));
		Tile tile = tilePool.pop();
		tile.setVal(num);
		tile.setVisible(true);
		tile.setBounds(pos.x, pos.y, Settings.getInstance().blockInnerSize);
		GameManager.getInstance().boardManager.dict[x][y] = tile.getIndex();
		GameManager.getInstance().boardManager.val[x][y] = num;
	}

	public void moveJLabelFromTo(int index, int x, int y, boolean destroy) {
		Tile tile = getTile(index);
		Int2 pos = calculateTilePosition(new Int2(x, y));
		Rebounder rebounder = new Rebounder(tile, pos.x, pos.y, Settings.getInstance().animationSlides, destroy);
		timer.scheduleAtFixedRate(rebounder, 0, Settings.getInstance().animationPeriod);
	}

	private void Initiate() {
		this.timer = new Timer();
		this.setLayout(null);
		this.setBounds(Settings.getInstance().padding.x, Settings.getInstance().padding.y,
				Settings.getInstance().blockSize.x * 4, Settings.getInstance().blockSize.y * 4);
		this.setBackground(Settings.getInstance().boardBackgroundColor);
		this.tilePool = new LinkedList<>();

		for (int i = 0; i < 24; i++) {
			Tile tile = new Tile(this, tileCounter, 2, 0, 0, Settings.getInstance().blockSize);
			tile.setVisible(false);
			add(tile, tileCounter);
			tileCounter += 1;
			tilePool.offer(tile);
		}

		randomCreateTile(2);
		randomCreateTile(2);
		randomCreateTile(4);
	}

	public void randomCreateTile(int num) {
		if (tilePool.size() == 0) {
			return;
		}
		int index = (int)(Math.random() * GameManager.getInstance().boardManager.calculateSpace());
		if (num == 0) {
			num = ((int)(Math.random() * 2) + 1) << 1;
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (GameManager.getInstance().boardManager.dict[i][j] == -1) {
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
		tilePool.offer(tile);
	}

	public Tile getTile(int index) {
		return (Tile) getComponent(index);
	}

	public void updateNumberAt(int index, int number) {
		Tile tile = getTile(index);
		tile.setVal(number);
	}

	public void resetGame() {
		tilePool.clear();

		for (int i = 0; i < 24; i++) {
			Tile tile = getTile(i);
			tile.setVisible(false);
			tilePool.offer(tile);
		}

		randomCreateTile(2);
		randomCreateTile(2);
		randomCreateTile(4);
	}

	@Override
	public void paintChildren(Graphics g) {
		final Int2 limit0 = Settings.getInstance().blockSize.sub(Settings.getInstance().blockInnerSize).div(2);
		final Int2 limit1 = Settings.getInstance().blockSize.sub(limit0);
		g.setColor(Settings.getInstance().blockBorderColor);
		for (int i = 0; i < 4; i++) {
			g.fillRect(i * Settings.getInstance().blockSize.x, 0, limit0.x, getHeight());
			g.fillRect(i * Settings.getInstance().blockSize.x + limit1.x, 0, limit0.x, getHeight());
			g.fillRect(0, i * Settings.getInstance().blockSize.y, getWidth(), limit0.y);
			g.fillRect(0, i * Settings.getInstance().blockSize.y + limit1.y, getWidth(), limit0.y);
		}
		super.paintChildren(g);
	}
}
