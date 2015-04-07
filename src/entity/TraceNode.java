package entity;
import java.util.Hashtable;


public class TraceNode implements Comparable<TraceNode>{
	public int taxiid;
	public int time;
	public double lon;
	public double lat;
	
	public TraceNode(int taxiid, int time, double lon, double lat)
	{
		this.taxiid = taxiid;
		this.id = id;
		this.time = time;
		this.lon = lon;
		this.lat = lat;
	}
	
	public double distance(TraceNode t)
	{
		double sumsqure = Math.pow(this.lon-t.lon, 2)+Math.pow(this.lat-t.lat, 2);
		return Math.sqrt(sumsqure);
	}
	
	public String toString()
	{
		return String.format("%d %d %f %f", this.taxiid,this.time,this.lon, this.lat);
	}

	@Override
	public int compareTo(TraceNode arg0) {
		if(this.time-arg0.time>0)return 1;
		if(this.time-arg0.time<0)return -1;
		else return 0;
		//return this.time>arg0.time?1:-1;
	}

}
