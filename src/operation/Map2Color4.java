/**
 * 
 */
package operation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import main.config;
import entity.AdjRelationShip;
import entity.Cell;
import entity.Queue;

/**
 * @author Yang Wenjing
 * 
 * 采用广度遍历的方式
 *
 */
public class Map2Color4 extends AbsReadFile {
	public static List<Cell> cellList = new ArrayList<Cell>();//用于读取cell
	public static String out_file_path = config.output_file_dir;
	public static HashMap<String, Cell> cellsMap=new HashMap<String, Cell>();
	public static Queue<AdjRelationShip> queue = new Queue<AdjRelationShip>();//初始化队列
	public static int threshold = config.threshold;
	public static int regionScale = config.regionScale;
	
	public static int scaleCount = 0;
	
	public static String getKey(int x, int y) {
		String key = String.format("%d_%d", x,y);
		return key;
	}

	
	
	
	/* (non-Javadoc)
	 * @see operation.AbsReadFile#dealWithFile(java.io.File)
	 */
	@Override
	public void dealWithFile(File file) {
		// 主体处理函数
		FileReader fr;
		
		try {
			fr = new FileReader(file);
			BufferedReader reader = new BufferedReader(fr);
			String line;
			while((line = reader.readLine())!=null)
			{
				if(line=="")
					continue;
				
				String[]arrl = line.split("\t");
				int x = Integer.parseInt(arrl[0]);
				int y = Integer.parseInt(arrl[1]);
				int id = Integer.parseInt(arrl[3]);
				Cell cell = new Cell(x,y,0,id);
				String key = getKey(x,y);
				AdjRelationShip adj = getAdjRelationShip(id);
				cellsMap.put(key, cell);
			}
			reader.close();
			fr.close();
			
			/**
			 * 建立关系
			 */
			for(int x=0;x<100;x++)
			{
				for(int y=0;y<100;y++)
				{
					findNeighbors(x,y);
				}
			}
			
			File fileout = new File(out_file_path+"\\"+file.getName());
			System.out.println("处理节点文件"+file.getName());
			FileWriter fw = new FileWriter(fileout);
			for(int key:this.relationFactory.keySet())
			{
				AdjRelationShip adj = this.relationFactory.get(key);
				if(adj.color!=0)
					continue;
				
				queue.enqueue(adj);
				while(!queue.isEmpty())
				{
					adj=queue.dequeue();
					if(adj.color!=0)
					{
						continue;
					}
					else{
						adj.setColor();
					}
					for(AdjRelationShip a:adj.neighbors)
					{
						if(a.color==0)
							queue.enqueue(a);
					}
				}
				//fw.write(adj.toString()+"\r\n");
				//System.out.println(adj.toString());
			}
			
			/**
			 * 输出最后的值
			 */
			for(int x=0;x<100;x++)
			{
				for(int y=0;y<100;y++)
				{
					AdjRelationShip adj = getAdjRelationShipBy(x,y);
					fw.write(String.format("%d %d %d %d\r\n", x,y,adj.id, adj.color));
					//System.out.println(String.format("%d %d %d %d", x,y,adj.id,adj.color));
				}
				//fw.write(String.format("\r\n"));
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
	
	private void findNeighbors(int x, int y) {
		// TODO Auto-generated method stub
		String key = getKey(x,y);
		Cell ci = this.cellsMap.get(key);
		AdjRelationShip aAdj = getAdjRelationShip(ci.cluster);
		AdjRelationShip bAdj = findAdjCell(x,y-1);
		addRelations(aAdj,bAdj);
		bAdj = findAdjCell(x,y+1);
		addRelations(aAdj,bAdj);
		bAdj = findAdjCell(x-1,y);
		addRelations(aAdj,bAdj);
		bAdj = findAdjCell(x+1,y);
		addRelations(aAdj,bAdj);
	}

	private  AdjRelationShip findAdjCell(int x, int y) {
		// TODO Auto-generated method stub
		if(x<0||y<0||x>99||y>99)
		{
			return null;
		}
		
		Cell c = getCell(getKey(x,y));
		return getAdjRelationShip(c.cluster);
		
	}

	/***
	 * 对区域中的每个格子找其邻居
	 */
	public Cell getCell(String key)
	{
		if(!this.cellsMap.containsKey(key))
			return null;
		return this.cellsMap.get(key);
	}
	
	public AdjRelationShip getAdjRelationShipBy(int x,int y)
	{
		String key = getKey(x,y);
		Cell c = getCell(key);
		return getAdjRelationShip(c.cluster);
	}
	
	public static HashMap<Integer, AdjRelationShip> relationFactory=new HashMap<Integer, AdjRelationShip>();
	public AdjRelationShip getAdjRelationShip(int id)
	{
		if(!this.relationFactory.containsKey(id))
		{
			AdjRelationShip adj = new AdjRelationShip(id);
			this.relationFactory.put(id, adj);
			return adj;
		}
		
		return this.relationFactory.get(id);
	}
	
	
	public void addRelations(AdjRelationShip aAdj,AdjRelationShip bAdj)
	{
		if(aAdj==null||bAdj==null||aAdj.id==bAdj.id)
			return;
		if(!aAdj.neighbors.contains(bAdj))
			aAdj.neighbors.add(bAdj);
		if(!bAdj.neighbors.contains(aAdj))
			bAdj.neighbors.add(aAdj);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map2Color4 readMatrixFile = new Map2Color4();
		try{
			readMatrixFile.readfile(config.txt_file_dir);
			
		}catch(FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
