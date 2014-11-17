package test;

import org.jgrapht.Graph;
import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.DirectedPseudograph;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Gui.Dijkstra;
import Gui.WeightedEdge;

public class TestDijkstra {
	
	 

	Graph<String, WeightedEdge> graph1 = new DirectedPseudograph<>(WeightedEdge.class);
	Graph<String, WeightedEdge> graph2 = new DirectedPseudograph<>(WeightedEdge.class);
	private Dijkstra d = new Dijkstra();
	
	@Before
	public void createGraph() {
		graph1.addVertex("A");
		graph1.addVertex("B");
		graph1.addVertex("C");
		graph1.addVertex("D");
		graph1.addVertex("E");
		graph1.addVertex("F");
		graph1.addVertex("G");
		graph1.addVertex("H");
		graph1.addVertex("I");
		graph1.addVertex("J");
		
		graph1.addEdge("A", "B");
		graph1.addEdge("A", "C");
		graph1.addEdge("B", "E");
		graph1.addEdge("B", "F");
		graph1.addEdge("C", "F");
		graph1.addEdge("E", "F");
		graph1.addEdge("E", "G");
		graph1.addEdge("F", "A");
		graph1.addEdge("F", "B");
		graph1.addEdge("F", "G");
		graph1.addEdge("G", "I");
		graph1.addEdge("I", "H");
		graph1.addEdge("H", "I");
		graph1.addEdge("H", "J");
		graph1.addEdge("J", "D");
		graph1.addEdge("J", "E");
		
		
		graph2.addVertex("1");
		graph2.addVertex("2");
		graph2.addVertex("3");
		graph2.addVertex("4");
		graph2.addVertex("5");
		graph2.addVertex("6");
		
		WeightedEdge e12 = graph2.addEdge("1", "2");
		((AbstractBaseGraph<String, WeightedEdge>) graph2)
		.setEdgeWeight(e12, 3d);
		
		WeightedEdge e13 = graph2.addEdge("1", "3");
		((AbstractBaseGraph<String, WeightedEdge>) graph2)
		.setEdgeWeight(e13, 1d);
		
		WeightedEdge e23 = graph2.addEdge("2", "3");
		((AbstractBaseGraph<String, WeightedEdge>) graph2)
		.setEdgeWeight(e23, 1d);
		
		WeightedEdge e24 = graph2.addEdge("2", "4");
		((AbstractBaseGraph<String, WeightedEdge>) graph2)
		.setEdgeWeight(e24, 4d);
		
		WeightedEdge e25 = graph2.addEdge("2", "5");
		((AbstractBaseGraph<String, WeightedEdge>) graph2)
		.setEdgeWeight(e25, 3d);
		
		WeightedEdge e45 = graph2.addEdge("4", "5");
		((AbstractBaseGraph<String, WeightedEdge>) graph2)
		.setEdgeWeight(e45, 1d);
		
		WeightedEdge e46 = graph2.addEdge("4", "6");
		((AbstractBaseGraph<String, WeightedEdge>) graph2)
		.setEdgeWeight(e46, 5d);
		
		WeightedEdge e56 = graph2.addEdge("5", "6");
		((AbstractBaseGraph<String, WeightedEdge>) graph2)
		.setEdgeWeight(e56, 2d);
	}

	@Test
	public void test() {
		Assert.assertEquals(2, d.returnDistance(graph2, "1", "2"), 0.0);
		Assert.assertEquals(5, d.returnDistance(graph2, "1", "5"), 0.0);
	}

}
