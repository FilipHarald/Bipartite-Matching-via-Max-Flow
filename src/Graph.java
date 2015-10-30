import java.util.HashMap;
import java.util.LinkedList;

public class Graph {
	
	HashMap<Vertex, LinkedList<Vertex>> adjacencyMap;
	Edge[] edges;
	Vertex[] vertecies;
	
	public Graph(Edge[] edges,Vertex[] vertecies) {
		this.edges = edges;
		this.vertecies = vertecies;
		createAdjacencyMap();
	}
	
	public void createAdjacencyMap(){
		//TODO For now this method only adds adjacent Vertices in one direction. Don't know if it's correct.
		adjacencyMap = new HashMap<Vertex, LinkedList<Vertex>>();
		for(Edge e : edges){
			Vertex start = e.getStart();
			//For every start Vertex that DOES NOT already exist in adjacencyMap
			if(!adjacencyMap.containsKey(start)){
				//Add it to to adjacencyMap with an empty LinkedList
				adjacencyMap.put(start, new LinkedList<Vertex>());				
			}
		}
		for(Edge e : edges){
			//For every start Vertex, add it's correlating end Vertex to the starts LinkedList
			adjacencyMap.get(e.getStart()).push(e.getEnd());
		}
	}
	
	public Graph getResidualGraph(){
		return null;
	}
}
