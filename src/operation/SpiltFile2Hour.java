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

import main.config;

/**
 * @author yangwenjing01
 *
 */
public class SpiltFile2Hour extends AbsReadFile {
	public static String out_file_path = config.split2Hours_out;

	/**
	 * ����һ���ļ���ÿ���ļ��а���24Сʱ�����ݣ�����ʱ���ֶΣ�ÿ��Сʱ��Ϊһ���ļ���
	 */
	@Override
	public void dealWithFile(File file) {
		FileReader fr;
		
		try {
			fr = new FileReader(file);
			BufferedReader reader = new BufferedReader(fr);
		
			String line;
			
			System.out.println("�����ļ�"+file.getName());

			while((line = reader.readLine())!=null)
			{
				String[] s = line.split("\t");
				String period = s[5];
				
				File fileout = new File(out_file_path+"\\"+period+"-"+file.getName());
//				File fileout = null;
				FileWriter fw = new FileWriter(fileout, true);
//				System.out.println(String.format("%s %d %s %s\r\n", taxiid,time,lon,lat));
				//taxiidHt.get(taxiid).append((String.format("%s %d %s %s\r\n", taxiid,time,lon,lat)));
				fw.write(line+"\r\n");
				fw.close();
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
		//����ʵ��
		SpiltFile2Hour rwf = new SpiltFile2Hour();
		try {
			rwf.readfile(config.split2Hours_in);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
