package com.math.statistics;

import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

import JSci.maths.statistics.TDistribution;
/***
 * {@link https://en.wikipedia.org/wiki/Pearson_correlation_coefficient#Testing_using_Student.27s_t-distribution}
 * {@link https://blog.csdn.net/miaoyibo12/article/details/127027440?spm=1001.2014.3001.5501}
 * @author miaoyibo
 *
 */
public class Pearson {
	
	private double[] x;

	private double[] y;
	PearsonsCorrelation p=new PearsonsCorrelation();

	public Pearson(double[] x, double[] y) {
		this.x = x;
		this.y = y;
	}
	
	public double getR() {
		
		return p.correlation(x, y);
	}
	public double getTValue() {
		double up=x.length-2;
		double r=getR();
		double down=1-(r*r);
		return r*Math.sqrt(up/down);
	}
	/***
	 * 
	 * @param flag:true=双侧 false=单侧
	 * @return
	 */
	public double getPValue(boolean flag) {
		TDistribution td=new TDistribution(x.length-2);
		double t=getTValue();
		double cumulative = td.cumulative(t);
		double p=t>0?1-cumulative:cumulative;
		return flag?p*2:p;
	}
}
