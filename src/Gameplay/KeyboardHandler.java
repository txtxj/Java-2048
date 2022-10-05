package Gameplay;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardHandler implements KeyListener {
	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode() + " is pressed");
		switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT -> Board.getInstance().moveLeft();
			case KeyEvent.VK_RIGHT -> Board.getInstance().moveRight();
			case KeyEvent.VK_UP -> Board.getInstance().moveUp();
			case KeyEvent.VK_DOWN -> Board.getInstance().moveDown();
			default -> {}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
