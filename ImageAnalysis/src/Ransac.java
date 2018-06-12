import java.util.ArrayList;

import javax.swing.plaf.basic.BasicBorders.MarginBorder;

import Jama.Matrix;

public class Ransac {

	private ArrayList<Point> pairPoints;
	
	private ArrayList<Point> ransacPoints;
	
	private ArrayList<Point> bestPoints;
	
	public ArrayList<Point> getBestPoints() {
		return bestPoints;
	}

	public Ransac(ArrayList<Point> pairPoints) {
		this.pairPoints = pairPoints;
	}
	
	public void run(int iterations, double min, double max){
		
		int threshold = 8;
		
		double a, b, c, d, e, f;
		
		double matrixTemp1[][] = new double[6][6];
		double matrixTemp2[] = new double[6];
		
		Matrix matrixA;
		Matrix matrixB;
		Matrix modelParameterMatrix = null;
		double error = 0; 
		int minCounter = Integer.MAX_VALUE;
		int maxCounter = 0;
		int counter = 0;
		double distance = 0;
		
		for(int i = 0; i < 30; i++){
			
			for(int p1 = 0; p1 < pairPoints.size(); p1++){
				
				for(int p2 = p1 + 1; p2 < pairPoints.size(); p2++){
					
					distance = Math.hypot(pairPoints.get(p1).getX()-pairPoints.get(p2).getX(), pairPoints.get(p1).getY()-pairPoints.get(p2).getY());
					if(distance < min || distance > max ){
						continue;
					}
					
					for(int p3 = p2 + 1; p3 < pairPoints.size(); p3++){
						
						distance = Math.hypot(pairPoints.get(p1).getX()-pairPoints.get(p3).getX(), pairPoints.get(p1).getY()-pairPoints.get(p3).getY());
						if(distance < min || distance > max ){
							continue;
						}
						
						distance = Math.hypot(pairPoints.get(p2).getX()-pairPoints.get(p3).getX(), pairPoints.get(p2).getY()-pairPoints.get(p3).getY());
						if(distance < min || distance > max ){
							continue;
						}
						
						
								matrixTemp1[0][0] = pairPoints.get(p1).getX();
								matrixTemp1[0][1] = pairPoints.get(p1).getY();
								matrixTemp1[0][2] = 1;
								
								matrixTemp1[1][0] = pairPoints.get(p2).getX();
								matrixTemp1[1][1] = pairPoints.get(p2).getY();
								matrixTemp1[1][2] = 1;
								
								matrixTemp1[2][0] = pairPoints.get(p3).getX();
								matrixTemp1[2][1] = pairPoints.get(p3).getY();
								matrixTemp1[2][2] = 1;
						
								matrixTemp1[3][3] = pairPoints.get(p1).getX();
								matrixTemp1[3][4] = pairPoints.get(p1).getY();
								matrixTemp1[3][5] = 1;
								
								matrixTemp1[4][3] = pairPoints.get(p2).getX();
								matrixTemp1[4][4] = pairPoints.get(p2).getY();
								matrixTemp1[4][5] = 1;
								
								matrixTemp1[5][3] = pairPoints.get(p3).getX();
								matrixTemp1[5][4] = pairPoints.get(p3).getY();
								matrixTemp1[5][5] = 1;
								
								matrixTemp2[0] = pairPoints.get(p1).nearestPoint.getX();
								matrixTemp2[1] = pairPoints.get(p2).nearestPoint.getX();
								matrixTemp2[2] = pairPoints.get(p3).nearestPoint.getX();
								matrixTemp2[3] = pairPoints.get(p1).nearestPoint.getY();
								matrixTemp2[4] = pairPoints.get(p2).nearestPoint.getY();
								matrixTemp2[5] = pairPoints.get(p3).nearestPoint.getY();
								
								matrixA = new Matrix(matrixTemp1);
								matrixB = new Matrix(matrixTemp2, 1);
								
								if(matrixA.det() != 0){
									modelParameterMatrix = matrixA.inverse();
									matrixB = matrixB.transpose();
									modelParameterMatrix = modelParameterMatrix.times(matrixB);
									
//									modelParameterMatrix.print(1, 1);
									a = modelParameterMatrix.get(0, 0);
									b = modelParameterMatrix.get(1, 0);
							        c = modelParameterMatrix.get(2, 0);
							        d = modelParameterMatrix.get(3, 0);
							        e = modelParameterMatrix.get(4, 0);
							        f = modelParameterMatrix.get(5, 0);
							        
//							        double distance = Math.hypot(x1-x2, y1-y2);
							        
							        counter = 0;
							        ransacPoints = new ArrayList<>();
							        for(int point = 0; point < pairPoints.size(); point++){
							        	
							        	error = estimateError(pairPoints.get(point), a, b, c, d, e, f);
							        	
							        	if(error <= threshold){
							        		counter++;
							        		ransacPoints.add(pairPoints.get(point));
							        	}
							        }
							        
							        if(counter > maxCounter){
							        	maxCounter = counter;
							        	bestPoints = new ArrayList<>(ransacPoints);
							        	
							        }
							        
								}
					}
				}
			}
		}
	System.out.println("Affiniczna, s¹siedzi: " + maxCounter);		
	}

	public double estimateError(Point point, double a, double b, double c, double d, double e, double f) 
	{
		double error = Math.hypot( (a * point.getX() + b * point.getY() + c) 
	        		- point.getNearestPoint().getX(),
	        		(d * point.getX() + e * point.getY() + f)
	        		- point.getNearestPoint().getY());
		
		return error;
	}
}
