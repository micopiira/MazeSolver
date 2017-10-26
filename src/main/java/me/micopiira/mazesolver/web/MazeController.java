package me.micopiira.mazesolver.web;

import me.micopiira.mazesolver.math.Matrix;
import me.micopiira.mazesolver.math.Vector2;
import me.micopiira.mazesolver.maze.MazePoint;
import me.micopiira.mazesolver.maze.MazeSolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;

@Controller
@SessionAttributes({"maze", "size"})
public class MazeController {

	private final MazeSolver mazeSolver;

	@Autowired
	public MazeController(MazeSolver mazeSolver) {
		this.mazeSolver = mazeSolver;
	}

	@PostMapping("/setsize")
	public String setSize(@RequestParam("size") int size, Model model) {
		model.addAttribute("size", size);
		model.addAttribute("maze", getMaze(size));
		return "redirect:/";
	}

	@RequestMapping("/solve")
	public String solve(@ModelAttribute("maze") Matrix<MazePoint> maze, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("solvedPath", mazeSolver.solve(maze).orElseThrow(() -> new RuntimeException("No path found!")));
		return "redirect:/";
	}

	@RequestMapping("/toggle")
	public String toggle(@ModelAttribute("maze") Matrix<MazePoint> maze,
	                     @RequestParam("x") int x,
	                     @RequestParam("y") int y) {
		Vector2 pos = Vector2.of(x, y);
		MazePoint mazePoint = maze.get(pos)
				.filter(Arrays.asList(MazePoint.WALL, MazePoint.EMPTY)::contains)
				.orElseThrow(() -> new IllegalArgumentException("Cannot toggle this maze point"));
		maze.set(pos, mazePoint.equals(MazePoint.WALL) ? MazePoint.EMPTY : MazePoint.WALL);
		return "redirect:/";
	}

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@ModelAttribute("size")
	public int getSize() {
		return 5;
	}

	@ModelAttribute("maze")
	public Matrix<MazePoint> getMaze(@ModelAttribute("size") int size) {
		Matrix<MazePoint> m = new Matrix<>(MazePoint.EMPTY, size);
		m.set(Vector2.of(0, 0), MazePoint.START);
		m.set(Vector2.of(size - 1, size - 1), MazePoint.GOAL);
		return m;
	}

}
