package danny.liao.densitycalculator;

public class DpCalculator {
	public Double[] calculate(final double dp){
		if(dp > 0){
			Double[] results = new Double[5];
			
			results[0] = dp * 120 / 160;
			results[1] = dp;
			results[2] = dp * 240 / 160;
			results[3] = dp * 2;
			results[4] = dp * 3;
			return results;
		} else {
			return new Double[0];
		}
	}
}
