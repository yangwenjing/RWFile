package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import operation.MatchNodeSegment;
import operation.ReadSegmentFile;


public class client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//读取地图文件
		
		try {
			ReadSegmentFile sgmFile = new ReadSegmentFile();
			sgmFile.readfile(config.segments_file_in_Name);
			
			System.out.println("读取道路: "+sgmFile.segments.size());
			
			MatchNodeSegment nodeFile = new MatchNodeSegment();
			nodeFile.setSegments(sgmFile.segments);
			System.out.println("设置道路完毕，开始节点文件！ ");
			nodeFile.readfile(config.nodes_files_in_dir);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
