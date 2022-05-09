package com.math.statistics;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

import JSci.maths.statistics.TDistribution;

/***
 * 配对T检验
 * @author miaoyibo
 *
 */
public class PairedTTest {
	
	private double[] x;
	
	private double[] y;
	
	private int freedom;
	
	StandardDeviation standardDeviation =new StandardDeviation();
	
	public PairedTTest(double[] x, double[] y) {
		this.x = x;
		this.y = y;
		this.freedom=x.length-1;
	}
	public int getXSize() {
		return x==null?0:x.length;
	}
	public int getYSize() {
		return y==null?0:y.length;
	}
	/***
	 * 计算差值的平均值
	 * @return
	 */
	public double getMean() {
		double sum=0;
		for(int i=0;i<x.length;i++) {
			sum=sum+(y[i]-x[i]);
		}
		return sum/x.length;
	}
	/***
	 * 计算方差
	 * @param x
	 * @return
	 */
	public double getStandard() {
		double sum=0;
		double pow=0;
		for(int i=0;i<x.length;i++) {
			double d=(y[i]-x[i]);
			pow=pow+Math.pow(d,2);
			sum=sum+d;
		}
		int n=x.length;
		return pow-Math.pow(sum,2)/n;
	}
	public double calculateTvalue() {
		double sp=getStandard()/freedom;
		double sm=Math.sqrt(sp/x.length);
		return (getMean()-0)/sm;
	}
	public int getDegreesOfFreedom() {

		return freedom;
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
		
		double t=calculateTvalue();
		TDistribution td=new TDistribution(freedom);
		double cumulative = td.cumulative(t);
		return t>0?(1-cumulative)*2:cumulative*2;
	}
	/***
	 * 效应大小
	 * @return
	 */
	public double getRValue() {
		double t=calculateTvalue();
		return Math.pow(t,2)/(Math.pow(t,2)+freedom);
	}
	/***
	 * 科恩d值
	 * @return
	 */
	public double getDValue() {
		double m=getMean();
		double s=getStandard()/freedom;
		return Math.abs(m/Math.sqrt(s));
	}
}
