package graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Graph {


	private HashMap<Vertex, LinkedList<Edge>> adjacencyMap;
	private Set<Map.Entry<Vertex, LinkedList<Edge>>> adjacencySet;

	public Graph(HashMap<Vertex, LinkedList<Edge>> adjacencyMap) {
		this.adjacencyMap = adjacencyMap;
		this.adjacencySet = adjacencyMap.entrySet();
	}

	public Graph getResidualGraph() {
		HashMap<Vertex, LinkedList<Edge>> residualEdges = new HashMap<Vertex, LinkedList<Edge>>();

		for (Map.Entry<Vertex, LinkedList<Edge>> entry : adjacencySet) {
			LinkedList<Edge> adjacencyEdges = new LinkedList<Edge>();

			for (Edge e : entry.getValue()) {
				if (e.getCapacityFlowDifference() != 0)
					adjacencyEdges.add(e.getResidualEdge());
				if (e.getFlow() != 0)
					adjacencyEdges.add(e.getAntiParallelEdge());
			}

			residualEdges.put(entry.getKey(), adjacencyEdges);
		}

		return new Graph(residualEdges);
	}

	public LinkedList<Edge> findAugmentingPath(Vertex source, Vertex sink) {
		LinkedList<Vertex> visitedVertecies = new LinkedList<Vertex>();
		LinkedList<Edge> edgesInPath = new LinkedList<Edge>();
		findAugmentingPath(source, sink, visitedVertecies, edgesInPath);
		return edgesInPath;
	}

	private boolean findAugmentingPath(Vertex current, Vertex sink, LinkedList<Vertex> visitedVertecies, LinkedList<Edge> edgesInPath) {
		visitedVertecies.add(current);

		if (current.equals(sink)) {
			return true;
		} else {
			System.out.println("VISITED " + visitedVertecies);
			for (Edge e : adjacencyMap.get(current)) {
				System.out.println("CURRENT :" + current);
				System.out.println(e);
				if (!visitedVertecies.contains(e.getTo())) {
					System.out.println("Finding augumenting path for " + e);
					if (findAugmentingPath(e.getTo(), sink, visitedVertecies, edgesInPath)) {
						edgesInPath.add(e);
						return true;
					}
				}
			}
		}
		return false;
	}

	public void setEdgesFlowToZero() {
		for (Map.Entry<Vertex, LinkedList<Edge>> entry : adjacencySet) {
			for (Edge e : entry.getValue()) {
			
				e.setFlow(0);
			}
		}
	}
	
	
	public LinkedList<Edge> getEdgesWithFlow(){
		LinkedList<Edge> edgeList = new LinkedList<Edge>();
		for (Map.Entry<Vertex, LinkedList<Edge>> entry : adjacencySet) {
			for (Edge e : entry.getValue()) {
				if (e.getFlow() > 0) edgeList.add(e);
			}
		}
		return edgeList;
	}
	public void addFlowToPath(LinkedList<Edge> path, int augmentingPathCapacity) {
		for (Edge e : path) {	
			for (Edge neighbourEdge : adjacencyMap.get(e.getFrom())) {
				if (neighbourEdge.getTo().equals(e.getTo())) {
					neighbourEdge.addFlow(augmentingPathCapacity);
				} else if(neighbourEdge.getTo().equals(e.getFrom())) {
					neighbourEdge.addFlow(-augmentingPathCapacity);
				}
			}
		}
	}
	@Override
	public String toString() {
		String s = "";
		for (Map.Entry<Vertex, LinkedList<Edge>> entry : adjacencySet) {
			for (Edge e : entry.getValue()) {
				s += "\n " + e;
			}
		}
		return s;
	}
}

