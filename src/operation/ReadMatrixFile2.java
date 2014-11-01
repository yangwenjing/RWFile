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

import entity.Cell;
import entity.Queue;

/**
 * @author Yang Wenjing
 * 
 * 采用广度遍历的方式
 *
 */
public class ReadMatrixFile2 extends AbsReadFile {
	public static List<Cell> cellList = new ArrayList<Cell>();//用于读取cell
	public static String out_file_path = config.output_file_dir;
	public static HashMap<String, Cell> cellsMap=new HashMap<String, Cell>();
	public static Queue<Cell> queue = new Queue<Cell>();//初始化队列
	public static int threshold = config.threshold;
	
	public static String getKey(int x, int y) {
		String key = String.format("%d_%d", x,y);
		return key;
	}

	/* (non-Javadoc)
	 * @see operation.AbsReadFile#dealWithFile(java.io.File)
	 */
	@Override
	public void dealWithFile(File file) {
		// TODO Auto-generated method stub
		FileReader fr;
		// TODO Auto-generated method stub
		try {
			fr = new FileReader(file);
			BufferedReader reader = new BufferedReader(fr);
			String line;
			while((line = reader.readLine())!=null)
			{
				if(line=="")
					continue;
				
				String[]arrl = line.split("\t");
				int x = Integer.parseInt(arrl[1]);
				int y = Integer.parseInt(arrl[2]);
				int num = Integer.parseInt(arrl[0]);
				
				Cell c = new Cell(x,y,num);
				if(pass(c))
				{
					String key = getKey(x,y);
					cellsMap.put(key, c);
					cellList.add(c);
				}
				
				
			}
			reader.close();
			fr.close();
			
			/**
			 * 区域识别算法
			 */
			cluster();
			
			/**
			 * 打印结果到文件
			 */
			printArr(cellList,file);

			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private void printArr(List<Cell> cellList2, File file) throws IOException {
		// TODO Auto-generated method stub
		File fileout = new File(out_file_path+"\\"+file.getName());
		System.out.println("处理节点文件"+file.getName());
		FileWriter fw = new FileWriter(fileout);
		String key;
		for(int i=0;i<100;i++)
		{
			for(int j=0;j<100;j++)
			{
				key = getKey(i,j);
				if(cellsMap.containsKey(key))
				{
					Cell c = cellsMap.get(key);
					fw.write(c.toString()+"\r\n");
				}
				else{
					fw.write(i+" "+j+" 0 "+-1+"\r\n\r\n");
				}
			}
		}
		fw.close();
		
	}

	private boolean pass(Cell c) {
		// TODO Auto-generated method stub
		return c.num>=threshold?true:false;
	}

	/**
	 * 区域识别的核心算法
	 */
	public void cluster() {
		Collections.sort(cellList);
		
		String key;
		for(Cell c:cellList)
		{
			if(!c.isUsed())
			{
				Cell.addClusterCounter();
				c.setCluster();
				queue.enqueue(c);
				while(!queue.isEmpty())
				{
					Cell pcell = queue.dequeue();
					pcell.setUsed();
					enqueueNext(pcell.x, pcell.y-1);
					enqueueNext(pcell.x-1,pcell.y);
					enqueueNext(pcell.x,pcell.y+1);
					enqueueNext(pcell.x+1,pcell.y);
						
				}
			}
		}
	}

	/**
	 * @param c
	 * @param x
	 * @param y
	 */
	public void enqueueNext(int x, int y) {
		String key;
		key = getKey(x,y);
		
		if(cellsMap.containsKey(key))
		{
			Cell next=cellsMap.get(key);
			if(!next.isUsed())
			{
				next.setCluster();
				queue.enqueue(next);
			}
		}
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReadMatrixFile2 readMatrixFile = new ReadMatrixFile2();
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
