package Global;

import java.awt.*;
import java.util.HashMap;
import GUI.Int2;

public class Settings
{
	private static Settings instance;

	public static Settings getInstance() {
		if (instance == null) {
			instance = new Settings();
		}
		return instance;
	}

	public Int2 windowSize;
	public Int2 blockSize;
	public Int2 gridRectWidth;
	public Int2 padding;
	public Int2 rankSize;
	public Int2 mapSize;
	public String titleText;
	public int animationTotalTime;
	public int animationSlides;
	public int animationPeriod;
	public Color leaderboardBorderColor;
	public Color leaderboardBackgroundColor;
	public Color frameBackgroundColor;
	public Color blockBorderColor;
	public Color blockBorderColorAlpha;
	public Color boardBackgroundColor;
	public Color gameOverBackgroundColor;
	public FontStyle leaderboardScoreStyle;
	public FontStyle gameOverTextStyle;
	public FontStyle finalScoreStyle;
	public int rankShowNumber;

	public static class FontStyle {
		public Color textColor;
		public Color backgroundColor;
		public Font textFont;

		public FontStyle(Integer textColor, Integer backgroundColor, Integer size) {
			this.textColor = new Color(textColor);
			if (backgroundColor == null) {
				this.backgroundColor = null;
			} else if (backgroundColor <= 0xffffff) {
				this.backgroundColor = new Color(backgroundColor);
			} else {
				this.backgroundColor = new Color(backgroundColor, true);
			}
			this.textFont = new Font("sans", Font.BOLD, size);
		}
	}

	public HashMap<Integer, FontStyle> palette;

	public Settings() {
		initiate();
	}

	// Modify here
	private void initiate() {
		// Block and windows setting
		this.blockSize = new Int2(125, 125);
		this.gridRectWidth = new Int2(2, 2);
		this.padding = new Int2(10, 10);
		this.rankSize = new Int2(300, 0);
		this.mapSize = new Int2(4, 4);
		this.titleText = "2048 游戏";

		// Animation setting
		this.animationTotalTime = 160;
		this.animationSlides = 100;
		this.animationPeriod = 1;

		// Leaderboard setting
		this.leaderboardScoreStyle = new FontStyle(0x595857, null, 24);
		this.leaderboardBorderColor = new Color(0x000b00);
		this.leaderboardBackgroundColor = new Color(0xfef4f4);
		this.rankShowNumber = 5;

		this.frameBackgroundColor = new Color(0xd3cbc6);
		this.blockBorderColor = this.frameBackgroundColor;
		this.blockBorderColorAlpha = new Color(0xd3, 0xcb, 0xc6, 0x7f);
		this.boardBackgroundColor = new Color(0xeeeeee);


		this.gameOverBackgroundColor = new Color(0x7f, 0x7f, 0x7f, 0x7f);
		this.gameOverTextStyle = new FontStyle(0xebf6f7, 0x3fc8c2be, 40);
		this.finalScoreStyle = new FontStyle(0xebf6f7, 0x3fc8c2be, 32);

		this.windowSize = this.blockSize.mul(this.mapSize).add(this.padding.mul(2)).add(this.rankSize);
		initiatePalette();
	}

	private void initiatePalette() {
		palette = new HashMap<>();
		palette.put(2, new FontStyle(0x776e65, 0xeee4da, 54));
		palette.put(4, new FontStyle(0x776e65, 0xede0c8, 54));
		palette.put(8, new FontStyle(0xf9f6f2, 0xf2b179, 54));
		palette.put(16, new FontStyle(0xf9f6f2, 0xf59563, 45));
		palette.put(32, new FontStyle(0xf9f6f2, 0xf67c5f, 45));
		palette.put(64, new FontStyle(0xf9f6f2, 0xf65e3b, 45));
		palette.put(128, new FontStyle(0xf9f6f2, 0xedcf72, 45));
		palette.put(256, new FontStyle(0xf9f6f2, 0xedcc61, 45));
		palette.put(512, new FontStyle(0xf9f6f2, 0xedc850, 42));
		palette.put(1024, new FontStyle(0xf9f6f2, 0xedc53f, 38));
		palette.put(2048, new FontStyle(0xf9f6f2, 0xedc22e, 36));
		palette.put(4096, new FontStyle(0xf9f6f2, 0x3c3a32, 36));
	}
}
