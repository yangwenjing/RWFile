package operation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import main.config_map;

public class ReadWktFile extends AbsReadFile {
	public static String out_file_path = config_map.txt_file_dir;
	
	@Override
	public void dealWithFile(File file) {
		// TODO Auto-generated method stub
		FileReader fr;
		
			try {
				fr = new FileReader(file);
				BufferedReader reader = new BufferedReader(fr);
				String line;
				File fileout = new File(out_file_path+"\\"+file.getName());
				System.out.println("处理节点文件"+file.getName());
				FileWriter fw = new FileWriter(fileout);
				while((line = reader.readLine())!=null)
				{
					if("".equals(line.trim()))
					{
						continue;
					}
					line = line.replace("LINESTRING", "").replace("(", "").replace(")", "");
					String[] lines = line.split(",");
					
					fw.write(lines[0].trim()+"\r\n");
					fw.write(lines[1].trim()+"\r\n");
					fw.write("\r\n");
					
					
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
		

	}

}
