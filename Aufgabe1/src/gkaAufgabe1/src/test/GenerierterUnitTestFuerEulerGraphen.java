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
		addEdge(eulerGraph, "v1", "v2", 39.0);
		addEdge(eulerGraph, "v1", "v6", 45.0);
		addEdge(eulerGraph, "v1", "v7", 36.0);
		addEdge(eulerGraph, "v2", "v3", 40.0);
		addEdge(eulerGraph, "v2", "v5", 39.0);
		addEdge(eulerGraph, "v2", "v7", 42.0);
		addEdge(eulerGraph, "v3", "v5", 34.0);
		addEdge(eulerGraph, "v3", "v9", 33.0);
		addEdge(eulerGraph, "v4", "v5", 34.0);
		addEdge(eulerGraph, "v4", "v8", 19.0);
		addEdge(eulerGraph, "v5", "v7", 29.0);
		addEdge(eulerGraph, "v5", "v8", 25.0);
		addEdge(eulerGraph, "v5", "v9", 29.0);
		addEdge(eulerGraph, "v6", "v9", 33.0);
		addEdge(eulerGraph, "v4", "v1", 33.0);
		addEdge(eulerGraph, "v6", "v2", 32.0);
		addEdge(eulerGraph, "v7", "v6", 19.0);
		addEdge(eulerGraph, "v7", "v3", 21.0);
		addEdge(eulerGraph, "v8", "v3", 18.0);
		addEdge(eulerGraph, "v4", "v6", 15.0);
		addEdge(eulerGraph, "v5", "v6", 27.0);
		addEdge(eulerGraph, "v5", "v1", 18.0);
		addEdge(eulerGraph, "v8", "v9", 22.0);
		addEdge(eulerGraph, "v4", "v9", 19.0);
		addEdge(eulerGraph, "v3", "v6", 11.0);
		addEdge(eulerGraph, "v3", "v1", 25.0);
		addEdge(eulerGraph, "v4", "v7", 18.0);
		addEdge(eulerGraph, "v4", "v2", 22.0);
		addEdge(eulerGraph, "v4", "v3", 16.0);
		addEdge(eulerGraph, "v8", "v6", 12.0);
		addEdge(eulerGraph, "v8", "v1", 26.0);
		addEdge(eulerGraph, "v8", "v2", 24.0);
		addEdge(eulerGraph, "v8", "v7", 17.0);
		addEdge(eulerGraph, "v9", "v1", 23.0);
		addEdge(eulerGraph, "v9", "v2", 17.0);
		addEdge(eulerGraph, "v9", "v7", 18.0);
	}
	
	@Test
	public void testMSTHeuristik() {
		Assert.assertEquals(32, MST.mstHeuristik(eulerGraph, "v7"), 0.0001);
		Assert.assertEquals(32, MST.mstHeuristik(eulerGraph, "v2"), 0.0001);
		Assert.assertEquals(32, MST.mstHeuristik(eulerGraph, "v6"), 0.0001);
	
		
	}
	
	private void addEdge(Graph<String, WeightedEdge> graph, String s, String t, Double w) {
		WeightedEdge edge = new WeightedEdge();
		edge.setWeight(w);
		graph.addEdge(s, t, edge);
	}

}
