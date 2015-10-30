package graph;

public class Edge {
	private Vertex start;
	private Vertex end;
	private int capacity;
	private int flow;
	

	public Edge(Vertex start, Vertex end, int capacity) {
		this.start = start;
		this.end = end;
		this.capacity = capacity;
		flow = 0;
	}
	
	public Vertex getStart() {
		return start;
	}
	
	public Vertex getEnd() {
		return end;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public int getFlow() {
		return flow;
	}
	
	public void setFlow(int flow) {
		this.flow = flow;
	}
	
	public int getCapacityFlowDifference(){
		return capacity - flow;
	}
	
	public Edge getResidualEdge(){
		return (new Edge(start, end, getCapacityFlowDifference()));
	}
	
	public Edge getAntiParallelEdge(){
		return (new Edge(end, start, flow));
	}

}
