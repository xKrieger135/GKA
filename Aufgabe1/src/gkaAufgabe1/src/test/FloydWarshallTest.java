package test;

import static org.junit.Assert.*;

import org.jgrapht.Graph;
import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.DirectedPseudograph;
import org.junit.Before;
import org.junit.Test;

import Gui.CreateBig;
import Gui.Dijkstra;
import Gui.FloydWarshall;
import Gui.WeightedEdge;

public class FloydWarshallTest {

	Graph<String, WeightedEdge> graph1 = new DirectedPseudograph<>(
			WeightedEdge.class);
	Graph<String, WeightedEdge> graph2 = new DirectedPseudograph<>(
			WeightedEdge.class);
	Graph<String, WeightedEdge> graph3;
	Graph<String, WeightedEdge> graph4 = new DirectedPseudograph<>(
			WeightedEdge.class);
	Graph<String, WeightedEdge> graph5 = new DirectedPseudograph<>(
			WeightedEdge.class);
	
	
	
	private Dijkstra d = new Dijkstra();
	private CreateBig createbig = new CreateBig();


	@Before
	public void createGraph() {
		
		graph3 = createbig.createBig();

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
		((AbstractBaseGraph<String, WeightedEdge>) graph2).setEdgeWeight(e12,
				3d);

		WeightedEdge e13 = graph2.addEdge("1", "3");
		((AbstractBaseGraph<String, WeightedEdge>) graph2).setEdgeWeight(e13,
				1d);

		WeightedEdge e23 = graph2.addEdge("3", "2");
		((AbstractBaseGraph<String, WeightedEdge>) graph2).setEdgeWeight(e23,
				1d);

		WeightedEdge e24 = graph2.addEdge("2", "4");
		((AbstractBaseGraph<String, WeightedEdge>) graph2).setEdgeWeight(e24,
				4d);

		WeightedEdge e25 = graph2.addEdge("2", "5");
		((AbstractBaseGraph<String, WeightedEdge>) graph2).setEdgeWeight(e25,
				3d);

		WeightedEdge e45 = graph2.addEdge("4", "5");
		((AbstractBaseGraph<String, WeightedEdge>) graph2).setEdgeWeight(e45,
				1d);

		WeightedEdge e46 = graph2.addEdge("4", "6");
		((AbstractBaseGraph<String, WeightedEdge>) graph2).setEdgeWeight(e46,
				5d);

		WeightedEdge e56 = graph2.addEdge("5", "6");
		((AbstractBaseGraph<String, WeightedEdge>) graph2).setEdgeWeight(e56,
				2d);
		
		
		graph4.addVertex("a");
		graph4.addVertex("b");
		graph4.addVertex("c");
		graph4.addVertex("d");
		

		WeightedEdge xac = graph4.addEdge("a", "c");
		((AbstractBaseGraph<String, WeightedEdge>) graph4).setEdgeWeight(xac,
				1d);

		WeightedEdge xca = graph4.addEdge("c", "a");
		((AbstractBaseGraph<String, WeightedEdge>) graph4).setEdgeWeight(xca,
				1d);

		WeightedEdge xab = graph4.addEdge("a", "b");
		((AbstractBaseGraph<String, WeightedEdge>) graph4).setEdgeWeight(xab,
				3d);

		WeightedEdge xbd = graph4.addEdge("b", "d");
		((AbstractBaseGraph<String, WeightedEdge>) graph4).setEdgeWeight(xbd,
				7d);
		
		WeightedEdge xcd = graph4.addEdge("c", "d");
		((AbstractBaseGraph<String, WeightedEdge>) graph4).setEdgeWeight(xcd,
				50d);
		
		addEdge("c", "b", 3d);
		
		graph4.addVertex("f");
		addEdge("c", "f", 5d);
		
		graph4.addVertex("g");
		addEdge("f", "g", 2d);
		addEdge("g", "c", 4d);
		addEdge("a", "g", 2.5d);
		addEdge("d", "c", 0.5d);
		
		graph5.addVertex("a");
		graph5.addVertex("b");
		graph5.addVertex("c");
		graph5.addVertex("d");
		graph5.addVertex("f");
		graph5.addVertex("g");
		
		undirectedaddEdge("a", "b", 5d);
		undirectedaddEdge("a", "c", 1d);
		undirectedaddEdge("b", "d", 7d);
		undirectedaddEdge("c", "b", 3d);
		undirectedaddEdge("a", "g", 2.5d);
		undirectedaddEdge("c", "f", 5d);
		undirectedaddEdge("c", "g", 4d);
		undirectedaddEdge("f", "g", 2d);
	}
	
	private void addEdge(String source, String target, double weight) {
		WeightedEdge edge = graph4.addEdge(source, target);
		((AbstractBaseGraph<String, WeightedEdge>) graph4).setEdgeWeight(edge,
				weight);
	}
	
	private void undirectedaddEdge(String source, String target, double weight) {
		WeightedEdge edge = graph5.addEdge(source, target);
		((AbstractBaseGraph<String, WeightedEdge>) graph5).setEdgeWeight(edge,
				weight);
		WeightedEdge edge2 = graph5.addEdge(target, source);
		((AbstractBaseGraph<String, WeightedEdge>) graph5).setEdgeWeight(edge2,
				weight);
	}

	@Test
	public void test() {
		FloydWarshall fw = new FloydWarshall();
		double expected10 = fw.floydWarshallAlgorithmus(graph5, "b", "g");
		//assertEquals(10, expected10);
		System.out.println(expected10);
		assertEquals(6.5, fw.floydWarshallAlgorithmus(graph5, "b", "g"), 0.0);
		assertEquals(11.0, fw.floydWarshallAlgorithmus(graph5, "a", "d"), 0.0);
		assertEquals(2.5, fw.floydWarshallAlgorithmus(graph5, "a", "g"), 0.0);
		assertEquals(7.0, fw.floydWarshallAlgorithmus(graph5, "b", "d"), 0.0);
		assertEquals(4.0, fw.floydWarshallAlgorithmus(graph5, "a", "b"), 0.0);
	}

}
