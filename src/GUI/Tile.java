package GUI;

import javax.swing.*;

import Global.Int2;
import Global.Settings;


class Tile extends JLabel {

	private int index;
	private final BoardPanel board;

	protected int getIndex() {
		return this.index;
	}
	protected void setIndex(int val) {
		this.index = val;
	}

	protected void setVal(int val) {
		Settings.FontStyle style = Settings.getInstance().palette.get(val);
		this.setText("%d".formatted(val));
		this.setFont(style.textFont);
		this.setBackground(style.backgroundColor);
		this.setForeground(style.textColor);
	}

	protected Tile(BoardPanel board, int index, int val, int x, int y, Int2 dimension) {
		this.board = board;
		this.index = index;
		this.setVal(val);
		this.setHorizontalAlignment(Tile.CENTER);
		this.setVerticalAlignment(Tile.CENTER);
		this.setBounds(x, y, dimension.x, dimension.y);
		this.setOpaque(true);
	}

	protected void setBounds(int x, int y, Int2 size) {
		super.setBounds(x, y, size.x, size.y);
	}

	protected void setBounds(int x, int y) {
		super.setBounds(x, y, getWidth(), getHeight());
	}

	protected void collect() {
		board.collect(this);
	}
}
