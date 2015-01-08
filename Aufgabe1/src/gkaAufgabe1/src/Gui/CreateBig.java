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
import org.jgrapht.graph.DirectedWeightedPseudograph;

public class CreateBig {

	Graph<String, WeightedEdge> BIG = new DirectedWeightedPseudograph<>(
			WeightedEdge.class);
//	private MyParser myParser;
//	private String newVertex = "";
//	private String newVertex2 = "";
	private List<String> vertexList = new ArrayList<>();

	public Graph<String, WeightedEdge> createBig() {


		for (int i = 0; i < 99; i++) {
			vertexList.add("" + i);
			BIG.addVertex("" + i);
		}

		for (int i = 0; i < 6000; i++) {
			int random1 = myRandom(0, 99);
			int random2 = myRandom(0, 99);

			BIG.addEdge(vertexList.get(random2), vertexList.get(random1));

		}
		// System.out.println(BIG.toString());
		// System.out.println(BIG.edgeSet().size());
		return BIG;
	}

	public static void main(String[] args) {
		CreateBig cb = new CreateBig();
		cb.createBig();
	}

	public void writeBigIntoFile() {
		Graph<String, WeightedEdge> graph = createBig();
		BufferedWriter writer = null;
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

	public int myRandom(int low, int high) {
		return (int) (Math.random() * (high - low) + low);
	}

}
