package Gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JApplet;
import javax.swing.JFrame;

import org.jgraph.JGraph;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.GraphModel;
import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.ListenableDirectedGraph;

public class graphGui extends JFrame {
	private GraphModel graphModel;
	private JGraphModelAdapter jGraphModelAdapter;


	private static final Color DEFAULT_BG_COLOR = Color.decode("#FAFBFF");
	private static final Dimension DEFAULT_SIZE = new Dimension(600, 400);

	public graphGui() {
		createGraph();
	}

	private void createGraph() {
		JFrame mainWindow = new JFrame("Gui für Graphen");
		mainWindow.setSize(750, 500);
		mainWindow.setVisible(true);
		mainWindow.setDefaultCloseOperation(mainWindow.EXIT_ON_CLOSE);
		
		// create a JGraphT graph
		ListenableGraph g = new ListenableDirectedGraph(DefaultEdge.class);

		// create a visualization using JGraph, via an adapter
		jGraphModelAdapter = new JGraphModelAdapter(g);
		

		JGraph jgraph = new JGraph(jGraphModelAdapter);

		adjustDisplaySettings(jgraph);
		mainWindow.getContentPane().add(jgraph);
		resize(DEFAULT_SIZE);

		// add some sample data (graph manipulated via JGraphT)
		g.addVertex("v1");
		g.addVertex("v2");
		g.addVertex("v3");
		g.addVertex("v4");

		g.addEdge("v1", "v2");
		g.addEdge("v2", "v3");
		g.addEdge("v3", "v1");
		g.addEdge("v4", "v3");

		// position vertices nicely within JGraph component
		positionVertexAt("v1", 130, 40);
		positionVertexAt("v2", 60, 200);
		positionVertexAt("v3", 310, 230);
		positionVertexAt("v4", 380, 70);

		// that's all there is to it!...
	}

	private void positionVertexAt(Object vertex, int x, int y) {
		DefaultGraphCell cell = jGraphModelAdapter.getVertexCell(vertex);
		Map attr = cell.getAttributes();
		Rectangle2D b = GraphConstants.getBounds(attr);

		GraphConstants.setBounds(attr, new Rectangle(x, y, (int) b.getWidth(),
				(int) b.getHeight()));

		Map cellAttr = new HashMap();
		cellAttr.put(cell, attr);
		jGraphModelAdapter.edit(cellAttr, null, null, null);
	}

	private void adjustDisplaySettings(JGraph jg) {
		jg.setPreferredSize(DEFAULT_SIZE);

		Color c = DEFAULT_BG_COLOR;
		String colorStr = null;

		try {
//			colorStr = getParameter( "bgcolor" );
		} catch (Exception e) {
		}

		if (colorStr != null) {
			c = Color.decode(colorStr);
		}

		jg.setBackground(c);
	}

}
