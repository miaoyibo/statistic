package com.math.statistics;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import JSci.maths.statistics.ChiSqrDistribution;

public class IndependenceTest {

	private Map<String, Double> map1;

	private Map<String, Double> map2;

	public IndependenceTest(Map<String, Double> map1, Map<String, Double> map2) {
		super();
		this.map1 = map1;
		this.map2 = map2;
	}

	public double getSum() {
		double sum1 = map1.values().stream().reduce(Double::sum).orElse((double) 0);
		double sum2 = map2.values().stream().reduce(Double::sum).orElse((double) 0);
		return sum1 + sum2;
	}

	public double getMap1Sum() {
		return map1.values().stream().reduce(Double::sum).orElse((double) 0);
	}

	public double getMap2Sum() {
		return map2.values().stream().reduce(Double::sum).orElse((double) 0);
	}

	/**
	 * 2为map的数量
	 * 
	 * @return
	 */
	public double getDegreesOfFreedom() {
		return (map1.keySet().size() - 1) * (2 - 1);
	}

	public Map<String, Double> getScale() {
		Set<String> keySet = map1.keySet();
		Map<String, Double> scale = new HashMap<String, Double>();
		for (String key : keySet) {
			scale.put(key, (map1.get(key) + map2.get(key)) / getSum());
		}
		return scale;
	}

	public double getPValue() {
		double map1Sum = getMap1Sum();
		double map2Sum = getMap2Sum();
		double x = 0;
		Map<String, Double> scale = getScale();
		Set<String> keySet = map1.keySet();
		for (String key : keySet) {
			double sampleValue1 = map1.get(key);
			double expectValue1 = map1Sum * scale.get(key);
			double map1KeyFre = ((sampleValue1 - expectValue1) * (sampleValue1 - expectValue1)) / expectValue1;

			double sampleValue2 = map2.get(key);
			double expectValue2 = map2Sum * scale.get(key);
			double map2KeyFre = ((sampleValue2 - expectValue2) * (sampleValue2 - expectValue2)) / expectValue2;

			x = x + map1KeyFre + map2KeyFre;

		}
		ChiSqrDistribution cd = new ChiSqrDistribution(getDegreesOfFreedom());
		return 1 - cd.cumulative(x);
	}

}
