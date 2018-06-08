import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PointCreator {
	
	public static Point[] createPoints(File img) throws FileNotFoundException{
		
		Point.resetCounter();
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
			
			ArrayList<Integer> features = new ArrayList<>();
			
			for(int i=5; i<133; i++) {
				features.add(Integer.parseInt(values[i]));
			}
			p.setFeatures(features);
			points.add(p);
		}
		
		scanner.close();
		
		return points.toArray(new Point[points.size()]);
	}
	
}
