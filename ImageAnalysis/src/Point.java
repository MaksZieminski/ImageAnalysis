import java.util.ArrayList;

public class Point 
{
	static int count;
	int id;
	float x;
	float y;
	short[] features = new short[128];
	
	
	Point()
	{
		id=count+1;
		count++;
	}
	
	private Point getNearestPoint(ArrayList<Point> points)
	{
		int nearestDistance = 1000000000;
		int nearestPointId = -1;
		
		for(int i=0; i<points.size(); i++)
		{
			if (nearestDistance > this.getDistance(points.get(i)))
			{
				
				nearestPointId = points.get(i).id;
				nearestDistance = this.getDistance(points.get(i));
			}
		}
		
		return points.get(nearestPointId);
	}
	
	private int getDistance(Point point)
	{
		
		return 0;
	}
	
	
}
