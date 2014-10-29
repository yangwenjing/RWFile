package main;

import java.io.FileNotFoundException;
import java.io.IOException;

import operation.MatchNodeSegment;
import operation.ReadSegmentFile;
import operation.ReadWktFile;

public class getWktClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ReadWktFile readfile = new ReadWktFile();
			readfile.readfile(config_map.wkt_file);
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
