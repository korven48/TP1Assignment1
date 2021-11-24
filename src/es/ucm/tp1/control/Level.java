package es.ucm.tp1.control;

public enum Level {

	TEST(10, 3, 8, 0.5, 0 , 0.0), EASY(30, 3, 8, 0.5, 0.5, 0.0), HARD(100, 5, 6, 0.7, 0.3, 0.0), ADVANCED(100, 3, 8, 0.3, 0.3, 0.1);

	private int length;

	private int width;

	private int visibility;

	private double coinFrequency;

	private double obstacleFrequency;
	
	private double advObjFreq;

	private Level(int length, int width, int visibility, double obstacleFrequency, double coinFrequency, double advObjFreq) {
		this.length = length;
		this.width = width;
		this.visibility = visibility;
		this.obstacleFrequency = obstacleFrequency;
		this.coinFrequency = coinFrequency;
		this.advObjFreq = advObjFreq;
	}
	
	public static final Level parse(final String levelName) {
		Level level = null;
		switch (levelName) {
			case "Test":
				level = Level.TEST;
				break;
			case "EASY":
				level = Level.EASY;
				break;
			case "HARD":
				level = Level.HARD;
				break; 
			}	
		return level;
	}

	// TODO fill your code
	public int getLength() {
		return length;
	}

	public int getWidth() {
		return width;
	}

	public int getVisibility() {
		return visibility;
	}
	
	public double getadvObjFreq() {
		return advObjFreq;
	}

	public double coinFrequency() {
		return coinFrequency;
	}

	public double obstacleFrequency() {
		return obstacleFrequency;
	}
	
	public double advancedObjectsFrequency() {
		return this.advObjFreq;
	}

	public static Level valueOfIgnoreCase(String inputString) {
		for (Level level : Level.values()) {
			if (level.name().equalsIgnoreCase(inputString)) {
				return level;
			}
		}
		return null;
	}

	public static String all(String separator) {
		StringBuilder buffer = new StringBuilder();
		int levelCount = 0;
		for (Level level : Level.values()) {
			if (levelCount > 0) {
				buffer.append(separator);
			}
			buffer.append(level.name());
			levelCount++;
		}
		return buffer.toString();
	}
	
	public boolean hasAdvancedObjects() {
		boolean result = false;
		if (this.advancedObjectsFrequency() > 0) {
			result = true;
		}
		return result;
	}
}
