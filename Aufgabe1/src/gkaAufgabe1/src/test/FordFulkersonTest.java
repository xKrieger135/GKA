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
		
//		graph3 = createbig.createBig();
//
//		graph1.addVertex("A");
//		graph1.addVertex("B");
//		graph1.addVertex("C");
//		graph1.addVertex("D");
//
//
//		graph1.addEdge("A", "B");
//		graph1.addEdge("A", "C");
//		graph1.addEdge("B", "E");
//		graph1.addEdge("B", "F");
//		graph1.addEdge("C", "F");
//		graph1.addEdge("E", "F");
//		graph1.addEdge("E", "G");
//		graph1.addEdge("F", "A");
//		graph1.addEdge("F", "B");
//		graph1.addEdge("F", "G");
//		graph1.addEdge("G", "I");
//		graph1.addEdge("I", "H");
//		graph1.addEdge("H", "I");
//		graph1.addEdge("H", "J");
//		graph1.addEdge("J", "D");
//		graph1.addEdge("J", "E");

		graph2.addVertex("q");
		graph2.addVertex("u");
		graph2.addVertex("v");
		graph2.addVertex("s");

		addEdge("q", "u", 5d);
		addEdge("q", "v", 2d);
		addEdge("u", "v", 3d);
		addEdge("u", "s", 2d);
		addEdge("v", "s", 6d);
		
		

	}

	@Test
	public void testFordFulkerson() {
//		Assert.assertArrayEquals(f.fordFulkersonAlgorithmus(graph2, "q", "s"), 2);
		System.out.println(f.fordFulkersonAlgorithmus(graph2, "q", "s"));
	}
	
	private void addEdge(String s, String t, Double w) {
		WeightedEdge e = new WeightedEdge();
		e.setWeight(w);
		graph2.addEdge(s, t, e);
	}

}
