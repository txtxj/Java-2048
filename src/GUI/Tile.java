package GUI;

import javax.swing.*;
import Global.Settings;


public class Tile extends JLabel {

	private int index;
	private final BoardPanel board;

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

	public Tile(BoardPanel board, int index, int val, int x, int y, Int2 dimension) {
		this.board = board;
		this.index = index;
		this.setVal(val);
		this.setHorizontalAlignment(Tile.CENTER);
		this.setVerticalAlignment(Tile.CENTER);
		this.setBounds(x, y, dimension.x, dimension.y);
		this.setOpaque(true);
	}

	public void setBounds(int x, int y, Int2 size) {
		super.setBounds(x, y, size.x, size.y);
	}

	public void setBounds(int x, int y) {
		super.setBounds(x, y, getWidth(), getHeight());
	}

	public void collect() {
		board.collect(this);
	}
}
