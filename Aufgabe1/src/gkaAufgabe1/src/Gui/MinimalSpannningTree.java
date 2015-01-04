package Gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.alg.util.UnionFind;

public class MinimalSpannningTree {
	Map<String, Map<String, Double>> minimalSpanningTree = new HashMap<String, Map<String, Double>>();
	Set<WeightedEdge> kantengewichtungenDesMinimalenSpannbaumes;

	public double mstHeuristik(Graph<String, WeightedEdge> graph, String startKnoten) {
		List<String> listeMitBesuchtenKnotenFuerTour = new ArrayList<>();
		List<WeightedEdge> listeMitBesuchtenKantenFuerTour = new ArrayList<>();
		List<WeightedEdge> listeMitAllenKantenDesMinimalenSpannbaumesVerdoppelt = new ArrayList<>();

		// set minimal spanning tree and duplicate all edges of this minimal
		// spanning tree
		minimalSpanningTree(graph);
		Graph<String, WeightedEdge> neuerGraph = duplicateEdgesForEulerTour(graph);

		// Here starts initialization
		// create new set, because we don't want to change elements in
		// kantengewichtungenDesMinimalenSpannbaumes
		Set<WeightedEdge> neuerSet = new HashSet<>();
		neuerSet.addAll(kantengewichtungenDesMinimalenSpannbaumes);

		while (!(listeMitAllenKantenDesMinimalenSpannbaumesVerdoppelt.size() == kantengewichtungenDesMinimalenSpannbaumes.size() * 2)) {
			listeMitAllenKantenDesMinimalenSpannbaumesVerdoppelt.add(neuerSet.iterator().next());
			listeMitAllenKantenDesMinimalenSpannbaumesVerdoppelt.add(neuerSet.iterator().next());
			neuerSet.remove(neuerSet.iterator().next());
		}

		String currentVertex = startKnoten;
		String vorgaenger = "";
		while (!(listeMitBesuchtenKantenFuerTour.size() == kantengewichtungenDesMinimalenSpannbaumes.size() * 2)) {


			boolean einWegWurdeGenutzt = false;
			for (WeightedEdge edge : neuerGraph.edgesOf(currentVertex)) {
				System.out.println("Edges OF CurrentVertex  " + currentVertex  + " : " + neuerGraph.edgesOf(currentVertex));
				// Man startet bei einem Knoten und möchte eine tour gehen
				// hierbei wird dann abgefragt ob die kante denn auch in dem
				// minimalen spannbaum enthalten war
				// denn so kann erst einmal sichergestellt werden dass die tour
				// über die verdopplung der kanten
				// vollbracht wird und es eine rundreise gibt.


				// Edge should be into the minimal spanning tree and target
				// vertex can't be the vorgaenger
				if (listeMitAllenKantenDesMinimalenSpannbaumesVerdoppelt.contains(edge) && !(edge.getTarget().equals(vorgaenger)) && !(edge.getSource().equals(vorgaenger))
						&& einWegWurdeGenutzt == false) {
					// TODO Abfrage vllt so gestalten, dass wenn der knoten noch
					// einen nachfolger hat soll man noch
					// nicht zurueck zu dem vorgaengerknoten gehen

					// Vorgaenger bestimmen, um sicherzugehen, dass man nicht
					// direkt zu dem vorgaengerknoten geht.
					vorgaenger = currentVertex;
					// damit der algorithmus weiter seine tour sucht den current
					// auf den target vertex setzen.
					currentVertex = edge.getTarget();

					// gefundene Kante in eine Liste tun fuer das ergebnis
					// spaeter bzw zum aendern wegen der
					// dreiecksgleichung -> vielleicht noch irgendwie ordnen, so
					// dass man kontrollieren
					// kann, wie der weg passiert wurde
					listeMitBesuchtenKantenFuerTour.add(edge);

					// set this boolean to safe, that only 1 way will be passed
					einWegWurdeGenutzt = true;
					// remove edge from listofmultiplyvertices
					listeMitAllenKantenDesMinimalenSpannbaumesVerdoppelt.remove(edge);
				} 
					
					// TODO Weitere if abfrage, die den fall abfaengt, wenn vom
					// Knoten keine kanten zu dem
					// Minimalen spannbaum gehoeren ( Sackgasse ) dann muss mann
					// zurueck zum vorgaenger!
					Set<WeightedEdge> setUmAufSackgasseZuPruefen = neuerGraph.edgesOf(currentVertex);
					List<WeightedEdge> listeMitZuPruefendenKnoten = new ArrayList<>();
					for (WeightedEdge weightedEdge : setUmAufSackgasseZuPruefen) {
						if(kantengewichtungenDesMinimalenSpannbaumes.contains(weightedEdge)) {
							listeMitZuPruefendenKnoten.add(weightedEdge);
						}
					}

					if (listeMitZuPruefendenKnoten.size() == 1 && currentVertex != startKnoten) {
						listeMitBesuchtenKantenFuerTour.add(edge);
						
						// set this boolean to safe, that only 1 way will be passed
						einWegWurdeGenutzt = true;
						// remove edge from listofmultiplyvertices
						listeMitAllenKantenDesMinimalenSpannbaumesVerdoppelt.remove(edge);
						// Tauschen der beiden knoten weil wir in einer sackgasse waren
						currentVertex = vorgaenger;
						vorgaenger = "";
					}
				

			}
		}

		return 0;
	}

	// Berechnung des minimalen Spannbaumes
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
			// graph.addEdge(edge.getSource(), edge.getTarget(), edge);
			graph.addEdge(edge.getSource(), edge.getTarget()).setWeight(edge.getWeight());
		}
		return graph;
	}

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

	// End KruskalMinimumSpanningTree.java

}
