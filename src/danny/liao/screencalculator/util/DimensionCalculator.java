package danny.liao.screencalculator.util;

public class DimensionCalculator {
	public double calculator(double length, double width, double density){
		if(length <= 0 && width <=0 && density <= 0)
			return 0d;
		double result = 0d;
		result = Math.sqrt(length * length + width * width) / density;
		return result;
	}
}
