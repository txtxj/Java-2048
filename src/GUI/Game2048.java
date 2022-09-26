package GUI;

import java.awt.*;
import javax.swing.*;
import Global.Settings;
import Global.Settings.TileStyle;

public class Game2048 {
	private final Settings settings = new Settings();
	private static Game2048 instance;
	private JPanel panel;
	private JLabel Title;
	private JPanel boardPanel;

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

		frame.setVisible(true);
	}

	private void createUIComponents() {
		boardPanel = new JPanel();
		GridLayout gridLayout = new GridLayout(4, 4);
		boardPanel.setLayout(gridLayout);

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				int printNum = Math.min(4096, 1 << (i * 4 + j + 1));
				JLabel label = new JLabel("%d".formatted(printNum), JLabel.CENTER);
				TileStyle tileStyle = Settings.getInstance().palette.get(printNum);
				label.setBackground(tileStyle.backgroundColor);
				label.setForeground(tileStyle.textColor);
				label.setFont(tileStyle.textFont);
				label.setOpaque(true);
				boardPanel.add(label);
			}
		}
	}
}
