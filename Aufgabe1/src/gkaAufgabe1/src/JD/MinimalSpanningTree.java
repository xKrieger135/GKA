package JD;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.alg.KruskalMinimumSpanningTree;

import Gui.WeightedEdge;

public class MinimalSpanningTree {
	private KruskalMinimumSpanningTree<String, WeightedEdge> SpannBaum;
	//private List<String> DurchlaufeneKnoten =new ArrayList();
	private List<String> VertexList = new ArrayList<>();
	private List<String> Tour = new ArrayList();
	private Double TourCost = 0.0;
	
	public double MinimalSpanningTree(Graph<String, WeightedEdge> graph) {
		
		//Vertexe Laden
		VertexList.addAll(graph.vertexSet());
		// Startpunkt setzen
		//Tour.add((VertexList).get(0));
		
		
		// Spannbaum erstellen
		SpannBaum = new KruskalMinimumSpanningTree<String, WeightedEdge>(
				graph);
		// Spannbaum durchlaufen und bei Problemen neue Wege finden
		//String current = Tour.get(0);
		
		for(WeightedEdge Edge : SpannBaum.getMinimumSpanningTreeEdgeSet()) {
			// Auskommentierter Block enthielt die Idee eines Aktuellen Elementes,
			// Diese Idee habe ich wieder verworfen da es ein Glücksfall wäre wirklich in einer Reihenfolge durch zu gehen
			
			/*if (Edge.getSource() == current && !Tour.contains(Edge.getTarget())) {
				// Kante von current nach neuem Knoten
				TourCost += Edge.getWeight();
				current=Edge.getTarget();
			} else if (Edge.getTarget() == current && !Tour.contains(Edge.getSource())) {
				// Kante von neuem Knoten nach current
				// TODO
				System.out.println("Aktueller K");
			} else*/ if (Tour.contains(Edge.getSource()) && !Tour.contains(Edge.getTarget())) {
				// Kantenanfang bekannt, Target bisher nicht
				// TODO
			} else if (Tour.contains(Edge.getTarget()) && !Tour.contains(Edge.getSource())) {
				// Kantenende bekannt, Anfang bisher nicht
				// TODO
			} else {
				// Sowohl Anfang, als auch Ende bisher unbekannt
				if (Tour.isEmpty()) {
					Tour.add(Edge.getSource());
					Tour.add(Edge.getTarget());
					TourCost += Edge.getWeight();
				}
			}
		}
		return 0.0;
	}

}
