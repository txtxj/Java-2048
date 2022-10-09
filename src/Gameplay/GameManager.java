package Gameplay;

import GUI.GameFrame;

public class GameManager {
	private static GameManager instance;
	public boolean isAnimating;
	public boolean isGameOver;

	public GameFrame gameFrame;
	public BoardManager boardManager;

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
		isAnimating = false;
		isGameOver = false;

		boardManager = new BoardManager();
		gameFrame = new GameFrame("2048");
	}

	public void gameOver() {
		isAnimating = false;
		isGameOver = true;
		System.out.println("Game Over!");
	}
}
