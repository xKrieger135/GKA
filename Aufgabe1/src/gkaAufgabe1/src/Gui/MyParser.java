package Gui;

import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jgrapht.Graph;
import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.DirectedWeightedPseudograph;
import org.jgrapht.graph.Pseudograph;

public class MyParser {
	private Gui gui;

	public MyParser() {

	}

	// Java REGEX zum auswerten, von Textzeilen, Texten
	public static final Pattern JAVAREGEX = Pattern
			.compile("(?<start>[a-zA-Z0-9]+)( ?(?<verbindung>-([>-])) ?(?<ende>[a-zA-Z0-9]+) ?( ?: (?<gewicht>[0-9]+))?)?;.*");
	private static String matcherVariable = "";

	// Hier werden Datein für Graphen eingelesen.
	public static String readGraphFromFile() {
		BufferedReader reader = null;
		// Moeglichkeit, eine Datei über einen Dialog auszuwählen.
		Object file = loadDialog();
		if (file == null) {
			return null;
		}

		// Lesen der Datei, sowie Speichern in einer Variable fuer die Methode
		// parse
		try {
			reader = new BufferedReader(new FileReader((String) file));
			String fileLines = "";
			String actualLine;
			while ((actualLine = reader.readLine()) != null) {
				fileLines += actualLine + "\n";
			}
			matcherVariable = fileLines;
			return fileLines;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Datei wurde nicht gefunden!");
		}
		return "";
	}

	// Hier wird der Graph mittels der Textdatei erstellt.
	public static Graph<String, WeightedEdge> parseTextFromTextFileToGraph() {

		Matcher matcher = JAVAREGEX.matcher(matcherVariable);

		Graph<String, WeightedEdge> graph;

		if (matcherVariable.contains("->")) {
			graph = new DirectedWeightedPseudograph<String, WeightedEdge>(
					WeightedEdge.class);
		} else {
			graph = new Pseudograph<String, WeightedEdge>(WeightedEdge.class);
		}

		while (matcher.find()) {
			String nodeStart = matcher.group("start");
			graph.addVertex(nodeStart);
			if (matcher.group("ende") != null) {
				String nodeEnd = matcher.group("ende");
				String edge = matcher.group("verbindung");
				String weight = matcher.group("gewicht");

				if (nodeEnd != null) {
					graph.addVertex(nodeEnd);

					if (weight != null) {
						// bullshit den jan gemacht hatte
//						WeightedEdge E = graph.addEdge(nodeStart, nodeEnd);
						WeightedEdge e = new WeightedEdge();
						e.setWeight(Double.parseDouble(weight));
						
						graph.addEdge(nodeStart, nodeEnd, e);
						// konnte keine kante mehr hinzufügen was du gemacht jan 
//						((AbstractBaseGraph<String, WeightedEdge>) graph)
//								.setEdgeWeight(E, Double.parseDouble(weight));
					} else {
						graph.addEdge(nodeStart, nodeEnd);
					}
				}
			}
		}
		return graph;
	}

	public static Object loadDialog() {
		FileDialog fd = new FileDialog(graphGui.mainWindow, "Choose a file",
				FileDialog.LOAD);
		fd.setDirectory("C:\\");
		fd.setFile("*.gka");
		fd.setVisible(true);
		String filename = fd.getFile();
		if (filename != null) {
			filename = fd.getDirectory().replace("\\", "/") + filename;
		}
		return filename;
	}

	public static void writeGraphIntoFile() {
		Graph<String, WeightedEdge> graph = parseTextFromTextFileToGraph();
		// BufferedWriter writer = null;
		PrintWriter pWriter = null;

		try {
			pWriter = new PrintWriter(
					new BufferedWriter(
							new FileWriter(
									"C:/Users/patrick_steinhauer/HAW/Semester3/GKA/Praktikum/Aufgabe 1/GKA/Aufgabe1/Beispielgraphen/Neue_Graphen/graphx.gka")));
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
						+ e.getTarget());
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
