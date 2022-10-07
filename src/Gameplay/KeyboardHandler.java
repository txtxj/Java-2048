package Gameplay;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardHandler implements KeyListener {
	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode() + " is pressed");
		if (switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT -> BoardManager.getInstance().moveLeft();
			case KeyEvent.VK_RIGHT -> BoardManager.getInstance().moveRight();
			case KeyEvent.VK_UP -> BoardManager.getInstance().moveUp();
			case KeyEvent.VK_DOWN -> BoardManager.getInstance().moveDown();
			default -> false;
		}) {
			BoardManager.getInstance().frame.randomCreate(0);
			if (BoardManager.getInstance().isOver()) {
				GameManager.getInstance().gameOver();
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
