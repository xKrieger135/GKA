package test;

import static org.junit.Assert.*;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.MinimumSpanningTree;
import org.jgrapht.graph.DirectedWeightedPseudograph;
import org.jgrapht.graph.Pseudograph;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Gui.Dijkstra;
import Gui.FordFulk;
import Gui.MST;
import Gui.MinimalSpannningTree;
import Gui.WeightedEdge;

public class MSTHeuristikTest {

//	Graph<String, WeightedEdge> graph2 = new   DirectedWeightedPseudograph<>(WeightedEdge.class);
	Graph<String, WeightedEdge> graph2 = new Pseudograph<>(WeightedEdge.class);
//	MinimalSpannningTree MST = new MinimalSpannningTree();
	MST MST = new MST();
	
	

	@Before
	public void createGraph() {

		graph2.addVertex("v0");
		graph2.addVertex("v1");
		graph2.addVertex("v2");
		graph2.addVertex("v3");
		graph2.addVertex("v4");
		graph2.addVertex("v5");
		graph2.addVertex("v6");

		addEdge("v0", "v1", 27d);
		addEdge("v0", "v2", 14d);
		addEdge("v0", "v5", 16d);
		addEdge("v0", "v6", 33d);
		addEdge("v1", "v4", 10d);
		addEdge("v1", "v6", 4d);
		addEdge("v2", "v3", 20d);
		addEdge("v2", "v5", 6d);
		addEdge("v2", "v6", 22d);
		addEdge("v3", "v6", 30d);
		addEdge("v3", "v4", 40d);
		addEdge("v3", "v5", 23d);
		addEdge("v4", "v6", 13d);

	}

	@Test
	public void testMinimalSpanningTree() {
		MST.minimalSpanningTree(graph2);
		Assert.assertEquals(76.0, MST.minimalSpanningTree(graph2), 0.0001);
	}
	
	@Test
	public void testDuplicatedEdgesForMinimalSpanningTree() {
		MST.minimalSpanningTree(graph2);
//		MST.duplicateEdgesForEulerTour(graph2);
		Assert.assertEquals(4, MST.duplicateEdgesForEulerTour(graph2).edgesOf("v4").size());
		System.out.println("Aktueller Graph2 : " + graph2);
	}
	
	@Test
	public void testMSTHeuristik() {
		Assert.assertEquals(130, MST.mstHeuristik(graph2, "v5"), 0.0001);
	}

	private void addEdge(String s, String t, Double w) {
		WeightedEdge e = new WeightedEdge();
		e.setWeight(w);
		graph2.addEdge(s, t, e);
	}
}
