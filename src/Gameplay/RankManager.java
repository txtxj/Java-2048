package Gameplay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RankManager {
	public static class RankItem implements Comparable<RankItem> {
		public int score;
		public long loginTime;
		public String name;

		public RankItem(int score, long loginTime, String name) {
			this.score = score;
			this.loginTime = loginTime;
			this.name = name;
		}

		@Override
		public int compareTo(RankItem o) {
			if (this.score == o.score) {
				return (int)(this.loginTime - o.loginTime);
			}
			return o.score - this.score;
		}

		@Override
		public String toString() {
			return "" + this.name + "     " + this.score;
		}
	}

	public List<RankItem> rankList;

	public int currentScore;

	public RankManager() {
		currentScore = 0;
		rankList = new ArrayList<>();
		// test
		rankList.add(new RankItem(666666, 5, "txtxj"));
		rankList.add(new RankItem(12345, 6, "cnmd"));
		rankList.add(new RankItem(50, 9, "一库一库"));
		rankList.add(new RankItem(41563, 1, "id4"));
		rankList.add(new RankItem(99999, 2, "id5"));
		Collections.sort(rankList);
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
