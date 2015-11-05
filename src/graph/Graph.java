package graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * Represents a graph holding vertices and edges.
 * 
 * @author Filip Harald & Andreas Indal
 */
public class Graph {

	private HashMap<Vertex, LinkedList<Edge>> adjacencyMap;
	private Set<Map.Entry<Vertex, LinkedList<Edge>>> adjacencySet;

	/**
	 * Creates a new graph with the supplied vertices and edges.
	 * 
	 * @param adjacencyMap A HashMap holding the vertices and edges of the graph.
	 */
	public Graph(HashMap<Vertex, LinkedList<Edge>> adjacencyMap) {
		this.adjacencyMap = adjacencyMap;
		this.adjacencySet = adjacencyMap.entrySet();
	}

	/**
	 * Creates a new empty graph.
	 */
	public Graph() {
		this.adjacencyMap = new HashMap<Vertex, LinkedList<Edge>>();
	}

	/**
	 * Creates a new residual graph from the the vertices and edges the current
	 * graph is holding.
	 * 
	 * @return The new residual graph.
	 */
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

	/**
	 * Adds an edge to the graph.
	 * 
	 * @param edge The edge to add.
	 */
	public void addEdge(Edge edge) {
		if (adjacencyMap.containsKey(edge.getFrom())) {
			adjacencyMap.get(edge.getFrom()).add(edge);
		} else {
			LinkedList<Edge> l = new LinkedList<Edge>();
			l.add(edge);
			adjacencyMap.put(edge.getFrom(), l);
		}
	}

	/**
	 * Generates an adjacency set for simplifying iterations of the graph.
	 */
	public void generateAdjacencySet() {
		this.adjacencySet = adjacencyMap.entrySet();
	}

	/**
	 * Finds the augmenting path of a graph.
	 * 
	 * @param source The source
	 * @param sink The sink
	 * @return A LinkedList containing edges that represents the augmenting path.
	 */
	public LinkedList<Edge> findAugmentingPath(Vertex source, Vertex sink) {
		LinkedList<Vertex> visitedVertecies = new LinkedList<Vertex>();
		LinkedList<Edge> edgesInPath = new LinkedList<Edge>();
		findAugmentingPath(source, sink, visitedVertecies, edgesInPath);
		return edgesInPath;
	}

	/**
	 * A method called recursively to find the augmenting path of the graph.
	 * 
	 * @param current The current vertex.
	 * @param sink The sink.
	 * @param visitedVertices A list containing the vertices that has already been visited.
	 * @param edgesInPath A list containing the edges in the (not-yet-complete) augmenting path.
	 * @return True if an edge was added to the path, otherwise false.
	 */
	private boolean findAugmentingPath(Vertex current, Vertex sink, LinkedList<Vertex> visitedVertices, LinkedList<Edge> edgesInPath) {
		visitedVertices.add(current);
		if (current.equals(sink)) {
			return true;
		} else {
			LinkedList<Edge> neighbourEdges = adjacencyMap.get(current);
			if (neighbourEdges != null) {
				for (Edge e : neighbourEdges) {
					if (!visitedVertices.contains(e.getTo())) {
						if (findAugmentingPath(e.getTo(), sink,
								visitedVertices, edgesInPath)) {
							edgesInPath.add(e);
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	/**
	 * Sets the flow of all edges in the graph to zero.
	 */
	public void setEdgesFlowToZero() {
		for (Map.Entry<Vertex, LinkedList<Edge>> entry : adjacencySet) {
			for (Edge e : entry.getValue()) {

				e.setFlow(0);
			}
		}
	}

	/**
	 * Returns a linked list containing all the edges of the graph with a flow
	 * higher than zero.
	 * 
	 * @return A linked list of edges.
	 */
	public LinkedList<Edge> getEdgesWithFlow() {
		LinkedList<Edge> edgeList = new LinkedList<Edge>();
		for (Map.Entry<Vertex, LinkedList<Edge>> entry : adjacencySet) {
			for (Edge e : entry.getValue()) {
				if (e.getFlow() > 0)
					edgeList.add(e);
			}
		}
		return edgeList;
	}

	/**
	 * Adds flow to a path of edges.
	 * 
	 * @param path The path to add flow to.
	 * @param augmentingPathCapacity The capacity of an augmenting path.
	 */
	public void addFlowToPath(LinkedList<Edge> path, int augmentingPathCapacity) {
		for (Edge e : path) {
			for (Edge neighbourEdge : adjacencyMap.get(e.getFrom())) {
				if (neighbourEdge.getTo().equals(e.getTo())) {
					neighbourEdge.addFlow(augmentingPathCapacity);
				}
			}
			//The sink node does not exist in the HashMap null-checker is necessary
			if(adjacencyMap.get(e.getTo()) != null){ 			
				for (Edge neighbourEdge : adjacencyMap.get(e.getTo())) {
					if (neighbourEdge.getTo().equals(e.getFrom())) {
						neighbourEdge.addFlow(-augmentingPathCapacity);
					}
				}
			}
		}
	}

	public boolean isBipartite() {
		LinkedList<Vertex> x = new LinkedList<Vertex>();
		for (Map.Entry<Vertex, LinkedList<Edge>> entry : adjacencySet)
			x.add(entry.getKey());

		for (Map.Entry<Vertex, LinkedList<Edge>> entry : adjacencySet)
			for (Edge e : entry.getValue())
				if (x.contains(e.getTo()))
					return false;

		return true;
	}

	@Override
	public String toString() {
		String s = "";
		for (Map.Entry<Vertex, LinkedList<Edge>> entry : adjacencySet) {
			for (Edge e : entry.getValue()) {
				s += "\n key: " + entry.getKey() + "          value :" + e;
			}
		}
		return s;
	}
}
