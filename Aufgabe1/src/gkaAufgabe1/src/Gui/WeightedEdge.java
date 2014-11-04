package Gui;

import org.jgraph.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;

public class WeightedEdge extends DefaultWeightedEdge {

	public WeightedEdge() {
		super();
	}

	public String getSource() {
		return (String) super.getSource();
	}

	public String getTarget() {
		return (String) super.getTarget();
	}

	public String toString() {
		return "" + this.getWeight();
	}

}
