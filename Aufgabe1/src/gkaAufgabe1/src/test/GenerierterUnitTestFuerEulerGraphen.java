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
		addEdge(eulerGraph, "v1", "v2", 31.0);
		addEdge(eulerGraph, "v1", "v4", 30.0);
		addEdge(eulerGraph, "v1", "v5", 18.0);
		addEdge(eulerGraph, "v1", "v6", 18.0);
		addEdge(eulerGraph, "v1", "v7", 22.0);
		addEdge(eulerGraph, "v1", "v8", 7.0);
		addEdge(eulerGraph, "v2", "v4", 27.0);
		addEdge(eulerGraph, "v2", "v5", 13.0);
		addEdge(eulerGraph, "v2", "v6", 24.0);
		addEdge(eulerGraph, "v2", "v7", 20.0);
		addEdge(eulerGraph, "v2", "v8", 5.0);
		addEdge(eulerGraph, "v3", "v4", 33.0);
		addEdge(eulerGraph, "v3", "v5", 22.0);
		addEdge(eulerGraph, "v3", "v6", 23.0);
		addEdge(eulerGraph, "v3", "v7", 18.0);
		addEdge(eulerGraph, "v3", "v8", 4.0);
		addEdge(eulerGraph, "v4", "v6", 35.0);
		addEdge(eulerGraph, "v4", "v7", 36.0);
		addEdge(eulerGraph, "v4", "v8", 19.0);
		addEdge(eulerGraph, "v5", "v6", 23.0);
		addEdge(eulerGraph, "v5", "v8", 24.0);
		addEdge(eulerGraph, "v6", "v7", 19.0);
		addEdge(eulerGraph, "v6", "v8", 10.0);
		addEdge(eulerGraph, "v7", "v8", 19.0);
		addEdge(eulerGraph, "v3", "v1", 11.0);
		addEdge(eulerGraph, "v3", "v2", 17.0);
		addEdge(eulerGraph, "v5", "v7", 9.0);
		addEdge(eulerGraph, "v5", "v4", 22.0);





		
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
