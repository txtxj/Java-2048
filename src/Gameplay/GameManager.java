package Gameplay;

public class GameManager {
	private static GameManager instance;
	public boolean isAnimating;

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
	}

	public void gameOver() {
		isAnimating = false;
	}
}
