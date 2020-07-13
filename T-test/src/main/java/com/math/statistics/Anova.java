package com.math.statistics;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.Variance;
import org.apache.commons.math3.stat.descriptive.summary.Sum;

import JSci.maths.statistics.FDistribution;

public class Anova {

	Variance variance = new Variance();

	Mean meanUtil = new Mean();
	Sum sumUtil = new Sum();
	private Map<String, double[]> map = new HashMap<String, double[]>();

	// private List<double[]> list=new ArrayList<double[]>();

	public void addE(String key, double[] e) {
		map.put(key, e);
	}

	public Map<String, Double> getVariance() {
		Map<String, Double> varianceMap = new HashMap<String, Double>();
		for (Entry<String, double[]> e : map.entrySet()) {
			varianceMap.put(e.getKey(), variance.evaluate(e.getValue()));
		}
		return varianceMap;
	}

	public Map<String, Double> getMean() {
		Map<String, Double> meanMap = new HashMap<String, Double>();
		for (Entry<String, double[]> e : map.entrySet()) {
			meanMap.put(e.getKey(), meanUtil.evaluate(e.getValue()));
		}
		return meanMap;
	}

	public double getSumMean() {
		double sum = 0;
		int n = 0;
		for (Entry<String, double[]> e : map.entrySet()) {
			sum = sum + sumUtil.evaluate(e.getValue());
			n = n + e.getValue().length;
		}

		return sum / n;
	}
	public int getSumNum() {
		int n = 0;
		for (Entry<String, double[]> e : map.entrySet()) {
			n = n + e.getValue().length;
		}

		return  n;
	}


	public double getMSTR() {
		double sumMean = getSumMean();
		double numerator=0;
		for (Entry<String, double[]> e : map.entrySet()) {
			double mean=meanUtil.evaluate(e.getValue());
			numerator=numerator+e.getValue().length*(mean-sumMean)*(mean-sumMean);
		}
		return numerator/(map.keySet().size()-1);
	}

	public double getMSE() {
		double numerator=0;
		for (Entry<String, double[]> e : map.entrySet()) {
			double v=variance.evaluate(e.getValue());
			numerator=numerator+(e.getValue().length-1)*v;
		}
		double denominator=getSumNum()-map.keySet().size();
		return numerator/denominator;
	}
	
	public double PValue() {
		double MSTR= getMSTR();
		double MSE=getMSE();
		double f=MSTR/MSE;
		double free1=map.keySet().size()-1;
		double free2=getSumNum()-map.keySet().size();
		FDistribution fd=new FDistribution(free1, free2);
		return 1-fd.cumulative(f);
	}
}
