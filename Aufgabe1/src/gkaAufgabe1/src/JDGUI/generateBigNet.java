package JDGUI;

import java.util.Random;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DirectedWeightedPseudograph;

import Gui.WeightedEdge;

public class generateBigNet {
	
	private int RandomRangeInPercent = 15;
	private int EdgeCount =40;
	private int EdgeRange=EdgeCount/100 * RandomRangeInPercent;
	
	private int VertexCount =800;
	private int VertexRange=VertexCount/100 * RandomRangeInPercent;
	
	
	public Graph generateBigNet() {
		Graph<String, WeightedEdge> Out = new DirectedWeightedPseudograph<>(
				WeightedEdge.class);
		
		
		
		Out.addVertex("s");
		Out.addVertex("q");
		
		int remainingEdges=random(EdgeCount-(EdgeRange/2),EdgeCount+(EdgeRange/2))-2;
		int remainingVertexes=random(VertexCount-(VertexRange/2),VertexCount+(VertexRange/2));
		
		for(int i = 0; i < remainingVertexes; i++)
		{
			Out.addVertex("v" + i);
		}
		
		
		return null;
		
	}
	private static int random(int min, int max)
	{
		Random r = new Random();
		return r.nextInt(max - min + 1) + min;
	}

}
