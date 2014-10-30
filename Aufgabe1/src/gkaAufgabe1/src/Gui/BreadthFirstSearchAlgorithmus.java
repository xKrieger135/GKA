package Gui;

import java.util.HashMap;
import java.util.Map;

import org.jgraph.graph.DefaultEdge;
import org.jgrapht.Graph;
import org.jgrapht.ListenableGraph;

public class BreadthFirstSearchAlgorithmus {
	private graphGui graphGui;
	private Controller controller;
	
	public int superSearch(ListenableGraph<String, GewichteteKante> graph, String start, String end) {
	Map<String, GewichteteKante> distance = new HashMap<String, GewichteteKante>();
	boolean reached = false;
	int shortestWay = 0;
	String actualNode;
	
	if(start == end) {
		return shortestWay;
	}
	
	while(reached == false && actualNode != end) {
		
	}
		
		HashMap<String, Integer> values = new HashMap<String, Integer>();
		values.put(start, 0);
		values.put(end, 3);
		values.put("V2", 2);
		values.put("V4", 4);
		if(graph.containsVertex(start) && graph.containsVertex(end)) {
			
		}
	}
	
}
