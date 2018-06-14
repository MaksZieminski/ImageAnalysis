import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ImageAnalysis {

	public static void main(String[] args) throws FileNotFoundException {

		File img1 = new File("photos/rsz_dsc_0283.png.haraff.sift");
		File img2 = new File("photos/rsz_dsc_0284.png.haraff.sift");

		Point[] points1 = PointCreator.createPoints(img1);
		Point[] points2 = PointCreator.createPoints(img2);

		System.out.println(points1.length);

		
		for (int i = 0; i < points1.length; i++) {
			points1[i].calculateNearestPoint(new ArrayList<>(Arrays.asList(points2)));
		}
		for (int i = 0; i < points2.length; i++) {
			points2[i].calculateNearestPoint(new ArrayList<>(Arrays.asList(points1)));
		}

		ArrayList<Point> nearestPoints1 = new ArrayList<>();
		ArrayList<Point> nearestPoints2 = new ArrayList<>();

		//PANEL ADMINISTRACYNY
		int neigbours = 8;
		double similarity = 0.4;
		//
		
		for (int i = 0; i < points1.length; i++) {
			points1[i].calculateNearestPoints(new ArrayList<>(Arrays.asList(points1)), neigbours);
		}
		for (int i = 0; i < points2.length; i++) {
			points2[i].calculateNearestPoints(new ArrayList<>(Arrays.asList(points2)), neigbours);
		}

		ArrayList<Point> pairPoints = new ArrayList<>();
		for (int i = 0; i < points1.length; i++) {
			if (points1[i].nearestPoint.nearestPoint == points1[i]) {
				pairPoints.add(points1[i]);
			}
		}

		System.out.println("p1: " + points1.length + " p2: " + points2.length + " pairs: " + pairPoints.size());
		int counter = 0;
		int similarPointsCounter = 0;
		
		
		for (int i = 0; i < pairPoints.size(); i++) {
			similarPointsCounter = 0;
			for (int neigbour = 0; neigbour < neigbours; neigbour++) {
				
				for (int k = 0; k < neigbours; k++) {
					if (pairPoints.get(i).getNearestPoint().getNearestPoints().get(k).getNearestPoint() == pairPoints
							.get(i).getNearestPoints().get(neigbour))
						similarPointsCounter++;
				}

			}
			if (((double) similarPointsCounter / neigbours) >= similarity) {
				counter++;
			}
		}
		System.out.println("Współczynnik podobieństwa dwóch obrazów = " + (((double) counter/pairPoints.size())));
		//System.out.println("Ile par przeszlo: " + counter);
		//System.out.println("Ile par spełniło warunek : " + ((double)counter/pairPoints.size()));
		
		Ransac ransac = new Ransac(pairPoints);  
		
	}
}