package me.micopiira.mazesolver.gui;

import javax.swing.*;
import java.awt.*;

class ControlsPanel extends JPanel {

	final JButton solveButton = new JButton("Solve");
	final JButton setGridSizeButton = new JButton("Reload");
	final JTextField gridSize = new JTextField("15");
	final JTextField startCoords = new JTextField("0,0");
	final JTextField targetCoords = new JTextField("5,5");

	ControlsPanel() {
		gridSize.setPreferredSize(new Dimension(50, 24));
		startCoords.setPreferredSize(new Dimension(50, 24));
		targetCoords.setPreferredSize(new Dimension(50, 24));
		add(startCoords);
		add(targetCoords);
		add(gridSize);
		add(setGridSizeButton);
		add(solveButton);
	}

}
