package operation;

import java.util.HashMap;

import entity.Neighbor;
import entity.Node;
import entity.Segment;

public class SegmentFactory {

	private static HashMap<String, Segment> pool = new HashMap<String, Segment>();
	public static HashMap<Integer, Neighbor> neighbors = new HashMap<Integer, Neighbor>();
	
	public static Segment getSegment(Node node1, Node node2){
		String key = node1.id>node2.id?node2.id+" "+node1.id:node1.id+" "+node2.id;
		if(pool.containsKey(key))
			return pool.get(key);
		
		addNeighbors(node1, node2);
		addNeighbors(node2,node1);
		
		Segment sgm = new Segment();
		sgm.node1 = node1;
		sgm.node2 = node2;
		pool.put(key, sgm);
		return sgm;	
	}

	private static void addNeighbors(Node node1, Node node2) {
		// TODO Auto-generated method stub
		if(!neighbors.containsKey(node1.id))
		{
			Neighbor nb = new Neighbor();
			nb.node = node1;
			nb.nodes.add(node2);
			neighbors.put(node1.id,nb);
			
		}
		else{
			if(!neighbors.get(node1.id).nodes.contains(node2))
			{
				neighbors.get(node1.id).nodes.add(node2);
			}
		}
		
	}
}
