import java.util.ArrayList;
import java.util.Optional;

public class Point 
{
	private static int count;
	int id = 0;
	float x;
	float y;
	short[] features = new short[128];
	Point nearestPoint;
	
	public Point()
	{
		id=count;
		count++;
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

	public Point getNearestPoint() {
		return nearestPoint;
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
				nearestPoint =points.get(i);
				return points.get(i);
			}
		}
		return null;
	}
	
	private int getDistance(Point point)
	{
		int distance = 0;
		
		for(int i = 0; i < point.features.length; i++){
			distance += Math.abs(point.features[i] - this.features[i]);
		}
		
		return distance;
	}
}
