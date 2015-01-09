package Gui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.alg.util.UnionFind;
import org.jgrapht.graph.DirectedWeightedPseudograph;

public class RecursiveMST {
	Map<String, Map<String, Double>> minimalSpanningTree = new HashMap<String, Map<String, Double>>();
	Set<WeightedEdge> kantengewichtungenDesMinimalenSpannbaumes;
//	List<WeightedEdge> listeMitBesuchtenKanten = new ArrayList<>();
//	List<String> listeMitBesuchtenKnoten = new ArrayList<>();
//	Queue<String> queueMitBesuchtenKnoten = new LinkedList<>();
	List<WeightedEdge> listeMitBesuchtenKanten = new ArrayList<>();
	List<String> listeMitBesuchtenKnoten = new ArrayList<>();
	Queue<String> queueMitBesuchtenKnoten = new LinkedList<>();
	private int counter = 0;		
	private double ergebnis = 0;
	private boolean initialize = false;

	public double recursiveMST(Graph<String, WeightedEdge> graph, String startKnoten) {

		minimalSpanningTree(graph);
		Graph<String, WeightedEdge> eulerGraph = duplicateEdgesForEulerTour(graph);

		Set<WeightedEdge> setMitKantenDesKnoten = eulerGraph.edgesOf(startKnoten);
		if (setMitKantenDesKnoten.isEmpty()) {
			System.out.println("Keine Möglichkeit weiter zu kommen.");
		} else {

			// while (listeMitBesuchtenKnoten.size() !=
			// eulerGraph.vertexSet().size()) {
			if (listeMitBesuchtenKnoten.size() != eulerGraph.vertexSet().size() + 1) {
				if (!listeMitBesuchtenKnoten.contains(startKnoten)) {

					listeMitBesuchtenKnoten.add(startKnoten);
					queueMitBesuchtenKnoten.add(startKnoten);
				}
				// queueMitBesuchtenKnoten.add(startKnoten);

				// List<WeightedEdge> listeMitKantenDesKnoten = new
				// ArrayList<>();
				// listeMitKantenDesKnoten.addAll(setMitKantenDesKnoten);

				for (String knoten : getNeighbors(eulerGraph, startKnoten)) {
					boolean istDieAktuelleKantengewichtungImSpannbaumEnthalten = kantengewichtungenDesMinimalenSpannbaumes.contains(graph.getEdge(startKnoten, knoten));
					Set<WeightedEdge> alleEdgesVomKnoten = graph.edgesOf(knoten);
					if (!listeMitBesuchtenKnoten.contains(knoten) && istDieAktuelleKantengewichtungImSpannbaumEnthalten
							&& !listeMitBesuchtenKanten.contains(graph.getEdge(startKnoten, knoten))) {

						listeMitBesuchtenKnoten.add(knoten);
						queueMitBesuchtenKnoten.add(knoten);
						listeMitBesuchtenKanten.add(graph.getEdge(startKnoten, knoten));
						counter++;
						recursiveMST(graph, knoten);

					} else if (!listeMitBesuchtenKnoten.contains(knoten) && counter != 0) {
						listeMitBesuchtenKnoten.add(knoten);
						queueMitBesuchtenKnoten.add(knoten);
						// listeMitBesuchtenKnoten.add(knoten);
						listeMitBesuchtenKanten.add(graph.getEdge(startKnoten, knoten));
						counter++;
						recursiveMST(graph, knoten);
					} else if (listeMitBesuchtenKnoten.size() == graph.vertexSet().size()) {
						startKnoten = queueMitBesuchtenKnoten.peek();
					} else {
						System.out.println("Else soll nichts tun!");

					}
				}

			}
		}

		queueMitBesuchtenKnoten.add(startKnoten);
		System.out.println("Wie viele Elemente sind hier drin ? " + queueMitBesuchtenKnoten.size());

		if (queueMitBesuchtenKnoten.size() == eulerGraph.vertexSet().size() + 1) {

			for (int i = 0; i < eulerGraph.vertexSet().size(); i++) {

				String a = queueMitBesuchtenKnoten.poll();
				System.out.println("a = " + a);
				String b = queueMitBesuchtenKnoten.peek();
				System.out.println("b = " + b);
				ergebnis = ergebnis + graph.getEdge(a, b).getWeight();
			}
		}

		return ergebnis;
	}

