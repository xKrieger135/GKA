package Gui;

import org.jgraph.graph.DefaultEdge;
import org.jgrapht.ext.JGraphXAdapter;


public class Controller {
	private graphGui graphGui;
	
	public Controller() {
		graphGui = new graphGui();
	}

	public static void main(String[] args) {
		Controller controller = new Controller();
	}

}
