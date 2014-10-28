package Gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jgraph.graph.DefaultEdge;
import org.jgrapht.Graph;
import org.jgrapht.graph.ListenableDirectedGraph;
import org.jgrapht.graph.ListenableUndirectedGraph;

public class MyParser {
	
	public MyParser() {
		
	}

	public static final Pattern JAVAREGEX = Pattern
			.compile("([a-z_A-Z_0-9]+) ?(-(>|-)) ?([a-z_A-Z_0-9]+);");
	private  static String matcherVariable = "";

	private static String readGraphFromFile(File file) {
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(file));
			String fileLines = "";
			String actualLine;
			while ((actualLine = reader.readLine()) != null) {
				fileLines = actualLine + "\n";
			}

			return fileLines;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Datei wurde nicht gefunden!");
		}
		return "";
	}

	public static Graph<String, DefaultEdge> parseTextFromTextFileToGraph() {
		Matcher matcher = JAVAREGEX.matcher(matcherVariable);
		Graph<String, DefaultEdge> graph;

		if (matcherVariable.contains("--")) {
			graph = new ListenableDirectedGraph<String, DefaultEdge>(
					DefaultEdge.class);
		} else {
			graph = new ListenableUndirectedGraph<String, DefaultEdge>(
					DefaultEdge.class);
		}

		while (matcher.find()) {
			String nodeStart = matcher.group(1);
			String nodeEnd = matcher.group(3);
			String edge = matcher.group(2);

			graph.addVertex(nodeStart);

			if (nodeEnd != null) {
				graph.addVertex(nodeEnd);
				graph.addEdge(nodeStart, nodeEnd);
			}
		}
		return graph;
	}
	
	public static void main(String[] args) {
		System.out.println(parseTextFromTextFileToGraph());
	}

	// renderedge methode
	// bekommt eine dateizeile -> Kanten
	// http://openbook.galileocomputing.de/javainsel/javainsel_15_004.html#dodtpac233475-9da3-4cec-9eab-d68a36830773
	// file reader
	//

}
