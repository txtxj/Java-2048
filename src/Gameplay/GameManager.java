package Gameplay;

public class GameManager {
	public static GameManager instance;

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

	}

	public void gameOver() {

	}
}
