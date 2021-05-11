package model;
import java.util.Arrays;

public class DataSet {
	private double[][] dataSet;

	public DataSet(double[][] dataSet) {
		this.dataSet = dataSet;
	}
	
	public int getLength() {
		return dataSet.length;
	}
	
	public double[] getX1Column() {
		return getColumn(0); 
	}
	
	public double[] getX2Column() {
		return getColumn(1); 
	}
	
	public double[] getYColumn() {
		return getColumn(2);
	}
	
	public double[][] getXMatrix() {
		int n = getLength();
		double xMatrix[][], x1[], x2[];
		xMatrix = new double[n][];
		x1 = getX1Column();
		x2 = getX2Column();
		
		for(int i = 0; i<n; i++) {
			xMatrix[i] = new double[]{1, x1[i], x2[i]};
		}
		return xMatrix;
	}
	
	private double[] getColumn(int col) {
		int n = getLength();
		double[] column = new double[n];
		for(int i = 0; i<n; i++) {
			column[i] = dataSet[i][col];
		}
		return column;
	}
	
	public String toString() {
		String str = "";
		for(double[] i : dataSet) {
			str+=Arrays.toString(i);
		}
		return str;
	}
}
