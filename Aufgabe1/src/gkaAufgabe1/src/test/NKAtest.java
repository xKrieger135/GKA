package test;

import org.jgrapht.Graph;
import org.jgrapht.graph.Pseudograph;
import org.junit.Before;
import org.junit.Test;

import Gui.WeightedEdge;
import JD.NearestNeighbor;

public class NKAtest {

//	Graph<String, WeightedEdge> graph2 = new   DirectedWeightedPseudograph<>(WeightedEdge.class);
	Graph<String, WeightedEdge> graph2 = new Pseudograph<>(WeightedEdge.class);
	NearestNeighbor MST = new NearestNeighbor();

	@Before
//	public void createGraph2() {
//
//		graph2.addVertex("v0");
//		graph2.addVertex("v1");
//		graph2.addVertex("v3");
//		graph2.addVertex("v4");
//		graph2.addVertex("v5");
//		graph2.addVertex("v6");
//
//		addEdge(graph2, "v0", "v1", 27d);
//		addEdge(graph2, "v0", "v2", 14d);
//		addEdge(graph2, "v0", "v5", 16d);
//		addEdge(graph2, "v0", "v6", 33d);
//		addEdge(graph2, "v1", "v4", 10d);
//		addEdge(graph2, "v1", "v6", 4d);
//		addEdge(graph2, "v2", "v3", 20d);
//		addEdge(graph2, "v2", "v5", 6d);
//		addEdge(graph2, "v2", "v6", 22d);
//		addEdge(graph2, "v3", "v6", 30d);
//		addEdge(graph2, "v3", "v4", 40d);
//		addEdge(graph2, "v3", "v5", 23d);
//		addEdge(graph2, "v4", "v6", 13d);
//
//	}
	public void createGraph() {

		graph2.addVertex("v");
		graph2.addVertex("w");
		graph2.addVertex("x");
		graph2.addVertex("y");
		graph2.addVertex("z");

		addEdge(graph2, "v", "w", 6d);
		addEdge(graph2, "v", "x", 15d);
		addEdge(graph2, "v", "y", 9d);
		addEdge(graph2, "v", "z", 12d);
		addEdge(graph2, "w", "x", 11d);
		addEdge(graph2, "w", "y", 8d);
		addEdge(graph2, "w", "z", 8d);
		addEdge(graph2, "x", "y", 12d);
		addEdge(graph2, "x", "z", 4d);
		addEdge(graph2, "y", "z", 15d);

	}
	@Test
	public void testMinimalSpanningTree() {
		System.out.println(""+MST.NearestNeighbor(graph2));
		//Assert.assertEquals(76.0, MST.NearestNeighbor(graph2), 54d);
	}
	
//	@Test
//	public void testDuplicatedEdgesForMinimalSpanningTree() {
//		//MST.minimalSpanningTree(graph2);
////		MST.duplicateEdgesForEulerTour(graph2);
//		Assert.assertEquals(4, MST.duplicateEdgesForEulerTour(graph2).edgesOf("v4").size());
//		System.out.println("Aktueller Graph2 : " + graph2);
//	}
	
//	@Test
//	public void testMSTHeuristik() {
//		Assert.assertEquals(1, MST.mstHeuristik(graph2, "v0"), 0.0001);
//	}
//
	private void addEdge(Graph<String, WeightedEdge> grph, String s, String t, Double w) {
		WeightedEdge e = new WeightedEdge();
		e.setWeight(w);
		grph.addEdge(s, t, e);
	}
}
