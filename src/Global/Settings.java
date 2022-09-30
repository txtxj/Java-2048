package Global;

import java.awt.*;
import java.util.HashMap;

public class Settings
{
	private static Settings instance;

	public static Settings getInstance() {
		if (instance == null) {
			instance = new Settings();
		}
		return instance;
	}

	public Dimension windowSize;
	public Dimension blockSize;
	public Dimension titleSize;
	public String titleText;

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
		blockSize = new Dimension(125, 125);
		titleSize = new Dimension(500, 10);
		windowSize = new Dimension(500, 510);
		titleText = "2048";

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
		palette.put(512, new TileStyle(0xf9f6f2, 0xedc850, 45));
		palette.put(1024, new TileStyle(0xf9f6f2, 0xedc53f, 35));
		palette.put(2048, new TileStyle(0xf9f6f2, 0xedc22e, 35));
		palette.put(4096, new TileStyle(0xf9f6f2, 0x3c3a32, 35));
	}
}
