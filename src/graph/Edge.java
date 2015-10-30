package graph;

public class Edge {
	private Vertex from;
	private Vertex to;
	private int capacity;
	private int flow;
	

	public Edge(Vertex from, Vertex to, int capacity) {
		this.from = from;
		this.to = to;
		this.capacity = capacity;
		flow = 0;
	}
	
	public Vertex getFrom() {
		return from;
	}
	
	public Vertex getTo() {
		return to;
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
	
	public void addFlow(int flow) {
		this.flow += flow;
	}
	
	public int getCapacityFlowDifference(){
		return capacity - flow;
	}
	
	public Edge getResidualEdge(){
		return (new Edge(from, to, getCapacityFlowDifference()));
	}
	

	public Edge getAntiParallelEdge(){
		return (new Edge(to, from, flow));
	}
	
	@Override
	public String toString() {
		return "Edge [from=" + from + ", to=" + to + ", capacity=" + capacity
				+ ", flow=" + flow + "]";
	}

}
