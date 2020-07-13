package com.math.regression;

import org.apache.commons.math3.stat.descriptive.moment.Mean;

import JSci.maths.statistics.TDistribution;
/***
 * least squares method
 * @author miaoyibo
 *
 */
public class RegressionEquation {
	//因变量y
	private double[] dependentValues;
	//自变量x
	private double[] independentValues;
	Mean meanUtil = new Mean();
	double xmean;
	double ymean;
	public RegressionEquation(double[] dependentValues, double[] independentValues) {
		this.dependentValues = dependentValues;
		this.independentValues = independentValues;
		xmean=meanUtil.evaluate(independentValues);
		ymean=meanUtil.evaluate(dependentValues);
	}

	
	public RegressionModel getRegressionModel() {
		RegressionModel model=new RegressionModel();
		double[] regression = calRegression();
		double sse=0;
		double sst=0;
		for(int i=0;i<dependentValues.length;i++) {
			double yi=independentValues[i]*regression[1]+regression[0];
			double y=dependentValues[i];
			sse=sse+(y-yi)*(y-yi);
			sst=sst+(y-ymean)*(y-ymean);
		}
		double coefficientOfDetermination = (sst-sse)/sst;
		model.setB0(regression[0]);
		model.setB1(regression[1]);
		model.setR(coefficientOfDetermination);
		Ttest(sse,model);
		return model;
		
	}
	/***
	 * 计算回归方程
	 * @return
	 */
	public double[] calRegression() {
		if(dependentValues.length!=independentValues.length) {
			return null;
		}
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
	/***
	 * 利用T检验检验两个变量之间是否存在一个显著的回归关系。
	 * @return
	 */
	private void Ttest(double sse,RegressionModel model) {
		
		//计算标准误差
		//自由度为2，因为涉及两个变量。
		double s=Math.sqrt(sse/(dependentValues.length-2));
		//计算标准差
		double xx=0;
		for(int i=0;i<independentValues.length;i++) {
			double xi=independentValues[i];
			xx=xx+((xi-xmean)*(xi-xmean));
		}
		double sb=s/Math.sqrt(xx);
		
		//计算t值
		double t=model.getB1()/sb;
		//计算p值
		TDistribution td=new TDistribution(dependentValues.length-2);
		double cumulative = td.cumulative(t);
		double p;
		if(t>0) {
			p=(1-cumulative)*2;
		}else {
			p=cumulative*2;
		}
		model.setP(p);
	}
}
