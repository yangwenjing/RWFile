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
	public static String out_file_dir = "C:\\Users\\chnhideyoshi\\Desktop\\road\\map2009_out_2.wkt";
	public static String out_file_dir_2 = "C:\\Users\\chnhideyoshi\\Desktop\\road\\";
	
	public List<RoadNode> used_nodes = new ArrayList<RoadNode>();
	public List<RoadNode> output_nodes = new ArrayList<RoadNode>();
	public Queue<RoadNode>queue = new Queue<RoadNode>();
	public File fileout = new File(out_file_dir);
	
	public void findMap() throws IOException
	{
		FileWriter fw = new FileWriter(fileout);
		File out1 = new File(out_file_dir_2+"out_type1.txt");
		File out2 = new File(out_file_dir_2+"out_type2.txt");
		File out3 = new File(out_file_dir_2+"out_type3.txt");
		File out4 = new File(out_file_dir_2+"out_type4.txt");
		FileWriter fw_t1 = new FileWriter(out1);
		FileWriter fw_t2 = new FileWriter(out2);
		FileWriter fw_t3 = new FileWriter(out3);
		FileWriter fw_t4 = new FileWriter(out4);
		String key = RoadNode.nodes.keys().nextElement();
		RoadNode node = RoadNode.nodes.get(key);
		queue.enqueue(node);
		while(!queue.isEmpty())
		{
			node = queue.dequeue();
			this.used_nodes.add(node);//出队列就会被设置为已用
			if(node.neighbor==null)
				continue;
			int i=0;
			while(i<node.neighbor.size())
			{
				RoadNode neinode = node.neighbor.get(i);
				if(neinode.neighbor.contains(node))
				{
					System.out.println(node.toString()+"\r\n"+neinode.toString()+"\r\n");
					String s=String.format("LINESTRING (%s, %s)\r\n\r\n", node.toString(),neinode.toString());
					fw.write(s);
					neinode.neighbor.remove(node);
					
					if(!this.output_nodes.contains(neinode))
					{	
						String s2 = String.format("POINT (%s)\r\n\r\n", neinode.toString()); 
						
						if(neinode.type==1)
						{
							fw_t1.write(s2);
						}
						else if(neinode.type==2)
						{
							fw_t2.write(s2);
						}
						else if(neinode.type==3)
						{
							fw_t3.write(s2);
						}
						else{
							fw_t4.write(s2);
						}
						this.output_nodes.add(neinode);
					}
				}
				
				if(!this.used_nodes.contains(neinode))
				{
					queue.enqueue(neinode);
				}
				i++;
			}
			
			node.neighbor.clear();//将所有的邻居都去掉。
			if(this.output_nodes.contains(node))
				continue;
			String s = String.format("POINT (%s)\r\n\r\n", node.toString());
			if(node.type==1)
			{
				fw_t1.write(s);
			}
			else if(node.type==2)
			{
				fw_t2.write(s);
			}
			else if(node.type==3)
			{
				fw_t3.write(s);
			}
			else{
				fw_t4.write(s);
			}
			this.output_nodes.add(node);
			
		}
		fw.close();
		fw_t1.close();
		fw_t2.close();
		fw_t3.close();
		fw_t4.close();
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
			//File fileout = new File(out_file_dir+"//"+file.getName());
			//FileWriter fw = new FileWriter(fileout);
			
			String line;
			RoadNode node = null;
			int road_id_bk = -1;
			RoadNode node_next = null;
			while((line = reader.readLine())!=null)
			{
				String s[] = line.trim().split("\t");
				double lon = Double.parseDouble(s[0]);
				double lat = Double.parseDouble(s[1]);
				int road_id = Integer.parseInt(s[2]);
				int type = Integer.parseInt(s[3]);
				node = node_next;
				node_next = RoadNode.Factory(lon, lat);
				if(node_next.type>type)
				{
					node_next.type = type;
				}
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
			//End 读文件完毕
			
			System.out.println("文件读入完毕！\r\n开始输出连通地图");
			
			findMap();
			
			
			//fw.close();
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
		String dir_in = "C:\\Users\\chnhideyoshi\\Desktop\\road\\map2009.txt";
		ExtractSubMap readfile = new ExtractSubMap();
		try{
			readfile.readfile(dir_in);
			
		}catch(FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

	}

}
