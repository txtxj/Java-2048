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
		this.setResizable(false);

		// Get insets of the windows
		this.setVisible(true);
		Insets insets = this.getInsets();
		int width = Settings.getInstance().windowSize.width + insets.left + insets.right;
		int height = Settings.getInstance().windowSize.height + insets.top + insets.bottom;
		this.setSize(width, height);
	}

	private void createJLabelAtPosition(int num, int x, int y) {
		int positionX = Settings.getInstance().blockSize.width * y;
		int positionY = Settings.getInstance().blockSize.height * x;
		JLabel label = new JLabel("%d".formatted(num), JLabel.CENTER);
		label.setBounds(positionX, positionY, Settings.getInstance().blockSize.width, Settings.getInstance().blockSize.height);
		Settings.TileStyle tileStyle = Settings.getInstance().palette.get(num);
		label.setBackground(tileStyle.backgroundColor);
		label.setForeground(tileStyle.textColor);
		label.setFont(tileStyle.textFont);
		label.setOpaque(true);
		boardPanel.add(label, tileCounter);
		Board.getInstance().dict[x][y] = tileCounter;
		tileCounter += 1;
	}

	public void moveJLabelFromTo(int index, int x, int y, AnimationManager.AfterMovingAction acton) {
		JLabel label = (JLabel) boardPanel.getComponent(index);
		// TODO: Animation, maybe yield return
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
		drawBoardPanel();
		drawTitle();
	}
}
