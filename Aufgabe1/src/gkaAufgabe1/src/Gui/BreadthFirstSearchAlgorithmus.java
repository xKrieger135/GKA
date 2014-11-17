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
import org.jgrapht.graph.DirectedWeightedPseudograph;

public class BreadthFirstSearchAlgorithmus {
	private Controller controller;
	private MyParser myParser;

	public int breadthFirstSearch(Graph<String, WeightedEdge> graph,
			String start, String end) {
		// Set<String> visitedNodes = new HashSet<>();
		// Set<String> set = new HashSet<>();
		int result = analyseNodesQ(graph, start, end);
		return result;
	}

	public int analyseNodesQ(Graph<String, WeightedEdge> graph, String start,
			String end) {

		// graph = myParser
		// .parseTextFromTextFileToGraph();
		Map<String, List<String>> map = new HashMap<>();

		printGraph(graph);

		Queue<String> queue = new LinkedList<>();
		queue.add(start);
		// es wird der start in die map getan und mit "0" initialisiert liste
		// hier, weil so später weg + Ziffer ausgegeben werden kann
		map.put(start, Arrays.asList(start));

		while (!queue.isEmpty()) {
			// currentnode ist das oberste Element der queue und wird
			// gespeichert
			String currentNode = queue.poll();

			if (currentNode.equals(end)) {
				System.out.println("Found shortest way:");
				for (String node : map.get(currentNode)) {
					System.out.println(node);
				}
				// hier wird der kürzeste Weg ausgegeben. - 1, weil dies bei
				// Start -> start ist 1 element in der Liste jedoch ist der Weg
				// 0
				return map.get(currentNode).size() - 1;
			}
			// fuer jeden Zielknoten wird es ausgefuehrt
			for (String targetNode : getNeighbors(graph, currentNode)) {
				if (!map.containsKey(targetNode)) {
					// eine Liste mit den gespeicherten Werten des Weges.
					List<String> list = new ArrayList<String>(
							map.get(currentNode));
					// neuen wert hinzufuegen fuer den Weg
					list.add(targetNode);
					map.put(targetNode, list);
					queue.add(targetNode);
				}
			}
		}

		return 0;
	}

	// getNeihbors holt sich die kanten eines Knoten und fuegt jeweils den
	// Endknoten hinzu Anfangsknoten nicht, da sonst gerichtete Graphen
	// nicht mehr funktionieren

	private Collection<String> getNeighbors(Graph<String, WeightedEdge> graph,
			String node) {
		Set<WeightedEdge> edgesOf = graph.edgesOf(node);
		Set<String> result = new HashSet<>();
		for (WeightedEdge edge : edgesOf) {
			// result.add(edge.getSource());
			result.add(edge.getTarget());
		}
		result.remove(node);
		return result;
	}

	// Funktion zum Speichern der Datei
	private void printGraph(Graph<String, WeightedEdge> graph) {
		String pfeil;
		if (graph instanceof DirectedWeightedPseudograph) {
			pfeil = "->";
		} else {
			pfeil = "--";
		}
		System.out.println("Printed graph:");
		Set<String> nodes = new HashSet<String>();
		for (WeightedEdge e : graph.edgeSet()) {
			System.out.println(e.getSource() + " " + pfeil + " "
					+ e.getTarget());
			nodes.add(e.getSource());
			nodes.add(e.getTarget());
		}
		Set<String> vertexSet = new HashSet<String>(graph.vertexSet());
		vertexSet.removeAll(nodes);
		for (String n : vertexSet) {
			System.out.println(n);
		}
	}
}
