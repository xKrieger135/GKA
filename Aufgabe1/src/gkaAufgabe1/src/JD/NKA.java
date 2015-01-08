package JD;

import java.util.ArrayList;

import org.jgrapht.Graph;
import org.jgrapht.graph.Pseudograph;

import Gui.WeightedEdge;

public class NKA {
	// Nächster Knoten Algorithmus
	private boolean error = false;
	private ArrayList<String> Knoten = new ArrayList();
	private Graph<String, WeightedEdge> myGraph;

	public double NKA(Graph<String, WeightedEdge> graph, String source) {
		init();
		myGraph = graph; // editierbare Copie
		Knoten.add(source);
		System.out.println("NKA gestartet");
		System.out.println("Starte bei Knoten: " + source);
		String next = getNearest(myGraph, source);
		if (next!=null) {
		System.out.println(source + " -> " + next);
		Knoten.add(next);
		myGraph.removeEdge(source, next);
		searchDeep(next, source); // Lösche Kante damit sie nicht wieder
									// auftaucht
		} else{error=true;}
		if (!error) {
			return Knoten.size();
		} else
			System.out.println("Error" );
			return -0.0;

	}

	private void init() {
		// TODO Auto-generated method stub
		boolean error = false;
		ArrayList<String> Knoten = new ArrayList();
		myGraph = new Pseudograph<>(WeightedEdge.class);
		
	}

	private void searchDeep(String current, String end) {
		if (current == end)
			return;
		// if (myGraph.)
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@     " + current
				+ " | " + end);
		String next = getNearest(myGraph, current);
		if (next == null) {
			error = true;
			return;
		}
		// TODO null fehler beheben
		System.out.println(current + " -> " + next);
		Knoten.add(next);
		myGraph.removeEdge(current, next);
		if (Knoten.size()==myGraph.vertexSet().size()) return;
		searchDeep(next, current); // Lösche Kante damit sie nicht wieder
									// auftaucht
	}

	private String getNearest(Graph<String, WeightedEdge> InGraph, String source) {

		double minDis = Double.POSITIVE_INFINITY;
		String minVertex = null;
		// System.out.println(graph.vertexSet().toString());
		for (String Elem : InGraph.vertexSet()) {
			if (!(Elem == source) && !(Knoten.contains(Elem))) {
				// System.out.println("In");
				if (InGraph.containsEdge(source, Elem)) {
					// System.out.println("Edge found "+source+" - "+Elem);
					if (minDis > InGraph.getEdgeWeight(InGraph
							.getEdge(source, Elem))) {
						// System.out.println("Is smaller");
						minDis = InGraph.getEdgeWeight(InGraph
								.getEdge(source, Elem));
						minVertex = Elem;
					}
				}
			} 		}

		return minVertex;

	}

	// public void main(String[] args) {
	// Graph<String, WeightedEdge> h = new Pseudograph<String,
	// WeightedEdge>(WeightedEdge.class);
	// h.addVertex("V0");
	// for (int i = 1; i <= 10; i++) {
	// h.addVertex("V"+i);
	// h.addEdge("V"+(i-1), "V"+(i));
	// h.getEdge("V"+(i-1), "V"+(i)).setWeight(1);
	//
	// }
	//
	// System.out.println(h.vertexSet().toString());
	// h.addEdge("V0", "V10");
	// h.getEdge("V0", "V10").setWeight(20);
	// System.out.println("NKA:"+NKA(h,"V0"));
	// }

}
