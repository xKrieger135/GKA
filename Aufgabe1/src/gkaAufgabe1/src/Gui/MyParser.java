package Gui;

import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jgrapht.Graph;
import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedPseudograph;
import org.jgrapht.graph.ListenableDirectedGraph;
import org.jgrapht.graph.ListenableUndirectedGraph;
import org.jgrapht.graph.Pseudograph;

public class MyParser {

	public MyParser() {

	}

	// Java REGEX zum auswerten, von Textzeilen, Texten
	public static final Pattern JAVAREGEX = Pattern
			.compile("(?<start>[a-zA-Z0-9]+)( ?(-(?<verbindung>[>-])) ?(?<ende>[a-zA-Z0-9]+) ?( ?: (?<gewicht>[0-9]+))?)?;.*");
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
						WeightedEdge E = graph.addEdge(nodeStart, nodeEnd);
						((AbstractBaseGraph<String, WeightedEdge>) graph)
								.setEdgeWeight(E, Double.parseDouble(weight));
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

}
