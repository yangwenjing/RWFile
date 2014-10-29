package operation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import main.config;
import entity.Node;

public class ReadNodeFile extends AbsReadFile {
	public static String out_file_path = config.output_file_dir;

	public String dealNode(Node node){
		return null;
	}
	
	@Override
	public void dealWithFile(File file) {
		// TODO 读取node文件
		FileReader fr;
		try {
			fr = new FileReader(file);
		
			BufferedReader reader = new BufferedReader(fr);
			File fileout = new File(out_file_path+"\\"+file.getName());
			System.out.println("处理节点文件"+file.getName());
			FileWriter fw = new FileWriter(fileout);
			
			String line;
			while((line = reader.readLine())!=null)
			{
				if(line=="")
					continue;
				
				String[]arrl = line.split(" ");
				if(arrl.length!=3)
					continue;
				
				Node node = NodeFactory.getNode(Integer.parseInt(arrl[0]) ,Double.parseDouble(arrl[1]),
						Double.parseDouble(arrl[2]));
				
				String output = dealNode(node);
				fw.write(output);
				
			}
			
			reader.close();
			fr.close();
			fw.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
