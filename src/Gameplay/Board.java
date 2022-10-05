package Gameplay;

import GUI.AnimationManager;

public class Board {
	public int[][] val;
	public int[][] dict;
	public static Board instance;

	public static Board getInstance() {
		if (instance == null) {
			instance = new Board();
		}
		return instance;
	}

	public Board() {
		Initiate();
	}

	private void Initiate() {
		this.val = new int[4][4];
		this.dict = new int[4][4];
	}

	private void merge(int sourceX, int sourceY, int otherX, int otherY, int originVal) {
		int preMoveIndex = dict[otherX][otherY];
		dict[otherX][otherY] = -1;
		val[sourceX][sourceY] = originVal << 1;
		val[otherX][otherY] = 0;
		Game2048.getInstance().frame.updateNumberAt(dict[sourceX][sourceY], val[sourceX][sourceY]);
		Game2048.getInstance().frame.moveJLabelFromTo(preMoveIndex, sourceX, sourceY, AnimationManager.AfterMovingAction.destroy);
	}

	private void move(int fromX, int fromY, int toX, int toY) {
		if (fromX == toX && fromY == toY) return;
		dict[toX][toY] = dict[fromX][fromY];
		dict[fromX][fromY] = -1;
		val[toX][toY] = val[fromX][fromY];
		val[fromX][fromY] = 0;
		Game2048.getInstance().frame.moveJLabelFromTo(dict[toX][toY], toX, toY, AnimationManager.AfterMovingAction.nothing);
	}

	public void moveLeft() {
		for (int i = 0; i < 4; i++) {
			int pre = -1;
			int index = -1;
			for (int j = 0; j < 4; j++) {
				if (val[i][j] == pre) {
					merge(i, index, i, j, val[i][j]);
					pre = -1;
				} else if (val[i][j] != 0) {
					pre = val[i][j];
					index += 1;
					move(i, j, i, index);
				}
			}
		}
	}

	public void moveRight() {
		for (int i = 0; i < 4; i++) {
			int pre = -1;
			int index = 4;
			for (int j = 3; j >= 0; j--) {
				if (val[i][j] == pre) {
					merge(i, index, i, j, val[i][j]);
					pre = -1;
				} else if (val[i][j] != 0) {
					pre = val[i][j];
					index -= 1;
					move(i, j, i, index);
				}
			}
		}
	}

	public void moveDown() {
		for (int j = 0; j < 4; j++) {
			int pre = -1;
			int index = 4;
			for (int i = 3; i >= 0; i--) {
				if (val[i][j] == pre) {
					merge(index, j, i, j, val[i][j]);
					pre = -1;
				} else if (val[i][j] != 0) {
					pre = val[i][j];
					index -= 1;
					move(i, j, index, j);
				}
			}
		}
	}

	public void moveUp() {
		for (int j = 0; j < 4; j++) {
			int pre = -1;
			int index = -1;
			for (int i = 0; i < 4; i++) {
				if (val[i][j] == pre) {
					merge(index, j, i, j, val[i][j]);
					pre = -1;
				} else if (val[i][j] != 0) {
					pre = val[i][j];
					index += 1;
					move(i, j, index, j);
				}
			}
		}
	}
}
