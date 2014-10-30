package Gui;

import org.jgraph.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;

public class WeightedEdge extends DefaultWeightedEdge {

	public String toString() {
		return "" + this.getWeight();
	}

}
