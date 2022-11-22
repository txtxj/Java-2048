package GUI;

import Gameplay.GameManager;
import Gameplay.RankManager;
import Global.Settings;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LeaderboardPanel extends JPanel {
	private JLabel currentScoreLabel;

	private List<RankManager.RankItem> rankList;
	private List<RankLabel> labelList;

	public LeaderboardPanel() {
		this.Initiate();
	}

	private void Initiate() {
		this.setLayout(null);
		this.setBounds(Settings.getInstance().blockSize.x * Settings.getInstance().mapSize.x + Settings.getInstance().padding.x,
				Settings.getInstance().padding.y,
				Settings.getInstance().rankSize.x,
				Settings.getInstance().blockSize.y * Settings.getInstance().mapSize.y);
		this.setBackground(Settings.getInstance().leaderboardBackgroundColor);

		CreateCurrentScore();
		CreateRankLabelList();
//		showAllRank();
	}

	private void CreateCurrentScore() {
		this.currentScoreLabel = new JLabel("Score: 0");
		this.currentScoreLabel.setBounds(
				Settings.getInstance().rankSize.x / 4,
				Settings.getInstance().rankSize.x / 4,
				Settings.getInstance().rankSize.x / 2,
				Settings.getInstance().rankSize.x / 4
		);
		this.currentScoreLabel.setFont(Settings.getInstance().leaderboardScoreStyle.textFont);
		this.currentScoreLabel.setForeground(Settings.getInstance().leaderboardScoreStyle.textColor);
		this.currentScoreLabel.setHorizontalAlignment(JLabel.CENTER);
		this.add(currentScoreLabel);
	}

	private void CreateRankLabelList() {
		this.rankList = GameManager.getInstance().rankManager.rankList;
		this.labelList = new ArrayList<>();
		int showNumber = Settings.getInstance().rankShowNumber;
		for (int i = 0; i < showNumber; i++) {
			RankLabel rankLabel = new RankLabel();
			rankLabel.setBounds(
					Settings.getInstance().rankSize.x / 8,
					Settings.getInstance().rankSize.x / 8 * (i + 5),
					Settings.getInstance().rankSize.x / 4 * 3,
					Settings.getInstance().rankSize.x / 4
			);
			this.labelList.add(rankLabel);
		}
		for (RankLabel label : this.labelList) {
			this.add(label);
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Settings.getInstance().leaderboardBorderColor);
		drawBorder(g);
	}

	private void drawBorder(Graphics g) {
		g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
	}

	public void setCurrentScore(int val) {
		this.currentScoreLabel.setText("Score: " + val);
	}

	public void showAllRank() {
		int showNumber = Settings.getInstance().rankShowNumber;
		for (int i = 0; i < showNumber; i++) {
			labelList.get(i).showItem(rankList.get(i));
		}
	}
}
