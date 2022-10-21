package Gameplay;

import java.util.ArrayList;
import java.util.List;

public class RankManager {
	public static class RankItem implements Comparable<RankItem> {
		public int score;
		public int loginTime;
		public String name;

		@Override
		public int compareTo(RankItem o) {
			if (this.score == o.score) {
				return this.loginTime - o.loginTime;
			}
			return o.score - this.score;
		}
	}

	List<RankItem> rankList;

	private int currentScore;

	public RankManager() {
		currentScore = 0;
		rankList = new ArrayList<>();
	}

	public void resetGame() {
		currentScore = 0;
		updateScore();
	}

	public void earnScore(int score) {
		currentScore += score;
		updateScore();
	}

	private void updateScore() {
		GameManager.getInstance().gameFrame.getRanking().setCurrentScore(currentScore);
	}

}
