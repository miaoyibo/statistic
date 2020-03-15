package com.math.demo;

import com.math.statistics.FisherLSD;

public class FishLSDDemo {

	public static void main(String[] args) {
		FisherLSD fisher=new FisherLSD();
		double[] a= {58,64,55,66,67};
		double[] b= {58,69,71,64,68};
		double[] c= {48,57,59,47,49};
		fisher.addE("a", a);
		fisher.addE("b", b);
		fisher.addE("c", c);		
		System.out.println(fisher.getPValue("a", "b"));

	}

}
