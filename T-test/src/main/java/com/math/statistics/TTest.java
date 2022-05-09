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
	/***
	 * <p>关于TDistribution中的cumulative和probability两个方法：</p>
	 * <p>cumulative求得是CDF值，也就是概率分布值；probability求得是PDF值，也就是概率密度值，一般情况我们用不到</p>
	 * <p>我们把cumulative函数得到的值记为P(x<=X)，probability函数得到的值记为P(x=X)</p>
	 * <p>当参数小于0时，会发现P(x<=X)<P(x=X),这似乎有违直觉：</p>
	 * <p>这是因为PDF指的其实不是一个点对应的值，而是这个点附近对应的值，它是一个密度，一个区间，一个宽度无限小的区间。
	 * <p>既然是附近，那就包括这个点的"左"，也包括"右"
	 * <p>而CDF是PDF的积分，严谨的说，是PDF在某个区间的积分，也就是曲线下的面积，在这里就是位于点左边的面积，并不包括点右侧，所以在t分布曲线的左半部分，最终的面积值会比点对应的PDF值要小。
	 * @return
	 */
	public double getPValue() {
		int free=getDegreesOfFreedom();
		double t=calculateTvalue();
		TDistribution td=new TDistribution(free);
		double cumulative = td.cumulative(t);
		return  t>0?(1-cumulative)*2:cumulative*2;
	}

}
