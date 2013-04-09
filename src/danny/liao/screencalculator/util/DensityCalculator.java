package danny.liao.screencalculator.util;

public class DensityCalculator {
	public double calculator(double length, double width, double dimension){
		if(length <=0 && width <=0 && dimension <= 0)
			return 0d;
		
		double result = 0d;
		result = Math.sqrt(length * length + width * width) / dimension;
		//FIXME take care the overflow problem
		return result;
	}
}
