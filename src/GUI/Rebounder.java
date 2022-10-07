package GUI;

import Gameplay.GameManager;

import java.util.TimerTask;

public class Rebounder extends TimerTask {
	private int counter;
	private final int origX;
	private final int origY;
	private final int targetX;
	private final int targetY;
	private final Tile tile;
	private final int slice;
	private final boolean destroy;

	public Rebounder(Tile tile, int x, int y, int slice, boolean destroy) {
		this.tile = tile;
		this.origX = tile.getX();
		this.origY = tile.getY();
		this.targetX = x;
		this.targetY = y;
		this.counter = 0;
		this.slice = slice;
		this.destroy = destroy;
		GameManager.getInstance().isAnimating = true;
	}

	@Override
	public void run() {
		int nowX = (counter * targetX + (slice - counter) * origX) / slice;
		int nowY = (counter * targetY + (slice - counter) * origY) / slice;
		tile.setBounds(nowX, nowY);
		if (counter == slice) {
			if (destroy) {
				tile.collect();
			}
			cancel();
		}
		counter += 1;
	}
}
