package operation;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import entity.Node;

public class NodeFactory {
	private static HashMap<String, Node> pool = new HashMap<String, Node>();
	public static String getKey(double x, double y)
	{
		return "node"+Math.floor(x/10)+""+Math.floor(y/10);
	}
	
	public static Node getNode(int id, double x, double y)
	{
		String key = getKey(x,y);
		if(pool.containsKey(key))
			return pool.get(key);
		
		Node node = new Node(id,x,y);
		pool.put(key, node);
		return node;	
	}
	public static Node getNode(double x, double y){
		String key = Math.floor(x/10)+""+Math.floor(y/10);
		if(pool.containsKey(key))
			return pool.get(key);
		
		Node node = new Node(x,y);
		pool.put(key, node);
		return node;	
	}
}
