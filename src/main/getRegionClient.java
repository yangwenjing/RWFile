package main;

import java.io.FileNotFoundException;
import java.io.IOException;

import operation.ReadMatrixFile;

public class getRegionClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReadMatrixFile readMatrixFile = new ReadMatrixFile();
		try{
			readMatrixFile.readfile(config.txt_file_dir);
			
		}catch(FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
