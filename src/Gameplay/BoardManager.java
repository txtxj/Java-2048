package Gameplay;

public class BoardManager {
	public int[][] val;
	public int[][] dict;

	public BoardManager() {
		Initiate();
	}

	private void Initiate() {
		this.val = new int[4][4];
		this.dict = new int[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				this.val[i][j] = 0;
				this.dict[i][j] = -1;
			}
		}
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

	public boolean moveLeft() {
		boolean flag = false;
		for (int i = 0; i < 4; i++) {
			int pre = -1;
			int index = -1;
			for (int j = 0; j < 4; j++) {
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

	public boolean moveRight() {
		boolean flag = false;
		for (int i = 0; i < 4; i++) {
			int pre = -1;
			int index = 4;
			for (int j = 3; j >= 0; j--) {
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

	public boolean moveDown() {
		boolean flag = false;
		for (int j = 0; j < 4; j++) {
			int pre = -1;
			int index = 4;
			for (int i = 3; i >= 0; i--) {
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

	public boolean moveUp() {
		boolean flag = false;
		for (int j = 0; j < 4; j++) {
			int pre = -1;
			int index = -1;
			for (int i = 0; i < 4; i++) {
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

	public boolean isOver() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (val[i][j] == 0 || (i != 3 && val[i][j] == val[i + 1][j]) || (j != 3 && val[i][j] == val[i][j + 1])) {
					return false;
				}
			}
		}
		return true;
	}

	public int calculateSpace() {
		int cnt = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (val[i][j] == 0) {
					cnt += 1;
				}
			}
		}
		return cnt;
	}

	public void resetGame() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				this.val[i][j] = 0;
				this.dict[i][j] = -1;
			}
		}
	}
}
