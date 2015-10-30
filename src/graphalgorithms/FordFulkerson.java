package graphalgorithms;

import java.util.LinkedList;
import graph.*;

public class FordFulkerson {
	
	public static Edge[] runAlgorithm(Graph graph){
		graph.setEdgesFlowToZero();
		
		Graph residualGraph = graph.getResidualGraph();
		LinkedList<Edge> edgesInAugmentingPath = residualGraph.findAugmentingPath();
		int augmentingPathCapacity = Integer.MAX_VALUE;
		
		while(edgesInAugmentingPath.size() >= 0){
			for(Edge e : edgesInAugmentingPath){
				if(e.getCapacity() < augmentingPathCapacity) augmentingPathCapacity = e.getCapacity();
			}
			graph.addFlowToPath(edgesInAugmentingPath);
		}
		
		return null;
	}
}
