package GUI;

import javax.swing.*;
import Global.Settings;

import java.awt.*;

public class Tile extends JLabel {

	private final int index;

	public int getIndex() {
		return this.index;
	}

	public void setVal(int val) {
		Settings.TileStyle style = Settings.getInstance().palette.get(val);
		this.setText("%d".formatted(val));
		this.setFont(style.textFont);
		this.setBackground(style.backgroundColor);
		this.setForeground(style.textColor);
	}

	public Tile(int index, int val, int x, int y, Dimension dimension) {
		this.index = index;
		this.setVal(val);
		this.setHorizontalAlignment(Tile.CENTER);
		this.setVerticalAlignment(Tile.CENTER);
		this.setBounds(x, y, dimension.width, dimension.height);
		this.setOpaque(true);
	}

	public void setBounds(int x, int y, Dimension dimension) {
		this.setBounds(x, y, dimension.width, dimension.height);
	}
}
