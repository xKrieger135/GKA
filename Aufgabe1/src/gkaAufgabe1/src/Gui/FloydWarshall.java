package Gui;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.jgrapht.Graph;

public class FloydWarshall {
	
	Map<String, Map<String, Double>> distances = new HashMap<String, Map<String, Double>>();
	Map<String, Map<String, String>> transit = new HashMap<String, Map<String, String>>();

	public double floydWarshallAlgorithmus(Graph<String, WeightedEdge> graph,
			String source, String target) {

		for (String v : graph.vertexSet()) {
			Map<String, Double> x = new HashMap<>();
			for (String v2 : graph.vertexSet()) {
				if (graph.getAllEdges(v, v2).isEmpty()) {
					//Keine Ecke vorhandne
					x.put(v2, Double.POSITIVE_INFINITY);
				} else if (v2 != v) {
					//Setze entfernung
					WeightedEdge edge = graph.getEdge(v, v2);
					x.put(v2, graph.getEdgeWeight(edge));
				} else {
					//Zu sich: 0
					x.put(v2, 0d);
				}
			}
			distances.put(v, x);
		}

		// Maps füllen mit Distanzen (unseren Graphen abbilden)
//		for (WeightedEdge edge : graph.edgeSet()) {
//			double weight = graph.getEdgeWeight(edge);
//			distances.get(edge.getSource()).put(edge.getTarget(), weight);
//		}
		System.out.println("initialized");

		for (String v : graph.vertexSet()) {
			Map<String, String> x = new HashMap<>();
			for (String v2 : graph.vertexSet()) {
				x.put(v2, null);
			}
			transit.put(v, x);
		}

		for (String j : graph.vertexSet()) {
			for (String i : graph.vertexSet()) {
				for (String k : graph.vertexSet()) {
					if (!i.equals(j) && !k.equals(j)) {
						double dik = distances.get(i).get(k);
						double dij = distances.get(i).get(j);
						double djk = distances.get(j).get(k);
						double min = Math.min(dik, dij + djk);

						distances.get(i).put(k, min);

						if (dik != distances.get(i).get(k)) {
							transit.get(i).put(k, j);
						}

					}
				}
			}
		}

		System.out.println("Shortest way via " + transit.get(source).get(target)+": " + distances.get(source).get(target));
		 return distances.get(source).get(target);
	}
}
