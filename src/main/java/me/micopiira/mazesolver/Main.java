package me.micopiira.mazesolver;

import me.micopiira.mazesolver.gui.Gui;
import me.micopiira.mazesolver.maze.AStarMazeSolver;

import javax.swing.*;

public class Main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new Gui(new AStarMazeSolver()).createAndShowGUI());
		Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.toString(), e.getMessage(), JOptionPane.ERROR_MESSAGE);
		});
	}

}
