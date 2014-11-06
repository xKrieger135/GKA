package test;

import org.jgrapht.Graph;
import org.jgrapht.graph.DirectedPseudograph;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Gui.BreadthFirstSearchAlgorithmus;
import Gui.WeightedEdge;

public class BreadthFirstSearchAlgorithmusTest {
	Graph<String, WeightedEdge> graph = new DirectedPseudograph<>(WeightedEdge.class);
	private BreadthFirstSearchAlgorithmus bfs = new BreadthFirstSearchAlgorithmus();
	
	@Before
	public void createGraph() {
		
		
		graph.addVertex("A");
		graph.addVertex("B");
		graph.addVertex("C");
		graph.addVertex("D");
		graph.addVertex("E");
		graph.addVertex("F");
		graph.addVertex("G");
		graph.addVertex("H");
		graph.addVertex("I");
		graph.addVertex("J");
		
		graph.addEdge("A", "B");
		graph.addEdge("A", "C");
		graph.addEdge("B", "E");
		graph.addEdge("B", "F");
		graph.addEdge("C", "F");
		graph.addEdge("E", "F");
		graph.addEdge("E", "G");
		graph.addEdge("F", "A");
		graph.addEdge("F", "B");
		graph.addEdge("F", "G");
		graph.addEdge("G", "I");
		graph.addEdge("I", "H");
		graph.addEdge("H", "I");
		graph.addEdge("H", "J");
		graph.addEdge("J", "D");
		graph.addEdge("J", "E");
	}
	
	@Test
	public void testShortestWay() {
		Assert.assertEquals(bfs.breadthFirstSearch(graph, "A", "B"), 1);
		Assert.assertEquals(bfs.breadthFirstSearch(graph, "A", "D"), 7);
		Assert.assertEquals(bfs.breadthFirstSearch(graph, "A", "E"), 2);
	}
	

}
