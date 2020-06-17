package com.math.regression;

import org.apache.commons.math3.stat.descriptive.moment.Mean;
/***
 * least squares method
 * @author miaoyibo
 *
 */
public class RegressionEquation {
	
	private double[] dependentValues;
	
	private double[] independentValues;

	public RegressionEquation(double[] dependentValues, double[] independentValues) {
		this.dependentValues = dependentValues;
		this.independentValues = independentValues;
	}
	
	public double getMean(double[] dd) {
		Mean meanUtil = new Mean();
		return meanUtil.evaluate(dd);
	}

	public double[] getRegressionModel() {
		if(dependentValues.length!=independentValues.length) {
			return null;
		}
		Mean meanUtil = new Mean();
		double xmean=meanUtil.evaluate(independentValues);
		double ymean=meanUtil.evaluate(dependentValues);
		double numerator=0d;
		double denominator=0d;
		for(int i=0;i<dependentValues.length;i++) {
			double x=independentValues[i];
			double y=dependentValues[i];
			numerator=numerator+(x-xmean)*(y-ymean);
			denominator=denominator+(x-xmean)*(x-xmean);
		}
		double b1=numerator/denominator;
		double b0=ymean-b1*xmean;
		double[] model= {b0,b1};
		return model;
	}
}
