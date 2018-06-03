import java.io.File;
import java.io.FileNotFoundException;

public class ImageAnalysis {

	public static void main(String[] args) throws FileNotFoundException {
		
		File img1 = new File("photos/rsz_dsc_0279.png.haraff.sift");
		File img2 = new File("photos/rsz_dsc_0280.png.haraff.sift");

		Point[] points1 = PointCreator.createPoints(img1);
	
		
		//System.out.println("elo, sprawdz to: " + points1[0].getX() + " " + points1[0].getY() + " " + points1[0].features.length);
		

	}

}
