package main;

import java.io.FileNotFoundException;
import java.io.IOException;

import operation.TransLonLat2MeterReadFile;

public class getTranse2MeterClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TransLonLat2MeterReadFile readfile = new TransLonLat2MeterReadFile();
		try {
			readfile.readfile(config_map_trans.txt_file_dir);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
