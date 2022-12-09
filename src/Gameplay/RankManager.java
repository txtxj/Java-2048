package Gameplay;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RankManager {
	public static class RankItem implements Comparable<RankItem> {
		public int score;
		private final int loginTime;
		public String name;

		protected RankItem(int score, int loginTime, String name) {
			this.score = score;
			this.loginTime = loginTime;
			this.name = name;
		}

		@Override
		public int compareTo(RankItem o) {
			if (this.score == o.score) {
				return this.loginTime - o.loginTime;
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

	protected RankManager() {
		currentScore = 0;
		rankList = new ArrayList<>();
		loadRanking();
	}

	protected void resetGame() {
		saveCurrentRanking();
		currentScore = 0;
		updateScore();
	}

	protected void earnScore(int score) {
		currentScore += score;
		updateScore();
	}

	private void updateScore() {
		GameManager.getInstance().gameFrame.getRanking().setCurrentScore(currentScore);
	}

	public void login(String name) {
		rankList.add(new RankItem(
				currentScore + GameManager.getInstance().boardManager.calBonus(),
				rankList.size(),
				name
		));
		Collections.sort(rankList);
	}

	private void saveCurrentRanking() {
		String path = "." + File.separator + ".sav";
		File file = new File(path);
		try {
			if (file.createNewFile()) {
				System.out.println("Create .sav file.");
			} else {
				System.out.println(".sav file already exists.");
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		try (FileWriter writer = new FileWriter(file)) {
			writer.write("" + rankList.size());
			for (RankItem item : rankList) {
				writer.write("\n");
				writer.write(item.name);
				writer.write("\n");
				writer.write("" + item.loginTime);
				writer.write("\n");
				writer.write("" + item.score);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void loadRanking() {
		String path = "." + File.separator + ".sav";
		File file = new File(path);
		if (!file.exists()) {
			return;
		}
		try (FileReader fReader = new FileReader(file)) {
			BufferedReader bRead = new BufferedReader(fReader);
			int counter = Integer.parseInt(bRead.readLine());
			String name, loginTime, score;
			for (int i = 0; i < counter; i++) {
				name = bRead.readLine();
				loginTime = bRead.readLine();
				score = bRead.readLine();
				rankList.add(new RankItem(
						Integer.parseInt(score),
						Integer.parseInt(loginTime),
						name.strip()
				));
			}
			Collections.sort(rankList);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
