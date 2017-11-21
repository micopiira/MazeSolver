package me.micopiira.mazesolver.gui;

import javax.swing.*;
import java.awt.*;

class ControlsPanel extends JPanel {

	final JButton solveButton = new JButton("Solve");
	final JButton setGridSizeButton = new JButton("Reload");
	final JSpinner gridSize = new JSpinner(new SpinnerNumberModel(15, 10, 50, 1));
	final Vector2Field startCoords = new Vector2Field("1,1");
	final Vector2Field targetCoords = new Vector2Field("13,13");
	final JButton randomizeBtn = new JButton("Randomize");

	ControlsPanel() {
		gridSize.setPreferredSize(new Dimension(50, 24));
		startCoords.setPreferredSize(new Dimension(50, 24));
		targetCoords.setPreferredSize(new Dimension(50, 24));
		add(new JLabel("Start"));
		add(startCoords);
		add(new JLabel("Target"));
		add(targetCoords);
		add(new JLabel("Size"));
		add(gridSize);
		add(setGridSizeButton);
		add(solveButton);
		add(randomizeBtn);
	}

}
