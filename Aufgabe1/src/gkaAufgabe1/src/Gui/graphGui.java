package Gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import org.jgraph.JGraph;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;
import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.ListenableDirectedGraph;

public class graphGui extends JFrame {
	private JGraphModelAdapter<String, DefaultEdge> jGraphModelAdapter;
	private JGraph jGraph;
	private Controller controller;
	

	private static final Color DEFAULT_BG_COLOR = Color.decode("#c7d2ff");
	private static final Dimension DEFAULT_SIZE = new Dimension(400, 400);

	public graphGui() {
		createGraph();
		
	}

	private void createGraph() {
		JFrame mainWindow = new JFrame("Gui für Graphen");
		mainWindow.setSize(750, 500);
		mainWindow.setVisible(true);
		mainWindow.setDefaultCloseOperation(mainWindow.EXIT_ON_CLOSE);

// Hier wird ein gerichteter graph erstellt
		ListenableGraph<String, DefaultEdge> graph = new ListenableDirectedGraph<String, DefaultEdge>(
				DefaultEdge.class);

// mit dem JgraphmodelAdapter werden die JGraphT Graphen dargestellt
		jGraphModelAdapter = new JGraphModelAdapter<String, DefaultEdge>(graph);

		jGraph = new JGraph(jGraphModelAdapter);

		graphColorChanges(jGraph);
		mainWindow.getContentPane().add(jGraph);
		setSize(DEFAULT_SIZE);

//Hier werden die Knoten V1-V4 hinzugefügt
		graph.addVertex("v1");
		graph.addVertex("v2");
		graph.addVertex("v3");
		graph.addVertex("v4");

//		Hier werden die Kanten zwischen den verschiedenen Knoten eingefügt
		graph.addEdge("v1", "v2");
		graph.addEdge("v2", "v3");
		graph.addEdge("v1", "v4");
		graph.addEdge("v4", "v3");

//Position der Knoten
		positionVertexAt("v1", 130, 40);
		positionVertexAt("v2", 60, 200);
		positionVertexAt("v3", 310, 230);
		positionVertexAt("v4", 300, 70);

		// that's all there is to it!...
	}

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
		String colorStr = null;

		try {
			// colorStr = getParameter( "bgcolor" );
		} catch (Exception e) {
		}

		if (colorStr != null) {
			c = Color.decode(colorStr);
		}

		jGraph.setBackground(c);
	}

}
