package operation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import main.config;

public class ICTFormat extends AbsReadFile {
	public static String out_file_path = config.ict_out_put;
	
	public class ICT{
		public double time;
		public double frequency;
		
		public ICT(double time, double frequency)
		{
			this.time = time;
			this.frequency = frequency;
		}
		
		public String toString()
		{
			return String.format("%f %f", this.time,this.frequency);
		}
	}



	@Override
	public void dealWithFile(File file) {
		// TODO Auto-generated method stub
		FileReader fr;
		List<ICT> icts =  new LinkedList<ICT>();
		
		List<ICT>output_icts =  new LinkedList<ICT>();
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
				
				double time = Double.parseDouble(s[0]);
				double fre = Double.parseDouble(s[1]);
				
				ICT ict = new ICT(time,fre);
				icts.add(ict);
				
			}
			reader.close();
			fr.close();
			
			double max = 0;
			ICT max_ict=icts.get(0);
			int index=0;
			while(index<icts.size())
			{
				while(index<icts.size()&&max<icts.get(index).frequency)
				{
					max=icts.get(index).frequency;
					max_ict = icts.get(index);
					index++;
				}
				
				while(output_icts.size()>1&&output_icts.get(output_icts.size()-1).frequency<max)
				{
					output_icts.remove(output_icts.size()-1);
				}
				
				output_icts.add(max_ict);
				max=0;
				index++;
			}
			
			/**
			 * 打印
			 */
			System.out.println("Print:"+file.getName());
			File fileout = new File(out_file_path +"\\"+file.getName());
			FileWriter fw = new FileWriter(fileout, true);
			for(ICT ict:output_icts)
			{
				fw.write(ict.toString()+"\r\n");
			}
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
		ICTFormat ft = new ICTFormat();
		
		try {
			ft.readfile(config.ict_in_put);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
