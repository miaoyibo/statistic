package com.t.test;

import java.math.BigInteger;

import JSci.maths.statistics.PoissonDistribution;

public class Demo1 {

	public static void main(String[] args) {
		//int[] change= {9,3,6,3,7,3,1,4,5,8};
		//maximumNumber("85818141247826137506",change);
	

	}

	public static String maximumNumber(String num, int[] change) {
		String[] ss = num.split("");
		String r="";
		
		for(int i=0;i<ss.length;i++) {
			int a=Integer.parseInt(ss[i]);
			if(a>=change[a]) {
				continue;
			}
			r=num.substring(0,i);
			for(int j=i;j<ss.length;j++) {
				int b=Integer.parseInt(ss[j]);
				if(change[b]>=b) {
					r=r+String.valueOf(change[b]);
				}else {	
					r=r+num.substring(j);
					break;
				}
			}
			if(r.compareTo(num)>0) {
				return r;
			}
			
		}
		
		return num;
	}

}
