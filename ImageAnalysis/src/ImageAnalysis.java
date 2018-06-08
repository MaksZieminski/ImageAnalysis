import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class ImageAnalysis {

public static void main(String[] args) throws FileNotFoundException {
		
		File img1 = new File("photos/rsz_dsc_0283.png.haraff.sift");
		File img2 = new File("photos/rsz_dsc_0284.png.haraff.sift");

		Point[] points1 = PointCreator.createPoints(img1);
		Point.resetCounter();
		Point[] points2 = PointCreator.createPoints(img2);
		
		System.out.println(points1.length);
		
		for(int i = 0; i < points1.length; i++){
			points1[i].calculateNearestPoint(new ArrayList<>(Arrays.asList(points2)));
		}
		
		for(int i = 0; i < points2.length; i++){
			points2[i].calculateNearestPoint(new ArrayList<>(Arrays.asList(points1)));
		}
		
		ArrayList<Point> nearestPoints1 = new ArrayList<>();
		ArrayList<Point> nearestPoints2 = new ArrayList<>();
		
		int neigbours = 10;
		
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
		
		int counter = 0;
		int similarPointsCounter = 0;
		double similarityThreshold = 0.7;
		for(int i = 0; i < pairPoints.size(); i++){
			for(int j = 0; j < neigbours; j++){
				if(pairPoints.get(i).getNearestPoints().contains(pairPoints.get(i).getNearestPoint().getNearestPoints().get(j).getNearestPoint())){
					similarPointsCounter++;
				}
			}
			if((double)(similarPointsCounter)/neigbours >= similarityThreshold){
				counter++;
			}
			similarPointsCounter = 0;
			
		}
		
		System.out.println("counter = " + counter);
		System.out.println("Ratio: " + (double)counter/pairPoints.size());
	
		
		
		
		Ransac ransac = new Ransac(pairPoints);
		
		
		
		
		
		
		
		
		
		
		
	}
}