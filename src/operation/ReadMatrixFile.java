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
import entity.Cell;

import main.config;

/**
 * @author Yang Wenjing
 *
 */
public class ReadMatrixFile extends AbsReadFile {
	public static String out_file_path = config.output_file_dir;
	public static Cell matrix[][] = new Cell[100][];
	public static int clusterScale = 0;
	
	public static HashMap<String, Cell> denseCells=new HashMap<String, Cell>();
	public static int threshold = config.threshold;
	/* (non-Javadoc)
	 * @see operation.AbsReadFile#dealWithFile(java.io.File)
	 * 用于处理100*100的event文件
	 */
	@Override
	public void dealWithFile(File file) {
		FileReader fr;
		// TODO Auto-generated method stub
		try {
			/***
			 * 初始化二维数组
			 */
			for(int i=0;i<100;i++)
			{
				matrix[i] = new Cell[100];
				for(int j=0;j<100;j++)
				{
					matrix[i][j] = new Cell(i,j,0);
				}
			}
			fr = new FileReader(file);
			BufferedReader reader = new BufferedReader(fr);
			String line;
			while((line = reader.readLine())!=null)
			{
				if(line=="")
					continue;
				
				String[]arrl = line.split("\t");
				setCell(arrl);
			}
			reader.close();
			fr.close();
			
			/**
			 * 对matix进行聚类
			 */
			cluster();
			
			/**
			 * 打印
			 */
			printArr(matrix,file);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void printArr(Cell[][] matrix2, File file) throws IOException {
		// TODO Auto-generated method stub
		File fileout = new File(out_file_path+"\\"+file.getName());
		System.out.println("处理节点文件"+file.getName());
		FileWriter fw = new FileWriter(fileout);
		for(int i=0;i<100;i++)
		{
			for(int j=0;j<100;j++)
			{
				fw.write(matrix[i][j].toString()+"\r\n");
			}
		}
		fw.close();
		
	}
	//用于分组迭代
	private static void cluster()
	{
		while(denseCells.size()>0)
		{
			Object[] keys = null;
			keys = denseCells.keySet().toArray();
			Cell.addClusterCounter();
			clusterScale=0;
			getSubCluster((String) keys[0]);
			
		}
	}
	
	private static void getSubCluster(String key) {
		// TODO Auto-generated method stub
		if(clusterScale>200)
			return;
		clusterScale++;
		
		Cell c = denseCells.get(key);
		c.setCluster();
		denseCells.remove(key);
		key = getKey(c.x, c.y-1);
		if(isDenseCell(key))
			getSubCluster(key);
		key = getKey(c.x, c.y+1);
		if(isDenseCell(key))
			getSubCluster(key);
		key = getKey(c.x-1, c.y);
		if(isDenseCell(key))
			getSubCluster(key);
		key = getKey(c.x+1, c.y);
		if(isDenseCell(key))
			getSubCluster(key);
		
	}
	private static boolean isDenseCell(String key) {
		return denseCells.containsKey(key);
		
	}
	/**
	 * @param x
	 * @param y
	 * @return
	 */
	public static String getKey(int x, int y) {
		String key = String.format("%d_%d", x,y);
		return key;
	}
	private static void setCell(String[] arrl) {
		// TODO Auto-generated method stub
		int x = Integer.parseInt(arrl[1]);
		int y = Integer.parseInt(arrl[2]);
		int num = Integer.parseInt(arrl[0]);
		matrix[x][y] = new Cell(x,y,num);
		
		if(num>threshold){
			String key = getKey(x, y);
			denseCells.put(key,matrix[x][y]);
			
		}
	}

}
