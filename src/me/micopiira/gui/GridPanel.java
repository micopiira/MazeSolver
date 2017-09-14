package me.micopiira.gui;

import me.micopiira.math.Matrix;
import me.micopiira.math.Vector2;
import me.micopiira.maze.MazePoint;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class GridPanel extends JPanel {

	private Matrix<MazeButton> buttons = new Matrix<>(new HashMap<>());

	public void redraw() {
		this.buttons.getElements().forEach((coordinate, mazeButton) -> {
			mazeButton.setMazePoint(Test.maze.get(coordinate).get());
			mazeButton.setOnPath(Test.solvedPath.contains(coordinate));
			mazeButton.reDraw();
		});
	}

	public GridPanel(int size) {

		setLayout(new GridLayout(size, size));

		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				Vector2 coordinate = Vector2.of(x, y);
				MazeButton mazeButton = Test.maze.get(coordinate).map(MazeButton::new).orElseThrow(() -> new RuntimeException("No MazePoint found at: " + coordinate));
				mazeButton.addActionListener(e -> {
					Test.solvedPath = new ArrayList<>();
					MazePoint mp = Test.maze.get(coordinate).get();
					boolean isWall = mp.equals(MazePoint.WALL);
					Test.maze.set(coordinate, isWall ? MazePoint.EMPTY : MazePoint.WALL);
					redraw();
				});
				buttons.set(coordinate, mazeButton);
				add(mazeButton);
			}
		}

	}
}
