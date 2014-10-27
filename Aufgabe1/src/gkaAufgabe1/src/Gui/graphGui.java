package Gui;

import javax.swing.JFrame;

import org.jgraph.JGraph;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.Edge;
import org.jgraph.graph.GraphModel;
import org.jgrapht.DirectedGraph;
import org.jgrapht.ListenableGraph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.ListenableDirectedGraph;
import org.jgrapht.graph.SimpleGraph;


public class graphGui {
	private GraphModel graphModel;
	private JGraphXAdapter<String, DefaultEdge> jGraphXAdapter; 
	private JGraph jGraph;
	
	public graphGui() {
		setWindow();
	}
	
	private void setWindow() {
		JFrame mainWindow = new JFrame("Gui für Graphen");
		mainWindow.setSize(750, 500);
		mainWindow.setVisible(true);
	    mainWindow.setDefaultCloseOperation(mainWindow.EXIT_ON_CLOSE);
		
	}
	

}
