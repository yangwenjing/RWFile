package operation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import main.config;

public class Read2FormatTrace extends AbsReadFile {
	public static String out_file_path = config.contactsOutFile;
	public static Hashtable<String,FileWriter> taxiidHt = new Hashtable<String, FileWriter>();
	//public static List<File> outfiles = new ArrayList<File>();


	@Override
	public void dealWithFile(File file) {
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
				String[] s = line.split("\t");
				String taxiid = s[0];
				int time = Integer.parseInt(s[1]);
				String lon = s[2];
				String lat = s[3];
				if(!taxiidHt.contains(taxiid))
				{
					File fileout = new File(out_file_path+"\\"+taxiid+".txt");
					FileWriter fw = new FileWriter(fileout, true);
					taxiidHt.put(taxiid, fw);
				}
				
				taxiidHt.get(taxiid).write(String.format("%s %d %s %s\n", taxiid,time,lon,lat));
			}
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
		Read2FormatTrace r2ft = new Read2FormatTrace();
		
		try {
			r2ft.readfile(config.trace_files_in);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
