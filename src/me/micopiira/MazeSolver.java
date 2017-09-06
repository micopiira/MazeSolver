package me.micopiira;

import java.util.List;
import java.util.Optional;

@FunctionalInterface
public interface MazeSolver {
	/**
	 * @return A path from {@link MazePoint#START} to {@link MazePoint#GOAL} in the {@code maze},
	 * {@link Optional#empty()} if there is no possible path
	 */
	Optional<List<Coordinate>> solve(Maze<MazePoint> maze);
}
