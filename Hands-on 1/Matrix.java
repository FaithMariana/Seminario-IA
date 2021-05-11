package model;

public class Matrix implements Cloneable{
	private int m;
	private int n;
	private double[][] data;

	public Matrix(int m, int n) {
		this(new double[m][n]);
	}

	public Matrix(double[][] data) {
		this.data=data;
		m = data.length;
		n = data[0].length;
	}

	public int getLength() {
		return m;
	}
	
	public static Matrix product(Matrix a, Matrix b) throws UnsupportedOperationException {
		Matrix result;
		if(a.n != b.m) {
			throw new UnsupportedOperationException("A.n is not equals to B.m");
		}
		result=new Matrix(a.m, b.n);
		for(int i=0; i<result.n; i++){
			for(int j=0; j<result.m; j++){
				for(int k=0; k<b.m; k++){
					result.data[i][j] += a.data[i][k]*b.data[k][j];
				}
			}
		}
		return result;
	}

	public static Vector product(Vector v, Matrix a){
		double prod[] = new double[a.m];
		double sum;
		for(int i=0; i<a.m; i++){
			sum = 0;
			for(int j = 0, n = v.getLength(); j<n; j++) {
				sum +=  v.getData(j)*a.data[i][j];
			}
			prod[i] = sum;
		}
		return new Vector(prod);
	}
	
	public static Vector product(Matrix a, Vector v){
		double prod[] = new double[a.m];
		double sum;
		for(int i=0; i<a.m; i++){
			sum = 0;
			for(int j = 0, n = v.getLength(); j<n; j++) {
				sum += a.data[i][j] * v.getData(j);
			}
			prod[i] = sum;
		}
		return new Vector(prod);
	}
	
	public double getDeterminant(){
		Matrix a=(Matrix)clone();
		double deter;
		for(int k=0; k<n-1; k++){
			for(int i=k+1; i<n; i++){
				for(int j=k+1; j<n; j++){
					a.data[i][j]-=a.data[i][k]*a.data[k][j]/a.data[k][k];
				}
			}
		}
		deter=1.0;
		for(int i=0; i<n; i++){
			deter*=a.data[i][i];
		}
		return deter;
	}
	
	public Matrix reverse() {
		int n=this.n;
		Matrix a=(Matrix)this.clone();
		Matrix identity=new Matrix(n, n);
		double[][] reverse=data;
		
		for(int i=0; i<n; i++){
			identity.data[i][i]=1.0;
		}

		for(int k=0; k<n-1; k++){
			for(int i=k+1; i<n; i++){
				for(int s=0; s<n; s++){
					identity.data[i][s]-=a.data[i][k]*identity.data[k][s]/a.data[k][k];
				}
				for(int j=k+1; j<n; j++){
					a.data[i][j]-=a.data[i][k]*a.data[k][j]/a.data[k][k];
				}
			}
		}
		
		for(int s=0; s<n; s++){
			reverse[n-1][s]=identity.data[n-1][s]/a.data[n-1][n-1];
			for(int i=n-2; i>=0; i--){
				reverse[i][s]=identity.data[i][s]/a.data[i][i];
				for(int k=n-1; k>i; k--){
					reverse[i][s]-=a.data[i][k]*reverse[k][s]/a.data[i][i];
				}
			}
		}
		return new Matrix(reverse);
	}

	public Matrix transposed() {
		double[][] x = data;
		double[][] xT = new double[n][m];
		for(int i = 0; i<n; i++){    
			for(int j = 0; j<m; j++){    
				xT[i][j]=x[j][i];
			}
		}
		return new Matrix(xT);
	}
	
	public String toString(){
		String text="\n";
		for(int i=0; i<m; i++){
			for(int j=0; j<n; j++){
				text+="\t "+(double)Math.round(1000*data[i][j])/1000;
			}
			text+="\n";
		}
		text+="\n";
		return text;
	}

	public double[][] getData() {
		return data;
	}

	public Object clone(){
		Matrix obj=null;
		try {
			obj=(Matrix)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		obj.data=(double[][])obj.data.clone();
		for(int i=0; i<obj.data.length; i++){
			obj.data[i]=(double[])obj.data[i].clone();
		}
		return obj;
	}
	
	public static class UnsupportedOperationException extends Exception {
		private static final long serialVersionUID = 1L;
		public UnsupportedOperationException(String msg) {
			super("Unsupported operation: "+msg);
		}
	}
}