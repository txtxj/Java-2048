package GUI;

import javax.swing.*;

import Gameplay.Board;
import Global.Settings;
import Global.Settings.TileStyle;
import GUI.AnimationManager.AfterMovingAction;

public class Game2048 {
	private static Game2048 instance;
	private JPanel panel;
	private JPanel boardPanel;
	private int tileCounter = 0;

	public static Game2048 getInstance() {
		if (instance == null) {
			instance = new Game2048();
		}
		return instance;
	}

	public Game2048() {
		instance = this;
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("2048");
		frame.setContentPane(new Game2048().panel);
		frame.setSize(Settings.getInstance().windowSize);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
	}

	private void createJLabelAtPosition(int num, int x, int y) {
		int positionX = Settings.getInstance().blockSize.width * y;
		int positionY = Settings.getInstance().blockSize.height * x;
		JLabel label = new JLabel("%d".formatted(num), JLabel.CENTER);
		label.setBounds(positionX, positionY, Settings.getInstance().blockSize.width, Settings.getInstance().blockSize.height);
		TileStyle tileStyle = Settings.getInstance().palette.get(num);
		label.setBackground(tileStyle.backgroundColor);
		label.setForeground(tileStyle.textColor);
		label.setFont(tileStyle.textFont);
		label.setOpaque(true);
		boardPanel.add(label, tileCounter);
		Board.getInstance().dict[x][y] = tileCounter;
		tileCounter += 1;
	}

	public void moveJLabelFromTo(int index, int x, int y, AfterMovingAction acton) {
		JLabel label = (JLabel) boardPanel.getComponent(index);
		// TODO: Animation, maybe yield return
	}

	private void createUIComponents() {
		boardPanel = new JPanel();
		boardPanel.setLayout(null);

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				int printNum = Math.min(4096, 1 << (i * 4 + j + 1));
				createJLabelAtPosition(printNum, i, j);
			}
		}
	}
}
