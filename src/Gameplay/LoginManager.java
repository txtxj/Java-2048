package Gameplay;

import Gameplay.RankManager.*;

import java.util.Date;

public class LoginManager {
	public LoginManager() {

	}

	public RankItem login(String name) {
		return new RankItem(
				GameManager.getInstance().rankManager.currentScore,
				new Date().getTime(),
				name
		);
	}
}
