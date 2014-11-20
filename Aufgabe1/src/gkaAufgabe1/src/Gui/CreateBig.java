package Gui;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DirectedPseudograph;
import org.jgrapht.graph.DirectedWeightedPseudograph;

public class CreateBig {
	Graph<String, WeightedEdge> BIG = new DirectedWeightedPseudograph<>(
			WeightedEdge.class);
	private MyParser myParser;
	private String newVertex = "";
	private String oldVertex = "";
	private List<String> vertexList = new ArrayList<>();

	public Graph<String, WeightedEdge> createBig() {
		for (int i = 0; i < 100; i++) {
			oldVertex = "a" + i;
			newVertex = "a" + (i + 1);
			i++;
			BIG.addVertex(newVertex);
			BIG.addVertex(oldVertex);
			System.out.println(oldVertex);
			System.out.println(newVertex);

			// for (int j = 0; j < 4000; j++) {
			// BIG.addEdge(oldVertex, newVertex);
			// }

		}

		for (String vertex : BIG.vertexSet()) {
			vertexList.add(vertex);

			for (int i = 0; i < vertexList.size() - 1; i++) {

				BIG.addEdge(vertex, vertexList.get(i));
			}
		}
		System.out.println("-----------------------------------------------------------" + BIG.vertexSet() + " ------------------------------" + BIG.edgeSet().size());

		return BIG;
	}

	public void writeBigIntoFile() {
		Graph<String, WeightedEdge> graph = createBig();
		// BufferedWriter writer = null;
		PrintWriter pWriter = null;

		try {
			pWriter = new PrintWriter(
					new BufferedWriter(
							new FileWriter(
									"C:/Users/patrick_steinhauer/HAW/Semester3/GKA/Praktikum/Aufgabe 1/GKA/Aufgabe1/Beispielgraphen/Neue_Graphen/graphBIG.gka")));
			// writer = new BufferedWriter(new FileWriter("graphx.gka"));
			String pfeil;
			if (graph instanceof DirectedWeightedPseudograph) {
				pfeil = "->";
			} else {
				pfeil = "--";
			}
			Set<String> nodes = new HashSet<String>();
			for (WeightedEdge e : graph.edgeSet()) {
				// writer.write(e.getSource());
				// writer.write("");
				// writer.write(pfeil);
				// writer.write("");
				// writer.write(e.getTarget());
				// writer.write("\n");
				pWriter.println(e.getSource() + " " + pfeil + " "
						+ e.getTarget() + ";");
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

}
