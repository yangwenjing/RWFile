package operation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import main.config;

public class ReadEventDis extends AbsReadFile {

	public String out_file_path = config.event_dis_out;
	@Override
	public void dealWithFile(File file) {
		// TODO Auto-generated method stub
		FileReader fr;
		
		try {
			fr = new FileReader(file);
			BufferedReader reader = new BufferedReader(fr);
		
			String line;
			
			System.out.println("处理节点文件"+file.getName());
			File fileout = new File(out_file_path+"\\"+file.getName());
			FileWriter fw = new FileWriter(fileout, true);
		
			//FileWriter fw = new FileWriter(fileout);
			while((line = reader.readLine())!=null)
			{
				String[] s = line.split("\t");
				int event = Integer.parseInt(s[0]);
				int lon = Integer.parseInt(s[1]);
				int lat = Integer.parseInt(s[2]);
				
				if(lon>90||lat<20)
				{
					event=0;
				}
				fw.write(String.format("%d %d %d\r\n", event,lon,lat));
			}
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReadEventDis eventDisFile = new ReadEventDis();
		try {
			eventDisFile.readfile(config.event_dis_in);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
