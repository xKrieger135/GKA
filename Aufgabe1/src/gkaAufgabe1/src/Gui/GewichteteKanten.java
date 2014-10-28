package Gui;

import org.jgraph.graph.DefaultEdge;

public class GewichteteKanten extends DefaultEdge{
	private String sourceNode;
	private String targetNode;
	private String edgeName;
	private boolean directed;
	

	private int gewicht;

	public int getGewicht() {
		return gewicht;
	}

	public void setGewicht(int gewicht) {
		this.gewicht = gewicht;
	}
	
	public boolean hatKanteEinGewicht() {
		if(gewicht != 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public String toString() {
		if(directed && hatKanteEinGewicht()) {
			return "gerichtet" + getGewicht();
		} else {
			return "ungerichtet" + getGewicht();
		}
	}
	
	
	
	
}
