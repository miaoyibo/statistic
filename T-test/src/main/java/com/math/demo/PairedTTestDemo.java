package com.math.demo;

import com.math.statistics.PairedTTest;

public class PairedTTestDemo {

	public static void main(String[] args) {
		double[] x= {9,4,5,4,7};
		double[] y= {3,1,0,3,2};
		PairedTTest pt=new PairedTTest(x, y);
		System.out.println(pt.calculateTvalue());//T����
		System.out.println(pt.getPValue());//Pֵ
		System.out.println(pt.getRValue());//ЧӦ��С
		System.out.println(pt.getDValue());//�ƶ�dֵ

	}

}
