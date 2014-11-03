package Gui;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.jgrapht.Graph;

public class BreadthFirstSearchAlgorithmus {
	private Controller controller;

	public static int breadthFirstSearch(Graph<String, WeightedEdge> graph,
			String start, String end) {
		int result = 0;
		Queue<String> queue = new LinkedList<String>();
		Set<String> set = new HashSet<String>();
		set.add(start);
		queue.add(start);
		boolean visited = false;

		while (!queue.isEmpty()) {
			String t = queue.poll();
			visited = true;

			if (t == end) {
				return result;
			}

			for (WeightedEdge edge : graph.edgesOf(t)) {
				

			}
		}

		return 0;
	}

}
