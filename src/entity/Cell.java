package entity;

import java.util.Collections;

public class Cell implements Comparable<Cell>{
	public int x;
	public int y;
	public int num;
	public int cluster=-1;//代表所在的区域编号， 为-1时为没有编号。
	private static int clusterCount = 0;
	
	private boolean used;
	public static void addClusterCounter()
	{
		clusterCount++;
	}
	
	public void setCluster()
	{
		this.cluster=clusterCount;
	}
	
	public Cell(int x, int y, int num)
	{
		this.x = x;
		this.y = y;
		this.num = num;
		this.used = false;
	}
	
	public void setUsed()
	{
		this.used = true;
	}
	
	public boolean isUsed()
	{
		return this.used;
	}
	
	@Override
	public int compareTo(Cell c)
	{	
		return this.num>=c.num?-1:1;
	}
	
	public String toString()
	{
		return String.format("%d %d %d %d\r\n", this.x, this.y, this.num, this.cluster);
	}

	

}
