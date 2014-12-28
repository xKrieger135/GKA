package Gui;

import org.jgraph.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;

public class WeightedEdge extends DefaultWeightedEdge {
	private double weight;

	public WeightedEdge() {
		super();
	}
	
	public WeightedEdge(double weight) {
		
	}

	public String getSource() {
		return (String) super.getSource();
	}

	public String getTarget() {
		return (String) super.getTarget();
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String toString() {
		return "" + this.getWeight();
	}

}
