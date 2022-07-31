package com.math.statistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import JSci.maths.statistics.FDistribution;
/***
 * 双因素方差分析
 * 独立测量
 * @author miaoyibo
 *
 */
public class TwoWayVariance {
	private List<double[]> data=new ArrayList<double[]>();
	private int row;
	private int col;
	/***
	 * 总变异（方差）
	 */
	private double ss_total;
	/***
	 * 总的自由度
	 */
	private int free_total;
	/***
	 * 所有数据总和
	 */
	private double sum;
	/***
	 * 所有数据个数
	 */
	private int num;
	/***
	 * 处理间变异（方差）
	 */
	private double ss_group;
	private int free_group;
	/***
	 * 处理内变异
	 */
	private double ss_inner;
	private int free_inner;
	
	private double ss_row;
	private double ss_col;
	private double ss_cross;
	private int free_row=row-1;
	private int free_col=col-1;
	private double fValue_row;
	private double fValue_col;
	private double fValue_cross;
	public TwoWayVariance(List<double[]> data, int row, int col) {
		this.data = data;
		this.row = row;
		this.col = col;
		cal();
	}
	public double[] getFValues() {
		double[] dd=new double[3];
		dd[0]=fValue_row;
		dd[1]=fValue_col;
		dd[2]=fValue_cross;
		return dd;
	}
	public double[] getPValues() {
		double[] dd=new double[3];
		dd[0]=getPValue_Row();
		dd[1]=getPValue_Col();
		dd[2]=getPValue_Cross();
		return dd;
	}
	private void cal() {
		if(data==null||data.isEmpty()||row*col!=data.size()) {
			throw new NullPointerException("数据格式异常");
		}
		calStageI();
		calStageII();
		double msinner=getMSInner();
		free_row=row-1;
		free_col=col-1;
		double msA=ss_row/free_row;
		double msB=ss_col/free_col;
		double msAB=ss_cross/(free_group-free_row-free_col);
		fValue_row=msA/msinner;
		fValue_col=msB/msinner;
		fValue_cross=msAB/msinner;
	}
	private double getPValue_Row() {
		FDistribution fd=new FDistribution(free_row, free_inner);
		double cumulative = fd.cumulative(fValue_row);
		return (1-cumulative)*2;
	}
	private double getPValue_Col() {
		FDistribution fd=new FDistribution(free_col, free_inner);
		double cumulative = fd.cumulative(fValue_col);
		return (1-cumulative)*2;
	}
	private double getPValue_Cross() {
		FDistribution fd=new FDistribution(free_group-free_row-free_col, free_inner);
		double cumulative = fd.cumulative(fValue_cross);
		return (1-cumulative)*2;
	}
	private void calStageI() {
		double gsum=0;
		double squareSum=0;
		for(double[] dd:data) {
			double isum=0;
			for(int i=0;i<dd.length;i++) {
				sum=sum+dd[i];
				isum=isum+dd[i];
				squareSum=squareSum+dd[i]*dd[i];
				num++;					
			}
			gsum=(isum*isum)/dd.length+gsum;
		}
		free_total=num-1;
		ss_total=squareSum-((sum*sum)/num);
		
		free_group=data.size()-1;
		ss_group=gsum-((sum*sum)/num);
		
		ss_inner=ss_total-ss_group;
		free_inner=free_total-free_group;
	}
	private void calStageII() {
		int r=0;
		double s=0;
		int n=0;
		double ss=0;
		Map<Integer,Double> sumMap=new HashMap<Integer, Double>();
		Map<Integer,Integer> countMap=new HashMap<Integer, Integer>();
		for(double[] dd:data) {	
			double cs=0;
			for(int i=0;i<dd.length;i++) {
				s=s+dd[i];
				cs=cs+dd[i];
				n++;
			}
			if(sumMap.get(r)==null) {
				sumMap.put(r, cs);
				countMap.put(r, dd.length);
			}else {
				sumMap.put(r, sumMap.get(r)+cs);
				countMap.put(r, countMap.get(r)+dd.length);
			}
			r++;
			if(r==col) {
				ss=ss+(s*s)/n;
				r=0;
				s=0;
				n=0;
			}
		}
		ss_row=ss-((sum*sum)/num);
		double sm = 0;
		for(int key:sumMap.keySet()) {
			Double d1 = sumMap.get(key);
			Integer n1 = countMap.get(key);
			sm=sm+((d1*d1)/n1);
		}
		ss_col=sm-((sum*sum)/num);
		ss_cross=ss_group-ss_row-ss_col;
	}
	private double getMSInner() {
		return ss_inner/free_inner;
	}

	public List<double[]> getData() {
		return data;
	}
	public void setData(List<double[]> data) {
		this.data = data;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public double getSs_total() {
		return ss_total;
	}
	public void setSs_total(double ss_total) {
		this.ss_total = ss_total;
	}
	public int getFree_total() {
		return free_total;
	}
	public void setFree_total(int free_total) {
		this.free_total = free_total;
	}
	public double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public double getSs_group() {
		return ss_group;
	}
	public void setSs_group(double ss_group) {
		this.ss_group = ss_group;
	}
	public int getFree_group() {
		return free_group;
	}
	public void setFree_group(int free_group) {
		this.free_group = free_group;
	}
	public double getSs_inner() {
		return ss_inner;
	}
	public void setSs_inner(double ss_inner) {
		this.ss_inner = ss_inner;
	}
	public int getFree_inner() {
		return free_inner;
	}
	public void setFree_inner(int free_inner) {
		this.free_inner = free_inner;
	}
	public double getSs_row() {
		return ss_row;
	}
	public void setSs_row(double ss_row) {
		this.ss_row = ss_row;
	}
	public double getSs_col() {
		return ss_col;
	}
	public void setSs_col(double ss_col) {
		this.ss_col = ss_col;
	}
	public double getSs_cross() {
		return ss_cross;
	}
	public void setSs_cross(double ss_cross) {
		this.ss_cross = ss_cross;
	}
	public int getFree_row() {
		return free_row;
	}
	public void setFree_row(int free_row) {
		this.free_row = free_row;
	}
	public int getFree_col() {
		return free_col;
	}
	public void setFree_col(int free_col) {
		this.free_col = free_col;
	}
	public double getfValue_row() {
		return fValue_row;
	}
	public void setfValue_row(double fValue_row) {
		this.fValue_row = fValue_row;
	}
	public double getfValue_col() {
		return fValue_col;
	}
	public void setfValue_col(double fValue_col) {
		this.fValue_col = fValue_col;
	}
	public double getfValue_cross() {
		return fValue_cross;
	}
	public void setfValue_cross(double fValue_cross) {
		this.fValue_cross = fValue_cross;
	}
}
