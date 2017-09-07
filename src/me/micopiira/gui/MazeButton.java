package me.micopiira.gui;

import me.micopiira.MazePoint;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MazeButton extends JButton {
	private MazePoint mazePoint;
	private static final Map<MazePoint, Color> colorMap = createColorMap();

	private static Map<MazePoint,Color> createColorMap() {
		final Map<MazePoint, Color> colorMap = new HashMap<>();
		colorMap.put(MazePoint.GOAL, Color.GREEN);
		colorMap.put(MazePoint.START, Color.RED);
		colorMap.put(MazePoint.WALL, Color.BLACK);
		colorMap.put(MazePoint.EMPTY, Color.WHITE);
		return colorMap;
	}

	public MazeButton(MazePoint mazePoint) {
		this.mazePoint = mazePoint;
		this.reDraw();

	}

	public void setMazePoint(MazePoint mazePoint) {
		this.mazePoint = mazePoint;
	}

	void reDraw() {
		setBackground(colorMap.get(mazePoint));
	}
}