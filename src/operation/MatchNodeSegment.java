package operation;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import entity.Node;
import entity.Segment;

public class MatchNodeSegment extends ReadNodeFile {
	public List<Segment> segments = null;
	private static HashMap<String, Double>distance_pool = new HashMap<String, Double>();
	private static HashMap<String, Integer> id_pool = new HashMap<String, Integer>();
	
	public void setSegments(List<Segment> segments)
	{
		this.segments = segments;
	}
	
	@Override
	public String dealNode(Node node){
		//TODO 计算最短的segmentid, 最短距离
		//System.out.println("处理节点:"+node.id);
		String nodeinfo = node.toString();
		
		double min_dist = -1;
		
		int min_segment_id = -1;
		String key = NodeFactory.getKey(node.x, node.y);
		if(id_pool.containsKey(key))
		{
			min_dist = distance_pool.get(key);
			min_segment_id = id_pool.get(key);
		}
		else
		{
			
			for(Segment sgm:this.segments)
			{
				double dist = distance(node, sgm);
				if(dist==-1)
					continue;
				if(min_dist==-1||dist<min_dist)
				{
					min_dist = dist;
					min_segment_id = sgm.id;
				}
			}
			//System.out.println("match complete!..."+min_dist);
			id_pool.put(key, min_segment_id);
			distance_pool.put(key, min_dist);
			if(min_segment_id == -1)
				return null;
		}
		
		return nodeinfo+" "+min_segment_id+" "+min_dist+"\r\n";
	}
	
	
	
	private double distance(Node node, Segment segment)
	{
		if(segment.node1==null||segment.node2==null)
			return -1;
		return Distance.distToSegment(node, segment.node1, segment.node2);
	}

}
