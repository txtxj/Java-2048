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
	public Int2 blockInnerSize;
	public Int2 padding;
	public Int2 rankSize;
	public String titleText;
	public int animationTotalTime;
	public int animationSlides;
	public int animationPeriod;
	public Color leaderboardBorderColor;
	public Color leaderboardBackgroundColor;
	public Color frameBackgroundColor;
	public Color blockBorderColor;
	public Color boardBackgroundColor;
	public FontStyle leaderboardScoreStyle;

	public static class FontStyle {
		public Color textColor;
		public Color backgroundColor;
		public Font textFont;

		public FontStyle(Integer textColor, Integer backgroundColor, Integer size) {
			this.textColor = new Color(textColor);
			this.backgroundColor = backgroundColor == null ? null : new Color(backgroundColor);
			this.textFont = new Font("sans", Font.BOLD, size);
		}
	}

	public HashMap<Integer, FontStyle> palette;

	public Settings() {
		Initiate();
	}

	// Modify here
	private void Initiate() {
		// Block and windows setting
		this.blockSize = new Int2(125, 125);
		this.blockInnerSize = new Int2(120, 120);
		this.padding = new Int2(10, 10);
		this.rankSize = new Int2(300, 0);
		this.titleText = "2048 游戏";

		// Animation setting
		this.animationTotalTime = 100;
		this.animationSlides = 100;
		this.animationPeriod = 1;

		//Leaderboard setting
		this.leaderboardScoreStyle = new FontStyle(0x595857, null, 24);

		this.leaderboardBorderColor = new Color(0x000b00);
		this.leaderboardBackgroundColor = new Color(0xfef4f4);
		this.frameBackgroundColor = new Color(0x949495);
		this.blockBorderColor = this.frameBackgroundColor;
		this.boardBackgroundColor = new Color(0xeeeeee);

		this.windowSize = this.blockSize.mul(4).add(this.padding.mul(2)).add(this.rankSize);
		InitiatePalette();
	}

	private void InitiatePalette() {
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
