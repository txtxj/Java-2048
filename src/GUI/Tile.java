package GUI;

import javax.swing.*;
import Global.Settings;

import java.awt.*;

public class Tile extends JLabel {

	private int index;
	private final GameFrame frame;

	public int getIndex() {
		return this.index;
	}
	public void setIndex(int val) {
		this.index = val;
	}

	public void setVal(int val) {
		Settings.TileStyle style = Settings.getInstance().palette.get(val);
		this.setText("%d".formatted(val));
		this.setFont(style.textFont);
		this.setBackground(style.backgroundColor);
		this.setForeground(style.textColor);
	}

	public Tile(GameFrame frame, int index, int val, int x, int y, Dimension dimension) {
		this.frame = frame;
		this.index = index;
		this.setVal(val);
		this.setHorizontalAlignment(Tile.CENTER);
		this.setVerticalAlignment(Tile.CENTER);
		this.setBounds(x, y, dimension.width, dimension.height);
		this.setOpaque(true);
	}

	public Tile(GameFrame frame, int index, int val, int x, int y, Int2 dimension) {
		this.frame = frame;
		this.index = index;
		this.setVal(val);
		this.setHorizontalAlignment(Tile.CENTER);
		this.setVerticalAlignment(Tile.CENTER);
		this.setBounds(x, y, dimension.x, dimension.y);
		this.setOpaque(true);
	}

	public void setBounds(int x, int y, Dimension dimension) {
		this.setBounds(x, y, dimension.width, dimension.height);
	}

	public void setBounds(int x, int y) {
		this.setBounds(x, y, getWidth(), getHeight());
	}

	public void collect() {
		frame.collect(this);
	}
}
