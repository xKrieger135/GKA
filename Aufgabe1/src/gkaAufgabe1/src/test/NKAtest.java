package test;

import static org.junit.Assert.*;

import org.jgrapht.Graph;
import org.jgrapht.graph.DirectedWeightedPseudograph;
import org.jgrapht.graph.Pseudograph;
import org.junit.Before;
import org.junit.Test;

import Gui.WeightedEdge;
import JD.NearestNeighbor;

public class NKAtest {

	Graph<String, WeightedEdge> graph2 = new   DirectedWeightedPseudograph<>(WeightedEdge.class);
	Graph<String, WeightedEdge> eulerGraph = new Pseudograph<>(WeightedEdge.class);
	NearestNeighbor nka = new NearestNeighbor();
//	NKA MST = new NKA();

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
	public void testMinimalSpanningTree() {
//		System.out.println(""+MST.NearestNeighbor(eulerGraph));
//		System.out.println(""+MST.NKA(eulerGraph, "v4"));
		assertEquals(115D, nka.NearestNeighbor(eulerGraph), 0.0001d);

	}

	private void addEdge(Graph<String, WeightedEdge> grph, String s, String t, Double w) {
		WeightedEdge e = new WeightedEdge();
		e.setWeight(w);
		grph.addEdge(s, t, e);
	}
}
