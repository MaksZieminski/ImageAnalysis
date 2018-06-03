import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PointCreator {
	
	public static Point[] createPoints(File img) throws FileNotFoundException{
		
		Scanner scanner = new Scanner(img);
		List<Point> points = new ArrayList<Point>();
		
		scanner.nextLine();
		scanner.nextLine();
		while(scanner.hasNextLine()){
			Point p = new Point();
			String line = scanner.nextLine();
			String[] values = line.split(" ");
			p.setX(Float.parseFloat(values[0]));
			p.setY(Float.parseFloat(values[1]));
			
			for(int i=5; i<133; i++) {
				
				p.features[i-5] = Short.parseShort(values[i]);
			}
			
			points.add(p);
			
			System.out.println("X:" + p.x + " Y:"+p.y);
			for(int i=0; i<p.features.length; i++)
			System.out.println("Feature-" +(i+1) + ": "+ p.features[i]);
		}
		
		scanner.close();
		
		return points.toArray(new Point[points.size()]);
	}
	
}
