package model;

import java.util.Arrays;

public class DataSet {
	private float[][] dataSet;
	
	public DataSet(Object[] args) {
		this(initializeDataSet(args));
	}
	
	public DataSet(float[][] dataSet) {
		this.dataSet = dataSet;
	}
	
	public int getLength() {
		return dataSet.length;
	}
	
	public float[] getYColumn() {
		return getColumn(1);
	}
	
	public float[] getXColumn() {
		return getColumn(0); 
	}
	
	private float[] getColumn(int col) {
		int n = getLength();
		float[] column = new float[n];
		for(int i = 0; i<n; i++) {
			column[i] = dataSet[i][col];
		}
		return column;
	}
	
	public String toString() {
		String str = "";
		for(float[] i : dataSet) {
			str+=Arrays.toString(i);
		}
		return str;
	}
	
	private static float[][] initializeDataSet(Object[] args) {
		int len;
		float[][] dataSet;
		if(args!=null) {
			dataSet = new float[len = args.length][];
			for(int i = 0; i<len; i++) {
				dataSet[i] = toFloatArray(args[i]+"");
			}
		} else {
			dataSet = getDefaultDataSet();
		}
		return dataSet;
	}
	
	private static float[] toFloatArray(String arg) {
		float array[];
		int len;
		String[] items;
		items = arg.replaceAll("\\{", "").replaceAll("\\}", "").split(" ");
		array = new float[len = items.length];
		for(int i = 0; i<len; i++) {
			array[i] = Integer.parseInt(items[i]);
		}
		return array;
	}
	
	private static float[][] getDefaultDataSet() {
		return new float[][] {
			{23, 651}, 
			{26, 762},
			{30, 856},
			{34, 1063},
			{43, 1190},
			{48, 1298},
			{52, 1421},
			{57, 1440},
			{58, 1518}
		};
	}
}
