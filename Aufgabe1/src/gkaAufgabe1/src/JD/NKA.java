package JD;

import java.util.ArrayList;

import org.jgrapht.Graph;
import org.jgrapht.graph.Pseudograph;

import Gui.WeightedEdge;

public class NKA {
	// Nächster Knoten Algorithmus
	private static ArrayList<String> Knoten = new ArrayList();
	private static Graph<String, WeightedEdge> myGraph;
	public static double NKA(Graph<String, WeightedEdge> graph, String source) {
		myGraph=graph; // editierbare Copie
		
		System.out.println("NKA gestartet");
		System.out.println("Starte bei Knoten: "+source);
		String next =getNearest(myGraph, source);
		System.out.println(source+" -> "+next);
		Knoten.add(next);
		myGraph.removeEdge(source, next);
		searchDeep(next,source); // Lösche Kante damit sie nicht wieder auftaucht
		
		return Knoten.size();
		
	}
	private static void searchDeep( String current, String end) {
		if (current== end) return;
		//if (myGraph.)
		String next =getNearest(myGraph, current);
		System.out.println(current+" -> "+next);
		Knoten.add(next);
		myGraph.removeEdge(current, next);
		searchDeep(next,current); // Lösche Kante damit sie nicht wieder auftaucht
	}
	private static String getNearest(Graph<String, WeightedEdge> graph,String source) {
		
		double minDis=Double.POSITIVE_INFINITY;
		String minVertex = null;
		
		for (String Elem:graph.vertexSet()) {
			if ((minDis==Double.POSITIVE_INFINITY || Elem==source)) {
				//System.out.println("In");
				if (graph.containsEdge(source, Elem)) {
					//System.out.println("Edge found "+source+" - "+Elem);
					if (minDis>graph.getEdgeWeight(graph.getEdge(source, Elem))) {
						//System.out.println("Is smaller");
						minDis=graph.getEdgeWeight(graph.getEdge(source, Elem));
						minVertex=Elem;
					}
				}
			}
		}
		
		
		return minVertex;
		
	}
	
	public static void main(String[] args) {
		Graph<String, WeightedEdge> h = new Pseudograph<String, WeightedEdge>(WeightedEdge.class);
		h.addVertex("V0");
		for (int i = 1; i <= 10; i++) {
			h.addVertex("V"+i);
			h.addEdge("V"+(i-1), "V"+(i));
			h.getEdge("V"+(i-1), "V"+(i)).setWeight(1);
			
		}
		
		System.out.println(h.vertexSet().toString());
		h.addEdge("V0", "V10");
		h.getEdge("V0", "V10").setWeight(20);
		System.out.println("NKA:"+NKA(h,"V0"));
	}

}
