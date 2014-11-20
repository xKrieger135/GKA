package Gui;

public class AlternativeLoesungMitQueue {
	// Variante für die Lösung mit der Queue
//	private class NodeValue2 {
//		private String previous;
//		private double distance;
//		private String vertex;
//	public NodeValue2(String vertex, String previous, double distance) {
//		super();
//		this.previous = previous;
//		this.distance = distance;
//		this.vertex = vertex;
//	}
//
//	public NodeValue2() {
//
//	}
//
//	public double getDistance() {
//		return distance;
//	}
//
//	public String getPrevious() {
//		return previous;
//	}
//
//	public void setPrevious(String previous) {
//		this.previous = previous;
//	}
//
//	public void setDistance(double distance) {
//		this.distance = distance;
//	}
//
//	public String getVertex() {
//		return vertex;
//	}
//
//	public void setVertex(String vertex) {
//		this.vertex = vertex;
//	}
//
//	@Override
//	public String toString() {
//		return "NodeValue2 [previous=" + previous + ", distance="
//				+ distance + ", vertex=" + vertex + "]";
//	}
//
//}

//public Queue<NodeValue2> dijkstraAlgorithmus2(
//		Graph<String, WeightedEdge> graph, String source) {
//	Queue<NodeValue2> queue = new LinkedList<>();
//	queue.add(new NodeValue2(source, source, 0d));
//	// initialisierung der restlichen Knoten in graph mit previous nicht
//	// definiert und distance unendlich
//	for (String vertex : graph.vertexSet()) {
//		if (!vertex.equals(source)) {
//			queue.add(new NodeValue2(vertex, null, Double.MAX_VALUE));
//		}
//	}
//
//	while (!queue.isEmpty()) {
//
//		NodeValue2 u = queue.poll();
//		Collection<String> neighbor = getNeighbors(graph, u.getVertex());
//		for (String v : neighbor) {
//			double alt = u.getDistance()
//					+ graph.getEdgeWeight(graph.getEdge(u.getVertex(), v));
//			NodeValue2 tmp = queue.poll();
//			if (alt < tmp.getDistance()) {
//				tmp.setDistance(alt);
//				tmp.setPrevious(u.getVertex());
//				queue.add(tmp);
//			}
//		}
//	}
//	return queue;
//}
	
	// private Collection<String> getNeighbors(Graph<String, WeightedEdge>
	// graph,
	// String node) {
	// Set<WeightedEdge> edgesOf = graph.edgesOf(node);
	// Set<String> result = new HashSet<>();
	// for (WeightedEdge edge : edgesOf) {
	// result.add(edge.getTarget());
	// }
	// result.remove(node);
	// return result;
	// }

}
