import java.util.ArrayList;

import javax.swing.plaf.basic.BasicBorders.MarginBorder;

import Jama.Matrix;

public class Ransac {

	private ArrayList<Point> pairPoints;
	
	public Ransac(ArrayList<Point> pairPoints) {
		this.pairPoints = pairPoints;
	}
	
	public void run(int iterations){
		
		double a, b, c, d, e, f;
		
		double matrixTemp1[][] = new double[6][6];
		double matrixTemp2[] = new double[6];
		
		Matrix matrixA;
		Matrix matrixB;
		Matrix modelParameterMatrix = null;
		double error = 0; 
		double minError = Integer.MAX_VALUE;
		double maxError = 0;
		
		for(int i = 0; i < iterations; i++){
			
			for(int p1 = 0; p1 < pairPoints.size(); p1++){
				
				for(int p2 = p1 + 1; p2 < pairPoints.size(); p2++){
					
					for(int p3 = p2 + 1; p3 < pairPoints.size(); p3++){
						
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
							        
							        error = Math.hypot( (a * pairPoints.get(p1).getX() + b * pairPoints.get(p1).getY() + c) 
							        		- pairPoints.get(p1).getNearestPoint().getX(),
							        		(d * pairPoints.get(p1).getX() + e * pairPoints.get(p1).getY() + f)
							        		- pairPoints.get(p1).getNearestPoint().getY());
							        
							        error += Math.hypot( (a * pairPoints.get(p2).getX() + b * pairPoints.get(p2).getY() + c) 
							        		- pairPoints.get(p2).getNearestPoint().getX(),
							        		(d * pairPoints.get(p2).getX() + e * pairPoints.get(p2).getY() + f)
							        		- pairPoints.get(p2).getNearestPoint().getY());
							        
							        error += Math.hypot( (a * pairPoints.get(p3).getX() + b * pairPoints.get(p3).getY() + c) 
							        		- pairPoints.get(p3).getNearestPoint().getX(),
							        		(d * pairPoints.get(p3).getX() + e * pairPoints.get(p3).getY() + f)
							        		- pairPoints.get(p3).getNearestPoint().getY());
							        
							        if(minError > error){
							        	minError = error;
							        }
							        if(maxError < error){
							        	maxError = error;
							        }
								}
					}
				}
			}
		}
	System.out.println("maxError: " + maxError + " minError: " + minError);		
	}

}
