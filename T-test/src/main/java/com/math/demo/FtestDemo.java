package com.math.demo;

import com.math.statistics.FTest;

public class FtestDemo {

	/**
	 * two-sided test
	 * @param args
	 */
	public static void main(String[] args) {
		double[] x= {300,280,344,385,372,360,288,321,376,290,301,283};
		double[] y= {274,220,308,336,198,300,315,258,318,310,332,263};
		FTest f=new FTest(x, y);
		System.out.println(f.getPValue());

	}

}
