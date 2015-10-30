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
	
	private Graph(){
		this.adjacencyMap = new HashMap<Vertex, LinkedList<Edge>>();
	}
	
	public Graph getResidualGraph() {
		Graph g = new Graph();
		
		for (Map.Entry<Vertex, LinkedList<Edge>> entry : adjacencySet) {
			LinkedList<Edge> adjacencyEdges = new LinkedList<Edge>();

			for (Edge e : entry.getValue()) {
				if (e.getCapacityFlowDifference() != 0)
					g.addEdge(e.getResidualEdge());
				if (e.getFlow() != 0)
					g.addEdge(e.getAntiParallelEdge());
			}
		}
		g.generateAdjacencySet();
		
		return g;
	}

	private void addEdge(Edge edge) {
		if (adjacencyMap.containsKey(edge.getFrom())){
			adjacencyMap.get(edge.getFrom()).add(edge);
		}else{
			LinkedList<Edge> l = new LinkedList<Edge>();
			l.add(edge);
			adjacencyMap.put(edge.getFrom(), l);
		}
	}

	private void generateAdjacencySet() {
		this.adjacencySet = adjacencyMap.entrySet();
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
			LinkedList<Edge> neighbourEdges = adjacencyMap.get(current);
			if(neighbourEdges != null){
				for (Edge e : neighbourEdges) {
					if (!visitedVertecies.contains(e.getTo())) {
						if (findAugmentingPath(e.getTo(), sink, visitedVertecies, edgesInPath)) {
							edgesInPath.add(e);
							return true;
						}
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
				} 
			}
			for (Edge neighbourEdge : adjacencyMap.get(e.getTo())) {
				if(neighbourEdge.getTo().equals(e.getFrom())) {
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
				s += "\n key: " + entry.getKey() + "          value :"+ e;
			}
		}
		return s;
	}
}

