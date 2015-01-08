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
		addEdge(eulerGraph, "v1", "v2", 4.0);
		addEdge(eulerGraph, "v1", "v3", 2.0);
		addEdge(eulerGraph, "v1", "v4", 2.0);
		addEdge(eulerGraph, "v2", "v3", 10.0);
		addEdge(eulerGraph, "v2", "v4", 2.0);
		addEdge(eulerGraph, "v3", "v4", 10.0);
		
	}
	
	@Test
	public void testMSTHeuristik() {
		Assert.assertEquals(130, MST.mstHeuristik(eulerGraph, "v4"), 0.0001);
	
		
	}
	
	private void addEdge(Graph<String, WeightedEdge> graph, String s, String t, Double w) {
		WeightedEdge edge = new WeightedEdge();
		edge.setWeight(w);
		graph.addEdge(s, t, edge);
	}

}
