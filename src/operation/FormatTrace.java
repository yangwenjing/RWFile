package operation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;

import main.config;
import entity.TraceNode;

public class FormatTrace extends AbsReadFile {
	public Hashtable<Integer, TraceNode> traces = null;
	public Hashtable<Integer, TraceNode> outputTraces = null;
	public static String out_file_path = config.formated_trace_dir;
	@Override
	public void dealWithFile(File file) {
		traces = new Hashtable<Integer, TraceNode>();
		outputTraces = new Hashtable<Integer, TraceNode>();
		// TODO Auto-generated method stub
		FileReader fr;
		
		try {
			fr = new FileReader(file);
			BufferedReader reader = new BufferedReader(fr);
		
			String line;
			
			System.out.println("处理节点文件"+file.getName());
		
		
			//FileWriter fw = new FileWriter(fileout);
			while((line = reader.readLine())!=null)
			{
				String[] s = line.split(" ");
				int taxiid = Integer.parseInt(s[0]);
				int time = Integer.parseInt(s[1]);
				double lon = Double.parseDouble(s[2]);
				double lat = Double.parseDouble(s[3]);
				TraceNode tn = new TraceNode(taxiid, time, lon, lat);
				traces.put(time, tn);
			}
			
			reader.close();
			fr.close();
			
			
			for(int tix=0;tix<=39600;tix+=15)
			{
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			

	}
	
	public TraceNode getMiddleTN(int time, TraceNode tn1, TraceNode tn2)
	{
		int t1 = time - tn1.time;
		int t2 = tn2.time-time;
		
		if(t1==0)return tn1;
		if(t2==0)return tn2;
		if(t1+t2==0)return null;
		int taxiid = tn1.taxiid;
		double lon = (tn2.lon-tn1.lon)*t1/(t1+t2)+tn1.lon;
		double lat = (tn2.lat-tn1.lat)*t1/(t1+t2)+tn1.lat;
		
		return new TraceNode(taxiid,time,lon,lat);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
