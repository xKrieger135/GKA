package Gui;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DirectedWeightedPseudograph;

public class FordFulkerson {
	Map<String, Map<String, Double>> kapazitaetenDesNetzwerkes = new HashMap<String, Map<String, Double>>();
	Map<String, Map<String, Double>> fluesseDesNetzwerkes = new HashMap<String, Map<String, Double>>();

	public double fordFulkersonAlgorithmus(Graph<String, WeightedEdge> graph, String quelle, String senke) {

		for (String v : graph.vertexSet()) {
			// hinterer Teil der geschachtelten Map welcher die Kapazität
			// enthalten soll oder den Teil für die Flusseinheit
			if (!kapazitaetenDesNetzwerkes.containsKey(v)) {
				Map<String, Double> kapazitaet = new HashMap<>();
				Map<String, Double> fluss = new HashMap<>();
				kapazitaetenDesNetzwerkes.put(v, kapazitaet);
				fluesseDesNetzwerkes.put(v, fluss);
			}
			for (String neighbor : getNeighbors(graph, v)) {
				// Setze Kantengewichtung bzw. die flusseinheit
				WeightedEdge edge = graph.getEdge(v, neighbor);
				kapazitaetenDesNetzwerkes.get(v).put(neighbor, edge.getWeight());
				fluesseDesNetzwerkes.get(v).put(neighbor, 0d);
			}
		}
		// Meldung zum visualisieren, dass die Maps initialisiert wurden
		System.out.println("initialized");

		// Abfrage ob es immer noch einen Weg gibt
		List<String> wegVonQuelleZuSenke = findeWegVonDerQuelleZurSenke(graph, quelle, senke);
		while (wegVonQuelleZuSenke.contains(senke)) {

			//für spätere Abfrage mit Math.min auf unendlich setzen, da die anderen werte dann auf jeden Fall am Anfang kleiner sind
			double minimalesFlussGewicht = Double.POSITIVE_INFINITY;
			for (int i = 0; i < wegVonQuelleZuSenke.size() - 1; i++) {

				String sourceVertex = wegVonQuelleZuSenke.get(i);
				String targetVertex = wegVonQuelleZuSenke.get(i + 1);

				double kantenKapazitaet = kapazitaetenDesNetzwerkes.get(sourceVertex).get(targetVertex);
				double kantenFluss = fluesseDesNetzwerkes.get(sourceVertex).get(targetVertex);

				minimalesFlussGewicht = Math.min(minimalesFlussGewicht, kantenKapazitaet - kantenFluss);

			}
			// Zeile 6
			for (int j = 0; j < wegVonQuelleZuSenke.size() - 1; j++) {
				String sourceVertex = wegVonQuelleZuSenke.get(j);
				String targetVertex = wegVonQuelleZuSenke.get(j + 1);
				// Zeile 7
				double kantenFluss = fluesseDesNetzwerkes.get(sourceVertex).get(targetVertex);
				fluesseDesNetzwerkes.get(sourceVertex).put(targetVertex, kantenFluss + minimalesFlussGewicht);
			}
			wegVonQuelleZuSenke = findeWegVonDerQuelleZurSenke(graph, quelle, senke);
		}

	
		Double highestFlow = 0d;
		for (Double toTest : fluesseDesNetzwerkes.get(quelle).values()) {
			if (toTest > highestFlow) {
				highestFlow =  toTest;
			}
		}
//		 
//		Set<String> keySet = fluesseDesNetzwerkes.keySet();
//		for (String s : keySet) {
//			for (String t : fluesseDesNetzwerkes.get(s).keySet()) {
//				System.out.println("Flow from " + s + " to " +t + ": " + fluesseDesNetzwerkes.get(s).get(t));
////				result = result + fluesseDesNetzwerkes.get(s).get(t);
//			}
//		}
//					
//		String neuerKnoten = quelle;
//		double newResult = 0;
//		double altesFlussGewicht = 0;
//		for (Double flussGewicht : fluesseDesNetzwerkes.get(neuerKnoten).values()) {			
//			if (flussGewicht > altesFlussGewicht) {
//				altesFlussGewicht = flussGewicht;
//				newResult = newResult + flussGewicht;
//			}
//		}
//		
//		List<String> neueListe = new ArrayList<>();
//		for (String vertex : graph.vertexSet()) {
//			neueListe.add(vertex);
//		}
//		
//		for (Double flussGewicht : fluesseDesNetzwerkes.get(neuerKnoten).values()) {
//			
//			for (String vertex : fluesseDesNetzwerkes.get(neueListe.get(i))) {
//				
//			}
//		}
//		
//		
//
//		System.out.println("Found highest flow: " + highestFlow);

		return highestFlow;
	}

	/**
	 * Methode um einen Weg von der Quelle zur Senke zu finden
	 * @param graph
	 * @param quelle
	 * @param senke
	 * @return Es wird eine liste mit dem Weg von der Quelle zur Senke zurückgegeben.
	 */
	private List<String> findeWegVonDerQuelleZurSenke(Graph<String, WeightedEdge> graph, String quelle, String senke) {
		// Ausgabeliste mit Quelle befüllen
		List<String> wegVonQuelleZuSenke = new ArrayList<>();
		wegVonQuelleZuSenke.add(quelle);
		System.out.println("Trying to find way from " + quelle + " to " + senke);

		String actualVertex = quelle;
		boolean foundWay = false;
		
		// Hier eine Do-While Schleife, weil ein Weg gesucht werden soll
		 do {
			 // Für alle nachbarn des aktuellen knotens -> Am Anfang Quelle
			for (String neighbor : kapazitaetenDesNetzwerkes.get(actualVertex).keySet()) {
				double kapazitaet = kapazitaetenDesNetzwerkes.get(actualVertex).get(neighbor);
				double flow = fluesseDesNetzwerkes.get(actualVertex).get(neighbor);
				
				// wenn die Kapazität größer ist als der flusswert den nachbar in die liste tun 
				// damit sich ein weg finden lässt muss der aktuellevertex auf den neuen gesetzt werden (nachbarn)
				if (kapazitaet > flow) {
					wegVonQuelleZuSenke.add(neighbor);
					actualVertex = neighbor;
					foundWay = true;
					// das break sorgt für den ausstieg aus der for schleife, sodass geschaut werden kann ob der aktuelle knoten der ziel knoten ist, denn die senke hat als nachfolger null.
					break;
				} else {
					System.out.println("Full edge");
				}

			}
		} while (!actualVertex.equals(senke) && foundWay);

		return wegVonQuelleZuSenke;
	}

	private Collection<String> getNeighbors(Graph<String, WeightedEdge> graph, String node) {
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
	}

}
