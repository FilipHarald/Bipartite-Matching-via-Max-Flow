public class Vertex {
	String name;
	boolean visited;
	
	public Vertex(String name){
		this.name = name;
		visited = false;
	}
	
	public boolean getVisited(){
		return visited;
	}
	
	public void setVisited(boolean visited){
		this.visited = visited;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
//	vet inte om denna kommer behövas
//	@Override
//	public boolean equals(Object obj) {
//		if(obj instanceof Vertex){
//			return name.equals(((Vertex)obj).name);
//		}else{			
//			return super.equals(obj);
//		}
//	}
}
