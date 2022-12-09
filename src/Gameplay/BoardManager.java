package Gameplay;

import Global.Settings;

public class BoardManager {
	public int[][] val;
	public int[][] dict;

	public BoardManager() {
		initiate();
	}

	private void initiate() {
		this.val = new int[Settings.getInstance().mapSize.y][Settings.getInstance().mapSize.x];
		this.dict = new int[Settings.getInstance().mapSize.y][Settings.getInstance().mapSize.x];
		this.resetGame();
	}

	private void merge(int sourceX, int sourceY, int otherX, int otherY, int originVal) {
		int preMoveIndex = dict[otherX][otherY];
		int targetIndex = dict[sourceX][sourceY];
		if (preMoveIndex < targetIndex) {
			GameManager.getInstance().gameFrame.swapTiles(preMoveIndex, targetIndex);
			dict[otherX][otherY] = targetIndex;
			dict[sourceX][sourceY] = preMoveIndex;
			preMoveIndex = targetIndex;
		}
		dict[otherX][otherY] = -1;
		val[sourceX][sourceY] = originVal << 1;
		val[otherX][otherY] = 0;
		GameManager.getInstance().gameFrame.getBoard().updateNumberAt(dict[sourceX][sourceY], val[sourceX][sourceY]);
		GameManager.getInstance().gameFrame.getBoard().moveJLabelFromTo(preMoveIndex, sourceX, sourceY, true);
		GameManager.getInstance().rankManager.earnScore(val[sourceX][sourceY]);
	}

	private boolean move(int fromX, int fromY, int toX, int toY) {
		if (fromX == toX && fromY == toY) return false;
		dict[toX][toY] = dict[fromX][fromY];
		dict[fromX][fromY] = -1;
		val[toX][toY] = val[fromX][fromY];
		val[fromX][fromY] = 0;
		GameManager.getInstance().gameFrame.getBoard().moveJLabelFromTo(dict[toX][toY], toX, toY, false);
		return true;
	}

	protected boolean moveLeft() {
		boolean flag = false;
		for (int i = 0; i < Settings.getInstance().mapSize.y; i++) {
			int pre = -1;
			int index = -1;
			for (int j = 0; j < Settings.getInstance().mapSize.x; j++) {
				if (val[i][j] == pre) {
					merge(i, index, i, j, val[i][j]);
					pre = -1;
					flag = true;
				} else if (val[i][j] != 0) {
					pre = val[i][j] == 4096 ? -1 : val[i][j];
					index += 1;
					flag |= move(i, j, i, index);
				}
			}
		}
		return flag;
	}

	protected boolean moveRight() {
		boolean flag = false;
		for (int i = 0; i < Settings.getInstance().mapSize.y; i++) {
			int pre = -1;
			int index = Settings.getInstance().mapSize.x;
			for (int j = Settings.getInstance().mapSize.x - 1; j >= 0; j--) {
				if (val[i][j] == pre) {
					merge(i, index, i, j, val[i][j]);
					pre = -1;
					flag = true;
				} else if (val[i][j] != 0) {
					pre = val[i][j] == 4096 ? -1 : val[i][j];
					index -= 1;
					flag |= move(i, j, i, index);
				}
			}
		}
		return flag;
	}

	protected boolean moveDown() {
		boolean flag = false;
		for (int j = 0; j < Settings.getInstance().mapSize.x; j++) {
			int pre = -1;
			int index = Settings.getInstance().mapSize.y;
			for (int i = Settings.getInstance().mapSize.y - 1; i >= 0; i--) {
				if (val[i][j] == pre) {
					merge(index, j, i, j, val[i][j]);
					pre = -1;
					flag = true;
				} else if (val[i][j] != 0) {
					pre = val[i][j] == 4096 ? -1 : val[i][j];
					index -= 1;
					flag |= move(i, j, index, j);
				}
			}
		}
		return flag;
	}

	protected boolean moveUp() {
		boolean flag = false;
		for (int j = 0; j < Settings.getInstance().mapSize.x; j++) {
			int pre = -1;
			int index = -1;
			for (int i = 0; i < Settings.getInstance().mapSize.y; i++) {
				if (val[i][j] == pre) {
					merge(index, j, i, j, val[i][j]);
					pre = -1;
					flag = true;
				} else if (val[i][j] != 0) {
					pre = val[i][j] == 4096 ? -1 : val[i][j];
					index += 1;
					flag |= move(i, j, index, j);
				}
			}
		}
		return flag;
	}

	protected boolean isOver() {
		for (int i = 0; i < Settings.getInstance().mapSize.y; i++) {
			for (int j = 0; j < Settings.getInstance().mapSize.x; j++) {
				if (val[i][j] == 0 || (i != Settings.getInstance().mapSize.y - 1 && val[i][j] == val[i + 1][j] && val[i][j] != 4096)
						|| (j != Settings.getInstance().mapSize.x - 1 && val[i][j] == val[i][j + 1] && val[i][j] != 4096)) {
					return false;
				}
			}
		}
		return true;
	}

	public int calculateSpace() {
		int cnt = 0;
		for (int i = 0; i < Settings.getInstance().mapSize.y; i++) {
			for (int j = 0; j < Settings.getInstance().mapSize.x; j++) {
				if (val[i][j] == 0) {
					cnt += 1;
				}
			}
		}
		return cnt;
	}

	protected void resetGame() {
		for (int i = 0; i < Settings.getInstance().mapSize.y; i++) {
			for (int j = 0; j < Settings.getInstance().mapSize.x; j++) {
				this.val[i][j] = 0;
				this.dict[i][j] = -1;
			}
		}
	}

	public int calBonus() {
		int ret = 0;
		for (int[] line : val) {
			for (int score : line) {
				ret += score;
			}
		}
		return ret;
	}
}
