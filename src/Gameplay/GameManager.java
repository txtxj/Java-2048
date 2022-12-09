package Gameplay;

import GUI.GameFrame;

public class GameManager {
	private static GameManager instance;
	public boolean isAnimating;
	public boolean isGameOver;

	protected GameFrame gameFrame;
	public BoardManager boardManager;
	public RankManager rankManager;

	public static GameManager getInstance() {
		if (instance == null) {
			instance = new GameManager();
		}
		return instance;
	}

	protected GameManager() {
		instance = this;
		initiate();
	}

	private void initiate() {
		this.isAnimating = false;
		this.isGameOver = false;

		this.rankManager = new RankManager();
		this.boardManager = new BoardManager();
		this.gameFrame = new GameFrame("2048");
	}

	protected void gameOver() {
		isAnimating = false;
		isGameOver = true;
		System.out.println("Game Over!");
		gameFrame.getBoard().showGameOver(true);
	}

	public void resetGame() {
		isAnimating = false;
		isGameOver = false;
		rankManager.resetGame();
		boardManager.resetGame();
		gameFrame.getBoard().resetGame();
		gameFrame.getRanking().showAllRank();
		gameFrame.requestFocus();
	}
}
