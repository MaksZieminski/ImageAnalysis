import java.io.File;

public class ImageAnalysis {

	public static void main(String[] args) {
		
<<<<<<< Upstream, based on branch 'master' of https://github.com/MaksZieminski/ImageAnalysis.git
		File img1 = new File("/ImageAnalysis/photos/rsz_dsc_0279.png");
		File img2 = new File("/ImageAnalysis/photos/rsz_dsc_0280.png");
=======
		File img1 = new File("photos/rsz_dsc_0279.png.haraff.sift");
		File img2 = new File("photos/rsz_dsc_0280.png.haraff.sift");
>>>>>>> 4c632ae pointCreator
		
<<<<<<< Upstream, based on branch 'master' of https://github.com/MaksZieminski/ImageAnalysis.git
=======
		Point[] points1 = PointCreator.createPoints(img1);
	
		
		System.out.println("elo, sprawdz to: " + points1[0].getX() + " " + points1[0].getY() + " " + points1[0].features.length);
>>>>>>> 4c632ae pointCreator
		

	}

}
