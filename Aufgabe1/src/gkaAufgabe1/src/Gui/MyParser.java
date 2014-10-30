package Gui;

import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jgrapht.Graph;
import org.jgrapht.graph.DirectedWeightedPseudograph;
import org.jgrapht.graph.ListenableDirectedGraph;
import org.jgrapht.graph.ListenableUndirectedGraph;
import org.jgrapht.graph.Pseudograph;

public class MyParser {

	public MyParser() {

	}

	public static final Pattern JAVAREGEX = Pattern
			.compile("(?<start>[a-zA-Z0-9]+) ?(-(?<verbindung>[>-])) ?(?<ende>[a-zA-Z0-9]+) ?(?: ([0-9]+))?;.*");
	private static String matcherVariable = "";

	public static String readGraphFromFile() {
		BufferedReader reader = null;
		Object file = loadDialog();
		if (file == null) return null;
		try {
			reader = new BufferedReader(
					new FileReader((String) file));
			String fileLines = "";
			String actualLine;
			while ((actualLine = reader.readLine()) != null) {
				fileLines += actualLine + "\n";
				System.out.println(actualLine);
			}
			matcherVariable = fileLines;
			return fileLines;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Datei wurde nicht gefunden!");
		}
		return "";
	}

	public static Graph<String, GewichteteKante> parseTextFromTextFileToGraph() {

		Matcher matcher = JAVAREGEX.matcher(matcherVariable);

		System.out.println("X=" + matcherVariable);
		Graph<String, GewichteteKante> graph;

		if (matcherVariable.contains("->")) {
			graph = new DirectedWeightedPseudograph<String, GewichteteKante>(
					GewichteteKante.class);
		} else {
			graph = new Pseudograph<String, GewichteteKante>(
					GewichteteKante.class);
		}

		while (matcher.find()) {
			String nodeStart = matcher.group("start");
			String nodeEnd = matcher.group("ende");
			String edge = matcher.group("verbindung");

			graph.addVertex(nodeStart);
			System.out.println(nodeStart);

			if (nodeEnd != null) {
				graph.addVertex(nodeEnd);
				graph.addEdge(nodeStart, nodeEnd);
			}
		}
		return graph;
	}

	public static void main(String[] args) {
		loadDialog();
	}

	public static Object loadDialog() {
		FileDialog fd = new FileDialog(graphGui.mainWindow, "Choose a file", FileDialog.LOAD);
		fd.setDirectory("C:\\");
		fd.setFile("*.gka");
		fd.setVisible(true);
		String filename = fd.getFile();
		System.out.println(fd.getDirectory()+filename);
		if (filename != null) {
			filename = fd.getDirectory().replace("\\", "/")+filename;
		}
		System.out.println(filename);
		return filename;
	}
	// renderedge methode
	// bekommt eine dateizeile -> Kanten
	// http://openbook.galileocomputing.de/javainsel/javainsel_15_004.html#dodtpac233475-9da3-4cec-9eab-d68a36830773
	// file reader
	//

}
