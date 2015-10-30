package graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Graph {

	private HashMap<Vertex, LinkedList<Edge>> adjacencyMap;

	public Graph(HashMap<Vertex, LinkedList<Edge>> adjacencyMap) {
		this.adjacencyMap = adjacencyMap;
	}

	public Graph getResidualGraph() {
		Set<Map.Entry<Vertex, LinkedList<Edge>>> adjacencySet = adjacencyMap
				.entrySet();
		HashMap<Vertex, LinkedList<Edge>> residualEdges = new HashMap<Vertex, LinkedList<Edge>>();

		for (Map.Entry<Vertex, LinkedList<Edge>> entry : adjacencySet) {
			LinkedList<Edge> adjacencyEdges = new LinkedList<Edge>();

			for (Edge e : entry.getValue()) {
				if (e.getCapacityFlowDifference() != 0)
					adjacencyEdges.add(e.getResidualEdge());

				adjacencyEdges.add(e.getAntiParallelEdge());
			}

			residualEdges.put(entry.getKey(), adjacencyEdges);
		}

		return new Graph(adjacencyMap);
	}

	public LinkedList<Edge> findAugmentingPath() {

		return null;
	}

	public void setEdgesFlowToZero() {
		for (Edge e : edges) {
			e.setFlow(0);
		}
	}

	public void addFlowToPath(LinkedList<Edge> path) {

	}
}
