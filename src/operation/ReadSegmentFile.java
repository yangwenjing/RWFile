package operation;


import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import entity.Segment;
import entity.Node;

public class ReadSegmentFile extends AbsReadFile {
	public List<Segment> segments = new ArrayList<Segment>();
	@Override
	public void dealWithFile(File file) {
		/**
		 * ¶ÁµØÍ¼ÎÄ¼þ
		 */
		FileReader fr;
		try {
			fr = new FileReader(file);
		
			BufferedReader reader = new BufferedReader(fr);
			String line;
			
			while((line = reader.readLine())!=null)
			{
				String line2 = reader.readLine();
				String line3 = reader.readLine();
				
				
				String[]arrl = line.split(" ");
				String[]arrl2 = line2.split(" ");
				
				if(arrl.length!=2)
					continue;
				if(arrl2.length!=2)
					continue;
				
				Node node1 = NodeFactory.getNode(Double.parseDouble(arrl[0]),
						Double.parseDouble(arrl[1]));
				Node node2 = NodeFactory.getNode(Double.parseDouble(arrl2[0]),
						Double.parseDouble(arrl2[1]));
				Segment sgm = SegmentFactory.getSegment(node1, node2);
				if(this.segments.contains(sgm))
					continue;
				this.segments.add(sgm);
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
