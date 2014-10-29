package Gui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jgrapht.graph.ListenableDirectedGraph;
import org.jgrapht.graph.ListenableUndirectedGraph;
import org.jgrapht.graph.Pseudograph;

public class MyParser {
	
	public MyParser() {
		
	}

	public static final Pattern JAVAREGEX = Pattern
			.compile("(?<start>[a-zA-Z0-9]+) ?(-(?<verbindung>[>-])) ?(?<ende>[a-zA-Z0-9]+)?;.*");
	private  static String matcherVariable = "";

	public static String readGraphFromFile() {
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader("C:/Users/patrick_steinhauer/Dropbox/HAW-Semester/HAW/Semester3/Graphentheorie und Algorithmen/Praktikum/Aufgabe 1/GKA/Aufgabe1/Beispielgraphen/graph1.gka"));
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

	public static Pseudograph<String, GewichteteKante> parseTextFromTextFileToGraph() {


		Matcher matcher = JAVAREGEX.matcher(matcherVariable);
		System.out.println("X="+matcherVariable);
		Pseudograph<String, GewichteteKante> graph;

		if (matcherVariable.contains("->")) {
			graph = new Pseudograph<String, GewichteteKante>(
					GewichteteKante.class);
		} else {
			graph = new Pseudograph<String, GewichteteKante>(
					GewichteteKante.class);
		}

		while (matcher.find()) {
			String nodeStart = matcher.group("start");//1);
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
		System.out.println(readGraphFromFile());
		System.out.println(parseTextFromTextFileToGraph().edgesOf("a"));
	}

	// renderedge methode
	// bekommt eine dateizeile -> Kanten
	// http://openbook.galileocomputing.de/javainsel/javainsel_15_004.html#dodtpac233475-9da3-4cec-9eab-d68a36830773
	// file reader
	//

}
