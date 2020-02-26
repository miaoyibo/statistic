package com.math.demo;

import com.math.statistics.Anovo;

public class AnovoDemo {

	public static void main(String[] args) {
		Anovo anovo=new Anovo();
		double[] a= {58,64,55,66,67};
		double[] b= {58,69,71,64,68};
		double[] c= {48,57,59,47,49};
		anovo.addE("a", a);
		anovo.addE("b", b);
		anovo.addE("c", c);
		System.out.println(anovo.PValue());

	}

}
