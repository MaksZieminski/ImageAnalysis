import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.omg.CORBA.IMP_LIMIT;

public class ImageAnalysis {
	
	public static HashMap<Point, Point> getPointsPairs(Point[] points1, Point[] points2)
	{
		HashMap<Point, Point> mapOfPairs = new HashMap<Point, Point>();
		
		for(int i=0; i<points1.length; i++) 
		{
			Point pairForA = points1[i].getNearestPoint();
			Point pairForB = pairForA.getNearestPoint();
			
//			Point pointB = points1[i].calculateNearestPoint(new ArrayList<>(Arrays.asList(points2)));
//			Point pointA = pointB.calculateNearestPoint(new ArrayList<>(Arrays.asList(points1)));

//			if (points1[i] == pointA){
//				mapOfPairs.put(pointA, pointB);
//				System.out.println("A:" +pointA.id + " B:" + pointB.id);
//			}
			
			if(pairForA == pairForB.getNearestPoint()){
				mapOfPairs.put(points1[i], pairForA);
				System.out.println("A:" +points1[i].id + " B:" + pairForA.id);
			}
			
		}
		System.out.println(mapOfPairs.size());
		
		return mapOfPairs;
	}
			


	
	public static HashMap<Point, Point> getValidPairs(HashMap<Point, Point> pointPairs, int neighbours, float similarity){
		HashMap<Point, Point> validPairs = new HashMap<Point, Point>();
		
		ArrayList<Point> pointsA = new ArrayList<>(pointPairs.keySet());
		ArrayList<Point> pointsB = new ArrayList<>(pointPairs.values());
		
		for(Point key : pointPairs.keySet()){
			ArrayList<Point> points1Copy = new ArrayList<>(pointsA);
			ArrayList<Point> points2Copy = new ArrayList<>(pointsB);
			
			List<Point> nearestPointsA = new ArrayList<>();
			List<Point> nearestPointsB = new ArrayList<>();
			
			for(int i = 0; i < neighbours; i++){
				Point nearestA = pointsA.get(i).calculateNearestPoint(points1Copy);
				Point nearestB = pointsB.get(i).calculateNearestPoint(points2Copy);
				
				nearestPointsA.add(nearestA);
				nearestPointsB.add(nearestB);
				
				points1Copy.remove(nearestA);
				points2Copy.remove(nearestB);
			}
			int counter = 0;
			for(int i = 0; i < nearestPointsA.size(); i++){
				if(nearestPointsB.contains(pointPairs.get(nearestPointsA.get(i)))){
					counter++;
				}
			}
			if(counter / nearestPointsA.size() >= similarity)
				validPairs.put(key, pointPairs.get(key));
			
		}
		
		return validPairs;
	}
	
	
	public static void main(String[] args) throws FileNotFoundException {
		
		File img1 = new File("photos/rsz_dsc_0281.png.haraff.sift");
		File img2 = new File("photos/rsz_dsc_0282.png.haraff.sift");

		Point[] points1 = PointCreator.createPoints(img1);
		Point[] points2 = PointCreator.createPoints(img2);
		
		for(Point p : points1)
			p.calculateNearestPoint(new ArrayList<>(Arrays.asList(points2)));
		
		for(Point p : points2)
			p.calculateNearestPoint(new ArrayList<>(Arrays.asList(points1)));
		
		HashMap<Point, Point> pairs = ImageAnalysis.getPointsPairs(points1, points2);
		HashMap<Point, Point> validPairs = ImageAnalysis.getValidPairs(pairs, 3, 0.99f);
		System.out.println(validPairs.size());
	}

}
