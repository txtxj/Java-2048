package GUI;

import javax.swing.*;
import Global.Settings;

import java.awt.*;

public class Tile extends JLabel {

	private int val;

	public int getVal() {
		return this.val;
	}

	public void setVal(int val) {
		this.val = val;
	}

	public Tile(int val, int x, int y, Dimension dimension) {
		this.val = val;
		Settings.TileStyle style = Settings.getInstance().palette.get(val);
		this.setText("%d".formatted(val));
		this.setHorizontalAlignment(Tile.CENTER);
		this.setVerticalAlignment(Tile.CENTER);
		this.setFont(style.textFont);
		this.setBackground(style.backgroundColor);
		this.setForeground(style.textColor);
		this.setBounds(x, y, dimension.width, dimension.height);
		this.setOpaque(true);
	}
}
