package model;

public class MultipleLinearRegression {
	private DataSet dataSet;
	private double b0;
	private double b1;
	private double b2;

	public MultipleLinearRegression(DataSet dataSet) {
		this.dataSet = dataSet;
	}

	public MultipleLinearRegression(double[][] dataSet) {
		this(new DataSet(dataSet));
	}
	
	public void init() {
		try {
			Matrix m = new Matrix(dataSet.getXMatrix());
			Matrix t = m.transposed();
			Vector productTransposedYColumn = Matrix.product(t, new Vector(dataSet.getYColumn()));
			Vector betha = Matrix.product((Matrix.product(t, m)).reverse(), productTransposedYColumn);
			b0 = betha.getData(0);
			b1 = betha.getData(1);
			b2 = betha.getData(2);
		} catch (Matrix.UnsupportedOperationException e) {
			e.printStackTrace();
		}
	}

	public String getRegressionEquation() {
		return "'y = "+b0+" + "+b1+"x1 + "+b2+"x2'";
	}

	public double getB0() {
		return b0;
	}

	public double getB1() {
		return b1;
	}
	
	public double getB2() {
		return b2;
	}
	
	public double makePrediction(double x1, double x2) {
		return b0+(b1*x1)+(b2*x2);
	}

	public String getDataSetStr() {
		return dataSet.toString();
	}
}