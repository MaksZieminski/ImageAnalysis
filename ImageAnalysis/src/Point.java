import java.util.ArrayList;

public class Point {
	private static int count;
	private int id;
	private float x;
	private float y;
	private ArrayList<Integer> features = new ArrayList<>();
	private ArrayList<Point> nearestPoints = new ArrayList<>();
	
	

	
	Point nearestPoint;
	
	public Point(){
		id=count;
		count++;
	}
	
	public static void resetCounter(){
		count = 0;
	}

	public Point calculateNearestPoint(ArrayList<Point> points)
	{
		int nearestDistance = this.getDistance(points.get(0));
		int nearestPointId = points.get(0).id;
		
		for(int i=1; i<points.size(); i++)
		{
			if (nearestDistance > this.getDistance(points.get(i)))
			{
				nearestPointId = points.get(i).id;
				nearestDistance = this.getDistance(points.get(i));
			}
		}
		
		for(int i=0; i<points.size(); i++)
		{
			if(points.get(i).id==nearestPointId)
			{
				nearestPoint = points.get(i);
				return points.get(i);
			}
		}
		
		
		return null;
	}

	public ArrayList<Point> calculateNearestPoints(ArrayList<Point> points, int n){
		ArrayList<Point> nearestPoints = new ArrayList<>();
		
		int nearestDistance = this.getDistance(points.get(0));
		Point nearestPoint = points.get(0);
		int nearestIndex = 0;
		
		for (int c = 0; c < n; c++) {
			nearestDistance = this.getDistance(points.get(c));
			for (int i = 1; i < points.size(); i++) {
				if (nearestDistance > this.getDistance(points.get(i))) {
					nearestPoint = points.get(i);
					nearestDistance = this.getDistance(points.get(i));
					nearestIndex=i;
				}
			}
			nearestPoints.add(nearestPoint);
			points.remove(nearestIndex);
		}
		this.nearestPoints = nearestPoints;
		return nearestPoints;
	}
	
	public int getDistance(Point point)
	{
		int distance = 0;
		for(int i = 0; i < point.getFeatures().size(); i++){
			distance += Math.abs(point.getFeatures().get(i) - this.getFeatures().get(i));
		}
		return distance;
	}
	
	
	

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getId() {
		return id;
	}

	public ArrayList<Integer> getFeatures() {
		return features;
	}
	
	public void setFeatures(ArrayList<Integer> features) {
		this.features = features;
	}

	public Point getNearestPoint() {
		return nearestPoint;
	}
	
	public ArrayList<Point> getNearestPoints() {
		return nearestPoints;
	}

	public void setNearestPoints(ArrayList<Point> nearestPoints) {
		this.nearestPoints = nearestPoints;
	}

	public boolean isValidPair(Point p){
			if(p.nearestPoint == this.nearestPoint)
				return true;
			else
				return false;
	}

}




