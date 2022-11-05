package GUI;

import Global.Settings;

import javax.swing.*;
import java.awt.*;

public class LeaderboardPanel extends JPanel {

	private JLabel currentScoreLabel;

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

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Settings.getInstance().leaderboardBorderColor);
		drawBorder(g);
	}

	private void drawBorder(Graphics g) {
		g.drawLine(0, 0, 0, getHeight() - 1);
		g.drawLine(0, 0, getWidth() - 1, 0);
		g.drawLine(getWidth() - 1, 0, getWidth() - 1, getHeight() - 1);
		g.drawLine(0, getHeight() - 1, getWidth() - 1, getHeight() - 1);
	}

	public void setCurrentScore(int val) {
		this.currentScoreLabel.setText("Score: " + val);
	}
}
