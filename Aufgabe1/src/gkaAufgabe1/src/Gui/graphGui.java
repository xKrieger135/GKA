package Gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jgraph.JGraph;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;
import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.Pseudograph;

public class graphGui extends JFrame {
	private JGraphModelAdapter<String, WeightedEdge> jGraphModelAdapter;
	private JGraph jGraph;
	private Controller controller;
	private MyParser myParser;

	private static final Color DEFAULT_BG_COLOR = Color.decode("#c7d2ff");
	private static final Dimension DEFAULT_SIZE = new Dimension(400, 400);

	public static JFrame mainWindow = new JFrame("Gui für Graphen");

	public graphGui() {
		createGraph();
	}

	// erstellen des Graphen, sowie mainwindow zum anzeigen des graphen
	private void createGraph() {

		mainWindow.setSize(1200, 850);
		mainWindow.setVisible(true);
		mainWindow.setDefaultCloseOperation(mainWindow.EXIT_ON_CLOSE);

		// Hier wird ein gerichteter graph erstellt
		myParser.readGraphFromFile();
		Graph<String, WeightedEdge> graph = myParser
				.parseTextFromTextFileToGraph();
		// mit dem JgraphmodelAdapter werden die JGraphT Graphen dargestellt
		jGraphModelAdapter = new JGraphModelAdapter<String, WeightedEdge>(
				graph);

		// Anordnen der Vertexe (Graphen-Nodes) in Kreisform Code Übernahme
		double length = graph.vertexSet().size();
		Object[] vertex = graph.vertexSet().toArray();
		double pi2 = Math.PI * 2;
		for (int i = 0; i < length; i++) {
			positionVertexAt(vertex[i],
					(int) (Math.sin(pi2 * (i / length)) * 250 + 260),
					(int) (Math.cos(pi2 * (i / length)) * 250 + 260));
		}

		jGraph = new JGraph(jGraphModelAdapter);

		graphColorChanges(jGraph);
		mainWindow.getContentPane().add(jGraph);
		setSize(DEFAULT_SIZE);
//		Updatet das Mainwindow
		SwingUtilities.updateComponentTreeUI(mainWindow);
	}
	

	// Code Übernahme aus einem Tutorialvon JGraphT
	private void positionVertexAt(Object vertex, int x, int y) {
		DefaultGraphCell cell = jGraphModelAdapter.getVertexCell(vertex);
		Map attr = cell.getAttributes();
		Rectangle2D nodeForm = GraphConstants.getBounds(attr);

		GraphConstants.setBounds(
				attr,
				new Rectangle(x, y, (int) nodeForm.getWidth(), (int) nodeForm
						.getHeight()));

		Map cellAttr = new HashMap();
		cellAttr.put(cell, attr);
		jGraphModelAdapter.edit(cellAttr, null, null, null);
	}

	private void graphColorChanges(JGraph jGraph) {
		jGraph.setPreferredSize(DEFAULT_SIZE);

		Color c = DEFAULT_BG_COLOR;

		jGraph.setBackground(c);
	}

}
