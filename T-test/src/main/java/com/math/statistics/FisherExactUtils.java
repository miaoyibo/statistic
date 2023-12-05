package com.math.statistics;

public class FisherExactUtils {
	
	public static double[]  getConfidenceInterval(double[][] data) {
		if (data==null||data.length!=2||data[0].length!=2||data[1].length!=2) {
			return null;
		}
		double[] interval=new double[2];
		double a=data[0][0];
		double b=data[0][1];
		double c=data[1][0];
		double d=data[1][1];
		double r= (a / c) / (b / d);
		double cinit = Math.sqrt((1 / a) + (1 / b) + (1 / c) + (1 / d));
		double y = Math.log(r) - (1.96 * cinit);
		double z = Math.log(r) + (1.96 * cinit);
		y = Math.exp(y);
		z = Math.exp(z);
		y = Math.round(y * 10000d) / 10000d;
		z = Math.round(z * 10000d) / 10000d;
		interval[0]=y;
		interval[1]=z;
		return interval;
	}
	public static double  getOddsRatio(double[][] data) {
		if (data==null||data.length!=2||data[0].length!=2||data[1].length!=2) {
			return Double.NaN;
		}
		double a=data[0][0];
		double b=data[0][1];
		double c=data[1][0];
		double d=data[1][1];
		return (a / c) / (b / d);
	}
	public static void main(String[] args) {
		double[][] data= {{4,20},{1,80}};
		System.out.println("下限："+getConfidenceInterval(data)[0]);
		System.out.println("上限："+getConfidenceInterval(data)[1]);
		System.out.println(getOddsRatio(data));
	}
}
