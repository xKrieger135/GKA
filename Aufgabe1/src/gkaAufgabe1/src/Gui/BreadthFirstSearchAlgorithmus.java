package Gui;

import java.util.HashMap;

import org.jgraph.graph.DefaultEdge;
import org.jgrapht.Graph;
import org.jgrapht.ListenableGraph;

public class BreadthFirstSearchAlgorithmus {
	private graphGui graphGui;
	private Controller controller;
	
//	private ListenableGraph<String, DefaultEdge> myGraph = graphGui.saveCreatedGraph();
	
	public int superSearch(ListenableGraph<String, DefaultEdge> graph, String start, String end) {
		start = "V1";
		end = "V3";
		int nachbarCounter = 0;
		
		
		HashMap<String, Integer> values = new HashMap<String, Integer>();
		values.put(start, 0);
		values.put(end, 3);
		values.put("V2", 2);
		values.put("V4", 4);
		if(graph.containsVertex(start) && graph.containsVertex(end)) {
			
		}
	}
	
}
