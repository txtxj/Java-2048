package GUI;

import Gameplay.GameManager;
import Global.Int2;

import java.util.TimerTask;

class Rebounder extends TimerTask {
	private int counter;
	private final Int2 origin;
	private final Int2 target;
	private final Tile tile;
	private final int slice;
	private final boolean destroy;

	public Rebounder(Tile tile, int x, int y, int slice, boolean destroy) {
		this.tile = tile;
		this.origin = new Int2(tile.getX(), tile.getY());
		this.target = new Int2(x, y);
		this.counter = 0;
		this.slice = slice;
		this.destroy = destroy;
		GameManager.getInstance().isAnimating = true;
	}

	@Override
	public void run() {
		synchronized (tile) {
			int nowX = (counter * target.x + (slice - counter) * origin.x) / slice;
			int nowY = (counter * target.y + (slice - counter) * origin.y) / slice;
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
}
