import java.util.HashMap;
import java.util.LinkedList;

public class Graph {
	
	private HashMap<Vertex, LinkedList<Vertex>> adjacencyMap;
	private Edge[] edges;
	
	public Graph(Edge[] edges) {
		this.edges = edges;
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
		LinkedList<Edge> residualEdges = new LinkedList<Edge>();
		for(Edge e : edges){
			if((e.getCapacity() - e.getFlow()) != 0){
				residualEdges.add(new Edge(e.getStart(), e.getEnd(), e.getCapacity() - e.getFlow()));
			}
			residualEdges.add(new Edge(e.getEnd(), e.getStart(), e.getFlow()));
		}
		return new Graph(residualEdges.toArray(new Edge[residualEdges.size()]));
	}
}
