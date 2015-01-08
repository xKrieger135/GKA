package test;

import static org.junit.Assert.*;

import org.jgrapht.Graph;
import org.jgrapht.graph.Pseudograph;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Gui.MST;
import Gui.WeightedEdge;

public class GenerierterUnitTestFuerEulerGraphen {

	Graph<String, WeightedEdge> eulerGraph = new Pseudograph<>(WeightedEdge.class);
	MST MST = new MST();
	
	@Before
	public void createGraph() {

		eulerGraph.addVertex("v1");
		eulerGraph.addVertex("v2");
		eulerGraph.addVertex("v3");
		eulerGraph.addVertex("v4");
		eulerGraph.addVertex("v5");
		eulerGraph.addVertex("v6");
		eulerGraph.addVertex("v7");
		eulerGraph.addVertex("v8");
		eulerGraph.addVertex("v9");
		eulerGraph.addVertex("v10");
		eulerGraph.addVertex("v11");
		addEdge(eulerGraph, "v1", "v3", 8.0);
		addEdge(eulerGraph, "v1", "v4", 39.0);
		addEdge(eulerGraph, "v1", "v6", 58.0);
		addEdge(eulerGraph, "v1", "v8", 17.0);
		addEdge(eulerGraph, "v1", "v9", 53.0);
		addEdge(eulerGraph, "v1", "v10", 60.0);
		addEdge(eulerGraph, "v2", "v3", 62.0);
		addEdge(eulerGraph, "v2", "v8", 48.0);
		addEdge(eulerGraph, "v2", "v10", 62.0);
		addEdge(eulerGraph, "v3", "v5", 39.0);
		addEdge(eulerGraph, "v3", "v7", 53.0);
		addEdge(eulerGraph, "v3", "v9", 51.0);
		addEdge(eulerGraph, "v3", "v11", 60.0);
		addEdge(eulerGraph, "v4", "v6", 47.0);
		addEdge(eulerGraph, "v4", "v8", 49.0);
		addEdge(eulerGraph, "v4", "v10", 51.0);
		addEdge(eulerGraph, "v5", "v2", 23.0);
		addEdge(eulerGraph, "v5", "v7", 57.0);
		addEdge(eulerGraph, "v5", "v9", 23.0);
		addEdge(eulerGraph, "v5", "v11", 64.0);
		addEdge(eulerGraph, "v6", "v8", 55.0);
		addEdge(eulerGraph, "v6", "v10", 41.0);
		addEdge(eulerGraph, "v7", "v9", 1.0);
		addEdge(eulerGraph, "v7", "v11", 3.0);
		addEdge(eulerGraph, "v8", "v10", 23.0);
		addEdge(eulerGraph, "v8", "v11", 44.0);
		addEdge(eulerGraph, "v9", "v11", 61.0);
		addEdge(eulerGraph, "v2", "v1", 0.0);
		addEdge(eulerGraph, "v4", "v2", 1.0);
		addEdge(eulerGraph, "v4", "v3", 0.0);
		addEdge(eulerGraph, "v5", "v1", 2.0);
		addEdge(eulerGraph, "v5", "v4", 0.0);
		addEdge(eulerGraph, "v6", "v2", 2.0);
		addEdge(eulerGraph, "v6", "v3", 1.0);
		addEdge(eulerGraph, "v6", "v5", 0.0);
		addEdge(eulerGraph, "v7", "v1", 2.0);
		addEdge(eulerGraph, "v7", "v2", 1.0);
		addEdge(eulerGraph, "v7", "v4", 1.0);
		addEdge(eulerGraph, "v7", "v10", 1.0);
		addEdge(eulerGraph, "v7", "v6", 0.0);
		addEdge(eulerGraph, "v8", "v3", 2.0);
		addEdge(eulerGraph, "v8", "v5", 1.0);
		addEdge(eulerGraph, "v8", "v7", 0.0);
		addEdge(eulerGraph, "v9", "v2", 2.0);
		addEdge(eulerGraph, "v9", "v4", 1.0);
		addEdge(eulerGraph, "v9", "v6", 1.0);
		addEdge(eulerGraph, "v9", "v8", 0.0);
		addEdge(eulerGraph, "v10", "v3", 2.0);
		addEdge(eulerGraph, "v10", "v5", 1.0);
		addEdge(eulerGraph, "v10", "v9", 0.0);
		addEdge(eulerGraph, "v11", "v1", 2.0);
		addEdge(eulerGraph, "v11", "v2", 1.0);
		addEdge(eulerGraph, "v11", "v4", 1.0);
		addEdge(eulerGraph, "v11", "v6", 1.0);
		addEdge(eulerGraph, "v11", "v10", 0.0);

		
	}
	
	@Test
	public void testMSTHeuristik() {
		Assert.assertEquals(21, MST.mstHeuristik(eulerGraph, "v4"), 0.0001);
		Assert.assertEquals(21, MST.mstHeuristik(eulerGraph, "v2"), 0.0001);
	
		
	}
	
	private void addEdge(Graph<String, WeightedEdge> graph, String s, String t, Double w) {
		WeightedEdge edge = new WeightedEdge();
		edge.setWeight(w);
		graph.addEdge(s, t, edge);
	}

}
