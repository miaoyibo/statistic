package com.math.demo;

import java.util.HashMap;
import java.util.Map;

import com.math.statistics.IndependenceTest;

public class IndependenceDemo {

	public static void main(String[] args) {
		Map<String, Double> map1=new HashMap<String, Double>();
		map1.put("a", 51.0);
		map1.put("b", 56.0);
		map1.put("c", 25.0);
		Map<String, Double> map2=new HashMap<String, Double>();
		map2.put("a", 39.0);
		map2.put("b", 21.0);
		map2.put("c", 8.0);
		IndependenceTest it=new IndependenceTest(map1,map2);
		System.out.println(it.getPValue());

	}

}
