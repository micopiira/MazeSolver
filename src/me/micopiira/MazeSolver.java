package me.micopiira;

import java.util.List;
import java.util.Optional;

@FunctionalInterface
public interface MazeSolver {
	/**
	 * @return A path from {@link MazePoint#START} to {@link MazePoint#GOAL} in the {@code matrix},
	 * {@link Optional#empty()} if there is no possible path
	 */
	Optional<List<Coordinate>> solve(Matrix<MazePoint> matrix);
}
