package me.micopiira.mazesolver.gui;

import me.micopiira.mazesolver.math.Vector2;

import javax.swing.*;

public class Vector2Field extends JTextField {

	public Vector2Field(String text) {
		super(text);
	}

	public Vector2 getVector2() {
		return Vector2.parse(getText());
	}
}
