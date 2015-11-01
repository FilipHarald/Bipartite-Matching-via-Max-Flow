package graph;

public class Edge {
	private Vertex from;
	private Vertex to;
	private int capacity;
	private int flow;


	/**
	 * Creates a new Edge between two vertices.
	 * @param from The starting vertex.
	 * @param to The ending vertex.
	 * @param capacity The capacity of the edge.
	 */
	public Edge(Vertex from, Vertex to, int capacity) {
		this.from = from;
		this.to = to;
		this.capacity = capacity;
		flow = 0;
	}

	/**
	 * Creates a new Edge between two vertices, with the capacity and flow of zero.
	 * @param from The starting vertex.
	 * @param to The ending vertex.
	 */
	public Edge(Vertex from, Vertex to) {
		this.from = from;
		this.to = to;
		capacity = 0;
		flow = 0;
	}

	/**
	 * Returns the vertex from which the edge starts.
	 * @return Vertex
	 */
	public Vertex getFrom() {
		return from;
	}

	/**
	 * Returns the vertex to where the edge ends.
	 * @return Vertex
	 */
	public Vertex getTo() {
		return to;
	}

	/**
	 * Returns the capacity of the edge.
	 * @return The capcity of the edge.
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * Sets the capacity to the edge and returns the edge.
	 * @param capacity The new capacity.
	 * @return <img src="http://static.starity.hu/images/celebs/200x300/david-howell-evans-111813532623854369.jpg" alt="The Edge">
	 */
	public Edge setCapacity(int capacity) {
		this.capacity = capacity;
		return this;
	}

	/**
	 * Returns the flow of the edge.
	 * @return The flow of the edge.
	 */
	public int getFlow() {
		return flow;
	}

	/**
	 * Sets the flow of the edge.
	 * @param flow The new flow.
	 */
	public void setFlow(int flow) {
		this.flow = flow;
	}

	/**
	 * Adds flow to the edge.
	 * @param flow The flow to add.
	 */
	public void addFlow(int flow) {
		this.flow += flow;
	}

	/**
	 * Returns the difference between the capacity and the flow.
	 * @return The difference between the capacity and the flow.
	 */
	public int getCapacityFlowDifference(){
		return capacity - flow;
	}

	/**
	 * Returns the residual edge of the current edge.
	 * @return The residual edge of the current edge.
	 */
	public Edge getResidualEdge(){
		return (new Edge(from, to, getCapacityFlowDifference()));
	}

	/**
	 * Returns the anti parallel edge of the current edge.
	 * @return The anti parallel edge of the current edge.
	 */
	public Edge getAntiParallelEdge(){
		return (new Edge(to, from, flow));
	}
	
	@Override
	public String toString() {
		return "Edge [from=" + from + ", to=" + to + ", capacity=" + capacity
				+ ", flow=" + flow + "]";
	}

}
