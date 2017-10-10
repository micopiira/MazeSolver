package me.micopiira.mazesolver.gui;

import javax.swing.*;
import java.awt.*;

class ControlsPanel extends JPanel {

	final JButton solveButton = new JButton("Solve");
	final JButton setGridSizeButton = new JButton("Set grid size");
	final JTextField gridSize = new JTextField();

	ControlsPanel() {
		gridSize.setPreferredSize(new Dimension(200, 24));
		add(gridSize);
		add(setGridSizeButton);
		add(solveButton);
	}

}
