package com.math.demo;

import com.math.statistics.Anova;
/***
 * 此分析为单侧检验，用于多个总体均值相等的检验；
 * 结论只能得出总体均值是否不全相等；
 * 如果希望更进一步：确定到底是哪几个均值之间存在差异，可以使用多重比较方法。
 * @author miaoyibo
 *
 */
public class AnovaDemo {

	public static void main(String[] args) {
		Anova anovo=new Anova();
		double[] a= {58,64,55,66,67};
		double[] b= {58,69,71,64,68};
		double[] c= {48,57,59,47,49};
		anovo.addE("a", a);
		anovo.addE("b", b);
		anovo.addE("c", c);
		System.out.println(anovo.PValue());
		String ss="";

	}

}
