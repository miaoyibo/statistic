package com.math.regression;

public class RegressionModel {
	//y轴截距
	private double b0;
	//斜率
	private double b1;
	//判定系数
	private double r;
	
	private double p;
	public double getB0() {
		return b0;
	}
	public void setB0(double b0) {
		this.b0 = b0;
	}
	public double getB1() {
		return b1;
	}
	public void setB1(double b1) {
		this.b1 = b1;
	}
	public double getR() {
		return r;
	}
	public void setR(double r) {
		this.r = r;
	}
	public double getP() {
		return p;
	}
	public void setP(double p) {
		this.p = p;
	}
	
	

}
