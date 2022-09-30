package GUI;

import Gameplay.Board;
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
		//this.setResizable(false);
		this.resizeWithInsets();
	}

	private void resizeWithInsets() {
		setVisible(true);
		Insets insets = this.getInsets();
		int width = Settings.getInstance().windowSize.width + insets.left + insets.right;
		int height = Settings.getInstance().windowSize.height + insets.top + insets.bottom;
		setSize(width, height);
	}

	private void createJLabelAtPosition(int num, int x, int y) {
		int positionX = Settings.getInstance().blockSize.width * y;
		int positionY = Settings.getInstance().blockSize.height * x;
		Tile tile = new Tile(num, positionX, positionY, Settings.getInstance().blockSize);
		boardPanel.add(tile, tileCounter);
		Board.getInstance().dict[x][y] = tileCounter;
		tileCounter += 1;
	}

	public void moveJLabelFromTo(int index, int x, int y, AnimationManager.AfterMovingAction acton) {
		JLabel label = (JLabel) boardPanel.getComponent(index);

	}

	private void drawBoardPanel() {
		boardPanel = new JPanel();
		boardPanel.setLayout(null);

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				int printNum = Math.min(4096, 1 << (i * 4 + j + 1));
				createJLabelAtPosition(printNum, i, j);
			}
		}
	}

	private void drawTitle() {
		title = new JLabel(Settings.getInstance().titleText);
		title.setSize(Settings.getInstance().titleSize);
	}

	public void createUIComponents() {
		drawTitle();
		drawBoardPanel();
	}
}
