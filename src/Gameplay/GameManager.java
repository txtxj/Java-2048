package Gameplay;

import GUI.GameFrame;

public class GameManager {
	private static GameManager instance;
	public boolean isAnimating;
	public boolean isGameOver;

	public GameFrame gameFrame;
	public BoardManager boardManager;
	public RankManager rankManager;

	public static GameManager getInstance() {
		if (instance == null) {
			instance = new GameManager();
		}
		return instance;
	}

	public GameManager() {
		instance = this;
		Initiate();
	}

	public void Initiate() {
		this.isAnimating = false;
		this.isGameOver = false;

		this.boardManager = new BoardManager();
		this.gameFrame = new GameFrame("2048");
		this.rankManager = new RankManager();
	}

	public void gameOver() {
		isAnimating = false;
		isGameOver = true;
		System.out.println("Game Over!");
	}

	public void resetGame() {
		isAnimating = false;
		isGameOver = false;
		rankManager.resetGame();
		boardManager.resetGame();
		gameFrame.getBoard().resetGame();
	}
}
