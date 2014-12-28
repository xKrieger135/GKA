package test;

import static org.junit.Assert.*;

import org.jgrapht.Graph;
import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.DirectedWeightedPseudograph;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Gui.CreateBig;
import Gui.Dijkstra;
import Gui.FordFulkerson;
import Gui.WeightedEdge;

public class FordFulkersonTest {

//	Graph<String, WeightedEdge> graph1 = new DirectedWeightedPseudograph<>(
//			WeightedEdge.class);
	Graph<String, WeightedEdge> graph2 = new DirectedWeightedPseudograph<>(
			WeightedEdge.class);
	
	
	private Dijkstra d = new Dijkstra();
	private FordFulkerson f = new FordFulkerson();


	@Before
	public void createGraph() {
		
		graph2.addVertex("q");
		graph2.addVertex("u");
		graph2.addVertex("v");
		graph2.addVertex("s");

		addEdge("q", "u", 4d);
		addEdge("q", "v", 2d);
		addEdge("u", "v", 3d);
		addEdge("u", "s", 1d);
		addEdge("v", "s", 6d);
		
		

	}

	@Test
	public void testFordFulkerson() {
		Assert.assertEquals(f.fordFulkersonAlgorithmus(graph2, "q", "s"), 4, 0.0);
	}
	
	private void addEdge(String s, String t, Double w) {
		WeightedEdge e = new WeightedEdge();
		e.setWeight(w);
		graph2.addEdge(s, t, e);
	}

}
