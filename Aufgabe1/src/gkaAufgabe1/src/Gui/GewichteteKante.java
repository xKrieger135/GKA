package Gui;

import org.jgraph.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;

public class GewichteteKante extends DefaultWeightedEdge{
//	private String sourceNode;
//	private String targetNode;
//	private boolean directed;
//	private int gewicht;
//
//	public int getGewicht() {
//		return gewicht;
//	}
//
//	public void setGewicht(int gewicht) {
//		this.gewicht = gewicht;
//	}
//	
//	public boolean istKanteGewichtet() {
//		return gewicht != 1;
//	}
//	
//	public String toString() {
//		if(directed) {
//			return "gerichtet" + getGewicht();
//		} else {
//			return "ungerichtet" + getGewicht();
//		}
//	}
//	
	public String toString(){
		return ""+this.getWeight();
	}
	
	
	
}
