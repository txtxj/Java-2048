package GUI;

import Gameplay.KeyboardHandler;
import Global.Settings;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
	private JPanel panel;
	public JPanel boardPanel;

	public GameFrame(String title) {
		this.setTitle(title);
		this.setContentPane(panel);
		this.setSize(Settings.getInstance().windowSize.getD());
		this.setDefaultCloseOperation(GameFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.resizeWithInsets();
		this.addKeyListener(new KeyboardHandler());
	}

	private void resizeWithInsets() {
		setVisible(true);
		Insets insets = this.getInsets();
		int width = Settings.getInstance().windowSize.x + insets.left + insets.right;
		int height = Settings.getInstance().windowSize.y + insets.top + insets.bottom;
		setSize(width, height);
	}

	private void drawBoardPanel() {
		boardPanel = new BoardPanel();
	}

	public void createUIComponents() {
		drawBoardPanel();
	}

	public BoardPanel getBoard() {
		return (BoardPanel) boardPanel;
	}

	public void swapTiles(int a, int b) {
		Tile first = (Tile) boardPanel.getComponent(a);
		Tile second = (Tile) boardPanel.getComponent(b);
		first.setIndex(b);
		second.setIndex(a);
		boardPanel.setComponentZOrder(first, b);
		boardPanel.setComponentZOrder(second, a);
	}
}
