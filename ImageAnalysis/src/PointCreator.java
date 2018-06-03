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
			p.setX(scanner.nextFloat());
			p.setY(scanner.nextFloat());
			
			for(int i = 0; scanner.hasNext(); i++){
				p.features[i] = scanner.nextShort();
			}
			points.add(p);
		}
		scanner.close();
		
		return points.toArray(new Point[points.size()]);
	}
	
}
