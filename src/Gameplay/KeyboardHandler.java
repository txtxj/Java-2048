package Gameplay;

import Global.Settings;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class KeyboardHandler implements KeyListener {
	@Override
	public void keyPressed(KeyEvent e) {
		if (!GameManager.getInstance().isGameOver && !GameManager.getInstance().isAnimating
				&& switch (e.getKeyCode()) {
					case KeyEvent.VK_LEFT -> GameManager.getInstance().boardManager.moveLeft();
					case KeyEvent.VK_RIGHT -> GameManager.getInstance().boardManager.moveRight();
					case KeyEvent.VK_UP -> GameManager.getInstance().boardManager.moveUp();
					case KeyEvent.VK_DOWN -> GameManager.getInstance().boardManager.moveDown();
					default -> false;
		}) {
			Timer timer = new Timer();
			TimerTask task = new TimerTask() {
				@Override
				public void run() {
					GameManager.getInstance().isAnimating = false;
					GameManager.getInstance().gameFrame.getBoard().randomCreate(0);
					if (GameManager.getInstance().boardManager.isOver()) {
						GameManager.getInstance().gameOver();
					}
				}
			};
			timer.schedule(task, Settings.getInstance().animationTotalTime);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_R) {
			GameManager.getInstance().resetGame();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
