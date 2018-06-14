import java.util.ArrayList;

import Jama.Matrix;
import java.lang.Object;

public class Ransac {

	private ArrayList<Point> pairPoints;
	
	
	
	public Ransac(ArrayList<Point> pairPoints) {
		this.pairPoints = pairPoints;
		run(10);
	}
	
	public double EstimateError(Point point, int a, int b, int c, int d, int e, int f) 
	{
		double error = Math.hypot( (a * point.getX() + b * point.getY() + c) 
	        		- point.getNearestPoint().getX(),
	        		(d * point.getX() + e * point.getY() + f)
	        		- point.getNearestPoint().getY());
		
		return error;
	}
	
	public void run(int iterations){
		
		float x1, y1, x2, y2, x3, y3;
		float u1, v1, u2, v2, u3, v3;
		double a,b,c,d,e,f;
		double matrixTemp1[][] = new double[6][6];
		double matrixTemp2[][] = new double[1][6];
		
		Matrix matrixA;
		Matrix matrixB;
		Matrix modelParameterMatrix;
		
		for(int i = 0; i < iterations; i++){
			
			for(int p1 = 0; p1 < pairPoints.size(); p1++){
				
				for(int p2 = p1 + 1; p2 < pairPoints.size(); p2++){
					
					for(int p3 = p2 + 1; p3 < pairPoints.size(); p3++){
						
								matrixTemp1[0][0] = pairPoints.get(p1).getX();
								matrixTemp1[1][0] = pairPoints.get(p1).getY();
								matrixTemp1[2][0] = 1;
								
								matrixTemp1[0][1] = pairPoints.get(p2).getX();
								matrixTemp1[1][1] = pairPoints.get(p2).getY();
								matrixTemp1[2][1] = 1;
								
								matrixTemp1[0][2] = pairPoints.get(p3).getX();
								matrixTemp1[1][2] = pairPoints.get(p3).getY();
								matrixTemp1[2][2] = 1;
						
								matrixTemp1[3][3] = pairPoints.get(p1).getX();
								matrixTemp1[4][3] = pairPoints.get(p1).getY();
								matrixTemp1[5][3] = 1;
								
								matrixTemp1[3][4] = pairPoints.get(p2).getX();
								matrixTemp1[4][4] = pairPoints.get(p2).getY();
								matrixTemp1[5][4] = 1;
								
								matrixTemp1[3][5] = pairPoints.get(p3).getX();
								matrixTemp1[4][5] = pairPoints.get(p3).getY();
								matrixTemp1[5][5] = 1;
								
								
								matrixTemp2[0][0] = pairPoints.get(p1).nearestPoint.getX();
								matrixTemp2[0][1] = pairPoints.get(p2).nearestPoint.getX();
								matrixTemp2[0][2] = pairPoints.get(p3).nearestPoint.getX();
								matrixTemp2[0][3] = pairPoints.get(p1).nearestPoint.getY();
								matrixTemp2[0][4] = pairPoints.get(p2).nearestPoint.getY();
								matrixTemp2[0][5] = pairPoints.get(p3).nearestPoint.getY();
								
								matrixA = new Matrix(matrixTemp1);
								matrixB = new Matrix(matrixTemp2);
								
							//modelParameterMatrix =
								matrixA.inverse();
								
								//modelParameterMatrix = matrixA.inverse().arrayTimes(matrixB);
								modelParameterMatrix = matrixB.arrayTimesEquals(matrixA.inverse());
								
								
								a=modelParameterMatrix.get(0, 1);
								b=modelParameterMatrix.get(0, 2);
								c=modelParameterMatrix.get(0, 3);
								d=modelParameterMatrix.get(0, 4);
								e=modelParameterMatrix.get(0, 5);
								f=modelParameterMatrix.get(0, 6);
								
								
								//estimateScore(a
								
					}
				}
			}
		}
			
	}

}
