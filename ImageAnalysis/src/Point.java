import java.util.ArrayList;
import java.util.Optional;

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
	
	private Optional<Point> getNearestPoint(ArrayList<Point> points)
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
		
		final int pointId = nearestPointId;
		return points.stream().findAny().filter(p->p.id==pointId);
		
	
	}
	
	private float getDistance(Point point)
	{
		
		float distance = (float) Math.sqrt((this.x - point.x)*(this.x - point.x) - (this.y - point.y)*(this.y - point.y));
		return distance>0? distance : -distance;
	}
	
	
}
