package Gui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DirectedWeightedPseudograph;

public class Dijkstra {

	private Map<String, NodeValue> table = new HashMap<>();
	private Queue<NodeValue> resultQueue = new LinkedList<>();

	public Map<String, NodeValue> dijkstraAlgorithmus(
			Graph<String, WeightedEdge> graph, String source) {
		table.put(source, new NodeValue(source, source, 0d));
		List<String> queue = new LinkedList<>();

		// initialisierung der restlichen Knoten in graph mit previous nicht
		// definiert und distance unendlich
		for (String vertex : graph.vertexSet()) {
			if (!vertex.equals(source)) {
				table.put(vertex, new NodeValue(vertex, null, Double.MAX_VALUE));
			}
			queue.add(vertex);
		}

		while (!queue.isEmpty()) {

			NodeValue minimumNode = getMinimumDistanceNode(source, queue);
			
				queue.remove(minimumNode.getVertex());
				
			

			String node = minimumNode.getVertex();
			System.out.println("	Neighbors : " + getNeighbors(graph, node));
			for (String v : getNeighbors(graph, node)) {
				double alt = table.get(minimumNode.getVertex()).getDistance()
						+ graph.getEdgeWeight(graph.getEdge(
								minimumNode.getVertex(), v));
				if (alt < table.get(v).getDistance()) {
					table.get(v).setDistance(alt);
					table.get(v).setPrevious(minimumNode.getVertex());
				}
			}
		}
		return table;
	}

	private NodeValue getMinimumDistanceNode(String source, List<String> queue) {
		NodeValue minimumNode = null;
		double min = Double.MAX_VALUE;
		for (String toInspectNode : queue) {
			NodeValue toInspectNodeValue = table.get(toInspectNode);
			if (toInspectNodeValue.getDistance() <= min) {
				minimumNode = toInspectNodeValue;
			}
		}
		return minimumNode;
	}

	public double returnDistance(Graph<String, WeightedEdge> graph,
			String source, String target) {

		Map<String, NodeValue> table = dijkstraAlgorithmus(graph, source);
		
		List<String> hops = new ArrayList<>();
		String u = target;

//		while (table.get(u).getPrevious() != source) {
//			hops.add(0, u);
//			u = table.get(u).getPrevious();
//		}

		for (String hop : hops) {
			System.out.println(hop);
		}
		System.out.println("Found way: " + table.get(target).getDistance());
		return table.get(target).getDistance();

	}

	// Nodevalue für variante mit der Map
	private class NodeValue {
		private String previous;
		private double distance;
		private String vertex;

		public NodeValue(String vertex, String previous, double distance) {
			super();
			this.previous = previous;
			this.distance = distance;
			this.vertex = vertex;
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

		public String getVertex() {
			return vertex;
		}

		public void setVertex(String vertex) {
			this.vertex = vertex;
		}

		@Override
		public String toString() {
			return "NodeValue [previous=" + previous + ", distance=" + distance
					+ ", vertex=" + vertex + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result
					+ ((vertex == null) ? 0 : vertex.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			NodeValue other = (NodeValue) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (vertex == null) {
				if (other.vertex != null)
					return false;
			} else if (!vertex.equals(other.vertex))
				return false;
			return true;
		}

		private Dijkstra getOuterType() {
			return Dijkstra.this;
		}

	}

	private Collection<String> getNeighbors(Graph<String, WeightedEdge> graph,
			String node) {
		try {
		Set<WeightedEdge> edgesOf = graph.edgesOf(node);
		Set<String> result = new HashSet<>();
		for (WeightedEdge edge : edgesOf) {
			if (graph instanceof DirectedWeightedPseudograph<?, ?>) {
				result.add(edge.getTarget());
			} else {
				result.add(edge.getSource());
				result.add(edge.getTarget());

			}
		}
		result.remove(node);
		return result;
		} catch (Exception e){
			return new ArrayList<String>();
		}
	}

}
