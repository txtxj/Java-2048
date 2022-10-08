package Gameplay;

public class GameManager {
	private static GameManager instance;
	public boolean isAnimating;
	public boolean isGameOver;

	public static GameManager getInstance() {
		if (instance == null) {
			instance = new GameManager();
		}
		return instance;
	}

	public GameManager() {
		Initiate();
	}

	public void Initiate() {
		isAnimating = false;
		isGameOver = false;
	}

	public void gameOver() {
		isAnimating = false;
		isGameOver = true;
		System.out.println("Game Over!");
	}
}
