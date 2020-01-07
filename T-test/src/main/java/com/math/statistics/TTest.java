package com.math.statistics;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

import JSci.maths.statistics.TDistribution;

public class TTest {
	
	private double[] x;
	
	private double[] y;
	StandardDeviation standardDeviation =new StandardDeviation();
	
	public TTest(double[] x, double[] y) {
		super();
		this.x = x;
		this.y = y;
	}
	public int getXSize() {
		return x==null?0:x.length;
	}
	public int getYSize() {
		return y==null?0:y.length;
	}
	public double getXMean() {
		int n=x.length;
		double sum=0;
		for (double d : x) {
			sum=sum+d;
		}
		return sum/n;
	}
	public double getYMean() {
		int n=y.length;
		double sum=0;
		for (double d : y) {
			sum=sum+d;
		}
		return sum/n;
	}
	public double getStandard(double[] x) {
		return standardDeviation.evaluate(x);
	}
	public double calculateTvalue() {
		double a=getXMean()-getYMean();
		double q1=getStandard(x);
		double q2=getStandard(y);
		double s1=q1*q1;
		double s2=q2*q2;
		double b=Math.sqrt(s1/x.length+s2/y.length);
		return a/b;
	}
	public int getDegreesOfFreedom() {
		double q1=getStandard(x);
		double q2=getStandard(y);
		double s1=q1*q1;
		double s2=q2*q2;
		double a=(s1/x.length+s2/y.length)*(s1/x.length+s2/y.length);
		double b=((s1/x.length)*(s1/x.length))/(x.length-1)+((s2/y.length)*(s2/y.length))/(y.length-1);
		Double free=a/b;
		return free.intValue();
	}
	public double getPValue() {
		int free=getDegreesOfFreedom();
		double t=calculateTvalue();
		TDistribution td=new TDistribution(free);
		double cumulative = td.cumulative(t);
		double p;
		if(cumulative>0) {
			p=(1-cumulative)*2;
		}else {
			p=cumulative*2;
		}
		return p;
	}
}
