package test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import graph.*;
import graphalgorithms.FordFulkerson;

public class FordFulkersonTest {
	public static void main(String[] args) {
		HashMap<Vertex, LinkedList<Edge>> lol = new HashMap<Vertex, LinkedList<Edge>>();
		Vertex a = new Vertex("a");
		Vertex b = new Vertex("b");
		Vertex c = new Vertex("c");
		Vertex d = new Vertex("d");
		Vertex e = new Vertex("e");
		LinkedList<Edge> al = new LinkedList<Edge>();
		al.add(new Edge(a, b, 5));
		al.add(new Edge(a, c, 5));
		LinkedList<Edge> bl = new LinkedList<Edge>();
		bl.add(new Edge(b, c, 1));
		bl.add(new Edge(b, d, 5));
		LinkedList<Edge> cl = new LinkedList<Edge>();
		cl.add(new Edge(c, d, 5));
//		cl.add(new Edge(c, e, 2));
		LinkedList<Edge> dl = new LinkedList<Edge>();
//		LinkedList<Edge> el = new LinkedList<Edge>();
		
		lol.put(a, al);
		lol.put(b, bl);
		lol.put(c, cl);
		lol.put(d, dl);
//		lol.put(e, el);
		
		Graph g = new Graph(lol);
		LinkedList<Edge> test = FordFulkerson.runAlgorithm(g, a, d);
		System.out.println("----------------------------------------------------------------------------");
		for(Edge edge : test){
			System.out.println(edge);
		}
		System.out.println("----------------------------------------------------------------------------");
		System.out.println(g);
	}
}
