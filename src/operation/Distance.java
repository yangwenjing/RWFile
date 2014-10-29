package operation;

import entity.Node;

public class Distance {
	private static  double dist(Node n1, Node n2){
		return Math.sqrt(Math.pow(n1.x-n2.x,2)+Math.pow(n1.y-n2.y,2));
	}
	
	public static double distToSegment(Node p, Node v, Node w)
	{
		double l2 = dist(v,w);
		if (l2==0) return dist(p,w);
		double t = ((p.x - v.x) * (w.x - v.x) + (p.y - v.y) * (w.y - v.y)) / l2;
		if(t<0) return dist(p,v);
		if(t>1) return dist(p,w);
		Node n3 = new Node();
		n3.x = v.x+t*(w.x-v.x);
		n3.y = v.y+t*(w.y-v.y);
		return dist(p,n3);
	}

}
