package danny.liao.densitycalculator;

public class PxCalculator {
	public Double[] calculator(final double px){
		if(px > 0){
			Double[] results = new Double[5];
			results[0] = px * 160 / 120;
			results[1] = px;
			results[2] = px * 160 / 240;
			results[3] = px / 2;
			results[4] = px / 3;
			return results;
		} 
		else return new Double[0];
	}
}
