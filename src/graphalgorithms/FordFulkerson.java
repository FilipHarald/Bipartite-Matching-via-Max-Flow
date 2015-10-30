package graphalgorithms;

import java.util.LinkedList;

import graph.*;

public class FordFulkerson {
	
	public static LinkedList<Edge> runAlgorithm(Graph graph, Vertex source, Vertex sink) {
		graph.setEdgesFlowToZero();
		
		Graph residualGraph = graph.getResidualGraph();
		LinkedList<Edge> edgesInAugmentingPath = residualGraph.findAugmentingPath(source, sink);
		int augmentingPathCapacity = Integer.MAX_VALUE;
		
		while (edgesInAugmentingPath.size() > 0) {
			for (Edge e : edgesInAugmentingPath)
				if (e.getCapacity() < augmentingPathCapacity)
					augmentingPathCapacity = e.getCapacity();

			graph.addFlowToPath(edgesInAugmentingPath, augmentingPathCapacity);
			residualGraph = graph.getResidualGraph();
			System.out.println("residual graph :\n" + residualGraph);
			edgesInAugmentingPath = residualGraph.findAugmentingPath(source, sink);
			augmentingPathCapacity = Integer.MAX_VALUE;
			System.out.println("\n" + edgesInAugmentingPath);
			System.out.println(edgesInAugmentingPath.size());
		}
		
		return graph.getEdgesWithFlow();
	}
}
