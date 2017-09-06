package me.micopiira;

public enum MazePoint {
	START("◌"),
	GOAL("☻"),
	EMPTY("░"),
	WALL("█");

	private final String s;

	MazePoint(String s) {
		this.s = s;
	}

	public String getS() {
		return s;
	}
}
