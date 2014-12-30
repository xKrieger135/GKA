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
	
	private int maxGewicht= 100;
	
	
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
		
		
		int edges =0;
		// Von s zu vx
		int random=random(1,10);
		for (int i = 0; i < random; i++) {
			WeightedEdge e = new WeightedEdge();
			e.setWeight(random(0,maxGewicht));
			Out.addEdge("s", "v"+random(1,EdgeCount),e);
			edges+=1;
		}
		
		// von s zu q
		random=random(0,1);
		for (int i = 0; i < random; i++) {
			WeightedEdge e = new WeightedEdge();
			e.setWeight(random(0,maxGewicht));
			Out.addEdge("s", "q",e);
			edges+=1;
		}
		
		// von vx zu q
		random=random(1,10);
		for (int i = 0; i < random; i++) {
			WeightedEdge e = new WeightedEdge();
			e.setWeight(random(0,maxGewicht));
			Out.addEdge("v"+random(1,EdgeCount),"q",e);
			edges+=1;
		}
		
		
		for (int i = 0; i < remainingEdges-edges; i++) {
			WeightedEdge e = new WeightedEdge();
			e.setWeight(random(0,maxGewicht));
			Out.addEdge("v"+random(1,EdgeCount), "v"+random(1,EdgeCount),e);
		}
		return Out;
		
	}
	private static int random(int min, int max)
	{
		Random r = new Random();
		return r.nextInt(max - min + 1) + min;
	}

}
