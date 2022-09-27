package com.math.regression;

import org.apache.commons.math3.stat.regression.GLSMultipleLinearRegression;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
/***
 * 多远线性回归
 * @author miaoyibo
 *
 */
public class MultipleRegression {

	public static void main(String[] args) {
		OLSMultipleLinearRegression oregression = new OLSMultipleLinearRegression();
		double[] y = new double[]{2.9, 3.0, 4.8, 1.8, 2.9,4.9,4.2,4.8,4.4,4.5};
		double[][] x = new double[10][];
		x[0] = new double[]{2, 1};
		x[1] = new double[]{6, 0};
		x[2] = new double[]{8, 1};
		x[3] = new double[]{3, 0};
		x[4] = new double[]{2, 1};
		x[5] = new double[]{7, 1};
		x[6] = new double[]{9, 0};
		x[7] = new double[]{8, 0};
		x[8] = new double[]{4, 1};
		x[9] = new double[]{6, 1};
		oregression.newSampleData(y, x);
		double[] beta = oregression.estimateRegressionParameters();
		for (double d : beta) {
			System.out.println(d);
		}
		double rSquared = oregression.calculateRSquared();
		double regressandVariance = oregression.estimateRegressionStandardError();
		System.out.println(rSquared);
		System.out.println(regressandVariance);

	}

}
