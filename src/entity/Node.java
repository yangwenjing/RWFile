package entity;

public class Node {
	public double x;
	public double y;
	public int id;
	private static int counter = 0;
	public Node(){
		setId();
	}
	
	public Node(double x, double y){
		this.x = x;
		this.y = y;
		setId();
				
	}
	
	public Node(int id,double x, double y)
	{
		this.id = id;
		this.x = x;
		this.y = y;
	}

	/**
	 * ◊‘∂Ø…Ë÷√id
	 */
	public void setId() {
		this.id = counter;
		counter++;
	}
	@Override
	public String toString(){
		return String.format("%.2f",((int)this.x)/10*10.0)+" "+String.format("%.2f",((int)this.y)/10*10.0);
		//return this.x+" "+this.y;
	}

}
