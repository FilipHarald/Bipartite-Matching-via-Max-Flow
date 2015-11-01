package test;

import graph.*;
import graphalgorithms.*;

import java.util.LinkedList;

/**
 * @author Filip Harald & Andreas Indal
 */
public class BipartiteMatchingTest {
	public static void main(String[] args) {
		Vertex[] v = new Vertex[9];
		for(int i = 0; i < v.length; i++){
			v[i] = new Vertex("" + i);
		}
		LinkedList<Edge> bipartiteGraph = new LinkedList<Edge>();
		bipartiteGraph.add(new Edge(v[0], v[5]));
		bipartiteGraph.add(new Edge(v[1], v[5]));
		bipartiteGraph.add(new Edge(v[1], v[7]));
		bipartiteGraph.add(new Edge(v[2], v[6]));
		bipartiteGraph.add(new Edge(v[2], v[7]));
		bipartiteGraph.add(new Edge(v[2], v[8]));
		bipartiteGraph.add(new Edge(v[3], v[7]));
		bipartiteGraph.add(new Edge(v[4], v[7]));
		
		for(Edge e : BipartiteMatching.runAlgorithm(bipartiteGraph)){
			System.out.println(e);
		}
	}
}
