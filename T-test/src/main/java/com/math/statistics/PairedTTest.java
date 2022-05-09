package com.math.statistics;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

import JSci.maths.statistics.TDistribution;

/***
 * ���T����
 * @author miaoyibo
 *
 */
public class PairedTTest {
	
	private double[] x;
	
	private double[] y;
	
	private int freedom;
	
	StandardDeviation standardDeviation =new StandardDeviation();
	
	public PairedTTest(double[] x, double[] y) {
		this.x = x;
		this.y = y;
		this.freedom=x.length-1;
	}
	public int getXSize() {
		return x==null?0:x.length;
	}
	public int getYSize() {
		return y==null?0:y.length;
	}
	/***
	 * �����ֵ��ƽ��ֵ
	 * @return
	 */
	public double getMean() {
		double sum=0;
		for(int i=0;i<x.length;i++) {
			sum=sum+(y[i]-x[i]);
		}
		return sum/x.length;
	}
	/***
	 * ���㷽��
	 * @param x
	 * @return
	 */
	public double getStandard() {
		double sum=0;
		double pow=0;
		for(int i=0;i<x.length;i++) {
			double d=(y[i]-x[i]);
			pow=pow+Math.pow(d,2);
			sum=sum+d;
		}
		int n=x.length;
		return pow-Math.pow(sum,2)/n;
	}
	public double calculateTvalue() {
		double sp=getStandard()/freedom;
		double sm=Math.sqrt(sp/x.length);
		return (getMean()-0)/sm;
	}
	public int getDegreesOfFreedom() {

		return freedom;
	}
	/***
	 * <p>����TDistribution�е�cumulative��probability����������</p>
	 * <p>cumulative�����CDFֵ��Ҳ���Ǹ��ʷֲ�ֵ��probability�����PDFֵ��Ҳ���Ǹ����ܶ�ֵ��һ����������ò���</p>
	 * <p>���ǰ�cumulative�����õ���ֵ��ΪP(x<=X)��probability�����õ���ֵ��ΪP(x=X)</p>
	 * <p>������С��0ʱ���ᷢ��P(x<=X)<P(x=X),���ƺ���Υֱ����</p>
	 * <p>������ΪPDFָ����ʵ����һ�����Ӧ��ֵ����������㸽����Ӧ��ֵ������һ���ܶȣ�һ�����䣬һ���������С�����䡣
	 * <p>��Ȼ�Ǹ������ǾͰ���������"��"��Ҳ����"��"
	 * <p>��CDF��PDF�Ļ��֣��Ͻ���˵����PDF��ĳ������Ļ��֣�Ҳ���������µ���������������λ�ڵ���ߵ�����������������Ҳ࣬������t�ֲ����ߵ���벿�֣����յ����ֵ��ȵ��Ӧ��PDFֵҪС��
	 * @return
	 */
	public double getPValue() {
		
		double t=calculateTvalue();
		TDistribution td=new TDistribution(freedom);
		double cumulative = td.cumulative(t);
		return t>0?(1-cumulative)*2:cumulative*2;
	}
	/***
	 * ЧӦ��С
	 * @return
	 */
	public double getRValue() {
		double t=calculateTvalue();
		return Math.pow(t,2)/(Math.pow(t,2)+freedom);
	}
	/***
	 * �ƶ�dֵ
	 * @return
	 */
	public double getDValue() {
		double m=getMean();
		double s=getStandard()/freedom;
		return Math.abs(m/Math.sqrt(s));
	}
}
