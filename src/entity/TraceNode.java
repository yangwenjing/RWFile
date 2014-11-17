package entity;

import java.util.Hashtable;

public class TraceNode implements Comparable<TraceNode>{
	public int id;
	public int time;
	public double lon;
	public double lat;
	
//	public static Hashtable<Integer,Integer>idHt = new Hashtable<Integer,Integer>();
//	
//	public static int counter = -1;

	public TraceNode(int id, int time, double lon, double lat) {
		// TODO Auto-generated constructor stub
//		if(!idHt.contains(id))
//		{
//			idHt.put(counter, id);
//			counter++;
//		}
//		
		this.id = id;
		this.time = time;
		this.lon = lon;
		this.lat = lat;
	}


	@Override
	public int compareTo(TraceNode arg0) {
		// TODO Auto-generated method stub
		return this.time>arg0.time?1:-1;
	}
	
	public String toString()
	{
		return String.format("%d %d %f %f", this.id, this.time,this.lon, this.lat);
	}

}
