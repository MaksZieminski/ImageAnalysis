import java.util.ArrayList;
import java.util.Optional;

public class Point 
{
	private static int count;
	int id = 0;
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

	float x;
	float y;
	short[] features = new short[128];
	
	
	public Point()
	{
		id=count;
		count++;
	}
	
	public static void resetCounter(){
		count = 0;
	}
	
	public Point getNearestPoint(ArrayList<Point> points)
	{
		float nearestDistance = 1000000000;
		int nearestPointId = -1;
		
		for(int i=0; i<points.size(); i++)
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
				return points.get(i);
			}
		}
		
		return null;
	}
	
	
	private float getDistance(Point point)
	{
		float distance = (float) Math.hypot(this.x - point.x, this.y - point.y);
		return distance;
	}
	
	
}
