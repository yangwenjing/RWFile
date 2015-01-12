package entity;

import java.util.Hashtable;
import java.util.List;

public class RoadNode {
	public double x;
	public double y;
	//public int road_id;
	//public int type;
	public String id;
	
	public List<RoadNode>neighbor;
	
	public static Hashtable<String,RoadNode> nodes = null;
	/**
	 * @param args
	 */
	public RoadNode(double lon,double lat, String id){
		this.x = lon;
		this.y = lat;
		this.id = id;
	}
	
	public static String getNodeId(double lon,double lat)
	{
		
		String s = (int)(lon)+"_"+(int)(lat);
		return s;
	}
	
	public void addNeighbor(RoadNode node)
	{
		if(!this.neighbor.contains(node))
		{
			this.neighbor.add(node);
		}	
	}
	
	
	public static RoadNode Factory(double lon, double lat)
	{
		if(nodes==null)
		{
			nodes = new Hashtable<String,RoadNode>();
		}
		String key = getNodeId(lon,lat);
		if(nodes.containsKey(key))
		{
			return nodes.get(key);
		}
		else{
			RoadNode roadnode = new RoadNode(lon,lat,key);
			nodes.put(key, roadnode);
			return roadnode;
		}
	}

}