package com.math.demo;

import com.math.statistics.PairedTTest;

public class PairedTTestDemo {

	public static void main(String[] args) {
		double[] x= {9,4,5,4,7};
		double[] y= {3,1,0,3,2};
		PairedTTest pt=new PairedTTest(x, y);
		System.out.println(pt.calculateTvalue());//T分数
		System.out.println(pt.getPValue());//P值
		System.out.println(pt.getRValue());//效应大小
		System.out.println(pt.getDValue());//科恩d值

	}

}
