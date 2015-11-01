package graph;

/**
 * A class representing a vertex in a graph.
 */
public class Vertex {
	private String name;

	/**
	 * Creates a new vertex.
	 * @param name The name of the vertex.
	 */
	public Vertex(String name){
		this.name = name;
	}

	/**
	 * Returns the name of the vertex.
	 * @return The name of the vertex.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the vertex.
	 * @param name The vertex’ new name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Vertex){
			return name.equals(((Vertex)obj).name);
		}else{			
			return super.equals(obj);
		}
	}
	@Override
	public String toString() {
		return "Vertex [name=" + name + "]";
	}
}
