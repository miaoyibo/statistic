package com.math.demo;

import java.util.ArrayList;
import java.util.List;

import com.math.statistics.TwoWayVariance;

public class TwoWayDemo {

	public static void main(String[] args) {
		List<double[]> data=new ArrayList<double[]>();
		double[] d1= {3,1,1,6,4};
		double[] d2= {2,5,9,7,7};
		double[] d3= {9,9,13,6,8};
		double[] d4= {1,2,1,1,3};
		double[] d5= {3,8,3,3,3};
		double[] d6= {2,2,2,5,2}; 
		data.add(d1);
		data.add(d2);
		data.add(d3);
		data.add(d4);
		data.add(d5);
		data.add(d6);
		TwoWayVariance tw=new TwoWayVariance(data, 2, 3);
		double[] fValues = tw.getFValues();
		double[] pValues = tw.getPValues();
		for(int i=0;i<=2;i++) {
			
		}
		System.out.println("湿度对应的F分数为："+fValues[0]+";P值为："+pValues[0]);
		System.out.println("温度对应的F分数为："+fValues[1]+";P值为："+pValues[1]);
		System.out.println("湿度和温度交叉作用对应的F分数为："+fValues[2]+";P值为："+pValues[2]);

	}

}
