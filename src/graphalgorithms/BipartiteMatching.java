package graphalgorithms;

import graph.*;

import java.util.HashSet;
import java.util.LinkedList;

public class BipartiteMatching {
	public static LinkedList<Edge> runAlgorithm(LinkedList<Edge> bipartiteEdges) {
		Vertex s = new Vertex("Source");
		Vertex t = new Vertex("Sink");
		Graph g = new Graph();

		HashSet<Vertex> x = new HashSet<Vertex>();
		HashSet<Vertex> y = new HashSet<Vertex>();

		for (Edge e : bipartiteEdges) {
			g.addEdge(e.setCapacity(1));

			if (!(x.contains(e.getFrom()))) {
				g.addEdge(new Edge(s, e.getFrom(), 1));
				x.add(e.getFrom());
			}
			if (!(y.contains(e.getTo()))) {
				g.addEdge(new Edge(e.getTo(), t, 1));
				y.add(e.getTo());
			}
		}

		g.generateAdjacencySet();
		LinkedList<Edge> edgesWithFlow = FordFulkerson.runAlgorithm(g, s, t);

		Edge[] edgeArray = edgesWithFlow
				.toArray(new Edge[edgesWithFlow.size()]);
		LinkedList<Edge> bipartiteMatchedEdges = new LinkedList<Edge>();

		for (int i = 0; i < edgeArray.length; i++) {
			Edge e = edgeArray[i];
			if (!(e.getFrom().equals(s) || e.getTo().equals(t))) {
				bipartiteMatchedEdges.add(e);
			}
		}
		return bipartiteMatchedEdges;
	}
}
