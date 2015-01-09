package JD;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.jgrapht.Graph;

import Gui.WeightedEdge;

public class NearestNeighbor {

	private List<String> VertexList = new ArrayList<>();
	private List<String> Tour = new ArrayList();
	Graph<String, WeightedEdge> graph;
	String[] foundTour;

	public double NearestNeighbor(Graph<String, WeightedEdge> graph) {
		this.graph = graph;
		VertexList.addAll(graph.vertexSet());

		// Schritt 1:
		Tour.add((VertexList).get(0));

		// Schritt 2:
		String[] Liste = graph.vertexSet().toArray(
				new String[graph.vertexSet().size() + 1]);
		Liste[Liste.length - 1] = Liste[0];
		ArrayList<String[]> PermuteList = permutations(Liste);
		System.out.println("Hi");
		double minTour = Double.POSITIVE_INFINITY;
		for (String[] Elem : PermuteList) {
			if (isPossible(Elem)) {
				double i = getTour(Elem);
				if (i < minTour) {
					minTour = i;
					foundTour = Elem;
				}
				// minTour = Math.min(minTour, getTour(Elem));
			}
		}
		System.out.println(Arrays.toString(foundTour));
		return minTour;
	}

	// permutation({1,2,3});

	private double getTour(String[] List) {
		// TODO Auto-generated method stub
		double tour = 0.0;
		for (int i = 0; i < List.length - 1; i++) {

			if (graph.containsEdge(List[i], List[i + 1])) {
				tour += graph
						.getEdgeWeight(graph.getEdge(List[i], List[i + 1]));
			} else if (graph.containsEdge(List[i + 1], List[i])) {
				tour += graph
						.getEdgeWeight(graph.getEdge(List[i + 1], List[i]));
			}
		}
		return tour;
	}

	private boolean isPossible(String[] List) {
		// TODO Auto-generated method stub
		boolean ret = true;
		for (int i = 0; i < List.length - 1; i++) {

			if (!(graph.containsEdge(List[i], List[i + 1]) || graph
					.containsEdge(List[i + 1], List[i]))) {
				ret = false;
			}
		}
		return ret;
	}

	// Permutation von http://ideone.com/jLpZow
	// Modifiziert zur Nutzung von Strings statt integers
	// Bereich wurde um 1 auf jeder Seite eingeschränkt
	static ArrayList<String[]> permutations(String[] a) {
		ArrayList<String[]> ret = new ArrayList<String[]>();
		permutation(a, 1, ret);
		return ret;
	}

	public static void permutation(String[] arr, int pos,
			ArrayList<String[]> list) {
		if (arr.length - pos == 1)
			list.add(arr.clone());
		else
			for (int i = pos; i < arr.length - 1; i++) {
				swap(arr, pos, i);
				permutation(arr, pos + 1, list);
				swap(arr, pos, i);
			}
	}

	public static void swap(String[] arr, int pos1, int pos2) {
		String h = arr[pos1];
		arr[pos1] = arr[pos2];
		arr[pos2] = h;
	}
}
