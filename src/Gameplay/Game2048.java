package Gameplay;

import GUI.GameFrame;

public class Game2048 {
	private static Game2048 instance;

	public GameFrame frame;

	public static Game2048 getInstance() {
		if (instance == null) {
			instance = new Game2048();
		}
		return instance;
	}

	public Game2048() {
		instance = this;
	}

	public static void main(String[] args) {
		getInstance().frame = new GameFrame("2048");
	}

	private void createUIComponents() {
		frame.createUIComponents();
	}
}
