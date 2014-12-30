package Gui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DirectedWeightedPseudograph;

public class FordFulk {

	Map<String, Map<String, Double>> kapazitaetenDesNetzwerkes = new HashMap<>();
	Map<String, Map<String, Double>> fluesseDesNetzwerkes = new HashMap<>();
	Map<String, ArrayList<Object>> markierteKnoten = new HashMap<>();
	Map<String, Map<Double, Boolean>> markierteKnoten2 = new HashMap<>();
	List<String> listeMitMarkiertenKnoten = new ArrayList<>();
	List<String> markierteUndInspizierteKnoten = new ArrayList<>();
	List<String> listeMitNichtInspiziertenKnoten = new ArrayList<>();

	public double fordFulkerson(Graph<String, WeightedEdge> graph,
			String quelle, String senke) {

		for (String vi : graph.vertexSet()) {
			// hinterer Teil der geschachtelten Map welcher die Kapazitaet
			// enthalten soll oder den Teil fuer die Flusseinheit
			if (!kapazitaetenDesNetzwerkes.containsKey(vi)) {
				Map<String, Double> kapazitaet = new HashMap<>();
				Map<String, Double> fluss = new HashMap<>();
				kapazitaetenDesNetzwerkes.put(vi, kapazitaet);
				fluesseDesNetzwerkes.put(vi, fluss);
			}
			for (String vj : getNeighbors(graph, vi)) {
				// Setze Kantengewichtung bzw. die flusseinheit
				WeightedEdge edge = graph.getEdge(vi, vj);
				kapazitaetenDesNetzwerkes.get(vi).put(vj, edge.getWeight());
				fluesseDesNetzwerkes.get(vi).put(vj, 0d);
			}
		}

		// q mit unendlich markieren
		ArrayList<Object> A = new ArrayList<>();
		A.add(Double.POSITIVE_INFINITY);
		A.add(true); // TODO
		markierteKnoten.put(quelle, A);
		//markierteKnoten.put(quelle, Double.POSITIVE_INFINITY);
		
//		Map<Double, Boolean> inkrementUndRichtung = new HashMap<Double, Boolean>();
//		markierteKnoten.put(quelle, inkrementUndRichtung);
		boolean senkeWurdeErreicht = true;

		// Solange s noch nicht markiert ist / erreicht
		while (senkeWurdeErreicht == true) {
			// Wir gehen immer den Weg vom Start zum Ende, dieser ist jedoch
			// unterschiedlich dann
			senkeWurdeErreicht = false;

			listeMitMarkiertenKnoten.add(quelle);
			System.out.println("Markierte Knoten - " + listeMitMarkiertenKnoten);
			listeMitNichtInspiziertenKnoten.add(quelle);
			System.out.println("Nicht Inspizierte Knoten" + listeMitNichtInspiziertenKnoten);

			while (!listeMitNichtInspiziertenKnoten.isEmpty() && senkeWurdeErreicht == false) {
				// Einen beliebigen Knoten der Markiert ist, jedoch nicht
				// inspiziert wurde.
				String vi = listeMitNichtInspiziertenKnoten.get(myRandom(0,
						listeMitNichtInspiziertenKnoten.size() - 1));
				System.out.println("Ausgabe von VI : " + vi);

				// Da der Knoten nun inspiziert wird muss er entfernt werden.
				listeMitNichtInspiziertenKnoten.remove(vi);

				double inkrement = (double) markierteKnoten.get(vi).get(0);
				//double inkrement = markierteKnoten.get(vi);
				System.out.println("Inkrement vor der For SChleife: " + inkrement);

				for (WeightedEdge eij : graph.edgesOf(vi)) {

					// vi = source und vj = target
					String vj = eij.getTarget();
					System.out.println("Zielknoten vj" + vj);

					// Fuer vorwaerts Kanten
					if (!vj.equals(vi)) {
						// maximale Kapazitaet
						double kapazitaet = eij.getWeight();
						System.out.println("Kapazitaet : " + kapazitaet);
						
						// fluss wird hier geholt
						// Den Fluss hier holen, da es sonst auftreten kann,
						// dass er zu sich selbst einen Fluss nehmen will 
						// (keine Ahnung wieso er sich selbst als Target sieht)
						double fluss = fluesseDesNetzwerkes.get(vi).get(vj);
						
						System.out.println("Fluss : " + fluss);
						if (kapazitaet > fluss) {
							// Abfrage, ob die Senke schon erreicht wurde oder
							// nicht
							if (!listeMitMarkiertenKnoten.contains(senke) && senkeWurdeErreicht == false) {
								// vj markieren mit dem minimum von c - f und
								// delta i
								double deltaJ = Math.min(kapazitaet - fluss,
										inkrement);
								System.out.println("DeltaJ : " + deltaJ);

								// Inkrement muss neu gesetzt werden, sonst wird
								// immer das inkrement von q benutzt was falsch
								// waere.
								inkrement = deltaJ;

								//markierteKnoten.put(vj, inkrement);
								ArrayList<Object> B =new ArrayList<>();
								B.add(inkrement);
								B.add(true); //TODO
								
								
								markierteKnoten.put(vj, B);
								System.out.println("MarkierteKnoten: " + markierteKnoten);
								
								listeMitMarkiertenKnoten.add(vj);
								
								System.out.println("ListeMitMarkiertenKnoten : " + listeMitMarkiertenKnoten);
								
								listeMitNichtInspiziertenKnoten.add(vj);
								
								System.out.println("ListeMitNichtInspiziertenKnoten : " + listeMitNichtInspiziertenKnoten);
								
								fluesseDesNetzwerkes.get(vi).put(vj, inkrement);
								
								System.out.println("vi --> vj Wert des aktuellen Flusses: " + fluesseDesNetzwerkes.get(vi).put(vj, inkrement));

							}

							if (vj.equals(senke)) {
								senkeWurdeErreicht = true;
							}
						}
					} else {
						// fuer rueckwaerts Kanten
						String source = eij.getSource();
						// maximale Kapazitaet
						double kapazitaet = eij.getWeight();

						double fluss = fluesseDesNetzwerkes.get(source).get(vj);
						System.out.println("Fluss fuer die Reverse Kante : " + fluss);
						
						if (fluss > 0) {
							if (!listeMitMarkiertenKnoten.contains(source) && senkeWurdeErreicht == false) {
								// vj markieren mit dem minimum delta i
								double deltaJ = Math.min(fluss, inkrement);
								
								// Inkrement muss neu gesetzt werden, sonst wird
								// immer das inkrement von q benutzt was falsch
								// waere.
								inkrement = deltaJ;
								
								ArrayList<Object> B = new ArrayList<>();
								B.add(inkrement);
								B.add(false); //TODO
								
								markierteKnoten.put(source, B);
								
								listeMitMarkiertenKnoten.add(source);

								listeMitNichtInspiziertenKnoten.add(source);

								fluesseDesNetzwerkes.get(source).put(vj, inkrement);
								System.out.println("FluesseDesNetzwerks Ausgabe  :  " + fluesseDesNetzwerkes.get(source).put(vj, inkrement));
								
							}
						}

					}

				}

			} //WHILE2 END
			
			System.out.println("WHILE 2 END");
			
			listeMitMarkiertenKnoten.clear();
			listeMitNichtInspiziertenKnoten.clear();
			// Vergroesserung der Flussstaerke
			 

		}
		
		System.out.println("Ende der ersten While Schleife!");

		// Muss noch korrigiert werden ist nur damit die Fehlermeldung weg ist
		// beim Funktionskopf
		return 0;
	}

	private Collection<String> getNeighbors(Graph<String, WeightedEdge> graph,
			String node) {
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

	public int myRandom(int low, int high) {
		return (int) (Math.random() * (high - low) + low);
	}

	@Override
	public String toString() {
		return "FordFulk [kapazitaetenDesNetzwerkes="
				+ kapazitaetenDesNetzwerkes + ", fluesseDesNetzwerkes="
				+ fluesseDesNetzwerkes + ", markierteKnoten=" + markierteKnoten
				+ ", listeMitMarkiertenKnoten=" + listeMitMarkiertenKnoten
				+ ", markierteUndInspizierteKnoten="
				+ markierteUndInspizierteKnoten
				+ ", listeMitNichtInspiziertenKnoten="
				+ listeMitNichtInspiziertenKnoten + "]";
	}
	

}