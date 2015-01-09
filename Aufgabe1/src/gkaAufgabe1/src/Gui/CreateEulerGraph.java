package Gui;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.alg.NeighborIndex;
import org.jgrapht.graph.DirectedWeightedPseudograph;
import org.jgrapht.graph.Pseudograph;

public class CreateEulerGraph {
	private Graph<String, WeightedEdge> eulerscherGraph = new Pseudograph<String, WeightedEdge>(WeightedEdge.class);

	public Graph<String, WeightedEdge> createEulerGraph() {
		List<String> listeMitKnoten = new ArrayList<>();

		for (int i = 1; i < 9; i++) {
			listeMitKnoten.add("v" + i);
			eulerscherGraph.addVertex("v" + i);
		}
		System.out.println("---------------------------------------------------------------------------");
		System.out.println();
		System.out.println("Liste mit Knoten : " + listeMitKnoten);
		System.out.println();
		System.out.println("---------------------------------------------------------------------------");
		System.out.println();
		System.out.println("Eulerscher Graph mit den Knoten : " + eulerscherGraph);
		System.out.println();
		System.out.println("---------------------------------------------------------------------------");

		List<Integer> listeMitAnzahlDerkanten = new ArrayList<>();
		listeMitAnzahlDerkanten = additionEinesWertes(listeMitKnoten.size());
		List<String> zwischenListe = new ArrayList<>();
		zwischenListe.addAll(listeMitKnoten);
		
		String vorgaenger = null;
		for (String vertex : listeMitKnoten) {
			
			for (int i = 0; i < listeMitKnoten.size(); i++) {
				// Addition der Knoten um die kanten des gesamten Graphen heraus
				// zu bekommen
				int randomWeight = listeMitAnzahlDerkanten.get(myRandom(0, listeMitAnzahlDerkanten.size() - 1));
				System.out.println("RandomWeight = " + randomWeight);
				WeightedEdge weightedEdge = new WeightedEdge();
				weightedEdge.setWeight(randomWeight);
				
				if (!listeMitKnoten.get(i).equals(vertex) && !eulerscherGraph.containsEdge(vertex, listeMitKnoten.get(i))) {
					eulerscherGraph.addEdge(vertex, listeMitKnoten.get(i), weightedEdge);
				}
				
				if (i == zwischenListe.size()) {
					zwischenListe.remove(vertex);
					listeMitAnzahlDerkanten.remove(randomWeight);
				}
				
				if (vorgaenger != null && !listeMitKnoten.get(i).equals(vertex)) {
					Collection<String> neighbors = getNeighbors(eulerscherGraph, vorgaenger);	
					WeightedEdge direkteEdgeZuNeuemknoten = eulerscherGraph.getEdge(vorgaenger, listeMitKnoten.get(i));
					if (eulerscherGraph.containsEdge(direkteEdgeZuNeuemknoten)) {
						WeightedEdge vorgaengerZuVertexEdge = eulerscherGraph.getEdge(vorgaenger, vertex);
						WeightedEdge neuHinzugefuegteEdge = eulerscherGraph.getEdge(vertex, listeMitKnoten.get(i));
						double ergebnisDerVerbindungUeberZweiKnoten = vorgaengerZuVertexEdge.getWeight() + neuHinzugefuegteEdge.getWeight();
//						if (ergebnisDerVerbindungUeberZweiKnoten < direkteEdgeZuNeuemknoten.getWeight()) {
							// Hier wird die Gewichtung gerade gezogen
							while (ergebnisDerVerbindungUeberZweiKnoten < direkteEdgeZuNeuemknoten.getWeight()) {
								WeightedEdge neueEdge1 = new WeightedEdge();
								neueEdge1.setWeight(neuHinzugefuegteEdge.getWeight() + 1);
								eulerscherGraph.removeEdge(vertex, listeMitKnoten.get(i));
								eulerscherGraph.addEdge(vertex, listeMitKnoten.get(i), neueEdge1);
								System.out.println("Neueedge 1 = " + neueEdge1.getWeight());
								
								WeightedEdge neueEdge2 = new WeightedEdge();
								neueEdge2.setWeight(vorgaengerZuVertexEdge.getWeight() + 1);
								eulerscherGraph.removeEdge(vertex, vorgaenger);
								eulerscherGraph.addEdge(vertex, vorgaenger, neueEdge2);
								ergebnisDerVerbindungUeberZweiKnoten = ergebnisDerVerbindungUeberZweiKnoten + 2;
								System.out.println("NeueEdge 2 = " + neueEdge2.getWeight());
								
								
							}
//						}
					}
				}
				

			}
			vorgaenger = vertex;
		}
		Graph<String, WeightedEdge> neuerEulerGraph = ueberpruefeGraphAufDreiecksBeziehung(eulerscherGraph);

//		return eulerscherGraph;
		return neuerEulerGraph;
	}
	
