package graph;
public class Vertex {
	private String name;
	
	public Vertex(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
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
}
