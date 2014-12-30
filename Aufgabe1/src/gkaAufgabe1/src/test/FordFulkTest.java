package test;

import static org.junit.Assert.*;

import org.jgrapht.Graph;
import org.jgrapht.graph.DirectedWeightedPseudograph;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Gui.Dijkstra;
import Gui.FordFulk;
import Gui.FordFulkerson;
import Gui.WeightedEdge;

public class FordFulkTest {
	
	Graph<String, WeightedEdge> graph2 = new DirectedWeightedPseudograph<>(
			WeightedEdge.class);
	
	
	private Dijkstra d = new Dijkstra();
	private FordFulk f = new FordFulk();


	@Before
	public void createGraph() {
		
		graph2.addVertex("q");
		graph2.addVertex("v1");
		graph2.addVertex("v2");
		graph2.addVertex("v3");
		graph2.addVertex("v5");
		graph2.addVertex("s");

		addEdge("q", "v1", 5d);
		addEdge("q", "v2", 4d);
		addEdge("q", "v5", 1d);
		addEdge("v1", "v3", 1d);
		addEdge("v1", "v5", 1d);
		addEdge("v1", "s", 3d);
		addEdge("v5", "s", 3d);
		addEdge("v2", "v3", 2d);
		addEdge("v3", "s", 3d);
		
		

	}

	@Test
	public void testFordFulkerson() {
		// Test noch richtig machen
		Assert.assertEquals(f.fordFulkerson(graph2, "q", "s"), 4, 0.0);
	}
	
	private void addEdge(String s, String t, Double w) {
		WeightedEdge e = new WeightedEdge();
		e.setWeight(w);
		graph2.addEdge(s, t, e);
	}

}
