package me.micopiira.mazesolver.gui;

import javax.swing.*;

class ControlsPanel extends JPanel {

	final JButton solveButton = new JButton("Solve");

	ControlsPanel() {
		JTextField gridSize = new JTextField();
		JButton setGridSizeButton = new JButton("Set");
		this.add(gridSize);
		this.add(solveButton);
		this.add(setGridSizeButton);
	}

}
