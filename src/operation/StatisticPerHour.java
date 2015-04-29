package operation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import main.config;

public class StatisticPerHour extends AbsReadFile {
	public static String out_file_path = config.statistic_out;
	/**
	 * 暂不使用，结构体，用于统计前10个
	 * @author yangwenjing01
	 *
	 */
	class EventCell{
		public int x;
		public int y;
		public int events;
	}

	private static String log2(String filename,int event_sum,int cells_count,double event_avg,int max,String max_x,String max_y){
		String outStr = filename+"\r\n*****\r\n";
		outStr+="event_sum:\t"+event_sum+"\r\n";
		outStr+="cells_count:\t"+cells_count+"\r\n";
		outStr+="event_avg:\t"+event_avg+"\r\n";
		outStr+="x-y-max event:\r\n";
		outStr+=max_x+"-"+max_y+"-"+max+"\r\n";
		return outStr;
	}
	
	private static String log(String filename,int event_sum,int cells_count,double event_avg,int max,String max_x,String max_y){
		String outStr = filename+"\t"+event_sum+"\t"+cells_count+"\t"+event_avg+"\t"+max+"\t"+max_x+"\t"+max_y;
		return outStr;
	}
	/**
	 * 针对每个文件，统计总的事件数，平均事件数，最大事件数
	 */
	@Override
	public void dealWithFile(File file) {
		
		int event_sum=0;
		int cells_count=0;
		double event_avg=0;
		int max=0;
		String max_x="";
		String max_y="";
		FileReader fr;
		
		try {
			fr = new FileReader(file);
			BufferedReader reader = new BufferedReader(fr);
		
			String line;
			
			System.out.println("处理文件"+file.getName());

			while((line = reader.readLine())!=null)
			{
				String[] s = line.split("\t");
				int event_num= Integer.parseInt(s[3]);
				event_sum += event_num;
				cells_count++;
				//判断是否是最大值
				if(event_num>max)
				{
					max=event_num;
					max_x = s[0];
					max_y = s[1];
					
				}
				

			}
			
			File fileout = new File(out_file_path);
			FileWriter fw = new FileWriter(fileout, true);
			String outStr = log(file.getName(),event_sum,cells_count,(double)event_sum/(120.0*120.0),max,max_x,max_y);
			fw.write(outStr+"\r\n");
			fw.close();
			
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
		
		StatisticPerHour rwf = new  StatisticPerHour();
		
		try {
			rwf.readfile(config.split2Hours_out);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
