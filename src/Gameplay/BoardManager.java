package Gameplay;

import GUI.AnimationManager;
import GUI.GameFrame;

public class BoardManager {
	public int[][] val;
	public int[][] dict;
	public GameFrame frame;
	public static BoardManager instance;

	public static BoardManager getInstance() {
		if (instance == null) {
			instance = new BoardManager();
		}
		return instance;
	}

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
		dict[otherX][otherY] = -1;
		val[sourceX][sourceY] = originVal << 1;
		val[otherX][otherY] = 0;
		frame.updateNumberAt(dict[sourceX][sourceY], val[sourceX][sourceY]);
		frame.moveJLabelFromTo(preMoveIndex, sourceX, sourceY, AnimationManager.AfterMovingAction.destroy);
	}

	private boolean move(int fromX, int fromY, int toX, int toY) {
		if (fromX == toX && fromY == toY) return false;
		dict[toX][toY] = dict[fromX][fromY];
		dict[fromX][fromY] = -1;
		val[toX][toY] = val[fromX][fromY];
		val[fromX][fromY] = 0;
		frame.moveJLabelFromTo(dict[toX][toY], toX, toY, AnimationManager.AfterMovingAction.nothing);
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
					pre = val[i][j];
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
					pre = val[i][j];
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
					pre = val[i][j];
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
					pre = val[i][j];
					index += 1;
					flag |= move(i, j, index, j);
				}
			}
		}
		return flag;
	}
}
