package Gui;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import org.jgraph.graph.DefaultEdge;
import org.jgrapht.Graph;
import org.jgrapht.ListenableGraph;

public class BreadthFirstSearchAlgorithmus {
	private Controller controller;

	public int breadthFirstSearch(Graph<String, WeightedEdge> graph,
			String start, String end) {
		int result = 0;
		start = controller.getStartVertex();
		end = controller.getEndVertex();
		Queue<String> queue = new LinkedList<String>();

		if (start.equals(end)) {
			return result;
		}

		return 1;
	}

}
