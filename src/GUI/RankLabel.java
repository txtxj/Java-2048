package GUI;

import Gameplay.RankManager;
import Global.Settings;

import javax.accessibility.Accessible;
import javax.swing.*;
import java.util.function.BiConsumer;

class RankLabel extends JComponent implements SwingConstants, Accessible {
	private final JLabel idLabel;
	private final JLabel scoreLabel;

	protected RankLabel() {
		idLabel = new JLabel();
		scoreLabel = new JLabel();
		BiConsumer<JLabel, String> labelInit = (JLabel label, String text) -> {
			label.setFont(Settings.getInstance().leaderboardScoreStyle.textFont);
			label.setForeground(Settings.getInstance().leaderboardScoreStyle.textColor);
			label.setHorizontalAlignment(label == idLabel ? JLabel.LEFT : JLabel.RIGHT);
			label.setText(text);
			this.add(label);
		};
		labelInit.accept(idLabel, "-----");
		labelInit.accept(scoreLabel, "-----");
	}

	@Override
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		this.idLabel.setBounds(0, 0, width / 2, height);
		this.scoreLabel.setBounds(width / 2, 0, width - width / 2, height);
	}

	protected void showItem(RankManager.RankItem item) {
		idLabel.setText(item.name);
		scoreLabel.setText(String.valueOf(item.score));
	}
}
