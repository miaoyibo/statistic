package com.math.demo;

import java.math.BigDecimal;

import com.math.regression.RegressionEquation;
import com.math.regression.RegressionModel;
/***
 * 
 * @author miaoyibo
 *
 */
public class RegressionDemo {

	public static void main(String[] args) {
		double[] dependentValues= {58,105,88,118,117,137,157,169,149,202};
		double[] independentValues= {2,6,8,8,12,16,20,20,22,26};
		RegressionEquation re=new RegressionEquation(dependentValues, independentValues);
		RegressionModel regressionModel = re.getRegressionModel();
		System.out.println("b0=="+regressionModel.getB0());
		System.out.println("b1=="+regressionModel.getB1());
		System.out.println("coefficient of determination=="+regressionModel.getR());
		System.out.println("T test=="+new BigDecimal(+regressionModel.getP()));
	}

}
