package me.micopiira.gui;

import me.micopiira.Coordinate;
import me.micopiira.Maze;
import me.micopiira.MazePoint;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Test {
	private JPanel jPanel;
	private Maze<MazePoint> maze = new Maze<>(new HashMap<>());

	private void initMaze(int cols, int rows) {
		for (int y = 0; y < cols; y++) {
			for (int x = 0; x < rows; x++) {
				maze.set(Coordinate.of(x, y), MazePoint.EMPTY);
			}
		}
	}

	public Test() {
		jPanel = new JPanel();
		jPanel.setBackground(Color.BLACK);

		int size = 10;

		jPanel.setLayout(new GridLayout(size, size));
		initMaze(size, size);
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				Coordinate coordinate = Coordinate.of(x, y);
				MazeButton button = maze.get(coordinate).map(MazeButton::new).orElseThrow(() -> new RuntimeException("No MazePoint found at: " + coordinate));
				button.addActionListener(e -> {
					MazePoint mp = maze.get(coordinate).get();
					boolean isWall = mp.equals(MazePoint.WALL);
					maze.set(coordinate,  isWall ? MazePoint.EMPTY : MazePoint.WALL);
					maze.get(coordinate).ifPresent(button::setMazePoint);
					button.reDraw();
				});
				jPanel.add(button);
			}
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Test");
		frame.setContentPane(new Test().jPanel);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(800, 800);
		//  frame.pack();
		frame.setVisible(true);
	}
}
