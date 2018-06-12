import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Set;

import javax.imageio.ImageIO;

public class ImageAnalysis {

public static void main(String[] args) throws IOException {
		
		File img1 = new File("photos/rsz_dsc_0285.png.haraff.sift");
		File img2 = new File("photos/rsz_dsc_0286.png.haraff.sift");

//		File img1 = new File("photos/a.png.haraff.sift");
//		File img2 = new File("photos/b.png.haraff.sift");
		
		
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
		
		int neigbours =10 ;
		
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
		double similarityThreshold = 0.8;
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
		
		System.out.println("Spójne s¹siedztwo, pary: " + counter);
	
		Ransac ransac = new Ransac(pairPoints);
		ransac.run(300, 10, 300);
		
		paintPairs("rsz_dsc_0285.png", "rsz_dsc_0286.png", "85-86 RANSAC.png", ransac.getBestPoints());
		
		
	
		
	}
private static void paintPairs(String image1Name, String image2Name, String outImageName, ArrayList<Point> pairPoints) throws IOException {
    BufferedImage image1 = ImageIO.read(new File("photos/"+image1Name));
    BufferedImage image2 = ImageIO.read(new File("photos/"+image2Name));
    
    if (image1.getHeight() != image2.getHeight() || image1.getWidth() != image2.getWidth()) {
        System.out.println("Failed to save images. Different sizes");
        return;
    }

    BufferedImage outImage = new BufferedImage(image1.getWidth(), image1.getHeight() * 2, BufferedImage.TYPE_INT_ARGB);

    Graphics graphics = outImage.getGraphics();
    graphics.drawImage(image1, 0, 0, null);
    graphics.drawImage(image2, 0, image1.getHeight(), null);

    Random rand = new Random();

    for (Point p: pairPoints) {
        graphics.setColor(new Color((int)(Math.random() * 0x1000000)));
        graphics.drawLine((int)p.getX(), (int)p.getY(), (int)p.getNearestPoint().getX(), image1.getHeight() + (int)p.getNearestPoint().getY());
    }

    ImageIO.write(outImage, "PNG", new File("photos/"+outImageName));

}


}