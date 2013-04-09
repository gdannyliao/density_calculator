package danny.liao.screencalculator.ui;

import danny.liao.densitycalculator.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class CalDensityFragment extends Fragment {

	public static CalDensityFragment newInstance(){
		return new CalDensityFragment();
	}
	
	public CalDensityFragment(){}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.fragment_cal_density, null);
		
		final EditText lengthInput = (EditText) layout.findViewById(R.id.editText_fragment_caldensity_length);
		final EditText widthInput = (EditText) layout.findViewById(R.id.editText_fragment_caldensity_width);
		final EditText dimensionInput = (EditText) layout.findViewById(R.id.editText_fragment_caldensity_inch);
		
		final TextView resultText = (TextView) layout.findViewById(R.id.textView_fragment_caldensity_result);
		final TextView resultUnit = (TextView) layout.findViewById(R.id.textView_fragment_caldensity_result_unit);
		
		final Spinner unitSpinner = (Spinner) layout.findViewById(R.id.spinner_fragment_caldensity_unit);
		
		
		return layout;
	}
	
}