	public double minimalSpanningTree(Graph<String, WeightedEdge> graph) {
		// Berechnet den minimalen Spannbaum eines Graphen G = (V, E)
		KruskalMinimumSpanningTree<String, WeightedEdge> kruskalMinimumSpanningTree = new KruskalMinimumSpanningTree<String, WeightedEdge>(graph);

		// get length of minimal spanning tree
		double laengeDesMinimalenSpannbaumes = kruskalMinimumSpanningTree.getSpanningTreeCost();
		System.out.println("Laenge des minimalen Spannbaumes : " + laengeDesMinimalenSpannbaumes);

		// get edges from minimal spanning tree
		kantengewichtungenDesMinimalenSpannbaumes = kruskalMinimumSpanningTree.getEdgeSet();
		System.out.println(kantengewichtungenDesMinimalenSpannbaumes);

		for (WeightedEdge weightedEdge : kantengewichtungenDesMinimalenSpannbaumes) {
			Map<String, Double> leereMapFuerTargetUndKantengewichtung = new HashMap<>();

			String edgeSource = weightedEdge.getSource();
			System.out.println("Source = " + edgeSource);

			String edgeTarget = weightedEdge.getTarget();
			System.out.println("Target = " + edgeTarget);

			// if minimal spanning tree not contains the source of our edge, we
			// will put it into the map for
			// the minimal spanning tree
			if (!minimalSpanningTree.containsKey(edgeSource)) {
				minimalSpanningTree.put(edgeSource, leereMapFuerTargetUndKantengewichtung);
				System.out.println("Minimaler Spannbaum : " + minimalSpanningTree);

				minimalSpanningTree.get(edgeSource).put(edgeTarget, weightedEdge.getWeight());
			} else {
				// if our map contains this vertex we will put all other
				// neighbors of that vertex into the map with
				// this source vertex
				minimalSpanningTree.get(edgeSource).put(edgeTarget, weightedEdge.getWeight());
			}

		}

		return laengeDesMinimalenSpannbaumes;
	}

	// Schritt um die Kanten des minimalen Spannbaumes zu verdoppeln
	public Graph<String, WeightedEdge> duplicateEdgesForEulerTour(Graph<String, WeightedEdge> graph) {
		for (WeightedEdge edge : kantengewichtungenDesMinimalenSpannbaumes) {
			graph.addEdge(edge.getSource(), edge.getTarget()).setWeight(edge.getWeight());
		}
		return graph;
	}

	// ------------------------------------------------------------------------------------------------------------------------------------------

	// Code Uebernahme fuer den Minimalenspannbaum
	// https://code.google.com/p/osgified/source/browse/org.osgified.org.jgrapht/src/main/java/org/jgrapht/alg/KruskalMinimumSpanningTree.java?r=8de44c01acf0bd50a3fa4b1cb882024a4354ec08
	private class KruskalMinimumSpanningTree<V, E> {
		// ~ Instance fields
		// --------------------------------------------------------

		private double spanningTreeCost;
		private Set<E> edgeList;

		// ~ Constructors
		// -----------------------------------------------------------

		/**
		 * Creates and executes a new KruskalMinimumSpanningTree algorithm
		 * instance. An instance is only good for a single spanning tree; after
		 * construction, it can be accessed to retrieve information about the
		 * spanning tree found.
		 * 
		 * @param graph
		 *            the graph to be searched
		 */
		public KruskalMinimumSpanningTree(final Graph<V, E> graph) {
			UnionFind<V> forest = new UnionFind<V>(graph.vertexSet());
			ArrayList<E> allEdges = new ArrayList<E>(graph.edgeSet());
			Collections.sort(allEdges, new Comparator<E>() {
				public int compare(E edge1, E edge2) {
					return Double.valueOf(graph.getEdgeWeight(edge1)).compareTo(graph.getEdgeWeight(edge2));
				}
			});

			spanningTreeCost = 0;
			edgeList = new HashSet<E>();

			for (E edge : allEdges) {
				V source = graph.getEdgeSource(edge);
				V target = graph.getEdgeTarget(edge);
				if (forest.find(source).equals(forest.find(target))) {
					continue;
				}

				forest.union(source, target);
				edgeList.add(edge);
				spanningTreeCost += graph.getEdgeWeight(edge);
			}
		}

		// ~ Methods
		// ----------------------------------------------------------------

		/**
		 * Returns the edges making up the tree found.
		 * 
		 * @return Set of Edges
		 */
		public Set<E> getEdgeSet() {
			return edgeList;
		}

		/**
		 * Returns the cost of the minimum spanning tree or forest.
		 * 
		 * @return Cost of the spanning tree
		 */
		public double getSpanningTreeCost() {
			return spanningTreeCost;
		}
	}

	public static class MarkedVertex {
		public String current;
		public double gewicht;
		public String vorgaenger;

		@Override
		public String toString() {
			return "MarkedVertex [name=" + current + ", gewicht=" + gewicht + ", vorgaenger=" + vorgaenger + "]";
		}

	}

	private Collection<String> getNeighbors(Graph<String, WeightedEdge> graph, String node) {
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
		} catch (Exception e) {
			return new ArrayList<String>();
		}
	}

	public int myRandom(int low, int high) {
		return (int) (Math.random() * (high - low) + low);
	}

}
