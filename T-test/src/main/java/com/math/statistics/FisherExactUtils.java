package com.math.statistics;

public class FisherExactUtils {
	
	/***
	 * 
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @return default two-tailed P-value
	 */
	public static double getPValue(int a,int b,int c,int d) {
		FisherExact fisherExact=new FisherExact();
		return fisherExact.getTwoTailedP(a, b, c, d);
	}

	public static double getPValue(int[][] data) {
		if (data==null||data.length!=2||data[0].length!=2||data[1].length!=2) {
			return Double.NaN;
		}
		return getPValue(data[0][0],data[0][1],data[1][0],data[1][1]);
	}
	public static double[]  getConfidenceInterval(int[][] data) {
		if (data==null||data.length!=2||data[0].length!=2||data[1].length!=2) {
			return null;
		}
		return getConfidenceInterval(data[0][0],data[0][1],data[1][0],data[1][1]);
	}
	public static double  getOddsRatio(int[][] data) {
		if (data==null||data.length!=2||data[0].length!=2||data[1].length!=2) {
			return Double.NaN;
		}
		return getOddsRatio(data[0][0],data[0][1],data[1][0],data[1][1]);
	}
	public static double[]  getConfidenceInterval(int m,int n,int e,int f) {
		double a=m;
		double b=n;
		double c=e;
		double d=f;
		double[] interval=new double[2];
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
	
	public static double  getOddsRatio(int m,int n,int e,int f) {
		double a=m;
		double b=n;
		double c=e;
		double d=f;
		return (a / c) / (b / d);
	}
	public static void main(String[] args) {
		int[][] data= {{4,20},{1,80}};
		System.out.println("下限："+getConfidenceInterval(data)[0]);
		System.out.println("上限："+getConfidenceInterval(data)[1]);
		System.out.println(getOddsRatio(data));
		System.out.println(getPValue(data));
	}
}

