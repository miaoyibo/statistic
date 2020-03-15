package com.math.statistics;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.Variance;
import org.apache.commons.math3.stat.descriptive.summary.Sum;

import JSci.maths.statistics.TDistribution;

public class FisherLSD {

	Variance variance = new Variance();

	Mean meanUtil = new Mean();
	Sum sumUtil = new Sum();
	private Map<String, double[]> map = new HashMap<String, double[]>();

	public void addE(String key, double[] e) {
		map.put(key, e);
	}

	public double getMean(String key1, String key2) {
		double[] d1 = map.get(key1);
		double[] d2 = map.get(key2);
		return meanUtil.evaluate(d1) - meanUtil.evaluate(d2);
	}

	public int getSumNum() {
		int n = 0;
		for (Entry<String, double[]> e : map.entrySet()) {
			n = n + e.getValue().length;
		}

		return n;
	}

	public double getMSE() {
		double numerator = 0;
		for (Entry<String, double[]> e : map.entrySet()) {
			double v = variance.evaluate(e.getValue());
			numerator = numerator + (e.getValue().length - 1) * v;
		}
		double denominator = getSumNum() - map.keySet().size();
		return numerator / denominator;
	}

	public double getPValue(String key1, String key2) {
		double numerator = getMean(key1, key2);

		double mse = getMSE();

		double a = 1.0 / map.get(key1).length + 1.0 / map.get(key2).length;

		double denominator = Math.sqrt(mse * a);

		double t = numerator / denominator;
		int free = getSumNum() - map.keySet().size();
		TDistribution td = new TDistribution(free);
		double cumulative = td.cumulative(t);
		double p;
		if (t > 0) {
			p = (1 - cumulative) * 2;
		} else {
			p = cumulative * 2;
		}
		return p;
	}

}
