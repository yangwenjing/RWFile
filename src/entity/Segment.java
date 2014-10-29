package entity;

public class Segment {
	public Node node1;
	public Node node2;
	public int id;
	private static int counter=0;
	
	public Segment(){
		this.id = counter;
		counter ++;
		this.node1 = this.node2 = null;
	}
	
	public void addNode(Node node){
		if(node1==null)
			node1 = node;
		else if(node2==null)
			node2 = node;
	}
	
	
	
}
