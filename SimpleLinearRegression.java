package model;

public class SimpleLinearRegression {
		private DataSet dataSet;
		private float b0;
		private float b1;
		
		public SimpleLinearRegression(DataSet dataSet) {
			this.dataSet = dataSet;
		}
		
		public SimpleLinearRegression(float[][] dataSet) {
			this(new DataSet(dataSet));
		}

		public SimpleLinearRegression(Object[] dataSet) {
			this(new DataSet(dataSet));
		}

		public void init() {
			float[] columnY, columnX;
			float sumX, sumY, sumXY, sumSquaredX, x, y;
			int n;
			n = dataSet.getLength();
			sumX = sumY = sumXY = sumSquaredX = 0;
			columnY = dataSet.getYColumn();
			columnX = dataSet.getXColumn();
			for(int i = 0; i<n; i++) {
				x = columnX[i];
				y = columnY[i];
				sumX += x;
				sumY += y;
				sumXY += x*y;
				sumSquaredX += x*x;
			}
			b0 = ((sumY*sumSquaredX)-(sumX*sumXY))/((n*sumSquaredX)-(sumX*sumX));
			b1 = ((n*sumXY)-(sumX*sumY))/((n*sumSquaredX)-(sumX*sumX));
		}

		public String getRegressionEquation() {
			return "'y = "+b0+" + "+b1+"x'";
		}
		
		public float getB0() {
			return b0;
		}
		
		public float getB1() {
			return b1;
		}
		
		public float makePrediction(float x) {
			return b0+(b1*x);
		}

		public String getDataSetStr() {
			return dataSet.toString();
		}
	}