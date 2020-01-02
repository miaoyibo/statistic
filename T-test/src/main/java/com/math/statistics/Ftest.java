package com.math.statistics;

import org.apache.commons.math3.stat.descriptive.moment.Variance;

import JSci.maths.statistics.FDistribution;

public class Ftest {
	private double[] x;

	private double[] y;

	Variance variance = new Variance();

	public Ftest(double[] x, double[] y) {
		super();
		this.x = x;
		this.y = y;
	}

	public double[] getX() {
		return x;
	}

	public void setX(double[] x) {
		this.x = x;
	}

	public double[] getY() {
		return y;
	}

	public void setY(double[] y) {
		this.y = y;
	}

	public double getXDegreesOfFreedom() {
		return x.length - 1;
	}

	public double getYDegreesOfFreedom() {
		return y.length - 1;
	}

	public double getXVariance() {

		return variance.evaluate(x);
	}

	public double getYVariance() {

		return variance.evaluate(y);
	}

	public double getPValue() {
		double q=getXVariance()*getXVariance();
		double p=getYVariance()*getYVariance();
		double f=q/p;
		FDistribution fd=new FDistribution(x.length - 1, y.length - 1);
		double cumulative = fd.cumulative(f);
		return (1-cumulative)*2;
	}
}
