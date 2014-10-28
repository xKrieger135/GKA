package Gui;

import org.jgraph.graph.DefaultEdge;

public class GewichteteKanten extends DefaultEdge{
	private String sourceNode;
	private String targetNode;
	private boolean directed;
	

//	private int gewicht;
//
//	public int getGewicht() {
//		return gewicht;
//	}
//
//	public void setGewicht(int gewicht) {
//		this.gewicht = gewicht;
//	}
	
	public String toString() {
		if(directed && getWeight() == null) {
			return "gerichtet" + getWeight();
		} else {
			return "ungerichtet" + getWeight();
		}
	}
	
	
	
	
}
