package me.micopiira.maze;

import me.micopiira.math.Matrix;
import me.micopiira.math.Vector2;

import java.util.List;
import java.util.Optional;

@FunctionalInterface
public interface MazeSolver {
	/**
	 * @return A path from {@link MazePoint#START} to {@link MazePoint#GOAL} in the {@code matrix},
	 * {@link Optional#empty()} if there is no possible path
	 */
	Optional<List<Vector2>> solve(Matrix<MazePoint> matrix);
}
