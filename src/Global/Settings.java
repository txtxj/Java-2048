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
	public Int2 padding;
	public String titleText;
	public int animationTotalTime;
	public int animationSlides;
	public int animationPeriod;

	public static class TileStyle {
		public Color textColor;
		public Color backgroundColor;
		public Font textFont;

		public TileStyle(int textColor, int backgroundColor, int size) {
			this.textColor = new Color(textColor);
			this.backgroundColor = new Color(backgroundColor);
			this.textFont = new Font("sans", Font.BOLD, size);
		}
	}

	public HashMap<Integer, TileStyle> palette;

	public Settings() {
		Initiate();
	}

	// Modify here
	private void Initiate() {
		blockSize = new Int2(125, 125);
		padding = new Int2(30, 30);
		windowSize = blockSize.mul(4).add(padding.mul(2));
		titleText = "2048 游戏";
		animationTotalTime = 75;
		animationSlides = 50;
		animationPeriod = 1;

		InitiatePalette();
	}

	private void InitiatePalette() {
		palette = new HashMap<>();
		palette.put(2, new TileStyle(0x776e65, 0xeee4da, 54));
		palette.put(4, new TileStyle(0x776e65, 0xede0c8, 54));
		palette.put(8, new TileStyle(0xf9f6f2, 0xf2b179, 54));
		palette.put(16, new TileStyle(0xf9f6f2, 0xf59563, 45));
		palette.put(32, new TileStyle(0xf9f6f2, 0xf67c5f, 45));
		palette.put(64, new TileStyle(0xf9f6f2, 0xf65e3b, 45));
		palette.put(128, new TileStyle(0xf9f6f2, 0xedcf72, 45));
		palette.put(256, new TileStyle(0xf9f6f2, 0xedcc61, 45));
		palette.put(512, new TileStyle(0xf9f6f2, 0xedc850, 42));
		palette.put(1024, new TileStyle(0xf9f6f2, 0xedc53f, 38));
		palette.put(2048, new TileStyle(0xf9f6f2, 0xedc22e, 36));
		palette.put(4096, new TileStyle(0xf9f6f2, 0x3c3a32, 36));
	}
}