	private Graph<String, WeightedEdge> ueberpruefeGraphAufDreiecksBeziehung(Graph<String, WeightedEdge> eulerGraph) {
		
		for (String currentVertex : eulerGraph.vertexSet()) {
			Set<String> listeMitVertexen = eulerGraph.vertexSet();
			//Dieses Set erstellen, weil das vorige unmodifiable ist und alle vertexe uebertragen
			Set<String> listeOhneCurrent = new HashSet<>();
			listeOhneCurrent.addAll(listeMitVertexen);
			listeOhneCurrent.remove(currentVertex);
			Iterator<String> listeOhneCurrentIterator = listeOhneCurrent.iterator();
		while (listeOhneCurrentIterator.hasNext()) {
			String zielVertex = listeOhneCurrentIterator.next();
			
			listeOhneCurrentIterator.remove();
			for (String vorgaengerVertex : listeOhneCurrent) {
				double gewichtVonCurrentZuZiel       = eulerGraph.getEdge(currentVertex, zielVertex).getWeight();
				double gewichtVonCurrentZuVorgaenger = eulerGraph.getEdge(currentVertex, vorgaengerVertex).getWeight();
				double gewichtVonVorgaengerZuZiel    = eulerGraph.getEdge(vorgaengerVertex, zielVertex).getWeight();
				
				double wegUeberVorgaengerNachCurrentZuZiel = gewichtVonCurrentZuVorgaenger + gewichtVonCurrentZuZiel;
				while (wegUeberVorgaengerNachCurrentZuZiel < gewichtVonVorgaengerZuZiel) {
					WeightedEdge neueEdge1 = new WeightedEdge();
					neueEdge1.setWeight(gewichtVonCurrentZuVorgaenger + 1);
					eulerGraph.removeEdge(currentVertex, vorgaengerVertex);
					eulerGraph.addEdge(currentVertex, vorgaengerVertex, neueEdge1);
					System.out.println("Neueedge 1 = " + neueEdge1.getWeight());
					
					WeightedEdge neueEdge2 = new WeightedEdge();
					neueEdge2.setWeight(gewichtVonCurrentZuZiel + 1);
					eulerGraph.removeEdge(currentVertex, zielVertex);
					eulerGraph.addEdge(currentVertex, zielVertex, neueEdge2);
					System.out.println("NeueEdge 2 = " + neueEdge2.getWeight());
					wegUeberVorgaengerNachCurrentZuZiel += 2;
				}
			}
		}
//			for (String zielVertex : listeOhneCurrent) {
//			}
		}
		
		return eulerGraph;
	}


	public int myRandom(int low, int high) {
		return (int) (Math.random() * (high - low) + low);
	}

	public static void main(String[] args) {
		CreateEulerGraph ceg = new CreateEulerGraph();
		ceg.createEulerGraph();
		String x = "a";
		ceg.writeEulerGraphIntoFile();
		ceg.generateCodeForunitTest();
		System.out.println("Gesamter Euler Graph nach Ausführung der Methode : " + ceg.createEulerGraph());
		System.out.println("eulerGraph.addVertex" + "(\"" + x + "\");");
		System.out.println("Fak : " + ceg.additionEinesWertes(5));
	}

	public void writeEulerGraphIntoFile() {
		Graph<String, WeightedEdge> graph = createEulerGraph();
		BufferedWriter writer = null;
		PrintWriter pWriter = null;

		try {
			pWriter = new PrintWriter(new BufferedWriter(new FileWriter(
					"C:/Users/patrick_steinhauer/HAW/Semester3/GKA/Praktikum/Aufgabe 1/GKA/Aufgabe1/Beispielgraphen/Neue_Graphen/graphEuler.gka")));
//		            "C:/Users/Paddy-Gaming/HAW/Semester3/GKA/graphEuler.gka")));
			// writer = new BufferedWriter(new FileWriter("graphx.gka"));
			String pfeil;
			if (graph instanceof DirectedWeightedPseudograph) {
				pfeil = "->";
			} else {
				pfeil = "--";
			}
			Set<String> nodes = new HashSet<String>();
			for (WeightedEdge e : graph.edgeSet()) {
				pWriter.println(e.getSource() + " " + pfeil + " " + e.getTarget() + " : " + (int) e.getWeight() + ";");
				nodes.add(e.getSource());
				nodes.add(e.getTarget());
			}
			Set<String> vertexSet = new HashSet<String>(graph.vertexSet());
			vertexSet.removeAll(nodes);
			for (String n : vertexSet) {
				System.out.println("xxxx" + n);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Datei wurde nicht gefunden!");
		} finally {
			if (pWriter != null) {
				pWriter.flush();
				pWriter.close();
			}
		}

	}

	private void generateCodeForunitTest() {
		Graph<String, WeightedEdge> neuerEulerGraph = createEulerGraph();
		PrintWriter pWriter = null;

		try {
			pWriter = new PrintWriter(new BufferedWriter(new FileWriter(
					"C:/Users/patrick_steinhauer/HAW/Semester3/GKA/Praktikum/Aufgabe 1/GKA/Aufgabe1/Beispielgraphen/Neue_Graphen/unitTestGeneration.txt")));
//		"C:/Users/Paddy-Gaming/HAW/Semester3/GKA/unitTestGeneration.txt")));

			for (String vertex : neuerEulerGraph.vertexSet()) {
				pWriter.println("eulerGraph.addVertex" + "(\"" + vertex + "\");");
			}

			for (WeightedEdge edge : neuerEulerGraph.edgeSet()) {
				pWriter.println("addEdge(" + "eulerGraph, \"" + edge.getSource() + "\", \"" + edge.getTarget() + "\", " + edge.getWeight() + ");");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Datei wurde nicht gefunden!");
		} finally {
			if (pWriter != null) {
				pWriter.flush();
				pWriter.close();
			}
		}
	}

	private List<Integer> additionEinesWertes(int n) {
		int ergebnis = 0;
		List<Integer> anzahlDerKanten = new ArrayList<>();

		while (n > 0) {
			ergebnis = ergebnis + n;
			n = n - 1;
		}

		for (int i = 0; i < ergebnis; i++) {
			anzahlDerKanten.add(ergebnis - i);
		}

		return anzahlDerKanten;
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
	
	

}
