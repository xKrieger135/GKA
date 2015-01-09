package test;

import org.jgrapht.Graph;
import org.jgrapht.graph.Pseudograph;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Gui.MST;
import Gui.WeightedEdge;

public class MSTHeuristikTest {

//	Graph<String, WeightedEdge> graph2 = new   DirectedWeightedPseudograph<>(WeightedEdge.class);
	Graph<String, WeightedEdge> graph2 = new Pseudograph<>(WeightedEdge.class);
	Graph<String, WeightedEdge> graph3 = new Pseudograph<>(WeightedEdge.class);
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
		
		graph3.addVertex("v");
		graph3.addVertex("w");
		graph3.addVertex("x");
		graph3.addVertex("y");
		graph3.addVertex("z");
		
		addEdge(graph3, "w", "v", 6d);
		addEdge(graph3, "w", "x", 11d);
		addEdge(graph3, "w", "y", 8d);
		addEdge(graph3, "w", "z", 8d);
		addEdge(graph3, "x", "v", 15d);
		addEdge(graph3, "x", "y", 12d);
		addEdge(graph3, "x", "z", 4d);
		addEdge(graph3, "z", "v", 12d);
		addEdge(graph3, "y", "v", 9d);
		addEdge(graph3, "z", "y", 15d);
		 
		addEdge(graph2, "v0", "v1", 27d);
		addEdge(graph2, "v0", "v2", 14d);
		addEdge(graph2, "v0", "v5", 16d);
		addEdge(graph2, "v0", "v6", 33d);
		addEdge(graph2, "v1", "v4", 10d);
		addEdge(graph2, "v1", "v6", 4d);
		addEdge(graph2, "v2", "v3", 20d);
		addEdge(graph2, "v2", "v5", 6d);
		addEdge(graph2, "v2", "v6", 22d);
		addEdge(graph2, "v3", "v6", 30d);
		addEdge(graph2, "v3", "v4", 40d);
		addEdge(graph2, "v3", "v5", 23d);
		addEdge(graph2, "v4", "v6", 13d);

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
//		Assert.assertEquals(39, MST.mstHeuristik(graph3, "x"), 0.0001);
//		// TODO diese beiden Tests sind noch falsch
//		Assert.assertEquals(39, MST.mstHeuristik(graph3, "v"), 0.0001);		
//		Assert.assertEquals(45, MST.mstHeuristik(graph3, "z"), 0.0001);	
//		
//		Assert.assertEquals(42, MST.mstHeuristik(graph3, "y"), 0.0001);
//		// TODO diese beiden Tests sind noch falsch
//		Assert.assertEquals(39, MST.mstHeuristik(graph3, "w"), 0.0001);		
		
	}

	private void addEdge(Graph<String, WeightedEdge> graph, String s, String t, Double w) {
		WeightedEdge e = new WeightedEdge();
		e.setWeight(w);
		graph.addEdge(s, t, e);
	}
}
