package Gameplay;

import Global.Settings;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class KeyboardHandler implements KeyListener {
	@Override
	public void keyPressed(KeyEvent e) {
		if (!GameManager.getInstance().isGameOver && !GameManager.getInstance().isAnimating && switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT -> BoardManager.getInstance().moveLeft();
			case KeyEvent.VK_RIGHT -> BoardManager.getInstance().moveRight();
			case KeyEvent.VK_UP -> BoardManager.getInstance().moveUp();
			case KeyEvent.VK_DOWN -> BoardManager.getInstance().moveDown();
			default -> false;
		}) {
			Timer timer = new Timer();
			TimerTask task = new TimerTask() {
				@Override
				public void run() {
					GameManager.getInstance().isAnimating = false;
					BoardManager.getInstance().frame.randomCreate(0);
					if (BoardManager.getInstance().isOver()) {
						GameManager.getInstance().gameOver();
					}
				}
			};
			timer.schedule(task, Settings.getInstance().animationTotalTime);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
