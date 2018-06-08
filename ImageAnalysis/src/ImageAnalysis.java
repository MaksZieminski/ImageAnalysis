import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ImageAnalysis {

public static void main(String[] args) throws FileNotFoundException {
		
		File img1 = new File("photos/rsz_dsc_0285.png.haraff.sift");
		File img2 = new File("photos/rsz_dsc_0286.png.haraff.sift");

		Point[] points1 = PointCreator.createPoints(img1);
		Point[] points2 = PointCreator.createPoints(img2);
		
		System.out.println(points1.length);
		
		for(int i = 0; i < points1.length; i++){
			points1[i].calculateNearestPoint(new ArrayList<>(Arrays.asList(points2)));
			points2[i].calculateNearestPoint(new ArrayList<>(Arrays.asList(points1)));
		}
		
		ArrayList<Point> nearestPoints1 = new ArrayList<>();
		ArrayList<Point> nearestPoints2 = new ArrayList<>();
		
		int neigbours = 2;
		
		for(int i = 0; i < points1.length; i++){
			 points1[i].calculateNearestPoints(new ArrayList<>(Arrays.asList(points1)), neigbours);
		}
		for(int i = 0; i < points2.length; i++){
			points2[i].calculateNearestPoints(new ArrayList<>(Arrays.asList(points2)), neigbours);
		}
		
		
		
		ArrayList<Point> pairPoints = new ArrayList<>();
		for(int i = 0; i < points1.length; i++){
			if(points1[i].nearestPoint.nearestPoint == points1[i]){
				pairPoints.add(points1[i]);
			}
		}
		
		System.out.println("p1: " + points1.length + " p2: " + points2.length + " pairs: " + pairPoints.size());
		
		
		int similarPointsCounter = 0;
		double similarity = 0.5;
		for(int i = 0; i < pairPoints.size(); i++){
			for(int neigbour = 0; neigbour < neigbours; neigbour++){
				if(pairPoints.get(i).getNearestPoint().getNearestPoints().contains
						(pairPoints.get(i).getNearestPoints().get(neigbour)))
				{
					similarPointsCounter++;
				}
			}
		}
		
		System.out.println("counter = " + (((double)similarPointsCounter)));
	
		
		
	}
}