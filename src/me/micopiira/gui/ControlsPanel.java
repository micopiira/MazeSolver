package me.micopiira.gui;

import javax.swing.*;

public class ControlsPanel extends JPanel {

	public final JButton solveButton;

	public ControlsPanel() {
		solveButton = new JButton("Solve");
		JTextField gridSize = new JTextField();
		JButton setGridSizeButton = new JButton("Set");

		this.add(gridSize);
		this.add(solveButton);
		this.add(setGridSizeButton);
	}

}
