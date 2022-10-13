package Gameplay;

import java.util.ArrayList;
import java.util.List;

public class RankManager {
	public static class RankItem implements Comparable<RankItem> {
		public int score;
		public String name;

		@Override
		public int compareTo(RankItem o) {
			return this.score - o.score;
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
	}

	public void earnScore(int score) {
		currentScore += score;
	}

}
