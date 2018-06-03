import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ImageAnalysis {
	
	public static HashMap<Point, Point> getPointsPairs(Point[] points1, Point[] points2)
	{
		HashMap<Point, Point> map = new HashMap<Point, Point>();
		
		for(int i=0; i<points1.length; i++) 
		{
			Point pointB = points1[i].getNearestPoint(new ArrayList<>(Arrays.asList(points2)));
			Point pointA = pointB.getNearestPoint(new ArrayList<>(Arrays.asList(points1)));

			if (points1[i] == pointA){
				map.put(pointA, pointB);
				System.out.println("A:" +pointA.id + " B:" + pointB.id);
			}
			
		}
		System.out.println(map.size());
		
		return map;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		
		File img1 = new File("photos/rsz_dsc_0287.png.haraff.sift");
		File img2 = new File("photos/rsz_dsc_0288.png.haraff.sift");

		Point[] points1 = PointCreator.createPoints(img1);
		Point[] points2 = PointCreator.createPoints(img2);
		
		ImageAnalysis.getPointsPairs(points1, points2);
	}

	
}
