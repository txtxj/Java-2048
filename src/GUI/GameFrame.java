package GUI;

import Gameplay.Board;
import Gameplay.KeyboardHandler;
import Global.Settings;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
	private JPanel panel;
	private JPanel boardPanel;
	private JLabel title;
	private int tileCounter = 0;

	public GameFrame(String title) {
		this.setTitle(title);
		this.setContentPane(panel);
		this.setSize(Settings.getInstance().windowSize);
		this.setDefaultCloseOperation(GameFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.resizeWithInsets();
		this.addKeyListener(new KeyboardHandler());
	}

	private void resizeWithInsets() {
		setVisible(true);
		Insets insets = this.getInsets();
		int width = Settings.getInstance().windowSize.width + insets.left + insets.right;
		int height = Settings.getInstance().windowSize.height + insets.top + insets.bottom;
		setSize(width, height);
	}

	private void createTileAtPosition(int num, int x, int y) {
		int positionX = Settings.getInstance().blockSize.width * y;
		int positionY = Settings.getInstance().blockSize.height * x;
		Tile tile = new Tile(num, positionX, positionY, Settings.getInstance().blockSize);
		boardPanel.add(tile, tileCounter);
		Board.getInstance().dict[x][y] = tileCounter;
		Board.getInstance().val[x][y] = num;
		tileCounter += 1;
	}

	public void moveJLabelFromTo(int index, int x, int y, AnimationManager.AfterMovingAction action) {
		Tile tile = (Tile) boardPanel.getComponent(index);
		// Todo: display animation
		int positionX = Settings.getInstance().blockSize.width * y;
		int positionY = Settings.getInstance().blockSize.height * x;
		tile.setBounds(positionX, positionY, Settings.getInstance().blockSize.width, Settings.getInstance().blockSize.height);
		// Todo: destroy unused tile
		if (action == AnimationManager.AfterMovingAction.destroy) {
			tile.setVisible(false);
		}
	}

	private void drawBoardPanel() {
		boardPanel = new JPanel();
		boardPanel.setLayout(null);

		// Test:
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				int printNum = Math.min(1024, 1 << (i * 4 + j + 1));
				createTileAtPosition(printNum, i, j);
			}
		}
	}

	private void drawTitle() {
		title = new JLabel(Settings.getInstance().titleText);
		title.setPreferredSize(Settings.getInstance().titleSize);
	}

	public void createUIComponents() {
		drawTitle();
		drawBoardPanel();
	}

	public void updateNumberAt(int index, int number) {
		Tile tile = (Tile) boardPanel.getComponent(index);
		tile.setVal(number);
	}
}
