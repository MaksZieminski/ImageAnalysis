import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class ImageAnalysis {

public static void main(String[] args) throws FileNotFoundException {
		
		File img1 = new File("photos/rsz_dsc_0281.png.haraff.sift");
		File img2 = new File("photos/rsz_dsc_0282.png.haraff.sift");

		Point[] points1 = PointCreator.createPoints(img1);
		Point[] points2 = PointCreator.createPoints(img2);
		
		for(int i = 0; i < points1.length; i++){
			points1[i].calculateNearestPoint(new ArrayList<>(Arrays.asList(points2)));
			points2[i].calculateNearestPoint(new ArrayList<>(Arrays.asList(points1)));
		}
		
		int neigbours = 4;
		
		ArrayList<Point> nearestPoints1 = new ArrayList<>();
		ArrayList<Point> nearestPoints2 = new ArrayList<>();
		
		for(int i = 0; i < points1.length; i++){
			 nearestPoints1 = points1[i].calculateNearestPoints(new ArrayList<>(Arrays.asList(points2)), neigbours);
			 nearestPoints2 = points2[i].calculateNearestPoints(new ArrayList<>(Arrays.asList(points1)), neigbours);
		}
		
		int similarPointsCounter = 0;
		
		for(Point p : nearestPoints1){
			if(nearestPoints2.contains(p))
				similarPointsCounter++;
		}
		
		System.out.println("ratio = " + ((similarPointsCounter/neigbours)));
	
		
		
	}
}