package me.micopiira.mazesolver.gui;

import me.micopiira.mazesolver.maze.MazePoint;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

class MazeButton extends JButton {
	private static final Map<MazePoint, Color> colorMap = createColorMap();
	private MazePoint mazePoint;
	private boolean onPath;

	MazeButton(MazePoint mazePoint) {
		this.mazePoint = mazePoint;
		this.reDraw();
	}

	private static Map<MazePoint, Color> createColorMap() {
		final Map<MazePoint, Color> colorMap = new HashMap<>();
		colorMap.put(MazePoint.GOAL, Color.GREEN);
		colorMap.put(MazePoint.START, Color.RED);
		colorMap.put(MazePoint.WALL, Color.BLACK);
		colorMap.put(MazePoint.EMPTY, Color.WHITE);
		return colorMap;
	}

	void setMazePoint(MazePoint mazePoint) {
		this.mazePoint = mazePoint;
	}

	void reDraw() {
		setBackground(isOnPath() && !mazePoint.equals(MazePoint.GOAL) ? Color.CYAN : colorMap.get(mazePoint));
	}

	private boolean isOnPath() {
		return onPath;
	}

	void setOnPath(boolean onPath) {
		this.onPath = onPath;
	}
}