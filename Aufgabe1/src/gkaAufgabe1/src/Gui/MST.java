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
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.EulerianCircuit;
import org.jgrapht.alg.util.UnionFind;
import org.jgrapht.graph.DirectedWeightedPseudograph;
import org.jgrapht.graph.Pseudograph;

public class MST {
	Map<String, Map<String, Double>> minimalSpanningTree = new HashMap<String, Map<String, Double>>();
	Set<WeightedEdge> kantengewichtungenDesMinimalenSpannbaumes;

	public double mstHeuristik(Graph<String, WeightedEdge> graph,
			String startKnoten) {
		// vllt als queue um eine reihenfolge herauszubekommen
		 List<String> listeMitBesuchtenKnoten = new ArrayList<>();
		Queue<String> queueMitBesuchtenKnoten = new LinkedList<>();
		List<WeightedEdge> listeMitBesuchtenKanten = new ArrayList<>();
		List<WeightedEdge> listeMitKantenDesMSTVerdoppelt = new ArrayList<>();
		double ergebnis = 0;

		// set minimal spanning tree and duplicate all edges of this minimal
		// spanning tree
		minimalSpanningTree(graph);
		Graph<String, WeightedEdge> eulerGraph = duplicateEdgesForEulerTour(graph);

		// Here starts initialization
		// create new set, because we don't want to change elements in
		// kantengewichtungenDesMinimalenSpannbaumes
		Set<WeightedEdge> neuerSet = new HashSet<>();
		neuerSet.addAll(kantengewichtungenDesMinimalenSpannbaumes);

		while (!(listeMitKantenDesMSTVerdoppelt.size() == kantengewichtungenDesMinimalenSpannbaumes.size() * 2)) {
			listeMitKantenDesMSTVerdoppelt.add(neuerSet.iterator().next());
			listeMitKantenDesMSTVerdoppelt.add(neuerSet.iterator().next());
			neuerSet.remove(neuerSet.iterator().next());
		}
		
		//-------------------------------------------------------------------------------------------
		// Fuer testzwecke mit dem doppelten Minimalen Spannbaum
		double erg = 0;
		for (int i = 0; i < listeMitKantenDesMSTVerdoppelt.size(); i++) {
			erg = erg + listeMitKantenDesMSTVerdoppelt.get(i).getWeight();
			System.out.println("Ergebnis vorher = " + erg);
		}
		System.out.println("-----------------------------------------Ergebnis--------------------------------------" + erg);
		//-------------------------------------------------------------------------------------------

		String current = startKnoten;
		String vorgaenger = null;
		boolean eineKanteWurdeUeberquert = true;

		while (!(listeMitBesuchtenKnoten.size() == graph.vertexSet().size())) {
			listeMitBesuchtenKnoten.add(current);
			queueMitBesuchtenKnoten.add(current);
			eineKanteWurdeUeberquert = false;

			Set<WeightedEdge> edgesOf = eulerGraph.edgesOf(current);
			for (WeightedEdge edge : edgesOf) {
				String source = edge.getSource();
				System.out.println("Source = " + source);
				String target = edge.getTarget();
				System.out.println("Target = " + target);

				// prueft, dass man nicht direkt bei einer gefundenen Kante den
				// Weg wieder zurueck geht.
				boolean nichtDirektDieKanteZurueckGehen = (!(edge.getSource().equals(vorgaenger)) || !(edge.getTarget().equals(vorgaenger)));
				// nicht zwei mal durch den gleich knoten laufen
				System.out.println("==== : " + !listeMitBesuchtenKnoten.contains(source));
				System.out.println("==== : " + !listeMitBesuchtenKnoten.contains(target));
				if (!listeMitBesuchtenKnoten.contains(source) || !listeMitBesuchtenKnoten.contains(target)) {

					// schauen ob die Kante im spannbaum liegt
					// oder im Graphen und einfach aus dem Graphen entfernen
					if (kantengewichtungenDesMinimalenSpannbaumes.contains(edge) && nichtDirektDieKanteZurueckGehen && eineKanteWurdeUeberquert == false) {
						if (source != current) {
							vorgaenger = current;
							current = source;
							eineKanteWurdeUeberquert = true;
//							listeMitKantenDesMSTVerdoppelt.remove(edge);
							listeMitBesuchtenKanten.add(edge);
							break;
						} else if (target != current) {
							vorgaenger = current;
							current = target;
							eineKanteWurdeUeberquert = true;
//							listeMitKantenDesMSTVerdoppelt.remove(edge);
							listeMitBesuchtenKanten.add(edge);
							break;
						} else {
							System.out
									.println("Der Else Fall soll nichts machen.");
						}
					}
				}
				// wenn es einen gleichen knoten gibt der noch nicht besucht
				// wurde zu den vorgaenger und current k�nnen
				// dreiecksbeziehung hier
				String habenGleichenTargetKnotenDerNochNichtBesuchtWurde = habenGleicheTargetKnotenDerNochNichtBesuchtWurde(eulerGraph, current, vorgaenger,listeMitBesuchtenKnoten);
				if (habenGleichenTargetKnotenDerNochNichtBesuchtWurde != null && (!listeMitBesuchtenKnoten.contains(target) || !listeMitBesuchtenKnoten.contains(source))) {

					vorgaenger = current;
					current = habenGleichenTargetKnotenDerNochNichtBesuchtWurde;
					eineKanteWurdeUeberquert = true;
					WeightedEdge neueEdge = eulerGraph.getEdge(vorgaenger, current);
					listeMitBesuchtenKanten.add(neueEdge);
					break;
				}

				// List<String> listeMitNichtbesuchtenNachbarn =
				// direkteverbindung(eulerGraph, current,
				// listeMitBesuchtenKnoten);
				List<String> listeMitNichtbesuchtenNachbarn = direkteverbindung(eulerGraph, current, listeMitBesuchtenKnoten);
				if (listeMitNichtbesuchtenNachbarn.size() == 1) {
					 String newCurrent = listeMitNichtbesuchtenNachbarn.get(0);
					vorgaenger = current;
					current = newCurrent;
					eineKanteWurdeUeberquert = true;
					break;
				}
				
				if(listeMitBesuchtenKnoten.size() == eulerGraph.vertexSet().size()) {
//				if((edge.getSource() == startKnoten && edge.getTarget() == current) || (edge.getSource() == current && edge.getTarget() == startKnoten)) {
					vorgaenger = current;
					current = startKnoten;
					break;
				}

			}
		}
		
		queueMitBesuchtenKnoten.add(current);

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

	// Berechnung des minimalen Spannbaumes
	public double minimalSpanningTree(Graph<String, WeightedEdge> graph) {
		// Berechnet den minimalen Spannbaum eines Graphen G = (V, E)
		KruskalMinimumSpanningTree<String, WeightedEdge> kruskalMinimumSpanningTree = new KruskalMinimumSpanningTree<String, WeightedEdge>(
				graph);

		// get length of minimal spanning tree
		double laengeDesMinimalenSpannbaumes = kruskalMinimumSpanningTree.getSpanningTreeCost();
		System.out.println("Laenge des minimalen Spannbaumes : " + laengeDesMinimalenSpannbaumes);

		// get edges from minimal spanning tree
		kantengewichtungenDesMinimalenSpannbaumes = kruskalMinimumSpanningTree
				.getEdgeSet();
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
				minimalSpanningTree.put(edgeSource,	leereMapFuerTargetUndKantengewichtung);
				System.out.println("Minimaler Spannbaum : "	+ minimalSpanningTree);

				minimalSpanningTree.get(edgeSource).put(edgeTarget,	weightedEdge.getWeight());
			} else {
				// if our map contains this vertex we will put all other
				// neighbors of that vertex into the map with
				// this source vertex
				minimalSpanningTree.get(edgeSource).put(edgeTarget,	weightedEdge.getWeight());
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

	// hier wird der neue Currentvertex herausgeholt und es wird vermieden
	// zweimal den gleichen knoten zu besuchen
	private String benutzeAndereKanteWennVertexSchonBesuchtWurde(Graph<String, WeightedEdge> graph, String current,	String vorgaenger, List<String> listeMitBesuchtenKnoten) {
		String zielknoten = habenGleicheTargetKnotenDerNochNichtBesuchtWurde(graph, current, vorgaenger, listeMitBesuchtenKnoten);
		return zielknoten;
	}

	// Soll einen Knoten zurueckgeben, der von den beiden betroffenen Knoten
	// erreicht werden kann.
	// Dreiecksbeziehung ueber den spannbaum --> nicht besuchter knoten
	private String habenGleicheTargetKnotenDerNochNichtBesuchtWurde(Graph<String, WeightedEdge> graph, String current,String vorgaenger, List<String> listeMitBesuchtenKnoten) {
		Collection<String> nachbarnVonCurrent = getNeighbors(graph, current);
		System.out.println("Nachbarn von Current : " + nachbarnVonCurrent);

		Collection<String> nachbarnVonVorgaenger = getNeighbors(graph,
				vorgaenger);
		System.out
				.println("Nachbarn von vorgaenger : " + nachbarnVonVorgaenger);

		Set<String> gleicheKnoten = new HashSet<>();
		// alle nachbarn der beiden Knoten in den Set tun
		gleicheKnoten.addAll(nachbarnVonCurrent);
		gleicheKnoten.addAll(nachbarnVonVorgaenger);
		// entferne alle knoten, die nicht in der jeweiligen Liste auch
		// vorkommen
		gleicheKnoten.retainAll(nachbarnVonVorgaenger);
		gleicheKnoten.retainAll(nachbarnVonCurrent);

		String zielknoten = null;
		// setze den neuen Knoten nur wenn er nicht in den bereits besuchten
		// knoten vorhanden ist
		for (String knoten : gleicheKnoten) {
			if (!listeMitBesuchtenKnoten.contains(knoten)) {
				zielknoten = knoten;
			}
		}

		return zielknoten;
	}

	// Wenn es nur noch einen knoten gibt zu dem man kann direkte verbindung
	// nutzen
	private List<String> direkteverbindung(Graph<String, WeightedEdge> graph,String current, List<String> listeMitBesuchtenKnoten) {
		// List<String> knotenMitDirekterverbindung = new ArrayList<>();
		List<String> knotenMitDirekterverbindung = new LinkedList<>();

		Collection<String> nachbarKnotenVonCurrent = getNeighbors(graph,
				current);
		for (String string : nachbarKnotenVonCurrent) {
			if (!listeMitBesuchtenKnoten.contains(string)) {
				knotenMitDirekterverbindung.add(string);
			}
		}

		return knotenMitDirekterverbindung;
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
					return Double.valueOf(graph.getEdgeWeight(edge1))
							.compareTo(graph.getEdgeWeight(edge2));
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
			return "MarkedVertex [name=" + current + ", gewicht=" + gewicht
					+ ", vorgaenger=" + vorgaenger + "]";
		}

	}

	private Collection<String> getNeighbors(Graph<String, WeightedEdge> graph,String node) {
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

	// End KruskalMinimumSpanningTree.java

}
