package GUI;

import Global.Settings;

import javax.swing.*;
import java.awt.*;

public class RankingPanel extends JPanel {
	public RankingPanel() {
		this.Initiate();
	}

	private void Initiate() {
		this.setLayout(null);
		this.setBounds(Settings.getInstance().blockSize.x * 4 + Settings.getInstance().padding.x,
				Settings.getInstance().padding.y,
				Settings.getInstance().rankSize.x, Settings.getInstance().blockSize.y * 4);
		this.setBackground(Settings.getInstance().rankingBackgroundColor);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Settings.getInstance().rankingBorderColor);
		drawBorder(g);
	}

	private void drawBorder(Graphics g) {
		g.drawLine(0, 0, 0, getHeight() - 1);
		g.drawLine(0, 0, getWidth() - 1, 0);
		g.drawLine(getWidth() - 1, 0, getWidth() - 1, getHeight() - 1);
		g.drawLine(0, getHeight() - 1, getWidth() - 1, getHeight() - 1);
	}
}
