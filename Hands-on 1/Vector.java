package model;
public class Vector {
	private int m;
	private double[] data;

	public Vector(int n) {
		this(new double[n]);
	}

	public Vector(double[] x) {
		this.data=x;
		m=x.length;
	}

	public int getLength() {
		return m;
	}

	public void setData(int i, double x) {
		data[i] = x;
	}

	public double getData(int i) {
		return data[i];
	}

	public static double ScalarProduct(Vector a, Vector b){
		double resultado=0;
		for(int i=0; i<a.m; i++){
			resultado+=a.data[i]*b.data[i];
		}
		return resultado;
	}

	public double module(){
		return Math.sqrt(ScalarProduct(this, this));
	}

	public String toString(){
		String str=" ";
		for(int i=0; i<m; i++){
			str+="\t "+(double)Math.round(1000*data[i])/1000;
		}
		str+="\n";
		return str;
	}
}