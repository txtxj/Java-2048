package GUI;

import Gameplay.GameManager;
import Global.Settings;

import javax.swing.*;

public class GameOverMenu extends JPanel {
	private JTextField loginTextField;
	private JLabel currentScore;

	public GameOverMenu(int x, int y, int width, int height) {
		this.setLayout(null);
		this.setBounds(x, y, width, height);
		this.setBackground(Settings.getInstance().gameOverBackgroundColor);
		this.initiate();
		this.setVisible(false);
	}

	private void initiate() {
		drawResetButton();
		drawGameOverText();
		drawLoginTextField();
		drawLoginButton();
		drawCurrentScore();
	}

	private void drawResetButton() {
		JButton reset = new JButton("Reset");
		reset.setBounds(getWidth() / 16 * 3, getHeight() / 4 * 3, getWidth() / 4, getWidth() / 16);
		reset.addActionListener(e -> GameManager.getInstance().resetGame());
		reset.setVisible(true);
		add(reset);
	}

	private void drawGameOverText() {
		JLabel gameOver = new JLabel("Game Over");
		gameOver.setFont(Settings.getInstance().gameOverTextStyle.textFont);
		gameOver.setForeground(Settings.getInstance().gameOverTextStyle.textColor);
		gameOver.setBackground(Settings.getInstance().gameOverTextStyle.backgroundColor);
		gameOver.setBounds(0, getHeight() / 8, getWidth(), getHeight() / 4);
		gameOver.setVerticalAlignment(JLabel.CENTER);
		gameOver.setHorizontalAlignment(JLabel.CENTER);
		gameOver.setVisible(true);
		gameOver.setOpaque(true);
		add(gameOver);
	}

	private void drawLoginTextField() {
		loginTextField = new JTextField();
		loginTextField.setBounds(getWidth() / 16 * 5, getHeight() / 8 * 5, getWidth() / 8 * 3, getHeight() / 32 * 3);
		loginTextField.requestFocus();
		loginTextField.setHorizontalAlignment(JTextField.CENTER);
		loginTextField.setFont(Settings.getInstance().leaderboardScoreStyle.textFont);
		loginTextField.addActionListener(e -> {
			GameManager.getInstance().rankManager.login(loginTextField.getText());
			GameManager.getInstance().resetGame();
		});
		add(loginTextField);
	}

	private void drawLoginButton() {
		JButton reset = new JButton("Login");
		reset.setBounds(getWidth() / 16 * 9, getHeight() / 4 * 3, getWidth() / 4, getWidth() / 16);
		reset.addActionListener(e -> {
			GameManager.getInstance().rankManager.login(loginTextField.getText());
			GameManager.getInstance().resetGame();
		});
		reset.setVisible(true);
		add(reset);
	}

	private void drawCurrentScore() {
		currentScore = new JLabel();
		currentScore.setBounds(0, getHeight() / 2, getWidth(), getHeight() / 16);
		currentScore.setFont(Settings.getInstance().finalScoreStyle.textFont);
		currentScore.setForeground(Settings.getInstance().finalScoreStyle.textColor);
		currentScore.setBackground(Settings.getInstance().finalScoreStyle.backgroundColor);
		currentScore.setVerticalAlignment(JLabel.CENTER);
		currentScore.setHorizontalAlignment(JLabel.CENTER);
		currentScore.setVisible(true);
		currentScore.setOpaque(true);
		add(currentScore);
	}

	@Override
	public void setVisible(boolean aFlag) {
		super.setVisible(aFlag);
		loginTextField.setText("");
		if (aFlag) {
			int bonus = GameManager.getInstance().boardManager.calBonus();
			currentScore.setText(
					"Score: " + GameManager.getInstance().rankManager.currentScore + " + " + bonus
			);
		}
	}
}
