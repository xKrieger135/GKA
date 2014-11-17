package Gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DirectedPseudograph;

public class Dijkstra {

	private Map<String, NodeValue> table = new HashMap<>();

	// public Map<String, NodeValue> dijkstraAlgorithmus(
	// Graph<String, WeightedEdge> graph, String source) {
	// // initialisierung des Startvertex mit 0
	// table.put(source, new NodeValue(source, 0d));
	// Queue<String> queue = new LinkedList<>();
	// // initialisierung der restlichen Knoten in graph mit previous nicht
	// // definiert und distance unendlich
	// for (String vertex : graph.vertexSet()) {
	// if (!vertex.equals(source)) {
	// table.put(vertex, new NodeValue(null, 10000d));
	// }
	// queue.add(vertex);
	// System.out.println("Added " + vertex + " to queue");
	// System.out.println("Queue : " + queue);
	// }
	//
	// while (!queue.isEmpty()) {
	// // String currentVertex = table.entrySet()
	// String currentVertex = queue.poll();
	// System.out.println("Visiting node " + currentVertex);
	// System.out.println("------------"
	// + table.entrySet().iterator().next().getValue()
	// .getDistance());
	//
	// for (String neighbor : getNeighbors(graph, currentVertex)) {
	// System.out.println("Visiting neighbor " + neighbor);
	//
	// NodeValue currentNodeValue = table.get(currentVertex);
	// double edgeWeight = graph.getEdgeWeight(graph.getEdge(
	// currentVertex, neighbor));
	// double alt = currentNodeValue.getDistance() + edgeWeight;
	// System.out.println("Distance between " + currentVertex
	// + " and " + neighbor + ": " + alt);
	// System.out.println("Needs to be shorter than "
	// + table.get(neighbor).getDistance());
	//
	// if (alt < table.get(neighbor).getDistance()) {
	// System.out
	// .println("Found shorter way with distance " + alt);
	// alt = table.get(neighbor).getDistance();
	// currentVertex = table.get(neighbor).getPrevious();
	// }
	// }
	// }
	// return table;
	// }

	public Map<String, NodeValue> dijkstraAlgorithmus(
			Graph<String, WeightedEdge> graph, String source) {
		table.put(source, new NodeValue(source, 0d));
		Queue<String> queue = new LinkedList<>();
		// initialisierung der restlichen Knoten in graph mit previous nicht
		// definiert und distance unendlich
		for (String vertex : graph.vertexSet()) {
			if (!vertex.equals(source)) {
				table.put(vertex, new NodeValue(null, Double.MAX_VALUE));
			}
			queue.add(vertex);
		}

		while (!queue.isEmpty()) {

			String u = queue.poll();

			for (String v : getNeighbors(graph, u)) {
				double alt = table.get(u).getDistance()
						+ graph.getEdgeWeight(graph.getEdge(u, v));
				if (alt < table.get(v).getDistance()) {
					// alt = table.get(v).getDistance();

					table.get(v).setDistance(alt);
					table.get(v).setPrevious(u);
					// u = table.get(u).getPrevious();

				}
			}
		}
		return table;
	}

	public double returnDistance(Graph<String, WeightedEdge> graph,
			String source, String target) {

		Map<String, NodeValue> table = dijkstraAlgorithmus2(graph, source);
		List<String> hops = new ArrayList<>();
		String u = target;
		// while (table.get(u).getPrevious() != null) {
		// hops.add(0, u);
		// u = table.get(u).getPrevious();
		// }

		System.out.println("Found way: " + table.get(target).getDistance());
		for (String hop : hops) {
			System.out.println(hop);
		}
		return table.get(target).getDistance();

	}

	private Collection<String> getNeighbors(Graph<String, WeightedEdge> graph,
			String node) {
		Set<WeightedEdge> edgesOf = graph.edgesOf(node);
		Set<String> result = new HashSet<>();
		for (WeightedEdge edge : edgesOf) {
			result.add(edge.getTarget());
		}
		result.remove(node);
		return result;
	}

	// Nodevalue für variante mit der Map
	private class NodeValue {
		private String previous;
		private double distance;
		private String vertex;

		public NodeValue(String previous, double distance) {
			super();
			this.previous = previous;
			this.distance = distance;
		}

		public NodeValue() {

		}

		public double getDistance() {
			return distance;
		}

		public String getPrevious() {
			return previous;
		}

		public void setPrevious(String previous) {
			this.previous = previous;
		}

		public void setDistance(double distance) {
			this.distance = distance;
		}

		@Override
		public String toString() {
			return "NodeValue [previous=" + previous + ", distance=" + distance
					+ "]";
		}

	}

	// Variante für die Lösung mit der Queue
	private class NodeValue2 {
		private String previous;
		private double distance;
		private String vertex;

		public NodeValue2(String vertex, String previous, double distance) {
			super();
			this.previous = previous;
			this.distance = distance;
			this.vertex = vertex;
		}

		public NodeValue2() {

		}

		public double getDistance() {
			return distance;
		}

		public String getPrevious() {
			return previous;
		}

		public void setPrevious(String previous) {
			this.previous = previous;
		}

		public void setDistance(double distance) {
			this.distance = distance;
		}

		public String getVertex() {
			return vertex;
		}

		public void setVertex(String vertex) {
			this.vertex = vertex;
		}

		@Override
		public String toString() {
			return "NodeValue [previous=" + previous + ", distance=" + distance
					+ "]";
		}

	}

	public Map<String, NodeValue> dijkstraAlgorithmus2(
			Graph<String, WeightedEdge> graph, String source) {
		Queue<NodeValue2> queue = new LinkedList<>();
		queue.add(new NodeValue2(source, source, 0d));
		// initialisierung der restlichen Knoten in graph mit previous nicht
		// definiert und distance unendlich
		for (String vertex : graph.vertexSet()) {
			if (!vertex.equals(source)) {
				queue.add(new NodeValue2(null, vertex, Double.MAX_VALUE));
			}
		}

		while (!queue.isEmpty()) {

			NodeValue2 u = queue.poll();

			for (String v : getNeighbors(graph, u.getVertex())) {
				double alt = u.getDistance()
						+ graph.getEdgeWeight(graph.getEdge(u.getVertex(), v));
				if (alt < table.get(v).getDistance()) {
					// NodeValue tmp = queue.
					// if (alt < ) {
					// alt = table.get(v).getDistance();

					// table.get(v).setDistance(alt);
					// table.get(v).setPrevious(u);
					// u = table.get(u).getPrevious();

				}
			}
		}
		return table;
	}

}
