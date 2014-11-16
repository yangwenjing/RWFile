package operation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import main.config;

import entity.TraceNode;

public class FormatTrace_Step2 extends AbsReadFile {
	public static String out_file_path = config.contactsOutFile_step2;
	public static List<TraceNode> traces = null;
	public static List<TraceNode> output_traces = null;
	/**
	 * 读入一个文件
	 * 对数据排序
	 * 对数据进行插值
	 */
	@Override
	public void dealWithFile(File file) {
		// TODO 读入文件
		FileReader fr;
		traces =  new LinkedList<TraceNode>();
		output_traces =  new LinkedList<TraceNode>();
		try {
			fr = new FileReader(file);
			BufferedReader reader = new BufferedReader(fr);
		
			String line;
			
			System.out.println("处理节点文件"+file.getName());
		
			
			//读入数据
			System.out.println("读文件"+file.getName());
			while((line = reader.readLine())!=null)
			{
				String[] s = line.split(" ");
				int taxiid = Integer.parseInt(s[0]);
				int time = Integer.parseInt(s[1]);
				double lon = Double.parseDouble(s[2]);
				double lat = Double.parseDouble(s[3]);
				traces.add(new TraceNode(taxiid,time,lon,lat));
			}
			reader.close();
			fr.close();
			
			//排序
			System.out.println("排序:"+file.getName());
			Collections.sort(traces);
			
			//插值
			System.out.println("insert:"+file.getName());
			for(int i=11*3600;i<14*3600;i+=10)
			{
				int index=0;
				TraceNode tn = null;
				
				do{
					tn = getTraceNode(index,i);
					index++;
				}while(tn==null&&index<=traces.size()-3);
				output_traces.add(tn);
			}
			
			
			/**
			 * 打印
			 */
			System.out.println("Print:"+file.getName());
			File fileout = new File(out_file_path +"\\"+file.getName());
			FileWriter fw = new FileWriter(fileout, true);
			for(TraceNode tn:output_traces)
			{
				if(tn==null)continue;
				fw.write(tn.toString()+"\r\n");
			}
			fw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public TraceNode getTraceNode(int begin, int time)
	{
		TraceNode tn0 = traces.get(begin);
		TraceNode tn1 = traces.get(begin+1);
		TraceNode tn2 = traces.get(begin+2);
		
		if(tn0.time==time)return tn0;
		if(tn1.time==time)return tn1;
		if(tn2.time==time)return tn2;
		
		if(tn0.time<time&tn1.time<time&tn2.time<time)
		{
			return null;
		}	
		
		if(tn0.time>time&tn1.time>time&tn2.time>time)
		{
			if(tn0.time!=tn1.time)
				return getThirdTN(tn0, tn1, time);
			
			if(tn1.time!=tn2.time)
				return getThirdTN(tn1, tn2, time);
			
			return null;
		}
		
		if((tn0.time<time)^(tn1.time<time))
			return getThirdTN(tn0, tn1, time);
		
		if((tn1.time<time)^(tn2.time<time))
			return getThirdTN(tn1, tn2, time);
	
		return null;
	}
	
	private TraceNode getThirdTN(TraceNode tn1, TraceNode tn2, int time) {
		// TODO Auto-generated method stub
		double lon = (tn2.lon-tn1.lon)*(double)(time-tn1.time)/(double)(tn2.time-tn1.time)+tn1.lon;
		double lat = (tn2.lat-tn1.lat)*(double)(time-tn1.time)/(double)(tn2.time-tn1.time)+tn1.lon;
		
		return new TraceNode(tn1.id, time, lon,lat);
	}

	public static void main(String[] args)
	{
		FormatTrace_Step2 ft = new FormatTrace_Step2();
		
		try {
			ft.readfile(config.contactsOutFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
