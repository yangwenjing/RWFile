package operation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import entity.RoadNode;
import entity.Queue;

public class ExtractSubMap extends AbsReadFile {
	public static String out_file_dir = "";
	public List<RoadNode> used_nodes = new ArrayList<RoadNode>();
	public Queue<RoadNode>queue = new Queue<RoadNode>();
	public void findMap()
	{
		RoadNode node = null;
		queue.enqueue(node);
		while(!queue.isEmpty())
		{
			
		}
		
	}
	
	
	
	/**
	 * 初始化，建立图
	 */
	@Override
	public void dealWithFile(File file) {
		// TODO Auto-generated method stub
		FileReader fr;
		
		try {
			fr = new FileReader(file);
			BufferedReader reader = new BufferedReader(fr);
			File fileout = new File(out_file_dir+"//"+file.getName());
			FileWriter fw = new FileWriter(fileout);
			
			String line;
			RoadNode node = null;
			int road_id_bk = -1;
			RoadNode node_next = null;
			while((line = reader.readLine())!=null)
			{
				String s[] = line.split(" ");
				double lon = Double.parseDouble(s[0]);
				double lat = Double.parseDouble(s[1]);
				int road_id = Integer.parseInt(s[2]);
				int type = Integer.parseInt(s[3]);
				node = node_next;
				node_next = RoadNode.Factory(lon, lat);
				if(road_id_bk==road_id)
				{
					if(node!=null)
					{
						node.addNeighbor(node_next);
						node_next.addNeighbor(node);
					}
				}
				road_id_bk=road_id;
				
		
			}
			
			fw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
